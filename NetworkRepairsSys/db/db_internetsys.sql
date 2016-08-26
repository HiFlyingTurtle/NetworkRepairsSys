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
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

/*Data for the table `t_repair` */

insert  into `t_repair`(`taskId`,`userName`,`publishTime`,`userAddress`,`type`,`troubleDesc`,`repairer`,`repairTime`,`finishTime`,`dealWay`,`state`) values (75,'南方新世纪led','2016-08-26 10:26:10','珠江新城','硬件','屏幕有裂痕','1001',NULL,'2016-08-27 10:44:18','dada','已维修'),(76,'111','2016-08-27 10:41:50','珠江新城','硬件','tets','wwh',NULL,'2016-08-27 15:22:00','test','已维修'),(77,'222','2016-08-26 15:10:47','珠江新城','软件','系统宕机','wwh',NULL,NULL,NULL,'待维修');

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`userName`,`password`,`level`,`name`,`sex`,`birthday`,`grade`,`major`,`phone`,`address`) values (13,'wwh','123456','职员','王文浩','男','2016-08-24','2','cs','132','gz'),(14,'admin','admin','管理员','admin',NULL,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
