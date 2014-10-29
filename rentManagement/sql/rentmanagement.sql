/*
Navicat MySQL Data Transfer

Source Server         : cwb
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : rentmanagement

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2014-10-29 21:51:59
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
-- Table structure for `bad_debt_device`
-- ----------------------------
DROP TABLE IF EXISTS `bad_debt_device`;
CREATE TABLE `bad_debt_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `badDebtId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bad_debt_device
-- ----------------------------

-- ----------------------------
-- Table structure for `bad_debt_sheet`
-- ----------------------------
DROP TABLE IF EXISTS `bad_debt_sheet`;
CREATE TABLE `bad_debt_sheet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `carNumber` varchar(255) DEFAULT NULL,
  `customerId` bigint(20) DEFAULT NULL,
  `contractId` bigint(20) DEFAULT NULL,
  `handler` varchar(255) DEFAULT NULL,
  `storehouseId` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bad_debt_sheet
-- ----------------------------

-- ----------------------------
-- Table structure for `car_driver`
-- ----------------------------
DROP TABLE IF EXISTS `car_driver`;
CREATE TABLE `car_driver` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `carNumber` varchar(255) DEFAULT NULL,
  `carType` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_driver
-- ----------------------------

-- ----------------------------
-- Table structure for `contract`
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customerId` bigint(20) DEFAULT NULL,
  `customerName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `startTime` date DEFAULT NULL,
  `endTime` date DEFAULT NULL,
  `signTime` date DEFAULT NULL,
  `projectLocation` varchar(255) DEFAULT NULL,
  `chargeMan` varchar(255) DEFAULT NULL,
  `preBuryMan` varchar(255) DEFAULT NULL,
  `preBuryTime` date DEFAULT NULL,
  `preBuryStatus` varchar(255) DEFAULT NULL,
  `needInstallCount` bigint(20) DEFAULT NULL,
  `installCount` bigint(20) DEFAULT NULL,
  `netRegisterMan` varchar(255) DEFAULT NULL,
  `netRegisterTime` date DEFAULT NULL,
  `selfInspectStatus` varchar(255) DEFAULT NULL,
  `selfInspectTime` date DEFAULT NULL,
  `removeMan` varchar(255) DEFAULT NULL,
  `removeStatus` varchar(255) DEFAULT NULL,
  `removeTime` date DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contract
-- ----------------------------

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `linkman` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `bankAccount` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------

-- ----------------------------
-- Table structure for `device`
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mainDeviceId` bigint(20) DEFAULT NULL,
  `typeId` bigint(20) DEFAULT NULL,
  `storehouseId` bigint(20) DEFAULT NULL,
  `contractId` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `produceTime` date DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `priceUnit` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------

-- ----------------------------
-- Table structure for `device_type`
-- ----------------------------
DROP TABLE IF EXISTS `device_type`;
CREATE TABLE `device_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `isMainDevice` bigint(20) DEFAULT NULL,
  `warnTime` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device_type
-- ----------------------------

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `departmentId` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `skillId` bigint(20) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `employedTime` date DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------

-- ----------------------------
-- Table structure for `installation`
-- ----------------------------
DROP TABLE IF EXISTS `installation`;
CREATE TABLE `installation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contractId` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `installDeviceId` bigint(20) DEFAULT NULL,
  `installMan` varchar(255) DEFAULT NULL,
  `installTime` date DEFAULT NULL,
  `installStatus` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of installation
-- ----------------------------

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
-- Table structure for `prebury`
-- ----------------------------
DROP TABLE IF EXISTS `prebury`;
CREATE TABLE `prebury` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contractId` bigint(20) DEFAULT NULL,
  `preBuryMan` varchar(255) DEFAULT NULL,
  `preBuryTime` date DEFAULT NULL,
  `preBuryStatus` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prebury
-- ----------------------------

-- ----------------------------
-- Table structure for `remove`
-- ----------------------------
DROP TABLE IF EXISTS `remove`;
CREATE TABLE `remove` (
  `id` bigint(20) NOT NULL,
  `contractId` bigint(20) DEFAULT NULL,
  `removeDeviceId` bigint(20) DEFAULT NULL,
  `removeMan` varchar(255) DEFAULT NULL,
  `removeStatus` varchar(255) DEFAULT NULL,
  `removeTime` date DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of remove
-- ----------------------------

-- ----------------------------
-- Table structure for `selfinspect`
-- ----------------------------
DROP TABLE IF EXISTS `selfinspect`;
CREATE TABLE `selfinspect` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contractId` bigint(20) DEFAULT NULL,
  `selfInspectDeviceId` varchar(255) DEFAULT NULL,
  `selfInspectMan` varchar(255) DEFAULT NULL,
  `selfInspectTime` date DEFAULT NULL,
  `selfInspectStatus` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of selfinspect
-- ----------------------------

-- ----------------------------
-- Table structure for `skill`
-- ----------------------------
DROP TABLE IF EXISTS `skill`;
CREATE TABLE `skill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of skill
-- ----------------------------

-- ----------------------------
-- Table structure for `stock_in_device`
-- ----------------------------
DROP TABLE IF EXISTS `stock_in_device`;
CREATE TABLE `stock_in_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stockInId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock_in_device
-- ----------------------------

-- ----------------------------
-- Table structure for `stock_in_sheet`
-- ----------------------------
DROP TABLE IF EXISTS `stock_in_sheet`;
CREATE TABLE `stock_in_sheet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `carNumber` varchar(255) DEFAULT NULL,
  `customerId` bigint(20) DEFAULT NULL,
  `contractId` bigint(20) DEFAULT NULL,
  `handler` varchar(255) DEFAULT NULL,
  `storehouseId` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock_in_sheet
-- ----------------------------

-- ----------------------------
-- Table structure for `stock_out_device`
-- ----------------------------
DROP TABLE IF EXISTS `stock_out_device`;
CREATE TABLE `stock_out_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stockOutId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock_out_device
-- ----------------------------

-- ----------------------------
-- Table structure for `stock_out_sheet`
-- ----------------------------
DROP TABLE IF EXISTS `stock_out_sheet`;
CREATE TABLE `stock_out_sheet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `carNumber` varchar(255) DEFAULT NULL,
  `customerId` bigint(20) DEFAULT NULL,
  `contractId` bigint(20) DEFAULT NULL,
  `handler` varchar(255) DEFAULT NULL,
  `storehouseId` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock_out_sheet
-- ----------------------------

-- ----------------------------
-- Table structure for `storehouse`
-- ----------------------------
DROP TABLE IF EXISTS `storehouse`;
CREATE TABLE `storehouse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `linkman` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of storehouse
-- ----------------------------

-- ----------------------------
-- Table structure for `storehouse_device`
-- ----------------------------
DROP TABLE IF EXISTS `storehouse_device`;
CREATE TABLE `storehouse_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `storehouseId` bigint(20) DEFAULT NULL,
  `deviceTypeId` bigint(20) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of storehouse_device
-- ----------------------------

-- ----------------------------
-- Table structure for `supplier`
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `linkman` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier
-- ----------------------------

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
