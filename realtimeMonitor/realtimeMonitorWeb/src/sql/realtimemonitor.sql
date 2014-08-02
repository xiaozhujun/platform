/*
Navicat MySQL Data Transfer

Source Server         : yy
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : realtimemonitor

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2014-07-30 21:13:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app`
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES ('1', '企业1', '1', '启用', '2014-05-28');

-- ----------------------------
-- Table structure for `area`
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES ('10', 'q1', '', '2014-07-18', '1', '1');
INSERT INTO `area` VALUES ('11', 'q2', '', '2014-07-18', '1', '1');
INSERT INTO `area` VALUES ('12', 'q1', '', '2014-07-18', '2', '1');
INSERT INTO `area` VALUES ('13', 'q2', '', '2014-07-18', '2', '1');
INSERT INTO `area` VALUES ('14', 'q3', '', '2014-07-18', '2', '1');
INSERT INTO `area` VALUES ('15', 'd', '', '2014-07-18', '1', '1');
INSERT INTO `area` VALUES ('16', '测量组1', null, '2014-07-19', '2', '1');

-- ----------------------------
-- Table structure for `authority`
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('1', 'ROLE_USER', '用户', '1');
INSERT INTO `authority` VALUES ('2', 'ROLE_ADMIN', '管理员', '1');

-- ----------------------------
-- Table structure for `authority_menu`
-- ----------------------------
DROP TABLE IF EXISTS `authority_menu`;
CREATE TABLE `authority_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authorityId` bigint(20) DEFAULT NULL,
  `authorityName` varchar(255) DEFAULT NULL,
  `menuId` bigint(20) DEFAULT NULL,
  `menuName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_menu
-- ----------------------------
INSERT INTO `authority_menu` VALUES ('1', '1', 'ROLE_USER', '1', '组管理');
INSERT INTO `authority_menu` VALUES ('2', '1', 'ROLE_USER', '2', '添加组');
INSERT INTO `authority_menu` VALUES ('3', '1', 'ROLE_USER', '3', '组列表');
INSERT INTO `authority_menu` VALUES ('4', '1', 'ROLE_USER', '4', '区域管理');

-- ----------------------------
-- Table structure for `authority_power`
-- ----------------------------
DROP TABLE IF EXISTS `authority_power`;
CREATE TABLE `authority_power` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authorityId` bigint(20) DEFAULT NULL,
  `powerId` bigint(20) DEFAULT NULL,
  `powerResource` varchar(255) DEFAULT NULL,
  `authorityName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_power
-- ----------------------------
INSERT INTO `authority_power` VALUES ('1', '1', '1', '/rs/**', 'ROLE_USER');
INSERT INTO `authority_power` VALUES ('2', '1', '4', '/index.jsp', 'ROLE_USER');
INSERT INTO `authority_power` VALUES ('4', '2', '1', '/rs/**', 'ROLE_ADMIN');
INSERT INTO `authority_power` VALUES ('5', '2', '2', '/user.html', 'ROLE_ADMIN');
INSERT INTO `authority_power` VALUES ('6', '2', '3', '/admin.html', 'ROLE_ADMIN');
INSERT INTO `authority_power` VALUES ('7', '2', '4', '/index.jsp', 'ROLE_ADMIN');
INSERT INTO `authority_power` VALUES ('8', '2', '15', 'cas/**', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for `collector`
-- ----------------------------
DROP TABLE IF EXISTS `collector`;
CREATE TABLE `collector` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `areaId` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `lastMessageTime` date DEFAULT NULL,
  `maxFrequency` varchar(255) DEFAULT NULL,
  `minFrequency` varchar(255) DEFAULT NULL,
  `workFrequency` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collector
-- ----------------------------
INSERT INTO `collector` VALUES ('20', 'c1', '无', '01', '1', '10', '在线正常工作', '2014-07-23', '1000', '100', '700', '1');
INSERT INTO `collector` VALUES ('21', 'c2', '无', '02', '1', '10', '位置或离线', '2014-07-23', '1000', '100', '700', '1');

-- ----------------------------
-- Table structure for `group`
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES ('1', '组1', '无', '2014-07-18', '1');
INSERT INTO `group` VALUES ('2', '组2', '无', '2014-07-18', '1');

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `level` bigint(20) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parentname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '组管理', '1', null, '菜单');
INSERT INTO `menu` VALUES ('2', '1', '添加组', '2', 'monitor/addGroup.html', '组管理');
INSERT INTO `menu` VALUES ('3', '1', '组列表', '2', 'monitor/groupList.html', '组管理');
INSERT INTO `menu` VALUES ('4', '0', '区域管理', '1', null, '菜单');
INSERT INTO `menu` VALUES ('5', '4', '添加区域', '2', 'monitor/addArea.html', '区域管理');
INSERT INTO `menu` VALUES ('6', '4', '区域列表', '2', 'monitor/areaList.html', '区域管理');
INSERT INTO `menu` VALUES ('7', '0', '采集仪管理', '1', null, '菜单');
INSERT INTO `menu` VALUES ('8', '7', '添加采集仪', '2', 'monitor/addCollector.html', '采集仪管理');
INSERT INTO `menu` VALUES ('9', '7', '采集仪列表', '2', 'monitor/collectorList.html', '采集仪管理');
INSERT INTO `menu` VALUES ('10', '0', '传感器管理', '1', null, '菜单');
INSERT INTO `menu` VALUES ('11', '10', '添加传感器', '2', 'monitor/addSensor.html', '传感器管理');
INSERT INTO `menu` VALUES ('12', '10', '传感器列表', '2', 'monitor/sensorList.html', '传感器管理');

-- ----------------------------
-- Table structure for `power`
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resource` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of power
-- ----------------------------
INSERT INTO `power` VALUES ('1', '/rs/**', 'resource', '所有rest服务');
INSERT INTO `power` VALUES ('2', '/user.html', 'url', null);
INSERT INTO `power` VALUES ('3', '/admin.html', 'url', null);
INSERT INTO `power` VALUES ('4', '/index.jsp', 'url', null);
INSERT INTO `power` VALUES ('15', 'cas/**', 'service', 'cas client test from android');

-- ----------------------------
-- Table structure for `sensor`
-- ----------------------------
DROP TABLE IF EXISTS `sensor`;
CREATE TABLE `sensor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `areaId` bigint(20) DEFAULT NULL,
  `collectorId` bigint(20) DEFAULT NULL,
  `maxFrequency` varchar(255) DEFAULT NULL,
  `minFrequency` varchar(255) DEFAULT NULL,
  `workFrequency` varchar(255) DEFAULT NULL,
  `shouldWarn` varchar(255) DEFAULT NULL,
  `warnType` varchar(255) DEFAULT NULL,
  `warnValue` varchar(255) DEFAULT NULL,
  `warnCount` bigint(20) DEFAULT NULL,
  `warnStatus` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sensor
-- ----------------------------
INSERT INTO `sensor` VALUES ('11', 's1', '无', '01', '1', '10', '20', '1000', '100', '700', '是', '均方差', '70', '0', null, '1');
INSERT INTO `sensor` VALUES ('12', 's2', '无', '02', '1', '10', '21', '1000', '100', '700', '是', '均方差', '70', '0', null, '1');
INSERT INTO `sensor` VALUES ('13', 's3', '无', '03', '1', '10', '20', '1000', '100', '700', '是', '均方差', '150', '0', null, '1');
INSERT INTO `sensor` VALUES ('14', 's1', '无', '2100000000010000', '1', '10', '20', '1000', '100', '700', '是', '均方差', '130', '24', null, '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT '1',
  `status` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'xiaozhujun', 'e10adc3949ba59abbe56e057f20f883e', '男', 'ROLE_USER;ROLE_ADMIN', '1', '启用', null);
INSERT INTO `user` VALUES ('2', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '男', 'ROLE_USER', '1', '启用', null);
INSERT INTO `user` VALUES ('3', 'sunhui', 'e68fa2bc61b75b8a06766e25905052c7', '男', 'ROLE_USER', '1', '启用', null);
INSERT INTO `user` VALUES ('4', 'liujinxia', 'c99c1cbefe13019978d90cb442cb8f78', '女', 'ROLE_ADMIN', '1', '启用', null);

-- ----------------------------
-- Table structure for `user_authority`
-- ----------------------------
DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL,
  `authorityId` bigint(20) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `authorityName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_authority
-- ----------------------------
INSERT INTO `user_authority` VALUES ('1', '1', '1', 'xiaozhujun', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('2', '1', '2', 'xiaozhujun', 'ROLE_ADMIN');
INSERT INTO `user_authority` VALUES ('5', '2', '1', 'zhangsan', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('6', '3', '1', 'sunhui', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('7', '4', '2', 'liujinxia', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for `warn_record`
-- ----------------------------
DROP TABLE IF EXISTS `warn_record`;
CREATE TABLE `warn_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `groupName` varchar(255) DEFAULT NULL,
  `areaName` varchar(255) DEFAULT NULL,
  `collectorName` varchar(255) DEFAULT NULL,
  `sensorName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `warnTime` date DEFAULT NULL,
  `curWarnValue` bigint(20) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of warn_record
-- ----------------------------
INSERT INTO `warn_record` VALUES ('51', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '152', '1');
INSERT INTO `warn_record` VALUES ('52', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '134', '1');
INSERT INTO `warn_record` VALUES ('53', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '133', '1');
INSERT INTO `warn_record` VALUES ('54', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '143', '1');
INSERT INTO `warn_record` VALUES ('55', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '143', '1');
INSERT INTO `warn_record` VALUES ('56', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '136', '1');
INSERT INTO `warn_record` VALUES ('57', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '147', '1');
INSERT INTO `warn_record` VALUES ('58', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '152', '1');
INSERT INTO `warn_record` VALUES ('59', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '148', '1');
INSERT INTO `warn_record` VALUES ('60', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '134', '1');
INSERT INTO `warn_record` VALUES ('61', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '151', '1');
INSERT INTO `warn_record` VALUES ('62', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '146', '1');
INSERT INTO `warn_record` VALUES ('63', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '153', '1');
INSERT INTO `warn_record` VALUES ('64', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '144', '1');
INSERT INTO `warn_record` VALUES ('65', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '145', '1');
INSERT INTO `warn_record` VALUES ('66', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '149', '1');
INSERT INTO `warn_record` VALUES ('67', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '134', '1');
INSERT INTO `warn_record` VALUES ('68', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '134', '1');
INSERT INTO `warn_record` VALUES ('69', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '144', '1');
INSERT INTO `warn_record` VALUES ('70', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '144', '1');
INSERT INTO `warn_record` VALUES ('71', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '138', '1');
INSERT INTO `warn_record` VALUES ('72', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '138', '1');
INSERT INTO `warn_record` VALUES ('73', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '145', '1');
INSERT INTO `warn_record` VALUES ('74', '组1', 'q1', 'c1', 's1', '2100000000010000', '2014-07-30', '153', '1');
