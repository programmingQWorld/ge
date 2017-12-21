## API文档

### 获取购物车列表接口
> 获取当前session中的购物车信息。

#### HTTP请求方式
GET
#### 请求URL
[http://10.10.112.207:8080/shop/cart/list](http://10.10.112.207:8080/shop/cart/list)
#### 请求参数

参数名 | 必选 | 类型 | 说明
|------|------|------|----|
|offset|否|int|偏移量
|limit|否|int|查询条数

用户已经登录
```json
{"success":true,"data":{"status":1,"info":"成功","cartid":15,"username":"张起灵","items":[{"commid":5,"commName":"手绘藤花画","price":16.0,"sallerName":"惠普","sallerPhone":""}]},"error":null}
```
```json
{"success":true,"data":{"status":3008,"info":"购物车是空的","cartid":15,"username":"张起灵","items":[]},"error":null}
```
用户暂未登录
```json
{"success":true,"data":{"status":3008,"info":"购物车是空的","cartid":0,"username":null,"items":[]},"error":null}
```
```json
{"success":true,"data":{"status":1,"info":"成功","cartid":0,"username":null,"items":[{"commid":5,"commName":"手绘藤花画","price":16.0,"sallerName":"惠普","sallerPhone":""}]},"error":null}
```

### 添加商品到购物车接口
> 从url中获得商品id，然后将对应添加到购物车中。

#### HTTP请求方式
GET
#### 请求URL
[http://10.10.112.207:8080/shop/cart/additem/3](http://10.10.112.207:8080/shop/cart/additem/3)
#### 请求参数

参数名 | 必选 | 类型 | 说明
|------|------|------|----|
|cno| 是|int|商品id

```json
{"success":true,"data":null,"error":"成功添加商品到购物车"}
```

```json
{"success":false,"data":null,"error":"指定的商品不存在"}    
```

### 移除购物车商品接口
> 从url中获得商品id，根据商品id从session缓存中删除购物车商品

#### HTTP请求方式
GET
#### 请求URL
[http://10.10.112.207:8080/shop/cart/reduceitem/3](http://10.10.112.207:8080/shop/cart/reduceitem/3)
#### 请求参数

参数名 | 必选 | 类型 | 说明
|------|------|------|----|
|cno| 是|int|商品id

```json
{"success":true,"data":null,"error":"移除商品成功"}
```

### 清空购物车商品接口
> 清空session中的购物车缓存

#### HTTP请求方式
GET
#### 请求URL
[http://10.10.112.207:8080/shop/cart/clear](http://10.10.112.207:8080/shop/cart/clear)
#### 请求参数

参数名 | 必选 | 类型 | 说明
|------|------|------|----|
|无

```json
{"success":true,"data":null,"error":"购物车已被清空"}
```

### 获得买家订单列表接口
> 根据买家的id到数据库订单表中进行查询订单信息。

#### HTTP请求方式
GET
#### 请求URL
[http://10.10.112.207:8080/shop/order/list](http://10.10.112.207:8080/shop/order/list)
#### 请求参数

参数名 | 必选 | 类型 | 说明
|------|------|------|----|
|offset|否|int|偏移量
/limit|否|int|要查询的数量
> 用户登录的情况
```json
{"success":true,"data":{"status":1,"info":"成功","orderList":[{"id":15,"isPay":1,"orderNo":"No000004","createTime":"2017年12月18日 15:25:18","status":0,"user":{"id":6,"account":null,"password":null,"nickname":"张起灵","avatar":null,"sex":"\u0000","birthday":null,"email":null,"phone":"12345678910"},"receiving":{"id":0,"receiver":"小李","phone":"13145879632","zipCode":null,"address":"广东省珠海市金湾区广东科学技术职业学院","isDefault":0,"user":null},"commodity":{"id":6,"name":"好人画","price":16.0,"picture":"ui.jpg","shippingCost":9.0,"createTime":null,"sizeWidth":0.0,"sizeHeight":0.0,"user":{"id":4,"account":null,"password":null,"nickname":"惠普","avatar":null,"sex":"\u0000","birthday":null,"email":null,"phone":null}}},{"id":16,"isPay":1,"orderNo":"No000005","createTime":"2017年12月18日 15:25:18","status":0,"user":{"id":6,"account":null,"password":null,"nickname":"张起灵","avatar":null,"sex":"\u0000","birthday":null,"email":null,"phone":"12345678910"},"receiving":{"id":0,"receiver":"jack2","phone":"12345678910","zipCode":null,"address":"广东省珠海市金湾区广东科学技术职业学院","isDefault":0,"user":null},"commodity":{"id":6,"name":"洋桔梗","price":16.0,"picture":"ui.jpg","shippingCost":9.0,"createTime":null,"sizeWidth":0.0,"sizeHeight":0.0,"user":{"id":4,"account":null,"password":null,"nickname":"惠普","avatar":null,"sex":"\u0000","birthday":null,"email":null,"phone":null}}},{"id":51,"isPay":0,"orderNo":"15136489830176","createTime":"2017年12月19日 10:03:19","status":0,"user":{"id":6,"account":null,"password":null,"nickname":"张起灵","avatar":null,"sex":"\u0000","birthday":null,"email":null,"phone":"12345678910"},"receiving":{"id":0,"receiver":"林出出","phone":"13156458366","zipCode":null,"address":"广东揭阳","isDefault":0,"user":null},"commodity":{"id":6,"name":"手绘藤花画","price":16.0,"picture":"ui.jpg","shippingCost":9.0,"createTime":null,"sizeWidth":0.0,"sizeHeight":0.0,"user":{"id":4,"account":null,"password":null,"nickname":"惠普","avatar":null,"sex":"\u0000","birthday":null,"email":null,"phone":null}}},{"id":52,"isPay":1,"orderNo":"15136489833396","createTime":"2017年12月19日 10:03:19","status":0,"user":{"id":6,"account":null,"password":null,"nickname":"张起灵","avatar":null,"sex":"\u0000","birthday":null,"email":null,"phone":"12345678910"},"receiving":{"id":0,"receiver":"林出出","phone":"13156458366","zipCode":null,"address":"广东揭阳","isDefault":0,"user":null},"commodity":{"id":6,"name":"惠普水彩画","price":16.0,"picture":"ui.jpg","shippingCost":9.0,"createTime":null,"sizeWidth":0.0,"sizeHeight":0.0,"user":{"id":4,"account":null,"password":null,"nickname":"惠普","avatar":null,"sex":"\u0000","birthday":null,"email":null,"phone":null}}}]},"error":null}
```
```json
{"success":true,"data":{"status":3005,"info":"没有任何关于你的订单"},"error":null}
```

> 用户未登录的情况
暂未处理，会报空异常

### 添加商品到购物车接口
> 从url中获得商品id，然后将对应添加到购物车中。

#### HTTP请求方式
GET
#### 请求URL
[http://10.10.112.207:8080/shop/cart/additem/3](http://10.10.112.207:8080/shop/cart/additem/3)
#### 请求参数

参数名 | 必选 | 类型 | 说明
|------|------|------|----|
|cno| 是|int|商品id

```json
{"success":true,"data":null,"error":"成功添加商品到购物车"}
```

```json
{"success":false,"data":null,"error":"指定的商品不存在"}    
```

### 删除订单信息
> 从u'r'l中获得订单id,根据订单id删除订单信息，
> 删除条件是：用户与订单必须是绑定关系，已经付完款的订单不支持删除

#### HTTP请求方式
GET
#### 请求URL
[http://10.10.112.207:8080/shop/order/del/3](http://10.10.112.207:8080/shop/order/del/3)
#### 请求参数

参数名 | 必选 | 类型 | 说明
|------|------|------|----|
|cno| 是|int|订单id

```json
{"success":false,"data":{"status":3010,"info":"您已经付过款了"},"error":null}
```

```json
{"success":false,"data":{"status":3009,"info":"订单不存在"},"error":null}
```

```json
{"success":true,"data":null,"error":"删除成功"}
```

### 创建订单接口
> 从formdata中获得commids(商品id集合)，分别创建这些商品订单
> 创建失败条件是：订单已存在了，再次创建会失败。
> 商品id集合中，有可以创建成功的，也有部分可能创建失败的。成功的自然创建出订单，失败的无法创建订单
> 这个接口会把那些无法转变为订单的商品id返回给前台

#### HTTP请求方式
GET
#### 请求URL
[http://10.10.112.207:8080/shop/order/addOrder](http://10.10.112.207:8080/shop/order/addOrder)
#### 请求参数

参数名 | 必选 | 类型 | 说明
|------|------|------|----|
|cno| 是|int|订单id

```json
{"success":true,"data":{"status":1,"info":"成功"},"error":null}
```
```json
{"success":true,"data":{"status":3002,"info":"生成订单订单失败，商品已被别人抢先下单了，如果那边1小时内没有完成付款，您还有机会","expList":[7,6]},"error":null}
```

### 获得买家订单列表接口
> 获得买家订单列表

#### HTTP请求方式
GET
#### 请求URL
[http://10.10.112.207:8080/shop/order/list](http://10.10.112.207:8080/shop/order/list)
#### 请求参数

参数名 | 必选 | 类型 | 说明
|------|------|------|----|
无|

```json
{"success":false,"data":null,"error":"用户未登录"}
```
```json
{"success":true,"data":{"status":1,"info":"成功","orderList":[{"id":69,"isPay":0,"orderNo":"15137511126611","createTime":"2017年12月20日 14:25:20","status":0,"user":{"id":1,"account":null,"password":null,"nickname":"java","avatar":null,"sex":"\u0000","birthday":null,"email":null,"phone":"12345678910"},"receiving":{"id":0,"receiver":"jack","phone":"12345678910","zipCode":null,"address":"广东省珠海市金湾区广东科学技术职业学院","isDefault":0,"user":null},"commodity":{"id":1,"name":"惠普卡通画","price":16.0,"picture":"ui.jpg","shippingCost":9.0,"createTime":null,"sizeWidth":0.0,"sizeHeight":0.0,"user":{"id":4,"account":null,"password":null,"nickname":"惠普","avatar":null,"sex":"\u0000","birthday":null,"email":null,"phone":null}}}]},"error":null}
```

```json
{"success":true,"data":{"status":3005,"info":"没有任何关于你的订单"},"error":null}
```