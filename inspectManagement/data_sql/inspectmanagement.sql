/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : inspectmanagement

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2014-05-23 10:02:59
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app
-- ----------------------------

-- ----------------------------
-- Table structure for `authority`
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_menu
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_power
-- ----------------------------

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '1', '1', '2014-05-19', '启用', '1');

-- ----------------------------
-- Table structure for `device`
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  `deviceTypeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------

-- ----------------------------
-- Table structure for `devicetype`
-- ----------------------------
DROP TABLE IF EXISTS `devicetype`;
CREATE TABLE `devicetype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of devicetype
-- ----------------------------
INSERT INTO `devicetype` VALUES ('1', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `employeeRoleName` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  `departmentId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------

-- ----------------------------
-- Table structure for `employeerole`
-- ----------------------------
DROP TABLE IF EXISTS `employeerole`;
CREATE TABLE `employeerole` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  `authorityId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employeerole
-- ----------------------------

-- ----------------------------
-- Table structure for `employeerole_inspecttable`
-- ----------------------------
DROP TABLE IF EXISTS `employeerole_inspecttable`;
CREATE TABLE `employeerole_inspecttable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employeeRoleId` bigint(20) DEFAULT NULL,
  `employeeRoleName` varchar(255) DEFAULT NULL,
  `inspectTableId` bigint(20) DEFAULT NULL,
  `inspectTableName` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employeerole_inspecttable
-- ----------------------------

-- ----------------------------
-- Table structure for `employee_employeerole`
-- ----------------------------
DROP TABLE IF EXISTS `employee_employeerole`;
CREATE TABLE `employee_employeerole` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employeeId` bigint(20) DEFAULT NULL,
  `employeeName` varchar(255) DEFAULT NULL,
  `employeeRoleId` bigint(20) DEFAULT NULL,
  `employeeRoleName` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_employeerole
-- ----------------------------

-- ----------------------------
-- Table structure for `inspectarea`
-- ----------------------------
DROP TABLE IF EXISTS `inspectarea`;
CREATE TABLE `inspectarea` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `deviceTypeId` bigint(20) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectarea
-- ----------------------------

-- ----------------------------
-- Table structure for `inspectchoice`
-- ----------------------------
DROP TABLE IF EXISTS `inspectchoice`;
CREATE TABLE `inspectchoice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `choiceValue` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectchoice
-- ----------------------------

-- ----------------------------
-- Table structure for `inspectitem`
-- ----------------------------
DROP TABLE IF EXISTS `inspectitem`;
CREATE TABLE `inspectitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `inspectTableId` bigint(20) DEFAULT NULL,
  `inspectAreaId` bigint(20) DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `isInput` tinyint(4) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectitem
-- ----------------------------

-- ----------------------------
-- Table structure for `inspectitemrecord`
-- ----------------------------
DROP TABLE IF EXISTS `inspectitemrecord`;
CREATE TABLE `inspectitemrecord` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `inspectTableId` bigint(20) DEFAULT NULL,
  `inspectTagId` bigint(20) DEFAULT NULL,
  `inspectItemId` bigint(20) DEFAULT NULL,
  `inspectChoiceId` bigint(20) DEFAULT NULL,
  `inspectChoiceValue` varchar(20) DEFAULT NULL,
  `inspectTableRecordId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectitemrecord
-- ----------------------------

-- ----------------------------
-- Table structure for `inspectitem_choice`
-- ----------------------------
DROP TABLE IF EXISTS `inspectitem_choice`;
CREATE TABLE `inspectitem_choice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `inspectItemId` bigint(20) DEFAULT NULL,
  `inspectChoiceId` bigint(20) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectitem_choice
-- ----------------------------

-- ----------------------------
-- Table structure for `inspecttable`
-- ----------------------------
DROP TABLE IF EXISTS `inspecttable`;
CREATE TABLE `inspecttable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspecttable
-- ----------------------------

-- ----------------------------
-- Table structure for `inspecttablerecord`
-- ----------------------------
DROP TABLE IF EXISTS `inspecttablerecord`;
CREATE TABLE `inspecttablerecord` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `inspectTableId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `exceptioncount` bigint(20) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspecttablerecord
-- ----------------------------

-- ----------------------------
-- Table structure for `inspecttag`
-- ----------------------------
DROP TABLE IF EXISTS `inspecttag`;
CREATE TABLE `inspecttag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `inspectAreaId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspecttag
-- ----------------------------

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `level` bigint(20) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parentname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of power
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
  `status` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

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
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_authority
-- ----------------------------
