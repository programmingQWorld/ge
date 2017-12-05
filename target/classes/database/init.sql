create database artshop default charset = utf8;
-- 用户表  ( shop_user )
drop TABLE IF EXISTS shop_user ;
create table shop_user (
  id int NOT NULL PRIMARY KEY  AUTO_INCREMENT ,
  account VARCHAR(16) NOT NULL  UNIQUE ,
  password VARCHAR(32) NULL ,
  nickname VARCHAR(32) NULL,
  acatar VARCHAR(256) NULL ,  -- 头像
  sex int default 0,
  birthday DATE NULL,
  email VARCHAR(64) NOT NULL ,
  phone VARCHAR(16)
) ENGINE = Innodb default charset = utf8;

-- 管理员表 ( shop_admin )
create table shop_admin (
  id int NOT NULL PRIMARY KEY  AUTO_INCREMENT ,
  account VARCHAR(16) UNIQUE ,
  password VARCHAR(32) NOT NULL
) ENGINE = Innodb default charset = utf8;
-- 收货信息表 ( shop_receiving )
create table shop_receiving (
  id int NOT NULL PRIMARY KEY  AUTO_INCREMENT ,
  receiver VARCHAR(16) NOT NULL ,
  phone VARCHAR(16) NOT NULL ,
  zip_code VARCHAR(16) NOT NULL ,
  address VARCHAR(64) NOT NULL ,
  is_default bool DEFAULT FALSE ,
  uid int NOT NULL ,
FOREIGN KEY (uid) REFERENCES shop_user (`id`) ON UPDATE CASCADE
) ENGINE = Innodb default charset = utf8;

-- 商品表 (shop_commodity )
create table shop_commodity (
  id int NOT NULL PRIMARY KEY  AUTO_INCREMENT ,
  name VARCHAR(100) NOT NULL ,
  price DECIMAL(10, 0) DEFAULT NULL,
  picture VARCHAR(256) NULL ,
  shipping_cost int DEFAULT NULL ,
  uid int NOT NULL ,
  CONSTRAINT FOREIGN KEY (uid) REFERENCES `shop_user` (`id`) ON UPDATE CASCADE
) ENGINE = Innodb default charset = utf8;


-- 订单表 （shop_order）
create table shop_order (
  id int NOT NULL PRIMARY KEY  AUTO_INCREMENT ,
  commodity int NOT NULL ,
  receiving int NOT NULL ,
  is_pay bool default FALSE,
  order_no VARCHAR(256) NOT NULL  UNIQUE ,
  create_time DATETIME NOT NULL ,
  status int default 0,
  uid int NOT NULL,
  FOREIGN KEY (commodity) REFERENCES `shop_commodity` (`id`) ON UPDATE CASCADE
) ENGINE = Innodb default charset = utf8;

-- 标签表  ( shop_tag )
create table shop_tag (
  id int NOT NULL PRIMARY KEY  AUTO_INCREMENT ,
  name VARCHAR(16) NOT NULL
) ENGINE = Innodb default charset = utf8;

-- 商品关联标签 标签关联商品 （shop_com_rel_tag）
create table shop_commodity_rel_tag (
  id int NOT NULL PRIMARY KEY  AUTO_INCREMENT ,
  cid int NOT NULL ,
  tid int NOT NULL,
  FOREIGN KEY (cid) REFERENCES shop_commodity(id) on UPDATE  CASCADE ,
  FOREIGN KEY (tid) REFERENCES shop_tag(id) on UPDATE  CASCADE
) ENGINE = Innodb default charset = utf8;

--  购物车 （shop_cart）
create table shop_cart (
  id int NOT NULL PRIMARY KEY  AUTO_INCREMENT ,
  uid INT NOT NULL ,
  pid INT NOT NULL ,
  rid INT NOT NULL,
  FOREIGN KEY (uid) REFERENCES shop_user(id) on UPDATE  CASCADE ,
  FOREIGN KEY (pid) REFERENCES shop_commodity(id) on UPDATE  CASCADE ,
  FOREIGN KEY (rid) REFERENCES shop_receiving(id) on UPDATE  CASCADE
) ENGINE = Innodb default charset = utf8;