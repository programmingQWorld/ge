package com.goodboy.picshop.controller;

import com.goodboy.picshop.dto.CartDto;
import com.goodboy.picshop.dto.CartItemDto;
import com.goodboy.picshop.dto.CommodityGetDto;
import com.goodboy.picshop.dto.JSONResult;
import com.goodboy.picshop.entity.Cart;
import com.goodboy.picshop.entity.CartItem;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.service.CartItemService;
import com.goodboy.picshop.service.CartService;
import com.goodboy.picshop.service.CommodityService;
import com.mysql.cj.api.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;

@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private CartService cartService;
	@Autowired
	private CommodityService commodityService;

	/**
	 * 模拟一个在线用户
	 * @param uid  用户id
	 * @return
	 */
	@RequestMapping(value = "login/{uid}", method = RequestMethod.GET)
	public JSONResult<Object> login (@PathVariable("uid")int uid, HttpSession session) {
		/*  模拟在线用户 - 晓琳*/
		User userOnline = new User();
		userOnline.setId(uid);
		userOnline.setNickname("晓琳");
		session.setAttribute("user", userOnline );
		/* */
		// 设置用户的购物车
		if ( session.getAttribute("islogin") != null ) { // 用户已经登陆，则不能再从数据库中查找用户的购物车信息
			return new JSONResult<>(true, "登录成功,用户id:" + userOnline.getId());
		}
		CartDto dbCartDto =  cartService.getCartInfoByUserId(userOnline.getId());
		CartDto sessionCartDto = (CartDto) session.getAttribute("usercart");

		if (sessionCartDto != null && dbCartDto != null ) {
			if (sessionCartDto.getItems() != null && sessionCartDto.getItems().size() > 0) {
				// 合并
				for ( CartItemDto dto : sessionCartDto.getItems() ) {
					if ( !dbCartDto.getItems().contains(dto)  ) {
						dbCartDto.getItems().add(dto);
					}
				}
			}
		}
		CartDto dto = (dbCartDto == null) ? sessionCartDto : dbCartDto ;
		session.setAttribute("usercart", dto);
		session.setAttribute("islogin", true);  // 标记用户已经登录，避免用户重复登录
		return new JSONResult<>(true, "登录成功,用户id:" + userOnline.getId());
	}

	@RequestMapping( value  = "logout", method = RequestMethod.GET)
	public JSONResult<Object> logout (HttpSession session) {

		// 保存信息回到数据库
		CartDto cartDto = (CartDto) session.getAttribute("usercart");
		User userOnline = (User)session.getAttribute("user");
		cartService.saveUserCartInfo( userOnline.getId(), cartDto  );
		// 在数据导入到数据库之后，才可以将购物车对象以及用户对象删除
		session.setAttribute("usercart", null);
		session.setAttribute("user", null);
		session.setAttribute("islogin", null);
		return null;
	}


	/**
	 * 添加商品到购物车
	 * @param commID 要添加的商品id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "additem/{cno}", method = RequestMethod.GET)
	public JSONResult<CartDto> addItem (@PathVariable("cno") int commID, HttpSession session) {
		CommodityGetDto commoditydto = commodityService.getById(commID);  //  根据商品id获取相应的商品数据
		if ( commoditydto.getCommodity() == null ) {
			return new JSONResult<>(false, "指定的商品不存在");
		}
		CartDto cartDto = (CartDto)session.getAttribute("usercart");
		if ( null==cartDto ) {  // session 中不存在购物车
			cartDto = new CartDto();
			cartDto.setItems( new ArrayList<CartItemDto>() );
			synchronizeWithSession(cartDto, session);
		}
		cartItemService.addCartItem(cartDto, commoditydto.getCommodity());  // 把商品信息封装成为cartitem,并添加到cart.cartitems中
		synchronizeWithSession(cartDto, session);  // 把修改过的cart信息同步session中usercart中
		return new JSONResult<CartDto>(true, "成功添加商品到购物车");
	}

	/**
	 * 从购物车中移除商品，原创艺术品只有1件，
	 * @param commID 操作的商品id
	 * @param session
	 */
	@RequestMapping(value = "reduceitem/{cno}", method = RequestMethod.GET)
	public JSONResult<Object> reduceItem (@PathVariable("cno") int commID, HttpSession session) {

		System.out.println("进入移除商品的方法");

		CartDto cartDto = (CartDto)session.getAttribute("usercart");
		Iterator<CartItemDto> it =  cartDto.getItems().iterator();
		while ( it.hasNext() ) {
			CartItemDto itemDto = it.next();
			if (itemDto.getCommid() == commID ) {
				it.remove();
			}
		}
		synchronizeWithSession(cartDto, session);  // 把修改过的cart信息同步session中usercart
		return new JSONResult<>(true, "移除商品成功");
	}

	@RequestMapping(value = "clear", method = RequestMethod.GET)
	public JSONResult<Object> clearCart (HttpSession session) {
		System.out.println("准备清空购物车");
		CartDto cartDto = (CartDto)getCurrentUserCart(session);
		if ( cartDto != null ) {
			cartDto.getItems().clear();
		}
		synchronizeWithSession(cartDto, session);  // 把修改过的cart信息同步session中usercart
		return new JSONResult<>(true, "购物车已被清空");
	}

	/**
	 * 购物车列表信息查询
	 * @param session
	 * @return
	 */
	@RequestMapping("list")
	public JSONResult<CartDto> cartInfo(HttpSession session) {

		CartDto cartDtoFromSession = (CartDto)session.getAttribute("usercart");
		User user = (User)session.getAttribute("user");

		if ( cartDtoFromSession == null ) {
			cartDtoFromSession = new CartDto();
			cartDtoFromSession.setItems( new ArrayList<CartItemDto>() );
			session.setAttribute("usercart", cartDtoFromSession);
		}
		return new JSONResult<CartDto>(true, cartDtoFromSession);
	}
	/**
	 * 从session中获得当前的购物车对象
	 * @param session 存放购物车的session
	 * @return
	 */
	public CartDto getCurrentUserCart(HttpSession session) {
		CartDto cartDto = (CartDto) session.getAttribute("usercart");
		return cartDto;
	}

	/**
	 * 同步购物车信息到session中
	 * @param cartDto 修改过的购物车
	 * @param session  存放购物车的session , 其对应的属性是  usercart
	 */
	public void synchronizeWithSession (CartDto cartDto, HttpSession session) {
		session.setAttribute("usercart", cartDto);
	}
}
