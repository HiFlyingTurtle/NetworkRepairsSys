/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.6.20 : Database - db_internetsys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_internetsys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_internetsys`;

/*Table structure for table `t_location` */

DROP TABLE IF EXISTS `t_location`;

CREATE TABLE `t_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `t_location` */

insert  into `t_location`(`id`,`location`) values (1,'广州南都北墙'),(2,'广州北京路'),(3,'广州车天车地'),(4,'深圳东门广场'),(5,'深圳新绿岛'),(6,'深圳金凯广场'),(7,'佛山国际ICC'),(8,'江门住建广场'),(9,'江门东方广场'),(10,'江门中泰来酒店'),(11,'中山兴中广场'),(12,'中山益华广场'),(13,'肇庆星湖广场'),(14,'东莞大朗新世纪广场'),(15,'惠州花边岭广场'),(16,'清远市政府'),(17,'云浮东街口'),(18,'珠海钰海'),(19,'潮州蓝月湾酒店');

/*Table structure for table `t_memo` */

DROP TABLE IF EXISTS `t_memo`;

CREATE TABLE `t_memo` (
  `memoId` int(11) NOT NULL AUTO_INCREMENT,
  `title` char(6) NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `time` varchar(25) DEFAULT NULL,
  `my` char(10) DEFAULT NULL,
  PRIMARY KEY (`memoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_memo` */

/*Table structure for table `t_message` */

DROP TABLE IF EXISTS `t_message`;

CREATE TABLE `t_message` (
  `messId` int(11) NOT NULL AUTO_INCREMENT,
  `title` char(4) NOT NULL,
  `who` varchar(10) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `time` varchar(25) DEFAULT NULL,
  `infoment` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`messId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_message` */

insert  into `t_message`(`messId`,`title`,`who`,`content`,`time`,`infoment`) values (1,'通知','wwh','test','2016-08-25 15:32:08','admin'),(2,'通知','wwh','test','2016-08-25 15:32:08','admin');

/*Table structure for table `t_repair` */

DROP TABLE IF EXISTS `t_repair`;

CREATE TABLE `t_repair` (
  `taskId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(8) DEFAULT NULL,
  `publishTime` varchar(25) NOT NULL,
  `userAddress` varchar(50) NOT NULL,
  `type` varchar(11) DEFAULT NULL,
  `troubleDesc` varchar(100) DEFAULT NULL,
  `repairer` varchar(8) DEFAULT NULL,
  `repairTime` varchar(25) DEFAULT NULL,
  `finishTime` varchar(25) DEFAULT NULL,
  `dealWay` varchar(100) DEFAULT NULL,
  `state` char(8) DEFAULT NULL,
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

/*Data for the table `t_repair` */

insert  into `t_repair`(`taskId`,`userName`,`publishTime`,`userAddress`,`type`,`troubleDesc`,`repairer`,`repairTime`,`finishTime`,`dealWay`,`state`) values (81,NULL,'2016-09-04 21:36:33','广州南都北墙','dasd','dasd','we',NULL,'2016-09-05 14:49:59','只是测试','已维修'),(82,NULL,'2016-09-04 22:34:08','广州南都北墙','硬件','test','wwh',NULL,'2016-09-05 14:51:13','hehe','已维修'),(83,NULL,'2016-09-04 22:53:13','广州北京路','软件','dadad','宋定生',NULL,NULL,NULL,'待维修'),(85,NULL,'2016-09-05 00:14:35','广州南都北墙','软件','test','何春',NULL,NULL,NULL,'待维修'),(90,NULL,'2016-09-06 10:32:27','广州车天车地','软件','dada','从键良',NULL,NULL,NULL,'待维修'),(91,NULL,'2016-09-05 10:33:00','深圳金凯广场','硬件','dasd','从键良',NULL,NULL,NULL,'待维修'),(92,NULL,'2016-09-05 10:59:46','广州南都北墙','硬件','sdads','宋定生',NULL,NULL,NULL,'待维修');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(8) NOT NULL,
  `password` char(6) NOT NULL,
  `level` varchar(6) NOT NULL,
  `name` varchar(8) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `grade` varchar(20) DEFAULT NULL,
  `major` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`userName`,`password`,`level`,`name`,`sex`,`birthday`,`grade`,`major`,`phone`,`address`) values (14,'admin','admin','管理员','admin',NULL,NULL,NULL,NULL,NULL,NULL),(15,'hec','hec','职员','何春','男',NULL,NULL,NULL,'13530116912',NULL),(16,'congjl','congjl','职员','从键良','男',NULL,NULL,NULL,'18027461551',NULL),(17,'yangzl','yangzl','职员','杨仲冬','男',NULL,NULL,NULL,'15013661948',NULL),(18,'songds','songds','职员','宋定生','男',NULL,NULL,NULL,'18098900732',NULL),(19,'fuda','fuda','职员','傅道安','男',NULL,NULL,NULL,'13450297970',NULL),(20,'wangyq','wangyq','职员','王烨全','男',NULL,NULL,NULL,'13535238728',NULL),(21,'lis','lis','职员','李胜','男',NULL,NULL,NULL,'18602069268',NULL),(22,'wufc','wufc','职员','吴凡超','男',NULL,NULL,NULL,'18676891783',NULL),(23,'liqb','liqb','职员','李启邦',NULL,NULL,NULL,NULL,'18666078413',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
