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
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  CONSTRAINT `shop_cart_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `shop_user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_cart`
--

LOCK TABLES `shop_cart` WRITE;
/*!40000 ALTER TABLE `shop_cart` DISABLE KEYS */;
INSERT INTO `shop_cart` VALUES (10,14),(12,23),(11,24);
/*!40000 ALTER TABLE `shop_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_cart_items`
--

DROP TABLE IF EXISTS `shop_cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_cart_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL COMMENT '商品表',
  `cart_id` int(11) NOT NULL COMMENT '该项所属购物车的id',
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  KEY `cart_id` (`cart_id`),
  CONSTRAINT `shop_cart_items_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `shop_commodity` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `shop_cart_items_ibfk_2` FOREIGN KEY (`cart_id`) REFERENCES `shop_cart` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_cart_items`
--

LOCK TABLES `shop_cart_items` WRITE;
/*!40000 ALTER TABLE `shop_cart_items` DISABLE KEYS */;
INSERT INTO `shop_cart_items` VALUES (5,39,10),(6,33,10),(7,29,10),(10,38,11),(11,29,11),(12,4,12);
/*!40000 ALTER TABLE `shop_cart_items` ENABLE KEYS */;
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
  `level` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `uid` (`uid`),
  CONSTRAINT `shop_commodity_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `shop_user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_commodity`
--

LOCK TABLES `shop_commodity` WRITE;
/*!40000 ALTER TABLE `shop_commodity` DISABLE KEYS */;
INSERT INTO `shop_commodity` VALUES (1,'hello',100,'http://10.10.112.170:8080/images/Original/image21.png',10,1,'2017-12-06 00:00:00',0,0,0),(2,'hello223',1001,'http://10.10.112.170:8080/images/Original/image26.png',0,1,'2017-12-21 00:00:00',0,0,0),(3,'hello5555',1001,'http://10.10.112.170:8080/images/Original/image22.png',0,7,'2017-12-25 00:00:00',0,0,0),(4,'h123213',1001,'http://10.10.112.170:8080/images/Original/image27.png',0,1,'2017-12-05 07:34:13',0,0,0),(5,'z231094',999,'http://10.10.112.170:8080/images/Original/image23.png',0,7,'2017-12-05 09:16:50',12,12,0),(6,'q12345',120,'http://10.10.112.170:8080/images/Original/image28.png',0,7,'2017-12-06 07:00:53',12,12,0),(7,'q123456',120,'http://10.10.112.170:8080/images/Original/image24.png',0,1,'2017-12-06 07:02:50',12,12,0),(9,'q123456234',120,'http://10.10.112.170:8080/images/Original/image25.png',0,7,'2017-12-06 07:53:34',12,12,0),(10,'流水',120,'http://10.10.112.170:8080/images/Original/image1.png',0,1,'2017-12-12 07:31:57',120,120,0),(12,'小桥',118,'http://10.10.112.170:8080/images/Original/image2.png',0,7,'2017-12-12 07:34:06',116,120,0),(13,'人家',120,'http://10.10.112.170:8080/images/Original/image3.png',0,1,'2017-12-12 07:35:09',120,120,0),(14,'古道',120,'http://10.10.112.170:8080/images/Original/image4.png',0,9,'2017-12-12 07:35:50',120,120,0),(15,'瘦马',120,'http://10.10.112.170:8080/images/Original/image5.png',0,1,'2017-12-12 07:36:19',120,120,0),(16,'西风',120,'http://10.10.112.170:8080/images/Original/image6.png',0,7,'2017-12-12 07:36:36',120,120,0),(20,'一字',5,'/upload/1514358721126.jpg',0,14,'2017-12-27 07:12:08',34,24,0),(21,'二字',5,'/upload/1514359880524.jpg',0,14,'2017-12-27 07:31:28',35,45,0),(22,'3',34,'/upload/1514363535585.jpg',0,14,'2017-12-27 08:32:31',60,40,0),(23,'面具',5,'/upload/1514363616311.jpg',0,14,'2017-12-27 08:33:48',60,40,0),(25,'none',8,'/upload/1514431874885.jpg',0,20,'2017-12-28 03:31:40',100,95,0),(26,'撒花',7,'/upload/1514441039009.jpg',0,14,'2017-12-28 06:04:06',34,56,0),(27,'花的故事',5,'/upload/1514441092789.jpg',0,14,'2017-12-28 06:04:59',24,15,0),(28,'石头',9,'/upload/1514441141072.jpg',0,14,'2017-12-28 06:05:50',25,24,0),(29,'阿楚姑娘',512,'/upload/1514441192066.jpg',0,14,'2017-12-28 06:06:44',54,34,0),(31,'盐系',95,'/upload/1514441324522.jpg',0,14,'2017-12-28 06:09:03',54,30,0),(33,'杂',15,'/upload/1514441490987.jpg',0,14,'2017-12-28 06:12:30',120,45,0),(34,'虹',50,'/upload/1514441782580.png',0,20,'2017-12-28 06:16:30',50,50,5),(35,'梦里寻花',46,'/upload/1514441820675.png',0,20,'2017-12-28 06:17:08',46,50,5),(36,'破浪',10,'/upload/1514441851928.png',0,20,'2017-12-28 06:17:38',50,46,5),(37,'阿联酋',10,'/upload/1514442113517.png',0,20,'2017-12-28 06:22:13',46,65,0),(38,'婚花',32,'/upload/1514442181194.png',0,20,'2017-12-28 06:23:10',50,45,0),(39,'笑人',120,'/upload/1514442524780.png',0,20,'2017-12-28 06:28:59',120,90,0),(40,'1one',58,'/upload/1514445491661.png',0,24,'2017-12-28 07:18:24',56,76,0),(41,'2two',65,'/upload/1514445520454.png',0,24,'2017-12-28 07:18:50',56,89,0),(42,'3three',45,'/upload/1514445543297.png',0,24,'2017-12-28 07:19:13',87,56,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_commodity_rel_tag`
--

LOCK TABLES `shop_commodity_rel_tag` WRITE;
/*!40000 ALTER TABLE `shop_commodity_rel_tag` DISABLE KEYS */;
INSERT INTO `shop_commodity_rel_tag` VALUES (1,26,3),(2,27,3),(3,28,3),(4,29,3),(6,31,7),(8,33,6),(9,34,7),(10,35,7),(11,36,7),(12,37,7),(13,38,7),(14,39,3),(15,40,6),(16,41,6),(17,42,6);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_order`
--

LOCK TABLES `shop_order` WRITE;
/*!40000 ALTER TABLE `shop_order` DISABLE KEYS */;
INSERT INTO `shop_order` VALUES (1,39,22,1,'151444357882414','2017-12-28 06:46:19',0,14),(2,39,22,1,'151444362043214','2017-12-28 06:47:00',0,14),(3,33,22,0,'151444362048214','2017-12-28 06:47:00',0,14),(4,29,22,0,'151444362057214','2017-12-28 06:47:01',0,14),(5,38,23,1,'151444402082723','2017-12-28 06:53:41',0,23),(6,39,24,1,'151444585611124','2017-12-28 07:24:16',0,24);
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_receiving`
--

LOCK TABLES `shop_receiving` WRITE;
/*!40000 ALTER TABLE `shop_receiving` DISABLE KEYS */;
INSERT INTO `shop_receiving` VALUES (21,'李白','10086','518101','3#111',0,20),(22,'李白','13510145181','518101','3#222',1,14),(23,'SADF','ASFD','AFSD','AFSD',1,23),(24,'libai','10086','518101','3#222',1,24);
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_tag`
--

LOCK TABLES `shop_tag` WRITE;
/*!40000 ALTER TABLE `shop_tag` DISABLE KEYS */;
INSERT INTO `shop_tag` VALUES (3,'书画'),(6,'国画'),(7,'山水画');
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
  `active` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_user`
--

LOCK TABLES `shop_user` WRITE;
/*!40000 ALTER TABLE `shop_user` DISABLE KEYS */;
INSERT INTO `shop_user` VALUES (1,'test0001','21232f297a57a5a743894a0e4a801fc3','java','http:xxx.xxx/xxx.jpg','男','2017-11-30','test0001@qq.com','12345678910',0),(7,'admin','21232f297a57a5a743894a0e4a801fc3','shier123','/upload/avatar/1514342165110.jpg','女','1995-02-19','admin@qq.com','1234567890',0),(9,'admin123','21232f297a57a5a743894a0e4a801fc3',NULL,'/images/Orders/Alipay.png','\0',NULL,'admin@qq.com','1234567890',0),(10,'11111','b0baee9d279d34fa1dfd71aadb908c3f',NULL,NULL,'\0',NULL,'11111','11111',0),(12,'goodmen','6412121cbb2dc2cb9e460cfee7046be2','123','/upload/avatar/1514359772622.jpg','女','1995-12-25','1011@qq.com','15817230806',1),(14,'Cyyyyyy','abd5a32ab83aab14e14b7b70a646a561','Cyyy','/upload/avatar/1514359902772.jpg','女','1999-12-11','nihao@qq.com','10086',1),(16,'abcd','e2fc714c4727ee9395f324cd2e7f331f',NULL,NULL,'\0',NULL,'cbas@qq','1231313213',0),(20,'aooo','abd5a32ab83aab14e14b7b70a646a561','O.A','/upload/avatar/1514431750091.jpg','男','1997-05-11','sweet807781595@qq.com','15817230806',1),(21,'qingge','e6f2607b7752457ed7669ef969c130b9',NULL,NULL,'\0',NULL,'1605542040@qq.com','11111111111',1),(22,'2621312','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,'\0',NULL,'84781821','123123123',0),(23,'123456789','25f9e794323b453885f5181f1b624d0b',NULL,NULL,'\0',NULL,'948853171@qq.com','136528121313',1),(24,'1234','e807f1fcf82d132f9bb018ca6738a19f',NULL,NULL,'\0',NULL,'925804725@qq.com','15817230806',1);
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

-- Dump completed on 2017-12-28 15:59:19
