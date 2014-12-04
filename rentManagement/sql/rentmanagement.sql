/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50533
Source Host           : localhost:3306
Source Database       : rentmanagement

Target Server Type    : MYSQL
Target Server Version : 50533
File Encoding         : 65001

Date: 2014-12-04 17:02:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app
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
-- Table structure for authority
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
-- Table structure for authority_power
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
-- Table structure for bad_debt_device
-- ----------------------------
DROP TABLE IF EXISTS `bad_debt_device`;
CREATE TABLE `bad_debt_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `badDebtId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bad_debt_device
-- ----------------------------
INSERT INTO `bad_debt_device` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for bad_debt_sheet
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bad_debt_sheet
-- ----------------------------
INSERT INTO `bad_debt_sheet` VALUES ('1', '123123', 'x1234', '1', '1', '肖竹军', '1', '没有描述', '2014-11-06', '肖竹军', '1');

-- ----------------------------
-- Table structure for batch
-- ----------------------------
DROP TABLE IF EXISTS `batch`;
CREATE TABLE `batch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `produceTime` datetime DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of batch
-- ----------------------------
INSERT INTO `batch` VALUES ('1', 'sdfsd', '123123123', null, '2014-11-23 11:04:38', '1');
INSERT INTO `batch` VALUES ('2', 'pc20141125-01', '20141125从中联重科采购10台标准塔吊', '2014-11-01 00:00:00', '2014-11-25 10:06:58', '1');
INSERT INTO `batch` VALUES ('3', 'bzj20141129', '20141129采购标准节', '2014-11-29 00:00:00', '2014-11-29 21:47:11', '1');

-- ----------------------------
-- Table structure for car_driver
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_driver
-- ----------------------------
INSERT INTO `car_driver` VALUES ('1', 'xxx', 'x1234', 'xxx', '2014-10-29', '1');

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customerId` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `startTime` date DEFAULT NULL,
  `endTime` date DEFAULT NULL,
  `signTime` date DEFAULT NULL,
  `projectLocation` varchar(255) DEFAULT NULL,
  `chargeMan` varchar(255) DEFAULT NULL,
  `attachment` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contract
-- ----------------------------
INSERT INTO `contract` VALUES ('1', '1', '合同1', '2222', '', '2014-11-04', '2014-11-14', '2014-11-30', '武汉市', '肖竹军', null, null, null, null, '0', '0', null, null, null, null, null, null, null, '1');

-- ----------------------------
-- Table structure for customer
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '中建一局', 'xxx', '武汉市洪山区', '李总', '543234234', 'sdfsdf@163.com', '234234234', 'xxx', '12332423423', '2014-10-29', '1');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', 'xxx', 'xxx1', '2014-10-29', '1');

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mainDeviceId` bigint(20) DEFAULT NULL,
  `batchId` bigint(20) DEFAULT NULL,
  `supplierId` bigint(20) DEFAULT NULL,
  `typeId` bigint(20) DEFAULT NULL,
  `storehouseId` bigint(20) DEFAULT NULL,
  `carDriverId` bigint(20) DEFAULT NULL,
  `contractId` bigint(20) DEFAULT NULL,
  `optionType` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `produceTime` date DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `priceUnit` varchar(255) DEFAULT NULL,
  `lng` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `havePrint` int(11) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('1', '1', '1', '1', '1', '1', null, '1', '4', null, '使用', '塔吊001', 'sjs001', null, '2013-11-01', '2014-11-09', null, '114.380494', '30.507115', '0', '1');
INSERT INTO `device` VALUES ('2', '0', '1', '1', '1', '1', null, '1', '2', null, '使用', '塔吊002', 'sjs002', null, '2013-11-14', '2014-11-09', null, '114.380494', '30.506115', '0', '1');
INSERT INTO `device` VALUES ('8', null, '1', '1', '1', '1', null, '1', '2', null, '维修', '塔吊0003', 'td0003', '500000', '2014-11-01', '2014-11-25', '元', null, null, '0', '1');
INSERT INTO `device` VALUES ('9', '1', '3', '2', '2', '1', null, '1', '4', null, null, '标准节0001', 'bzj0001', '20000', '2014-11-29', '2014-11-29', '元', null, null, '0', '1');
INSERT INTO `device` VALUES ('10', '1', '3', '2', '2', '1', null, '1', '4', null, null, '标准节0002', 'bzj0002', '20000', '2014-11-29', '2014-11-29', '元', null, null, '0', '1');
INSERT INTO `device` VALUES ('11', '1', '3', '2', '2', '1', null, '1', '4', null, null, '标准节0003', 'bzj0003', '20000', '2014-11-29', '2014-11-29', '元', null, null, '0', '1');
INSERT INTO `device` VALUES ('12', '1', '3', '2', '2', '1', null, '1', '4', null, null, '标准节0004', 'bzj0004', '20000', '2014-11-29', '2014-11-29', '元', null, null, '0', '1');
INSERT INTO `device` VALUES ('13', '1', '3', '2', '2', '1', null, '1', '4', null, null, '标准节0005', 'bzj0005', '20000', '2014-11-29', '2014-11-29', '元', null, null, '0', '1');
INSERT INTO `device` VALUES ('14', '0', '3', '2', '2', '1', null, '1', '2', null, null, '标准节0006', 'bzj0006', '20000', '2014-11-29', '2014-11-29', '元', null, null, '0', '1');
INSERT INTO `device` VALUES ('15', '0', '3', '2', '2', '1', null, '1', '2', null, null, '标准节0007', 'bzj0007', '20000', '2014-11-29', '2014-11-29', '元', null, null, '0', '1');
INSERT INTO `device` VALUES ('16', '0', '3', '2', '2', '1', null, '1', '2', null, null, '标准节0008', 'bzj0008', '20000', '2014-11-29', '2014-11-29', '元', null, null, '0', '1');
INSERT INTO `device` VALUES ('17', '0', '3', '2', '2', '1', null, '1', '2', null, null, '标准节0009', 'bzj0009', '20000', '2014-11-29', '2014-11-29', '元', null, null, '0', '1');
INSERT INTO `device` VALUES ('18', '0', '3', '2', '2', '1', null, '1', '2', null, null, '标准节0010', 'bzj0010', '20000', '2014-11-29', '2014-11-29', '元', null, null, '0', '1');

-- ----------------------------
-- Table structure for device_type
-- ----------------------------
DROP TABLE IF EXISTS `device_type`;
CREATE TABLE `device_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `mainDevice` bigint(20) DEFAULT NULL,
  `warnTime` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device_type
-- ----------------------------
INSERT INTO `device_type` VALUES ('1', '司机室', '司机室', '2014-11-06', '台', '1', '5', '1');
INSERT INTO `device_type` VALUES ('2', '标准节', '标准节', '2014-11-29', '个', '0', '5', '1');

-- ----------------------------
-- Table structure for employee
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '1', '安装', '男', '1', '18511451798', 'xiaozhujun520@163.com', '2014-11-03', '现场经理', '2014-11-06', '1');

-- ----------------------------
-- Table structure for installation
-- ----------------------------
DROP TABLE IF EXISTS `installation`;
CREATE TABLE `installation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contractId` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `installMan` varchar(255) DEFAULT NULL,
  `installTime` datetime DEFAULT NULL,
  `installStatus` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of installation
-- ----------------------------
INSERT INTO `installation` VALUES ('9', '1', '安装', '肖竹军、王洪柱', '2014-11-13 03:28:08', '未完成', '/rentManagementResource/rentImage/1/installation/9_towerCrane.jpg', '1');
INSERT INTO `installation` VALUES ('10', '1', '安装', '测试', '2014-11-13 03:34:00', '完成', '/rentManagementResource/rentImage/1/installation/10_towerCrane.jpg', '1');
INSERT INTO `installation` VALUES ('11', '1', '加装', '安装人员', '2014-11-13 04:06:37', '未完成', '/rentManagementResource/rentImage/1/installation/11_towerCrane.jpg', '1');
INSERT INTO `installation` VALUES ('14', '1', '安装', '肖竹军', '2014-11-29 22:31:38', '完成', '/rentManagementResource/rentImage/1/installation/14_towerCrane.jpg', '1');
INSERT INTO `installation` VALUES ('15', '1', '安装', '张三，李四', '2014-11-29 23:34:17', '未完成', '/rentManagementResource/rentImage/1/installation/15_towerCrane.jpg', '1');
INSERT INTO `installation` VALUES ('16', '1', '安装', '肖竹军', '2014-11-30 01:49:55', '完成', '/rentManagementResource/rentImage/1/installation/16_towerCrane.jpg', '1');

-- ----------------------------
-- Table structure for installation_device
-- ----------------------------
DROP TABLE IF EXISTS `installation_device`;
CREATE TABLE `installation_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `installationId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of installation_device
-- ----------------------------
INSERT INTO `installation_device` VALUES ('15', '9', '1');
INSERT INTO `installation_device` VALUES ('16', '9', '2');
INSERT INTO `installation_device` VALUES ('17', '10', '1');
INSERT INTO `installation_device` VALUES ('18', '10', '2');
INSERT INTO `installation_device` VALUES ('19', '11', '1');
INSERT INTO `installation_device` VALUES ('20', '11', '2');
INSERT INTO `installation_device` VALUES ('33', '14', '1');
INSERT INTO `installation_device` VALUES ('34', '14', '9');
INSERT INTO `installation_device` VALUES ('35', '14', '10');
INSERT INTO `installation_device` VALUES ('36', '14', '11');
INSERT INTO `installation_device` VALUES ('37', '14', '12');
INSERT INTO `installation_device` VALUES ('38', '14', '13');
INSERT INTO `installation_device` VALUES ('39', '15', '2');
INSERT INTO `installation_device` VALUES ('40', '15', '14');
INSERT INTO `installation_device` VALUES ('41', '15', '15');
INSERT INTO `installation_device` VALUES ('42', '15', '16');
INSERT INTO `installation_device` VALUES ('43', '15', '17');
INSERT INTO `installation_device` VALUES ('44', '15', '18');
INSERT INTO `installation_device` VALUES ('45', '16', '1');
INSERT INTO `installation_device` VALUES ('46', '16', '9');
INSERT INTO `installation_device` VALUES ('47', '16', '10');
INSERT INTO `installation_device` VALUES ('48', '16', '11');
INSERT INTO `installation_device` VALUES ('49', '16', '12');
INSERT INTO `installation_device` VALUES ('50', '16', '13');

-- ----------------------------
-- Table structure for power
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
-- Table structure for prebury
-- ----------------------------
DROP TABLE IF EXISTS `prebury`;
CREATE TABLE `prebury` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contractId` bigint(20) DEFAULT NULL,
  `preBuryMan` varchar(255) DEFAULT NULL,
  `preBuryCount` int(11) DEFAULT NULL,
  `preBuryTime` date DEFAULT NULL,
  `preBuryStatus` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prebury
-- ----------------------------
INSERT INTO `prebury` VALUES ('1', '1', '肖竹军', null, '2014-11-09', '未完成', null, '1');
INSERT INTO `prebury` VALUES ('2', '1', '肖竹军', '0', '2014-11-30', '完成', null, '1');
INSERT INTO `prebury` VALUES ('3', '1', '肖竹军', '0', '2014-11-30', '完成', '/rentManagementResource/rentImage/1/preBury/3_towerCrane.jpg', '1');
INSERT INTO `prebury` VALUES ('4', '1', '王麻子', '0', '2014-11-30', '完成', '/rentManagementResource/rentImage/1/preBury/4_towerCrane.jpg', '1');
INSERT INTO `prebury` VALUES ('5', '1', '王程', '0', '2014-12-01', '完成', '/rentManagementResource/rentImage/1/preBury/5_towerCrane.jpg', '1');
INSERT INTO `prebury` VALUES ('6', '1', 'sdsdf', '9', '2014-12-01', '完成', '/rentManagementResource/rentImage/1/preBury/6_towerCrane.jpg', '1');

-- ----------------------------
-- Table structure for remove
-- ----------------------------
DROP TABLE IF EXISTS `remove`;
CREATE TABLE `remove` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contractId` bigint(20) DEFAULT NULL,
  `removeMan` varchar(255) DEFAULT NULL,
  `removeStatus` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of remove
-- ----------------------------
INSERT INTO `remove` VALUES ('1', '1', '肖竹军', '未完成', null, '1', null);
INSERT INTO `remove` VALUES ('13', '1', '肖竹军', '完成', '/rentManagementResource/rentImage/1/remove/13_towerCrane.jpg', '1', '2014-11-29 22:36:49');
INSERT INTO `remove` VALUES ('14', '1', '王五', '完成', '/rentManagementResource/rentImage/1/remove/14_towerCrane.jpg', '1', '2014-11-29 23:35:23');

-- ----------------------------
-- Table structure for remove_device
-- ----------------------------
DROP TABLE IF EXISTS `remove_device`;
CREATE TABLE `remove_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `removeId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of remove_device
-- ----------------------------
INSERT INTO `remove_device` VALUES ('18', '13', '1');
INSERT INTO `remove_device` VALUES ('19', '13', '9');
INSERT INTO `remove_device` VALUES ('20', '13', '10');
INSERT INTO `remove_device` VALUES ('21', '13', '11');
INSERT INTO `remove_device` VALUES ('22', '13', '12');
INSERT INTO `remove_device` VALUES ('23', '13', '13');
INSERT INTO `remove_device` VALUES ('24', '14', '2');
INSERT INTO `remove_device` VALUES ('25', '14', '14');
INSERT INTO `remove_device` VALUES ('26', '14', '15');
INSERT INTO `remove_device` VALUES ('27', '14', '16');
INSERT INTO `remove_device` VALUES ('28', '14', '17');
INSERT INTO `remove_device` VALUES ('29', '14', '18');

-- ----------------------------
-- Table structure for selfinspect
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
-- Table structure for skill
-- ----------------------------
DROP TABLE IF EXISTS `skill`;
CREATE TABLE `skill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of skill
-- ----------------------------
INSERT INTO `skill` VALUES ('1', '安装', '安装技能', '2014-11-06', '1');

-- ----------------------------
-- Table structure for stock_in
-- ----------------------------
DROP TABLE IF EXISTS `stock_in`;
CREATE TABLE `stock_in` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `contractId` bigint(20) DEFAULT NULL,
  `driver` varchar(255) DEFAULT NULL,
  `carNumber` varchar(255) DEFAULT NULL,
  `transportId` bigint(20) DEFAULT NULL,
  `handler` varchar(255) DEFAULT NULL,
  `storehouseId` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock_in
-- ----------------------------
INSERT INTO `stock_in` VALUES ('1', '123123', '1', null, 'x12345', null, '肖竹军', '1', '没有描述', '2014-11-09', null, '1');
INSERT INTO `stock_in` VALUES ('2', 'sdfd', '1', 'sdfsdf', 'sdfsd', '0', 'xiaozhujun', '1', 'sdfsdf', '2014-11-18', '/rentManagementResource/rentImage/1/stockIn/2_towerCrane.jpg', '1');
INSERT INTO `stock_in` VALUES ('3', 'rk019809', '1', '肖竹军', '鄂A908B1', '0', 'xiaozhujun', '1', '设备有损耗', '2014-11-18', '/rentManagementResource/rentImage/1/stockIn/3_towerCrane.jpg', '1');
INSERT INTO `stock_in` VALUES ('4', 'rk20141129001', '1', '肖竹军', '', '0', 'xiaozhujun', '1', '无', '2014-11-29', '/rentManagementResource/rentImage/1/stockIn/4_towerCrane.jpg', '1');

-- ----------------------------
-- Table structure for stock_in_device
-- ----------------------------
DROP TABLE IF EXISTS `stock_in_device`;
CREATE TABLE `stock_in_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stockInId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock_in_device
-- ----------------------------
INSERT INTO `stock_in_device` VALUES ('1', '2', '1');
INSERT INTO `stock_in_device` VALUES ('2', '2', '2');
INSERT INTO `stock_in_device` VALUES ('3', '3', '1');
INSERT INTO `stock_in_device` VALUES ('4', '3', '2');
INSERT INTO `stock_in_device` VALUES ('5', '4', '2');
INSERT INTO `stock_in_device` VALUES ('6', '4', '14');
INSERT INTO `stock_in_device` VALUES ('7', '4', '15');
INSERT INTO `stock_in_device` VALUES ('8', '4', '16');
INSERT INTO `stock_in_device` VALUES ('9', '4', '17');
INSERT INTO `stock_in_device` VALUES ('10', '4', '18');

-- ----------------------------
-- Table structure for stock_out
-- ----------------------------
DROP TABLE IF EXISTS `stock_out`;
CREATE TABLE `stock_out` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `contractId` bigint(20) DEFAULT NULL,
  `driver` varchar(255) DEFAULT NULL,
  `carNumber` varchar(255) DEFAULT NULL,
  `handler` varchar(255) DEFAULT NULL,
  `transportId` bigint(20) DEFAULT NULL,
  `storehouseId` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock_out
-- ----------------------------
INSERT INTO `stock_out` VALUES ('1', '123123', '1', null, 'x1234', '肖竹军', null, '1', '没有描述', '2014-11-09', null, '1');
INSERT INTO `stock_out` VALUES ('2', 'asd', '1', 'asd', 'adsas', 'xiaozhujun', '0', '1', 'sdfsdf', '2014-11-18', '/rentManagementResource/rentImage/1/stockOut/2_towerCrane.jpg', '1');
INSERT INTO `stock_out` VALUES ('3', 'ck08834', '1', '肖竹军', '鄂A983B1', 'xiaozhujun', '0', '1', '设备完好', '2014-11-18', '/rentManagementResource/rentImage/1/stockOut/3_towerCrane.jpg', '1');
INSERT INTO `stock_out` VALUES ('5', 'ck20141130001', '1', '肖竹军', '', 'xiaozhujun', '0', '1', '无', '2014-11-30', '/rentManagementResource/rentImage/1/stockOut/5_towerCrane.jpg', '1');

-- ----------------------------
-- Table structure for stock_out_device
-- ----------------------------
DROP TABLE IF EXISTS `stock_out_device`;
CREATE TABLE `stock_out_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stockOutId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock_out_device
-- ----------------------------
INSERT INTO `stock_out_device` VALUES ('1', '0', '1');
INSERT INTO `stock_out_device` VALUES ('2', '2', '1');
INSERT INTO `stock_out_device` VALUES ('3', '2', '2');
INSERT INTO `stock_out_device` VALUES ('4', '3', '1');
INSERT INTO `stock_out_device` VALUES ('5', '3', '2');
INSERT INTO `stock_out_device` VALUES ('6', '5', '2');
INSERT INTO `stock_out_device` VALUES ('7', '5', '14');
INSERT INTO `stock_out_device` VALUES ('8', '5', '15');
INSERT INTO `stock_out_device` VALUES ('9', '5', '16');
INSERT INTO `stock_out_device` VALUES ('10', '5', '17');
INSERT INTO `stock_out_device` VALUES ('11', '5', '18');

-- ----------------------------
-- Table structure for storehouse
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of storehouse
-- ----------------------------
INSERT INTO `storehouse` VALUES ('1', '第一仓库', null, '武汉市武昌区', '肖竹军', '18511451798', '2014-11-06', '1');

-- ----------------------------
-- Table structure for supplier
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('1', '三一重工', '三一重工', 'xxx地址', '张三', '1234123123', 'sdfsdf@163.com', '888888', '2014-10-29', '1');
INSERT INTO `supplier` VALUES ('2', '中联重科', '中联重科', 'sss地址', '李四', '54545345', 'sdfsdf@163.com', '65645451', '2014-10-29', '1');

-- ----------------------------
-- Table structure for transport
-- ----------------------------
DROP TABLE IF EXISTS `transport`;
CREATE TABLE `transport` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `handler` varchar(255) DEFAULT NULL,
  `driver` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `lng` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transport
-- ----------------------------
INSERT INTO `transport` VALUES ('1', 'xiaozhujun', '肖竹军', '18511451744', '武汉市武昌区', '武汉市青山区', null, null, null, '2014-11-10 00:42:30', '1');
INSERT INTO `transport` VALUES ('2', 'xiaozhujun', '张三', '13511459876', '武汉市武昌区', '武汉市汉阳区', null, null, '/rentManagementResource/rentImage/1/transport/2_towerCrane.jpg', '2014-11-10 01:11:22', '1');
INSERT INTO `transport` VALUES ('5', 'xiaozhujun', '张三', '18387630976', '武汉市黄陂区', '武汉市江夏区', null, null, null, '2014-11-11 01:45:30', '1');
INSERT INTO `transport` VALUES ('6', 'xiaozhujun', '李四', '187643234', '武汉市新洲区', '武汉市洪山区', null, null, null, '2014-11-11 01:48:37', '1');
INSERT INTO `transport` VALUES ('47', 'xiaozhujun', '王五', '234234234', '武汉市黄陂区', '武汉市蔡甸区', null, null, '/rentManagementResource/rentImage/1/transport/47_towerCrane.jpg', '2014-11-12 00:55:44', '1');
INSERT INTO `transport` VALUES ('48', 'xiaozhujun', 'sdf', '123123', 'weq', 'qweqwe', null, null, '/rentManagementResource/rentImage/1/transport/48_towerCrane.jpg', '2014-11-12 23:35:19', '1');
INSERT INTO `transport` VALUES ('49', 'xiaozhujun', 'sdf', 'sdf', 'sdfsdf', 'sdf', null, null, '/rentManagementResource/rentImage/1/transport/49_towerCrane.jpg', '2014-11-13 03:38:02', '1');
INSERT INTO `transport` VALUES ('50', 'xiaozhujun', '运输人员', '1987342342', '武汉市武昌区', '武汉市黄陂区', null, null, '/rentManagementResource/rentImage/1/transport/50_towerCrane.jpg', '2014-11-13 04:07:21', '1');
INSERT INTO `transport` VALUES ('51', 'xiaozhujun', '肖竹军', '18511451798', '武汉市武昌区', '武汉市青山区', null, null, '/rentManagementResource/rentImage/1/transport/51_towerCrane.jpg', '2014-11-29 23:55:59', '1');

-- ----------------------------
-- Table structure for transport_device
-- ----------------------------
DROP TABLE IF EXISTS `transport_device`;
CREATE TABLE `transport_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transportId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transport_device
-- ----------------------------
INSERT INTO `transport_device` VALUES ('1', '5', '1');
INSERT INTO `transport_device` VALUES ('2', '5', '2');
INSERT INTO `transport_device` VALUES ('3', '6', '2');
INSERT INTO `transport_device` VALUES ('6', '8', '1');
INSERT INTO `transport_device` VALUES ('7', '8', '2');
INSERT INTO `transport_device` VALUES ('8', '9', '1');
INSERT INTO `transport_device` VALUES ('9', '9', '2');
INSERT INTO `transport_device` VALUES ('82', '47', '1');
INSERT INTO `transport_device` VALUES ('83', '47', '2');
INSERT INTO `transport_device` VALUES ('84', '48', '1');
INSERT INTO `transport_device` VALUES ('85', '48', '2');
INSERT INTO `transport_device` VALUES ('86', '49', '1');
INSERT INTO `transport_device` VALUES ('87', '49', '2');
INSERT INTO `transport_device` VALUES ('88', '50', '1');
INSERT INTO `transport_device` VALUES ('89', '50', '2');
INSERT INTO `transport_device` VALUES ('90', '51', '2');
INSERT INTO `transport_device` VALUES ('91', '51', '14');
INSERT INTO `transport_device` VALUES ('92', '51', '15');
INSERT INTO `transport_device` VALUES ('93', '51', '16');
INSERT INTO `transport_device` VALUES ('94', '51', '17');
INSERT INTO `transport_device` VALUES ('95', '51', '18');

-- ----------------------------
-- Table structure for user
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
-- Table structure for user_authority
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
