/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50140
Source Host           : localhost:3306
Source Database       : inspectmanagement

Target Server Type    : MYSQL
Target Server Version : 50140
File Encoding         : 65001

Date: 2014-06-19 15:30:53
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
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_menu
-- ----------------------------
INSERT INTO `authority_menu` VALUES ('130', '1', 'ROLE_USER', '1', '部门管理');
INSERT INTO `authority_menu` VALUES ('131', '1', 'ROLE_USER', '2', '添加部门');
INSERT INTO `authority_menu` VALUES ('132', '1', 'ROLE_USER', '6', '部门列表');
INSERT INTO `authority_menu` VALUES ('133', '1', 'ROLE_USER', '7', '添加员工类型');
INSERT INTO `authority_menu` VALUES ('134', '1', 'ROLE_USER', '8', '员工类型列表');
INSERT INTO `authority_menu` VALUES ('135', '1', 'ROLE_USER', '9', '添加员工');
INSERT INTO `authority_menu` VALUES ('136', '1', 'ROLE_USER', '10', '员工列表');
INSERT INTO `authority_menu` VALUES ('137', '1', 'ROLE_USER', '3', '设备管理');
INSERT INTO `authority_menu` VALUES ('138', '1', 'ROLE_USER', '11', '添加设备类型');
INSERT INTO `authority_menu` VALUES ('139', '1', 'ROLE_USER', '12', '设备类型列表');
INSERT INTO `authority_menu` VALUES ('140', '1', 'ROLE_USER', '13', '添加设备区域');
INSERT INTO `authority_menu` VALUES ('141', '1', 'ROLE_USER', '14', '设备类型区域列表');
INSERT INTO `authority_menu` VALUES ('142', '1', 'ROLE_USER', '15', '添加设备');
INSERT INTO `authority_menu` VALUES ('143', '1', 'ROLE_USER', '16', '设备列表');
INSERT INTO `authority_menu` VALUES ('144', '1', 'ROLE_USER', '17', '添加设备标签');
INSERT INTO `authority_menu` VALUES ('145', '1', 'ROLE_USER', '18', '设备标签列表');
INSERT INTO `authority_menu` VALUES ('146', '1', 'ROLE_USER', '4', '点检表管理');
INSERT INTO `authority_menu` VALUES ('147', '1', 'ROLE_USER', '19', '添加点检表');
INSERT INTO `authority_menu` VALUES ('148', '1', 'ROLE_USER', '20', '点检表列表');
INSERT INTO `authority_menu` VALUES ('149', '1', 'ROLE_USER', '21', '添加点检项');
INSERT INTO `authority_menu` VALUES ('150', '1', 'ROLE_USER', '22', '点检项列表');
INSERT INTO `authority_menu` VALUES ('151', '1', 'ROLE_USER', '23', '点检选值添加');
INSERT INTO `authority_menu` VALUES ('152', '1', 'ROLE_USER', '24', '点检选值列表');
INSERT INTO `authority_menu` VALUES ('153', '1', 'ROLE_USER', '5', '数据管理');
INSERT INTO `authority_menu` VALUES ('154', '1', 'ROLE_USER', '25', '点检结果上传');
INSERT INTO `authority_menu` VALUES ('155', '1', 'ROLE_USER', '26', '人员配置查询');
INSERT INTO `authority_menu` VALUES ('156', '1', 'ROLE_USER', '27', '设备配置查询');
INSERT INTO `authority_menu` VALUES ('157', '1', 'ROLE_USER', '28', '点检表下载');
INSERT INTO `authority_menu` VALUES ('158', '1', 'ROLE_USER', '29', '人员与点检项目表下载');
INSERT INTO `authority_menu` VALUES ('159', '1', 'ROLE_USER', '30', '报表管理');
INSERT INTO `authority_menu` VALUES ('160', '1', 'ROLE_USER', '31', '设备异常总数');
INSERT INTO `authority_menu` VALUES ('161', '1', 'ROLE_USER', '32', '设备异常明细');
INSERT INTO `authority_menu` VALUES ('162', '1', 'ROLE_USER', '33', '人员点检异常总数');
INSERT INTO `authority_menu` VALUES ('163', '1', 'ROLE_USER', '34', '人员点检异常明细');
INSERT INTO `authority_menu` VALUES ('164', '1', 'ROLE_USER', '35', '设备异常趋势分析');
INSERT INTO `authority_menu` VALUES ('165', '2', 'ROLE_ADMIN', '1', '部门管理');
INSERT INTO `authority_menu` VALUES ('166', '2', 'ROLE_ADMIN', '2', '添加部门');
INSERT INTO `authority_menu` VALUES ('167', '2', 'ROLE_ADMIN', '6', '部门列表');
INSERT INTO `authority_menu` VALUES ('168', '2', 'ROLE_ADMIN', '7', '添加员工类型');
INSERT INTO `authority_menu` VALUES ('169', '2', 'ROLE_ADMIN', '8', '员工类型列表');
INSERT INTO `authority_menu` VALUES ('170', '2', 'ROLE_ADMIN', '9', '添加员工');
INSERT INTO `authority_menu` VALUES ('171', '2', 'ROLE_ADMIN', '10', '员工列表');
INSERT INTO `authority_menu` VALUES ('172', '2', 'ROLE_ADMIN', '3', '设备管理');
INSERT INTO `authority_menu` VALUES ('173', '2', 'ROLE_ADMIN', '11', '添加设备类型');
INSERT INTO `authority_menu` VALUES ('174', '2', 'ROLE_ADMIN', '12', '设备类型列表');
INSERT INTO `authority_menu` VALUES ('175', '2', 'ROLE_ADMIN', '13', '添加设备区域');
INSERT INTO `authority_menu` VALUES ('176', '2', 'ROLE_ADMIN', '14', '设备类型区域列表');
INSERT INTO `authority_menu` VALUES ('177', '2', 'ROLE_ADMIN', '15', '添加设备');
INSERT INTO `authority_menu` VALUES ('178', '2', 'ROLE_ADMIN', '16', '设备列表');
INSERT INTO `authority_menu` VALUES ('179', '2', 'ROLE_ADMIN', '17', '添加设备标签');
INSERT INTO `authority_menu` VALUES ('180', '2', 'ROLE_ADMIN', '18', '设备标签列表');
INSERT INTO `authority_menu` VALUES ('181', '2', 'ROLE_ADMIN', '4', '点检表管理');
INSERT INTO `authority_menu` VALUES ('182', '2', 'ROLE_ADMIN', '19', '添加点检表');
INSERT INTO `authority_menu` VALUES ('183', '2', 'ROLE_ADMIN', '20', '点检表列表');
INSERT INTO `authority_menu` VALUES ('184', '2', 'ROLE_ADMIN', '21', '添加点检项');
INSERT INTO `authority_menu` VALUES ('185', '2', 'ROLE_ADMIN', '22', '点检项列表');
INSERT INTO `authority_menu` VALUES ('186', '2', 'ROLE_ADMIN', '23', '点检选值添加');
INSERT INTO `authority_menu` VALUES ('187', '2', 'ROLE_ADMIN', '24', '点检选值列表');
INSERT INTO `authority_menu` VALUES ('188', '2', 'ROLE_ADMIN', '5', '数据管理');
INSERT INTO `authority_menu` VALUES ('189', '2', 'ROLE_ADMIN', '25', '点检结果上传');
INSERT INTO `authority_menu` VALUES ('190', '2', 'ROLE_ADMIN', '26', '人员配置查询');
INSERT INTO `authority_menu` VALUES ('191', '2', 'ROLE_ADMIN', '27', '设备配置查询');
INSERT INTO `authority_menu` VALUES ('192', '2', 'ROLE_ADMIN', '28', '点检表下载');
INSERT INTO `authority_menu` VALUES ('193', '2', 'ROLE_ADMIN', '29', '人员与点检项目表下载');
INSERT INTO `authority_menu` VALUES ('194', '2', 'ROLE_ADMIN', '30', '报表管理');
INSERT INTO `authority_menu` VALUES ('195', '2', 'ROLE_ADMIN', '31', '设备异常总数');
INSERT INTO `authority_menu` VALUES ('196', '2', 'ROLE_ADMIN', '32', '设备异常明细');
INSERT INTO `authority_menu` VALUES ('197', '2', 'ROLE_ADMIN', '33', '人员点检异常总数');
INSERT INTO `authority_menu` VALUES ('198', '2', 'ROLE_ADMIN', '34', '人员点检异常明细');
INSERT INTO `authority_menu` VALUES ('199', '2', 'ROLE_ADMIN', '35', '设备异常趋势分析');
INSERT INTO `authority_menu` VALUES ('200', '2', 'ROLE_ADMIN', '37', '报表展示');

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
  `number` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  `deviceTypeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('1', '门座式起重机#04', '1', '们', '1', '1');
INSERT INTO `device` VALUES ('2', '门机1', '门', '1', '1', '1');
INSERT INTO `device` VALUES ('3', '设备1', '0001', '设备1', '1', '3');
INSERT INTO `device` VALUES ('7', '门座式起重机#01', 'menzuo001', '门座式起重机#01', '1', '8');
INSERT INTO `device` VALUES ('8', '门座式起重机#02', 'menzuo002', '门座式起重机#02', '1', '8');
INSERT INTO `device` VALUES ('9', '门座式起重机#03', 'menzuo003', '门座式起重机#03', '1', '8');
INSERT INTO `device` VALUES ('10', '轮胎式集装箱门式起重机#01', 'luntai0001', '轮胎式集装箱门式起重机#01', '1', '9');
INSERT INTO `device` VALUES ('11', '轮胎式集装箱门式起重机#02', 'luntai0002', '轮胎式集装箱门式起重机#02', '1', '9');
INSERT INTO `device` VALUES ('12', '轮胎式集装箱门式起重机#03', 'luntai0003', '轮胎式集装箱门式起重机#03', '1', '9');

-- ----------------------------
-- Table structure for `devicetype`
-- ----------------------------
DROP TABLE IF EXISTS `devicetype`;
CREATE TABLE `devicetype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of devicetype
-- ----------------------------
INSERT INTO `devicetype` VALUES ('1', '门式起重机', '1', '1', '1');
INSERT INTO `devicetype` VALUES ('2', '门机', '门', 'men', '1');
INSERT INTO `devicetype` VALUES ('3', '设备类型1', '0001', '描述1', '1');
INSERT INTO `devicetype` VALUES ('8', '门座式起重机', '0001', '门座式起重机', '1');
INSERT INTO `devicetype` VALUES ('9', '轮胎式集装箱门式起重机', '0002', '轮胎式集装箱门式起重机', '1');
INSERT INTO `devicetype` VALUES ('10', '岸边式集装箱起重机', '0003', '岸边式集装箱起重机', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'xiaozhujun', '123456', '男', '机修人员', '启用', '1', '1', '1');
INSERT INTO `employee` VALUES ('2', 'xiaopeng', '123', '男', '门机队机械技术员', '启用', '1', '1', '5');
INSERT INTO `employee` VALUES ('3', 'zhangsan', '12456', '男', '门机司机', '启用', '1', '1', '2');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employeerole
-- ----------------------------
INSERT INTO `employeerole` VALUES ('1', '机修人员', '', '启用', '1', '2');
INSERT INTO `employeerole` VALUES ('2', '门机司机', '', '启用', '1', '2');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employeerole_inspecttable
-- ----------------------------
INSERT INTO `employeerole_inspecttable` VALUES ('1', '1', '机修人员', '1', '机修人员点检表', '1');
INSERT INTO `employeerole_inspecttable` VALUES ('2', '2', '门机司机', '2', '门机司机点检表', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_employeerole
-- ----------------------------
INSERT INTO `employee_employeerole` VALUES ('1', '1', 'xiaozhujun', '1', '机修人员', '1');
INSERT INTO `employee_employeerole` VALUES ('2', '2', 'xiaopeng', '2', '门机队机械技术员', '1');

-- ----------------------------
-- Table structure for `exception`
-- ----------------------------
DROP TABLE IF EXISTS `exception`;
CREATE TABLE `exception` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exception
-- ----------------------------

-- ----------------------------
-- Table structure for `inspectaccount`
-- ----------------------------
DROP TABLE IF EXISTS `inspectaccount`;
CREATE TABLE `inspectaccount` (
  `id` bigint(20) NOT NULL,
  `inspectTaskId` bigint(20) DEFAULT NULL,
  `inspectTableId` bigint(20) DEFAULT NULL,
  `inspectTableRecordId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  `exceptioncount` bigint(20) DEFAULT NULL,
  `inspectTime` date DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of inspectaccount
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
  `number` varchar(255) DEFAULT NULL,
  `deviceTypeId` bigint(20) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectarea
-- ----------------------------
INSERT INTO `inspectarea` VALUES ('1', '旋转区域', '旋转区域', '2014-05-30', '1', '1', '1');
INSERT INTO `inspectarea` VALUES ('2', '转盘区域', '111', '2014-06-02', 'zzzz', '1', '1');
INSERT INTO `inspectarea` VALUES ('3', '司机室区域', '111', '2014-06-02', 'sssssss', '1', '1');
INSERT INTO `inspectarea` VALUES ('4', '臂架区域', '111', '2014-06-02', 'sssssss', '1', '1');

-- ----------------------------
-- Table structure for `inspectchoice`
-- ----------------------------
DROP TABLE IF EXISTS `inspectchoice`;
CREATE TABLE `inspectchoice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `choiceValue` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectchoice
-- ----------------------------
INSERT INTO `inspectchoice` VALUES ('1', '正常', '1');
INSERT INTO `inspectchoice` VALUES ('2', '异常', '1');
INSERT INTO `inspectchoice` VALUES ('3', '无', '1');

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
  `number` varchar(255) DEFAULT NULL,
  `isInput` tinyint(4) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectitem
-- ----------------------------
INSERT INTO `inspectitem` VALUES ('1', '铁屑', null, '2014-06-12', '1', '1', '001', null, '1');
INSERT INTO `inspectitem` VALUES ('2', '锚定', null, '2014-06-12', '1', '1', '002', null, '1');
INSERT INTO `inspectitem` VALUES ('16', '行走台车', null, '2014-06-12', '1', '2', '003', null, '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=400 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectitemrecord
-- ----------------------------
INSERT INTO `inspectitemrecord` VALUES ('352', '1', '1', '1', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('353', '1', '1', '2', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('354', '1', '1', '3', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('355', '1', '1', '4', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('356', '1', '1', '5', '2', '异常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('357', '1', '1', '6', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('358', '1', '1', '7', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('359', '1', '1', '8', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('360', '1', '1', '9', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('361', '1', '1', '10', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('362', '1', '1', '11', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('363', '1', '1', '12', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('364', '1', '1', '13', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('365', '1', '1', '14', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('366', '1', '1', '15', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('367', '1', '2', '16', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('368', '1', '2', '17', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('369', '1', '2', '18', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('370', '1', '2', '19', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('371', '1', '2', '20', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('372', '1', '2', '21', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('373', '1', '2', '22', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('374', '1', '2', '23', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('375', '1', '2', '24', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('376', '1', '3', '25', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('377', '1', '3', '26', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('378', '1', '3', '27', '3', '无', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('379', '1', '3', '28', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('380', '1', '3', '29', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('381', '1', '3', '30', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('382', '1', '3', '31', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('383', '1', '3', '32', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('384', '1', '3', '33', '2', '异常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('385', '1', '3', '34', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('386', '1', '3', '35', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('387', '1', '3', '36', '2', '异常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('388', '1', '3', '37', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('389', '1', '4', '38', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('390', '1', '4', '39', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('391', '1', '4', '40', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('392', '1', '4', '41', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('393', '1', '4', '42', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('394', '1', '4', '43', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('395', '1', '4', '44', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('396', '1', '4', '45', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('397', '1', '4', '46', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('398', '1', '4', '47', '1', '正常', '1', '40', '1', '1', null);
INSERT INTO `inspectitemrecord` VALUES ('399', '1', '4', '48', '1', '正常', '1', '40', '1', '1', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspecttable
-- ----------------------------
INSERT INTO `inspecttable` VALUES ('1', '机修人员点检表', '2014-05-29', '', '1');
INSERT INTO `inspecttable` VALUES ('2', '门机司机点检表', '2014-05-29', '', '1');
INSERT INTO `inspecttable` VALUES ('3', '门机队机械点检表', '2014-06-13', null, '1');

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
  `mongoId` varchar(255) DEFAULT NULL,
  `exceptionId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspecttablerecord
-- ----------------------------
INSERT INTO `inspecttablerecord` VALUES ('1', '1', '1', '2014-06-14', '0', '1', null, '1', '1');
INSERT INTO `inspecttablerecord` VALUES ('2', '1', '1', '2014-06-14', '3', '1', null, '1', '1');
INSERT INTO `inspecttablerecord` VALUES ('3', '3', '5', '2014-06-14', '5', '2', null, '3', '1');
INSERT INTO `inspecttablerecord` VALUES ('4', '3', '2', '2014-06-12', '3', '1', null, '1', '1');

-- ----------------------------
-- Table structure for `inspecttag`
-- ----------------------------
DROP TABLE IF EXISTS `inspecttag`;
CREATE TABLE `inspecttag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `number` varchar(20) DEFAULT NULL,
  `inspectAreaId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspecttag
-- ----------------------------
INSERT INTO `inspecttag` VALUES ('1', '行走区域', '1', '2014-05-30', '1', '1', '1', '1');
INSERT INTO `inspecttag` VALUES ('2', '转盘区域', 'sss', '2014-06-02', 'zp', '2', '1', '1');
INSERT INTO `inspecttag` VALUES ('3', '司机室区域', 'sss', '2014-06-02', 'sjs', '3', '1', '1');
INSERT INTO `inspecttag` VALUES ('4', '臂架区域', 'sss', '2014-06-02', 'bj', '4', '1', '1');

-- ----------------------------
-- Table structure for `inspecttask`
-- ----------------------------
DROP TABLE IF EXISTS `inspecttask`;
CREATE TABLE `inspecttask` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `inspectTableId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  `dayStart` date DEFAULT NULL,
  `dayEnd` date DEFAULT NULL,
  `timeStart` varchar(255) DEFAULT NULL,
  `timeEnd` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of inspecttask
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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

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
INSERT INTO `menu` VALUES ('30', '0', '报表管理', '1', '', '菜单');
INSERT INTO `menu` VALUES ('31', '30', '设备异常总数', '2', 'inspectReport/deviceCount.html', '报表管理');
INSERT INTO `menu` VALUES ('32', '30', '设备异常明细', '2', 'inspectReport/deviceInfo.html', '报表管理');
INSERT INTO `menu` VALUES ('33', '30', '人员点检异常总数', '2', 'inspectReport/peopleCount.html', '报表管理');
INSERT INTO `menu` VALUES ('34', '30', '人员点检异常明细', '2', 'inspectReport/peopleInfo.html', '报表管理');
INSERT INTO `menu` VALUES ('35', '30', '设备异常趋势分析', '2', 'inspectReport/deviceHistory.html', '报表管理');
INSERT INTO `menu` VALUES ('37', '0', '报表展示', '1', '', '菜单');
INSERT INTO `menu` VALUES ('38', '37', '报表展示', '2', 'inspectReport/reportShow.html', '报表展示');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'xiaozhujun', 'e10adc3949ba59abbe56e057f20f883e', '男', 'admin', '1', '启用');
INSERT INTO `user` VALUES ('2', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '男', 'ROLE_USER', '1', '启用');
INSERT INTO `user` VALUES ('3', 'sunhui', 'e68fa2bc61b75b8a06766e25905052c7', '男', 'ROLE_USER', '1', '启用');
INSERT INTO `user` VALUES ('4', 'liujinxia', 'c99c1cbefe13019978d90cb442cb8f78', '女', 'ROLE_ADMIN', '1', '启用');
INSERT INTO `user` VALUES ('5', 'xiaopeng', '202cb962ac59075b964b07152d234b70', '男', 'ROLE_ADMIN', '1', '启用');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_authority
-- ----------------------------
INSERT INTO `user_authority` VALUES ('1', '1', '1', 'xiaozhujun', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('2', '1', '2', 'xiaozhujun', 'ROLE_ADMIN');
INSERT INTO `user_authority` VALUES ('5', '2', '1', 'zhangsan', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('6', '3', '1', 'sunhui', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('7', '4', '2', 'liujinxia', 'ROLE_ADMIN');
INSERT INTO `user_authority` VALUES ('8', '5', '2', 'xiaopeng', 'ROLE_ADMIN');
