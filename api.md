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

```json
{"success":true,"data":{"status":0,"info":null,"cartid":2,"username":"张起灵","items":[{"commid":11,"commName":"城市夜景","price":5.0,"sallerName":"大锜","sallerPhone":""},{"commid":13,"commName":"不一样的品味","price":344.0,"sallerName":"大锜","sallerPhone":""},{"commid":14,"commName":"阿樱的亲子盖饭","price":1346.0,"sallerName":"大锜","sallerPhone":""}]},"error":null}
```
```json
{"success":true,"data":{"status":0,"info":null,"cartid":0,"username":null,"items":[]},"error":null}
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


