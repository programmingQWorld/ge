-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: artshop
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `shop_admin`
--

DROP TABLE IF EXISTS `shop_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(16) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_admin`
--

LOCK TABLES `shop_admin` WRITE;
/*!40000 ALTER TABLE `shop_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_cart`
--

DROP TABLE IF EXISTS `shop_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `pid` (`cid`),
  CONSTRAINT `shop_cart_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `shop_user` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `shop_cart_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `shop_commodity` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_cart`
--


LOCK TABLES `shop_cart` WRITE;
/*!40000 ALTER TABLE `shop_cart` DISABLE KEYS */;
INSERT INTO `shop_cart` VALUES (2,1,3);
/*!40000 ALTER TABLE `shop_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_commodity`
--

DROP TABLE IF EXISTS `shop_commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `picture` varchar(256) DEFAULT NULL,
  `shipping_cost` decimal(10,0) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `size_width` decimal(10,0) NOT NULL,
  `size_height` decimal(10,0) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `uid` (`uid`),
  CONSTRAINT `shop_commodity_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `shop_user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_commodity`
--

LOCK TABLES `shop_commodity` WRITE;
/*!40000 ALTER TABLE `shop_commodity` DISABLE KEYS */;
INSERT INTO `shop_commodity` VALUES (1,'hello',100,'http://goodboy.com/picture.png',10,1,'2017-12-06 00:00:00',0,0),(2,'hello223',1001,'http://goodboy.com/picture.png',0,1,'2017-12-21 00:00:00',0,0),(3,'hello5555',1001,'http://goodboy.com/picture.png',0,1,'2017-12-25 00:00:00',0,0),(4,'h123213',1001,'http://goodboy.com/picture.png',0,1,'2017-12-05 07:34:13',0,0),(5,'z231094',1001,'http://goodboy.com/picture.png',0,1,'2017-12-05 09:16:50',12,12),(6,'q12345',120,'http://shierd.info/shio.png',0,1,'2017-12-06 07:00:53',12,12),(7,'q123456',120,'http://shierd.info/shio.png',0,1,'2017-12-06 07:02:50',12,12);
/*!40000 ALTER TABLE `shop_commodity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_commodity_rel_tag`
--

DROP TABLE IF EXISTS `shop_commodity_rel_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_commodity_rel_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL,
  `tid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  KEY `tid` (`tid`),
  CONSTRAINT `shop_commodity_rel_tag_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `shop_commodity` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `shop_commodity_rel_tag_ibfk_2` FOREIGN KEY (`tid`) REFERENCES `shop_tag` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_commodity_rel_tag`
--

LOCK TABLES `shop_commodity_rel_tag` WRITE;
/*!40000 ALTER TABLE `shop_commodity_rel_tag` DISABLE KEYS */;
INSERT INTO `shop_commodity_rel_tag` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,6,1),(6,7,1);
/*!40000 ALTER TABLE `shop_commodity_rel_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_order`
--

DROP TABLE IF EXISTS `shop_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  `is_pay` tinyint(1) DEFAULT '0',
  `order_no` varchar(256) NOT NULL,
  `create_time` datetime NOT NULL,
  `status` int(11) DEFAULT '0',
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no` (`order_no`),
  KEY `commodity` (`cid`),
  KEY `rid` (`rid`),
  KEY `uid` (`uid`),
  CONSTRAINT `shop_order_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `shop_commodity` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `shop_order_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `shop_receiving` (`id`),
  CONSTRAINT `shop_order_ibfk_3` FOREIGN KEY (`uid`) REFERENCES `shop_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_order`
--

LOCK TABLES `shop_order` WRITE;
/*!40000 ALTER TABLE `shop_order` DISABLE KEYS */;
INSERT INTO `shop_order` VALUES (1,1,2,0,'No000001','2017-12-04 09:40:59',0,1),(3,1,2,0,'No000002','2017-12-04 09:41:36',0,1);
/*!40000 ALTER TABLE `shop_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_receiving`
--

DROP TABLE IF EXISTS `shop_receiving`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_receiving` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `receiver` varchar(16) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `zip_code` varchar(16) NOT NULL,
  `address` varchar(64) NOT NULL,
  `is_default` tinyint(1) DEFAULT '0',
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  CONSTRAINT `shop_receiving_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `shop_user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_receiving`
--

LOCK TABLES `shop_receiving` WRITE;
/*!40000 ALTER TABLE `shop_receiving` DISABLE KEYS */;
INSERT INTO `shop_receiving` VALUES (2,'jack2','12345678910','123456','广东省珠海市金湾区广东科学技术职业学院',1,1),(3,'jack1','12345678910','123456','广东省珠海市金湾区广东科学技术职业学院',0,1);
/*!40000 ALTER TABLE `shop_receiving` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_tag`
--

DROP TABLE IF EXISTS `shop_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_tag`
--

LOCK TABLES `shop_tag` WRITE;
/*!40000 ALTER TABLE `shop_tag` DISABLE KEYS */;
INSERT INTO `shop_tag` VALUES (1,'test');
/*!40000 ALTER TABLE `shop_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_user`
--

DROP TABLE IF EXISTS `shop_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(16) NOT NULL,
  `password` varchar(32) DEFAULT NULL,
  `nickname` varchar(32) DEFAULT NULL,
  `avatar` varchar(256) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(64) NOT NULL,
  `phone` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_user`
--

-- 添加 创建时间、 宽度、高度 到商品表
alter table shop_commodity add create_time timestamp;
alter table shop_commodity add size_width FLOAT;
alter table shop_commodity add size_height float;

-- 创建订单项表。
drop table IF EXISTS  shop_cart_items;
create table shop_cart_items (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  cid int (11) not null COMMENT '商品表',
  cart_id int(11) not null COMMENT '该项所属购物车的id',
  PRIMARY KEY (id),
  FOREIGN KEY (cid) REFERENCES shop_commodity(id) on UPDATE CASCADE ,
  FOREIGN KEY (cart_id) REFERENCES  shop_cart(id) ON UPDATE CASCADE
) ENGINE = innodb DEFAULT charset =  utf8;

-- 测试分支是否会被主分支自动分并


LOCK TABLES `shop_user` WRITE;
/*!40000 ALTER TABLE `shop_user` DISABLE KEYS */;
INSERT INTO `shop_user` VALUES (1,'test0001','test0001','java','http:xxx.xxx/xxx.jpg','男','2017-11-30','test0001@qq.com','12345678910');
/*!40000 ALTER TABLE `shop_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-06 15:43:36
