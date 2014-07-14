/*
Navicat MySQL Data Transfer

Source Server         : platform
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : realtimemonitor

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2014-07-13 16:06:09
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of area
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_menu
-- ----------------------------
INSERT INTO `authority_menu` VALUES ('76', '1', 'ROLE_USER', '1', '部门管理');
INSERT INTO `authority_menu` VALUES ('77', '1', 'ROLE_USER', '2', '添加部门');
INSERT INTO `authority_menu` VALUES ('78', '1', 'ROLE_USER', '6', '部门列表');
INSERT INTO `authority_menu` VALUES ('79', '1', 'ROLE_USER', '7', '添加员工类型');
INSERT INTO `authority_menu` VALUES ('80', '1', 'ROLE_USER', '8', '员工类型列表');
INSERT INTO `authority_menu` VALUES ('81', '1', 'ROLE_USER', '9', '添加员工');
INSERT INTO `authority_menu` VALUES ('82', '1', 'ROLE_USER', '10', '员工列表');
INSERT INTO `authority_menu` VALUES ('83', '1', 'ROLE_USER', '3', '设备管理');
INSERT INTO `authority_menu` VALUES ('84', '1', 'ROLE_USER', '11', '添加设备类型');
INSERT INTO `authority_menu` VALUES ('85', '1', 'ROLE_USER', '12', '设备类型列表');
INSERT INTO `authority_menu` VALUES ('86', '1', 'ROLE_USER', '13', '添加设备区域');
INSERT INTO `authority_menu` VALUES ('87', '1', 'ROLE_USER', '14', '设备类型区域列表');
INSERT INTO `authority_menu` VALUES ('88', '1', 'ROLE_USER', '15', '添加设备');
INSERT INTO `authority_menu` VALUES ('89', '1', 'ROLE_USER', '16', '设备列表');
INSERT INTO `authority_menu` VALUES ('90', '1', 'ROLE_USER', '17', '添加设备标签');
INSERT INTO `authority_menu` VALUES ('91', '1', 'ROLE_USER', '18', '设备标签列表');
INSERT INTO `authority_menu` VALUES ('92', '1', 'ROLE_USER', '4', '点检表管理');
INSERT INTO `authority_menu` VALUES ('93', '1', 'ROLE_USER', '19', '添加点检表');
INSERT INTO `authority_menu` VALUES ('94', '1', 'ROLE_USER', '20', '点检表列表');
INSERT INTO `authority_menu` VALUES ('95', '1', 'ROLE_USER', '21', '添加点检项');
INSERT INTO `authority_menu` VALUES ('96', '1', 'ROLE_USER', '22', '点检项列表');
INSERT INTO `authority_menu` VALUES ('97', '1', 'ROLE_USER', '23', '点检选值添加');
INSERT INTO `authority_menu` VALUES ('98', '1', 'ROLE_USER', '24', '点检选值列表');
INSERT INTO `authority_menu` VALUES ('99', '2', 'ROLE_ADMIN', '5', '数据管理');
INSERT INTO `authority_menu` VALUES ('100', '2', 'ROLE_ADMIN', '25', '点检结果上传');
INSERT INTO `authority_menu` VALUES ('101', '2', 'ROLE_ADMIN', '26', '人员配置查询');
INSERT INTO `authority_menu` VALUES ('102', '2', 'ROLE_ADMIN', '27', '设备配置查询');
INSERT INTO `authority_menu` VALUES ('103', '2', 'ROLE_ADMIN', '28', '点检表下载');
INSERT INTO `authority_menu` VALUES ('104', '2', 'ROLE_ADMIN', '29', '人员与点检项目表下载');
INSERT INTO `authority_menu` VALUES ('105', '2', 'ROLE_ADMIN', '4', '点检表管理');
INSERT INTO `authority_menu` VALUES ('106', '2', 'ROLE_ADMIN', '19', '添加点检表');
INSERT INTO `authority_menu` VALUES ('107', '2', 'ROLE_ADMIN', '20', '点检表列表');
INSERT INTO `authority_menu` VALUES ('108', '2', 'ROLE_ADMIN', '21', '添加点检项');
INSERT INTO `authority_menu` VALUES ('109', '2', 'ROLE_ADMIN', '22', '点检项列表');
INSERT INTO `authority_menu` VALUES ('110', '2', 'ROLE_ADMIN', '23', '点检选值添加');
INSERT INTO `authority_menu` VALUES ('111', '2', 'ROLE_ADMIN', '24', '点检选值列表');
INSERT INTO `authority_menu` VALUES ('112', '2', 'ROLE_ADMIN', '5', '数据管理');
INSERT INTO `authority_menu` VALUES ('113', '2', 'ROLE_ADMIN', '25', '点检结果上传');
INSERT INTO `authority_menu` VALUES ('114', '2', 'ROLE_ADMIN', '26', '人员配置查询');
INSERT INTO `authority_menu` VALUES ('115', '2', 'ROLE_ADMIN', '27', '设备配置查询');
INSERT INTO `authority_menu` VALUES ('116', '2', 'ROLE_ADMIN', '28', '点检表下载');
INSERT INTO `authority_menu` VALUES ('117', '2', 'ROLE_ADMIN', '29', '人员与点检项目表下载');

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
INSERT INTO `authority_power` VALUES ('2', '1', '4', '/index.html', 'ROLE_USER');
INSERT INTO `authority_power` VALUES ('4', '2', '1', '/rs/**', 'ROLE_ADMIN');
INSERT INTO `authority_power` VALUES ('5', '2', '2', '/user.html', 'ROLE_ADMIN');
INSERT INTO `authority_power` VALUES ('6', '2', '3', '/admin.html', 'ROLE_ADMIN');
INSERT INTO `authority_power` VALUES ('7', '2', '4', '/index.html', 'ROLE_ADMIN');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collector
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '部门管理', '1', '', '菜单');
INSERT INTO `menu` VALUES ('2', '1', '添加部门', '2', 'department/add.html', '部门管理');
INSERT INTO `menu` VALUES ('3', '0', '设备管理', '1', '', '菜单');
INSERT INTO `menu` VALUES ('4', '0', '点检表管理', '1', '', '菜单');
INSERT INTO `menu` VALUES ('5', '0', '数据管理', '1', '', '菜单');
INSERT INTO `menu` VALUES ('6', '1', '部门列表', '2', 'department/list.html', '部门管理');
INSERT INTO `menu` VALUES ('7', '1', '添加员工类型', '2', 'employeeRole/add.html', '部门管理');
INSERT INTO `menu` VALUES ('8', '1', '员工类型列表', '2', 'employeeRole/employeeRolelist.html', '部门管理');
INSERT INTO `menu` VALUES ('9', '1', '添加员工', '2', 'employee/addemployee.html', '部门管理');
INSERT INTO `menu` VALUES ('10', '1', '员工列表', '2', 'employee/employeeList.html', '部门管理');
INSERT INTO `menu` VALUES ('11', '3', '添加设备类型', '2', 'device/addDeviceType.html', '设备管理');
INSERT INTO `menu` VALUES ('12', '3', '设备类型列表', '2', 'device/listDeviceType.html', '设备管理');
INSERT INTO `menu` VALUES ('13', '3', '添加设备区域', '2', 'device/addInspectArea.html', '设备管理');
INSERT INTO `menu` VALUES ('14', '3', '设备类型区域列表', '2', 'device/listInspectArea.html', '设备管理');
INSERT INTO `menu` VALUES ('15', '3', '添加设备', '2', 'device/addDevice.html', '设备管理');
INSERT INTO `menu` VALUES ('16', '3', '设备列表', '2', 'device/listDevice.html', '设备管理');
INSERT INTO `menu` VALUES ('17', '3', '添加设备标签', '2', 'device/addInspectTag.html', '设备管理');
INSERT INTO `menu` VALUES ('18', '3', '设备标签列表', '2', 'device/listInspectTag.html', '设备管理');
INSERT INTO `menu` VALUES ('19', '4', '添加点检表', '2', 'InspectTable/addInspectTable.html', '点检表管理');
INSERT INTO `menu` VALUES ('20', '4', '点检表列表', '2', 'InspectTable/inspectTableList.html', '点检表管理');
INSERT INTO `menu` VALUES ('21', '4', '添加点检项', '2', 'InspectTable/addInspectItem.html', '点检表管理');
INSERT INTO `menu` VALUES ('22', '4', '点检项列表', '2', 'InspectTable/inspectItemList.html', '点检表管理');
INSERT INTO `menu` VALUES ('23', '4', '点检选值添加', '2', 'InspectTable/addInspectChoice.html', '点检表管理');
INSERT INTO `menu` VALUES ('24', '4', '点检选值列表', '2', 'InspectTable/inspectChoiceList.html', '点检表管理');
INSERT INTO `menu` VALUES ('25', '5', '点检结果上传', '2', 'inspectResult/inspectResultUpload.html', '数据管理');
INSERT INTO `menu` VALUES ('26', '5', '人员配置查询', '2', 'configuration/personnelConfiguration.html', '数据管理');
INSERT INTO `menu` VALUES ('27', '5', '设备配置查询', '2', 'configuration/deviceConfiguration.html', '数据管理');
INSERT INTO `menu` VALUES ('28', '5', '点检表下载', '2', 'configuration/inspectTableDownload.html', '数据管理');
INSERT INTO `menu` VALUES ('29', '5', '人员与点检项目表下载', '2', 'configuration/rolesTableDownload.html', '数据管理');

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
INSERT INTO `power` VALUES ('4', '/index.html', 'url', null);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sensor
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'xiaozhujun', 'e10adc3949ba59abbe56e057f20f883e', '男', 'admin', '1', '启用');
INSERT INTO `user` VALUES ('2', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '男', 'ROLE_USER', '1', '启用');
INSERT INTO `user` VALUES ('3', 'sunhui', 'e68fa2bc61b75b8a06766e25905052c7', '男', 'ROLE_USER', '1', '启用');
INSERT INTO `user` VALUES ('4', 'liujinxia', 'c99c1cbefe13019978d90cb442cb8f78', '女', 'ROLE_ADMIN', '1', '启用');

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
