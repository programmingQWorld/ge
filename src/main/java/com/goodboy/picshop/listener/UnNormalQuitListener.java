package com.goodboy.picshop.listener;

import com.goodboy.picshop.controller.UserController;
import com.goodboy.picshop.dto.CartDto;
import com.goodboy.picshop.entity.User;
import com.goodboy.picshop.service.CartService;
import com.goodboy.picshop.service.impl.CartServiceImpl;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UnNormalQuitListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
			User user = (User) httpSessionEvent.getSession().getAttribute("user");
			CartDto cartDto = (CartDto) httpSessionEvent.getSession().getAttribute("usercart");
			ServletContext servletContext = (ServletContext)httpSessionEvent.getSession().getServletContext();
			WebApplicationContext app = (WebApplicationContext)servletContext.getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.picshop-dispatcher");
			CartService cartService = (CartService)app.getBean("cartService");

			if ( user != null ) {
				cartService.saveUserCartInfo( user.getId(), cartDto  );
			}
	}
}
