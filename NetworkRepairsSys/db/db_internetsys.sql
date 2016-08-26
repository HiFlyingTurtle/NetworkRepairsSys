/*
 * 这个备份数据库只有结构，没有数据
 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Table structure for table `t_repair` */

DROP TABLE IF EXISTS `t_repair`;

CREATE TABLE `t_repair` (
  `taskId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(8) DEFAULT NULL,
  `publishTime` varchar(25) NOT NULL,
  `userAddress` varchar(50) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `troubleDesc` varchar(100) DEFAULT NULL,
  `repairer` varchar(8) DEFAULT NULL,
  `repairTime` varchar(25) DEFAULT NULL,
  `finishTime` varchar(25) DEFAULT NULL,
  `dealWay` varchar(100) DEFAULT NULL,
  `state` char(8) DEFAULT NULL,
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
