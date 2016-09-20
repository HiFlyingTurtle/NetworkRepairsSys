/*
Navicat MySQL Data Transfer

Source Server         : dynamicQuoSys
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : db_internetsys

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2016-09-20 14:39:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_location
-- ----------------------------
DROP TABLE IF EXISTS `t_location`;
CREATE TABLE `t_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_location
-- ----------------------------
INSERT INTO `t_location` VALUES ('1', '广州南都北墙');
INSERT INTO `t_location` VALUES ('2', '广州北京路');
INSERT INTO `t_location` VALUES ('3', '广州车天车地');
INSERT INTO `t_location` VALUES ('4', '深圳东门广场');
INSERT INTO `t_location` VALUES ('5', '深圳新绿岛');
INSERT INTO `t_location` VALUES ('6', '深圳金凯广场');
INSERT INTO `t_location` VALUES ('7', '佛山国际ICC');
INSERT INTO `t_location` VALUES ('8', '江门住建广场');
INSERT INTO `t_location` VALUES ('9', '江门东方广场');
INSERT INTO `t_location` VALUES ('10', '江门中泰来酒店');
INSERT INTO `t_location` VALUES ('11', '中山兴中广场');
INSERT INTO `t_location` VALUES ('12', '中山益华广场');
INSERT INTO `t_location` VALUES ('13', '肇庆星湖广场');
INSERT INTO `t_location` VALUES ('14', '东莞大朗新世纪广场');
INSERT INTO `t_location` VALUES ('15', '惠州花边岭广场');
INSERT INTO `t_location` VALUES ('16', '清远市政府');
INSERT INTO `t_location` VALUES ('17', '云浮东街口');
INSERT INTO `t_location` VALUES ('18', '珠海钰海');
INSERT INTO `t_location` VALUES ('19', '潮州蓝月湾酒店');

-- ----------------------------
-- Table structure for t_memo
-- ----------------------------
DROP TABLE IF EXISTS `t_memo`;
CREATE TABLE `t_memo` (
  `memoId` int(11) NOT NULL AUTO_INCREMENT,
  `title` char(6) NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `time` varchar(25) DEFAULT NULL,
  `my` char(10) DEFAULT NULL,
  PRIMARY KEY (`memoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_memo
-- ----------------------------

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
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

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES ('1', '通知', 'wwh', 'test', '2016-08-25 15:32:08', 'admin');
INSERT INTO `t_message` VALUES ('2', '通知', 'wwh', 'test', '2016-08-25 15:32:08', 'admin');

-- ----------------------------
-- Table structure for t_repair
-- ----------------------------
DROP TABLE IF EXISTS `t_repair`;
CREATE TABLE `t_repair` (
  `taskId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(8) DEFAULT NULL,
  `publishTime` varchar(25) NOT NULL,
  `userAddress` varchar(50) NOT NULL,
  `type` varchar(11) DEFAULT NULL,
  `troubleDesc` varchar(300) DEFAULT NULL,
  `repairer` varchar(8) DEFAULT NULL,
  `repairTime` varchar(25) DEFAULT NULL,
  `finishTime` varchar(25) DEFAULT NULL,
  `dealWay` varchar(300) DEFAULT NULL,
  `state` char(8) DEFAULT NULL,
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_repair
-- ----------------------------
INSERT INTO `t_repair` VALUES ('100', null, '2016-05-02 15:22:57', '广州车天车地', '软件系统', '弹框报错', '李启邦', null, '2016-05-02 15:24:59', '远程操作发现D盘数据满，导致系统不能正常运作，清除旧广告文件，清除数据库缓存后恢复', '已维修');
INSERT INTO `t_repair` VALUES ('101', null, '2016-05-02 15:26:14', '云浮东街口', '软件系统', '黑屏无广告', '李启邦', null, '2016-05-02 15:29:49', '远程检查发现无法远程操作，经多方了解后电源故障，屏幕从早上开始黑屏，是由于电源管控电脑故障导致屏幕黑屏。', '已维修');
INSERT INTO `t_repair` VALUES ('102', null, '2016-05-04 15:30:23', '深圳东门广场', '软件系统', '弹框报错', '李启邦', null, '2016-05-04 15:30:57', '重启电脑解决', '已维修');
INSERT INTO `t_repair` VALUES ('103', null, '2016-05-05 15:32:01', '广州车天车地', '软件系统', '黑屏无广告', '李启邦', null, '2016-05-05 15:32:25', '远程无法连接。\r\n到现场获知物业停电导致的播控电脑关机，无法播放广告。\r\n由于越秀区供电线路检修导致的停电，预计17时恢复供电', '已维修');
INSERT INTO `t_repair` VALUES ('104', null, '2016-05-14 15:32:42', '潮州蓝月湾酒店', '软件系统', '弹框报错', '李启邦', null, '2016-05-14 15:33:26', '广告画面有弹框报错，但广告仍然在继续播放\r\n重启电脑解决', '已维修');
INSERT INTO `t_repair` VALUES ('105', null, '2016-05-15 15:33:48', '潮州蓝月湾酒店', '软件系统', '黑屏无广告', '李启邦', null, '2016-05-15 15:34:14', '广告画面有弹框报错，没有广告在播放\r\n检查后发现有弹框报错\r\n重启电脑解决', '已维修');
INSERT INTO `t_repair` VALUES ('106', null, '2016-05-18 15:34:54', '潮州蓝月湾酒店', '软件系统', '弹框报错', '李启邦', null, '2016-05-18 15:37:33', '广告画面有弹框报错，但广告仍然在继续播放\r\n重启电脑解决', '已维修');
INSERT INTO `t_repair` VALUES ('107', null, '2016-09-19 15:37:46', '潮州蓝月湾酒店', '软件系统', '弹框报错', '李启邦', null, '2016-05-19 15:38:11', '广告画面有弹框报错，但广告仍然在继续播放\r\n重启电脑解决', '已维修');
INSERT INTO `t_repair` VALUES ('108', null, '2016-05-20 15:39:07', '潮州蓝月湾酒店', '软件系统', '黑屏无广告', '李启邦', null, '2016-05-20 15:39:32', '凌晨重新下载播表、视频素材后，重新启动播放器程序，没有再观察\r\n播放器在运行一条播表后自动关闭，checkplayer检查播放器程序关闭后自动重启播放器程序，如此重复。\r\n重新制作当日播表、插播播表后，重启电脑解决', '已维修');
INSERT INTO `t_repair` VALUES ('109', null, '2016-05-21 15:39:56', '潮州蓝月湾酒店', '软件系统', '弹框报错', '李启邦', null, '2016-05-21 15:40:22', '广告画面有弹框报错，但广告仍然在继续播放\r\n重启电脑解决', '已维修');
INSERT INTO `t_repair` VALUES ('110', null, '2016-05-22 15:40:42', '潮州蓝月湾酒店', '软件系统', '弹框报错', '李启邦', null, '2016-05-22 15:40:59', '广告画面有弹框报错，但广告仍然在继续播放\r\n重启电脑解决', '已维修');
INSERT INTO `t_repair` VALUES ('111', null, '2016-05-31 15:41:17', '潮州蓝月湾酒店', '软件系统', '更换播控电脑', '李启邦', null, '2016-05-31 15:41:39', '就过去两周出现的问题，推断为操作系统与播控系统不兼容造成，需重装系统。\r\n为一次性解决问题，将在公司测试一周运行正常的播控电脑替换', '已维修');
INSERT INTO `t_repair` VALUES ('112', null, '2016-05-30 15:41:58', '佛山国际ICC', '软件系统', '弹框报错', '李启邦', null, '2016-05-30 15:42:17', '广告画面有弹框报错，广告画面定格，不能继续播放\r\n重启电脑解决', '已维修');
INSERT INTO `t_repair` VALUES ('113', null, '2016-06-01 15:42:38', '佛山国际ICC', '软件系统', '弹框报错', '李启邦', null, '2016-06-01 15:42:57', '广告画面有弹框报错，广告画面定格，不能继续播放\r\n重启电脑解决', '已维修');
INSERT INTO `t_repair` VALUES ('114', null, '2016-06-03 15:43:19', '佛山国际ICC', '软件系统', '弹框报错', '李启邦', null, '2016-06-03 15:43:41', '广告画面有弹框报错，广告画面定格，不能继续播放\r\n重启电脑解决', '已维修');
INSERT INTO `t_repair` VALUES ('115', null, '2016-06-04 15:44:03', '佛山国际ICC', '软件系统', '弹框报错', '李启邦', null, '2016-06-04 15:44:25', '广告画面有弹框报错，广告画面定格，不能继续播放\r\n重启电脑解决', '已维修');
INSERT INTO `t_repair` VALUES ('116', null, '2016-06-11 15:44:40', '佛山国际ICC', '软件系统', '蓝屏', '李启邦', null, '2016-06-11 15:45:10', '屏幕显示蓝屏，不能进入操作系统，无法远程连接。\r\n当地维护员重启解决', '已维修');
INSERT INTO `t_repair` VALUES ('117', null, '2016-07-01 15:45:50', '惠州花边岭广场', '软件系统', '广告漏播', '李启邦', null, '2016-07-01 15:46:40', '客户反映广告漏播，检查系统后发现是播控终端数据库出错，导致7月1日该广告没有下载成功，才出现广告漏播问题。\r\n重新清理数据库缓存，对数据库数据进行优化，重新更新广告素材文件，编辑新播表进行测试。\r\n解决', '已维修');
INSERT INTO `t_repair` VALUES ('118', null, '2016-07-12 15:48:06', '惠州花边岭广场', '网络', '断网', '李启邦', null, '2016-07-12 15:48:36', '4G联通卡流程用完，欠费导致的停机，补缴费后恢复', '已维修');
INSERT INTO `t_repair` VALUES ('119', null, '2016-07-13 15:48:48', '广州车天车地', '网络', '断网', '李启邦', null, '2016-07-13 15:49:18', '不清楚原因，早上11点自动恢复', '已维修');
INSERT INTO `t_repair` VALUES ('120', null, '2016-07-19 15:49:50', '深圳新绿岛', '软件系统', '黑屏无广告', '李启邦', null, '2016-07-19 15:50:23', '监控显示黑屏没有广告播放，网络正常可以远程，小电脑正常播放，大电脑亮度控制正常，大电脑电源控制正常。\r\n重启大电脑电源控制系统后恢复正常播放', '已维修');
INSERT INTO `t_repair` VALUES ('121', null, '2016-07-19 15:50:40', '潮州蓝月湾酒店', '网络', '断网', '李启邦', null, '2016-07-19 15:51:13', '不清楚原因，需要人员现场处理\r\n现场人员处理无法解决，但自动恢复了', '已维修');
INSERT INTO `t_repair` VALUES ('122', null, '2016-07-24 15:51:58', '广州北京路', '网络', '断网', '李启邦', null, '2016-07-24 15:52:30', '系统显示离线，有邮件日志，大小电脑均无法远程，推断为网络故障。\r\n160724现场检查发现因物业装修，4楼弱电间电话线全部被剪断，导致3楼设备间一并断网，联系电信人员处理。\r\n160724利用3g卡临时恢复网络\r\n160725电信工程师重新接线恢复网络', '已维修');
INSERT INTO `t_repair` VALUES ('124', null, '2016-07-30 15:53:39', '中山兴中广场', '网络', '断网', '李启邦', null, '2016-07-30 15:54:20', '系统显示离线，有邮件日志，大小电脑均无法远程，推断为网络故障。\r\n现场维护员重启路由、modem设备不能解决，联系电信处理\r\n160803电信人员检查外墙光钎线路磨损，需要重新接驳\r\n光纤接驳后恢复', '已维修');
INSERT INTO `t_repair` VALUES ('125', null, '2016-07-30 15:56:00', '广州北京路', '网络', '断网', '李启邦', null, '2016-07-30 15:56:28', '系统显示离线，有邮件日志，大小电脑均无法远程，推断为网络故障。\r\n160801现场检查发现缺少modem和路由器，导致断网，已和物业沟通追查此事，准备备品更换\r\n160801pm更换新cisco路由和tplink modem恢复', '已维修');
INSERT INTO `t_repair` VALUES ('126', null, '2016-08-03 15:56:45', '佛山国际ICC', '软件系统', '不能登录操作系统', '李启邦', null, '2016-08-03 15:57:24', '播控电脑无法登录操作系统，提示硬盘缺失\r\n需要更换新电脑\r\n8月4日更换备机解决', '已维修');
INSERT INTO `t_repair` VALUES ('127', null, '2016-08-03 15:57:57', '惠州花边岭广场', '软件系统', '黑屏无广告', '李启邦', null, '2016-08-03 15:58:22', '播控电脑由于过热，运行10分钟左右自动关机', '已维修');
INSERT INTO `t_repair` VALUES ('128', null, '2016-08-03 15:58:37', '江门中泰来酒店', '软件系统', '广告漏播', '李启邦', null, '2016-08-03 15:59:02', '只有2条广告片在循环播放，由于网络不通，无法远程调试', '已维修');
INSERT INTO `t_repair` VALUES ('129', null, '2016-08-10 15:59:13', '佛山国际ICC', '软件系统', '广告漏播', '李启邦', null, '2016-08-10 15:59:46', '只有1条广告在循环播放\r\n有邮件报错提醒，系统显示在线，可远程连接\r\n重启播控小电脑后恢复', '已维修');
INSERT INTO `t_repair` VALUES ('131', null, '2016-08-11 16:00:54', '广州南都北墙', '软件系统', '广告漏播', '李启邦', null, '2016-08-11 16:01:11', '只有1条广告在循环播放\r\n有邮件报错提醒，系统显示在线，不可远程连接\r\n由于网络不通，初步需要人员现场操作\r\n经过半小时后，网络恢复正常，可以远程连接，重启播控小电脑后恢复', '已维修');
INSERT INTO `t_repair` VALUES ('132', null, '2016-08-12 16:01:24', '佛山国际ICC', '软件系统', '广告漏播', '李启邦', null, '2016-08-12 16:05:53', '只有1条广告在循环播放\r\n有邮件报错提醒，系统显示在线，可远程连接\r\n重启播控小电脑后恢复', '已维修');
INSERT INTO `t_repair` VALUES ('133', null, '2016-08-13 16:06:12', '潮州蓝月湾酒店', '软件系统', '弹框报错', '李启邦', null, '2016-08-13 16:06:31', '屏幕有弹框报错，广告在正常播放，但画面部分因弹框被遮盖\r\n报错：\r\nNo row can be iaserted after the uncornitted new row\r\n重启两次后恢复', '已维修');
INSERT INTO `t_repair` VALUES ('134', null, '2016-08-15 16:07:25', '潮州蓝月湾酒店', '软件系统', '弹框报错', '李启邦', null, '2016-08-13 16:07:48', '屏幕有弹框报错，广告在正常播放，但画面部分因弹框被遮盖\r\n与前一天报错一样，重启可解决\r\n广新技术人员已对播控系统进行升级', '已维修');
INSERT INTO `t_repair` VALUES ('135', null, '2016-08-22 16:08:07', '佛山国际ICC', '软件系统', '黑屏报错', '李启邦', null, '2016-08-22 16:08:25', '通过监控查看，播控电脑停留在启动操作系统画面，提示无法找到系统，无法进入桌面系统。\r\n重启电脑也无法解决，需要更换主机', '已维修');
INSERT INTO `t_repair` VALUES ('136', null, '2016-08-24 16:08:52', '佛山国际ICC', '软件系统', '黑屏无广告', '李启邦', null, '2016-08-24 16:09:16', '通过监控查看，屏幕黑屏无广告。\r\n无法远程连接到播控电脑，无法ping通，初步判断没有启动电脑。\r\n\r\n现场维护员检查电脑有通电，但没震动，重启电脑后恢复正常', '已维修');
INSERT INTO `t_repair` VALUES ('137', null, '2016-09-07 16:09:30', '深圳东门广场', '软件系统', '黑屏无广告', '李启邦', null, '2016-09-07 16:09:51', '没有邮件报错，系统显示在线，可以远程连接，远程连接成功发现系统卡死没法操作\r\n\r\n现场维护人员现场重启后恢复', '已维修');
INSERT INTO `t_repair` VALUES ('138', null, '2016-09-09 16:10:09', '潮州蓝月湾酒店', '网络', '断网', '李启邦', null, '2016-09-09 16:11:03', '电源管控电脑断网，小电脑正常，有日志反馈，大电脑无法通过teamviewer远程，推断大电脑网络故障\r\n\r\n当地维护员发现现场大电脑蓝屏，所以无法远程，重启后恢复正常', '已维修');
INSERT INTO `t_repair` VALUES ('139', null, '2016-09-12 16:11:15', '潮州蓝月湾酒店', '软件系统', '系统离线', '李启邦', null, '2016-09-12 16:11:37', '系统显示离线，有日志报错，无法远程连接大小电脑，推断网络出现故障\r\n\r\n当地维护员在现场重启路由后网络恢复正常。', '已维修');

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES ('1', '模组');
INSERT INTO `t_type` VALUES ('2', '电源');
INSERT INTO `t_type` VALUES ('3', '系统接收卡');
INSERT INTO `t_type` VALUES ('4', '系统发送卡');
INSERT INTO `t_type` VALUES ('5', '信号排线');
INSERT INTO `t_type` VALUES ('6', '电源端子、端子台');
INSERT INTO `t_type` VALUES ('7', '风机');
INSERT INTO `t_type` VALUES ('8', '空调');
INSERT INTO `t_type` VALUES ('9', '包饰漏水');
INSERT INTO `t_type` VALUES ('10', '配电柜');
INSERT INTO `t_type` VALUES ('11', '联播网控制电脑');
INSERT INTO `t_type` VALUES ('12', '其他(异常断电，异常断信号)');
INSERT INTO `t_type` VALUES ('13', '软件系统');
INSERT INTO `t_type` VALUES ('14', '硬件');
INSERT INTO `t_type` VALUES ('15', '网络');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
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

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('14', 'admin', 'admin', '管理员', 'admin', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('15', 'hec', 'hec', '职员', '何春', '男', null, null, null, '13530116912', null);
INSERT INTO `t_user` VALUES ('16', 'congjl', 'congjl', '职员', '从键良', '男', null, null, null, '18027461551', null);
INSERT INTO `t_user` VALUES ('17', 'yangzl', 'yangzl', '职员', '杨仲冬', '男', null, null, null, '15013661948', null);
INSERT INTO `t_user` VALUES ('18', 'songds', 'songds', '职员', '宋定生', '男', null, null, null, '18098900732', null);
INSERT INTO `t_user` VALUES ('19', 'fuda', 'fuda', '职员', '傅道安', '男', null, null, null, '13450297970', null);
INSERT INTO `t_user` VALUES ('20', 'wangyq', 'wangyq', '职员', '王烨全', '男', null, null, null, '13535238728', null);
INSERT INTO `t_user` VALUES ('21', 'lis', 'lis', '职员', '李胜', '男', null, null, null, '18602069268', null);
INSERT INTO `t_user` VALUES ('22', 'wufc', 'wufc', '职员', '吴凡超', '男', null, null, null, '18676891783', null);
INSERT INTO `t_user` VALUES ('23', 'liqb', 'liqb', '职员', '李启邦', null, null, null, null, '18666078413', null);
