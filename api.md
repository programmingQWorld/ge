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



