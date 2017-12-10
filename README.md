# ge
A website for showing and selling painting, deploy by SSM framework.
枨锜 -- working
##购物车
### 用户大锜创建了购物车
> insert into shop_cart (uid) values (3);
### 大锜 添加 《铅笔画》《好人画》《惠普卡通画》 到他的购物车中
> insert into shop_cart_items (cid, cart_id) <br> values (4, 6),<br>
(3, 6),<br>
(7, 6);<br>

### 用户 java 创建了购物车
> insert into shop_cart (uid) values (1);
### java 添加 《宝石菇》《手绘藤花画》《惠普卡通画》 到他的购物车中
> insert into shop_cart_items (cid, cart_id) <br> values (10, 7),<br>
(5, 7),<br>
(7, 7);<br>

### 用户大锜查看了他的购物车
商品名称 -- 价格 -- 图片 -- 用户的姓名
```sql
select  comm.id, comm.name, comm.price, comm.picture, user.nickname, cart.uid, user.id
FROM shop_cart cart
left join shop_cart_items items on cart.id = items.cart_id 
left join shop_commodity comm on items.cid = comm.id
left join shop_user user on user.id = cart.uid
where cart.uid = 3;
```
### 用户java也按照上面的方法查看了他的购物车

## 购物车内的商品操作
### 用户大锜不打算购买《惠普卡通画》了 
```sql
#delete from shop_cart_items where pid= 3 and cart_id = 5;
# 为实现可批量操作
#delete from shop_cart_items where id in (<foreach item="id" collection="list" separator=",">#{id}</foreach>) and cart_id = #{map.cartD}
delete from shop_cart_items where id = 6;
```
### 大锜查看购物车是否已经删除掉不要的商品《惠普卡通画》
删除前
+------+-----------------+-------+---------+----------+-----+------+<br>
| id   | name            | price | picture | nickname | uid | id   |<br>
+------+-----------------+-------+---------+----------+-----+------+<br>
|    4 | 铅笔画          |    16 | ui.jpg  | 大锜     |   3 |    3 |<br>
|    3 | 好人画          |    16 | ui.jpg  | 大锜     |   3 |    3 |<br>
|    7 | 惠普卡通画      |    16 | ui.jpg  | 大锜     |   3 |    3 |<br>
+------+-----------------+-------+---------+----------+-----+------+<br>
```sql
select  comm.id, comm.name, comm.price, comm.picture, user.nickname, cart.uid, user.id
FROM shop_cart cart
left join shop_cart_items items on cart.id = items.cart_id 
left join shop_commodity comm on items.cid = comm.id
left join shop_user user on user.id = cart.uid
where cart.uid = 3;
```
这是删除后的结果：（3条记录 变为 2条记录）
+------+-----------+-------+---------+----------+-----+------+<br>
| id        | name         | price   | picture     | nickname | uid   | id       |<br>
+------+-----------+-------+---------+----------+-----+------+<br>
|    3      | 好人画       |    16   | ui.jpg       | 大锜         |   3    |    3     |<br>
|    4      | 铅笔画       |    16   | ui.jpg       | 大锜         |   3    |    3     |<br>
+------+-----------+-------+---------+----------+-----+------+<br>

### 关于删除购物项商品信息的设想
每一次的删除信息其实只需要2个购物项的属性信息：
1. 商品的 id | name
2. 商品所在的购物车号
所以，每次只需要传入map对象。map对象中包含商品的id|name 和商品所在的购物车号应该就可以实现。

### 由于用户java实在是太喜欢《宝石菇》了，想买下来，于是他决定生成订单 -- <可批量操作>
insert into shop_order ()
values
(),
