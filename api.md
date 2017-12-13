##购物车信息接口列表
### 添加商品到购物车
说明 | url   |   request-method  |  data 
---|----|--------------------|--------| 
添加商品到购物车|cart/additem/{cno}/    |    GET |  cno(商品id)  | 

#### response
> 添加成功
```json
{
  "success": true,
  "data": null,
  "error": "成功添加商品到购物车"
}
```  
> 添加失败
```json
{
  "success": false,
  "data": null,
  "error": "指定的商品不存在"
}
```

### 删除购物车商品
说明 | url   |   request-method  |  data 
---|----|--------------------|--------| 
删除购物车商品|cart/reduceitem/{cno}/    |    GET |  cno(商品id)  |

#### response
> 移除成功
```json
{
  "success": true,
  "data": null,
  "error": "移除商品成功"
}
```

### 清空购物车
说明 | url   |   request-method  |  data 
---|----|--------------------|--------| 
清空购物车商品|cart/reduceitem/{cno}/    |    GET |  无  |

#### response
```json
{
  "success": true,
  "data": null,
  "error": "购物车已被清空"
}
```