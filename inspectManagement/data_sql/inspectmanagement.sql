/*
Navicat MySQL Data Transfer

Source Server         : yy
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : inspectmanagement

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2014-09-17 14:47:41
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES ('1', '企业1', '1', '启用', '2014-05-28');
INSERT INTO `app` VALUES ('2', '深圳市洲智电子有限公司', '深圳市洲智电子有限公司\n', '启用', '2014-07-17');
INSERT INTO `app` VALUES ('3', '起重机定检测试', '起重机定检测试', '启用', '2014-07-21');

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
) ENGINE=InnoDB AUTO_INCREMENT=327 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_menu
-- ----------------------------
INSERT INTO `authority_menu` VALUES ('242', '2', 'ROLE_ADMIN', '1', '部门管理');
INSERT INTO `authority_menu` VALUES ('243', '2', 'ROLE_ADMIN', '2', '添加部门');
INSERT INTO `authority_menu` VALUES ('244', '2', 'ROLE_ADMIN', '6', '部门列表');
INSERT INTO `authority_menu` VALUES ('245', '2', 'ROLE_ADMIN', '7', '添加员工类型');
INSERT INTO `authority_menu` VALUES ('246', '2', 'ROLE_ADMIN', '8', '员工类型列表');
INSERT INTO `authority_menu` VALUES ('247', '2', 'ROLE_ADMIN', '9', '添加员工');
INSERT INTO `authority_menu` VALUES ('248', '2', 'ROLE_ADMIN', '10', '员工列表');
INSERT INTO `authority_menu` VALUES ('249', '2', 'ROLE_ADMIN', '3', '设备管理');
INSERT INTO `authority_menu` VALUES ('250', '2', 'ROLE_ADMIN', '11', '添加设备类型');
INSERT INTO `authority_menu` VALUES ('251', '2', 'ROLE_ADMIN', '12', '设备类型列表');
INSERT INTO `authority_menu` VALUES ('252', '2', 'ROLE_ADMIN', '13', '添加设备区域');
INSERT INTO `authority_menu` VALUES ('253', '2', 'ROLE_ADMIN', '14', '设备类型区域列表');
INSERT INTO `authority_menu` VALUES ('254', '2', 'ROLE_ADMIN', '15', '添加设备');
INSERT INTO `authority_menu` VALUES ('255', '2', 'ROLE_ADMIN', '16', '设备列表');
INSERT INTO `authority_menu` VALUES ('256', '2', 'ROLE_ADMIN', '17', '添加设备标签');
INSERT INTO `authority_menu` VALUES ('257', '2', 'ROLE_ADMIN', '18', '设备标签列表');
INSERT INTO `authority_menu` VALUES ('258', '2', 'ROLE_ADMIN', '4', '点检表管理');
INSERT INTO `authority_menu` VALUES ('259', '2', 'ROLE_ADMIN', '19', '添加点检表');
INSERT INTO `authority_menu` VALUES ('260', '2', 'ROLE_ADMIN', '20', '点检表列表');
INSERT INTO `authority_menu` VALUES ('261', '2', 'ROLE_ADMIN', '21', '添加点检项');
INSERT INTO `authority_menu` VALUES ('262', '2', 'ROLE_ADMIN', '22', '点检项列表');
INSERT INTO `authority_menu` VALUES ('263', '2', 'ROLE_ADMIN', '23', '点检选值添加');
INSERT INTO `authority_menu` VALUES ('264', '2', 'ROLE_ADMIN', '24', '点检选值列表');
INSERT INTO `authority_menu` VALUES ('265', '2', 'ROLE_ADMIN', '5', '数据管理');
INSERT INTO `authority_menu` VALUES ('266', '2', 'ROLE_ADMIN', '25', '点检结果上传');
INSERT INTO `authority_menu` VALUES ('267', '2', 'ROLE_ADMIN', '26', '人员配置查询');
INSERT INTO `authority_menu` VALUES ('268', '2', 'ROLE_ADMIN', '27', '设备配置查询');
INSERT INTO `authority_menu` VALUES ('269', '2', 'ROLE_ADMIN', '28', '点检表下载');
INSERT INTO `authority_menu` VALUES ('270', '2', 'ROLE_ADMIN', '29', '人员与点检项目表下载');
INSERT INTO `authority_menu` VALUES ('271', '2', 'ROLE_ADMIN', '30', '数据分析');
INSERT INTO `authority_menu` VALUES ('272', '2', 'ROLE_ADMIN', '31', '设备异常总数');
INSERT INTO `authority_menu` VALUES ('273', '2', 'ROLE_ADMIN', '32', '设备异常明细');
INSERT INTO `authority_menu` VALUES ('274', '2', 'ROLE_ADMIN', '33', '人员点检异常总数');
INSERT INTO `authority_menu` VALUES ('275', '2', 'ROLE_ADMIN', '34', '人员点检异常明细');
INSERT INTO `authority_menu` VALUES ('276', '2', 'ROLE_ADMIN', '35', '设备异常趋势分析');
INSERT INTO `authority_menu` VALUES ('277', '2', 'ROLE_ADMIN', '37', '报表展示');
INSERT INTO `authority_menu` VALUES ('278', '2', 'ROLE_ADMIN', '38', '报表展示');
INSERT INTO `authority_menu` VALUES ('279', '2', 'ROLE_ADMIN', '40', '任务管理');
INSERT INTO `authority_menu` VALUES ('280', '2', 'ROLE_ADMIN', '41', '添加计划');
INSERT INTO `authority_menu` VALUES ('281', '2', 'ROLE_ADMIN', '42', '计划列表');
INSERT INTO `authority_menu` VALUES ('282', '2', 'ROLE_ADMIN', '43', '任务派发');
INSERT INTO `authority_menu` VALUES ('283', '1', 'ROLE_USER', '1', '部门管理');
INSERT INTO `authority_menu` VALUES ('284', '1', 'ROLE_USER', '2', '添加部门');
INSERT INTO `authority_menu` VALUES ('285', '1', 'ROLE_USER', '6', '部门列表');
INSERT INTO `authority_menu` VALUES ('286', '1', 'ROLE_USER', '7', '添加员工类型');
INSERT INTO `authority_menu` VALUES ('287', '1', 'ROLE_USER', '8', '员工类型列表');
INSERT INTO `authority_menu` VALUES ('288', '1', 'ROLE_USER', '9', '添加员工');
INSERT INTO `authority_menu` VALUES ('289', '1', 'ROLE_USER', '10', '员工列表');
INSERT INTO `authority_menu` VALUES ('290', '1', 'ROLE_USER', '3', '设备管理');
INSERT INTO `authority_menu` VALUES ('291', '1', 'ROLE_USER', '11', '添加设备类型');
INSERT INTO `authority_menu` VALUES ('292', '1', 'ROLE_USER', '12', '设备类型列表');
INSERT INTO `authority_menu` VALUES ('293', '1', 'ROLE_USER', '13', '添加设备区域');
INSERT INTO `authority_menu` VALUES ('294', '1', 'ROLE_USER', '14', '设备类型区域列表');
INSERT INTO `authority_menu` VALUES ('295', '1', 'ROLE_USER', '15', '添加设备');
INSERT INTO `authority_menu` VALUES ('296', '1', 'ROLE_USER', '16', '设备列表');
INSERT INTO `authority_menu` VALUES ('297', '1', 'ROLE_USER', '17', '添加设备标签');
INSERT INTO `authority_menu` VALUES ('298', '1', 'ROLE_USER', '18', '设备标签列表');
INSERT INTO `authority_menu` VALUES ('299', '1', 'ROLE_USER', '4', '点检表管理');
INSERT INTO `authority_menu` VALUES ('300', '1', 'ROLE_USER', '19', '添加点检表');
INSERT INTO `authority_menu` VALUES ('301', '1', 'ROLE_USER', '20', '点检表列表');
INSERT INTO `authority_menu` VALUES ('302', '1', 'ROLE_USER', '21', '添加点检项');
INSERT INTO `authority_menu` VALUES ('303', '1', 'ROLE_USER', '22', '点检项列表');
INSERT INTO `authority_menu` VALUES ('304', '1', 'ROLE_USER', '23', '点检选值添加');
INSERT INTO `authority_menu` VALUES ('305', '1', 'ROLE_USER', '24', '点检选值列表');
INSERT INTO `authority_menu` VALUES ('306', '1', 'ROLE_USER', '5', '数据管理');
INSERT INTO `authority_menu` VALUES ('307', '1', 'ROLE_USER', '25', '点检结果上传');
INSERT INTO `authority_menu` VALUES ('308', '1', 'ROLE_USER', '26', '人员配置查询');
INSERT INTO `authority_menu` VALUES ('309', '1', 'ROLE_USER', '27', '设备配置查询');
INSERT INTO `authority_menu` VALUES ('310', '1', 'ROLE_USER', '28', '点检表下载');
INSERT INTO `authority_menu` VALUES ('311', '1', 'ROLE_USER', '29', '人员与点检项目表下载');
INSERT INTO `authority_menu` VALUES ('312', '1', 'ROLE_USER', '30', '数据分析');
INSERT INTO `authority_menu` VALUES ('313', '1', 'ROLE_USER', '31', '设备异常总数');
INSERT INTO `authority_menu` VALUES ('314', '1', 'ROLE_USER', '32', '设备异常明细');
INSERT INTO `authority_menu` VALUES ('315', '1', 'ROLE_USER', '33', '人员点检异常总数');
INSERT INTO `authority_menu` VALUES ('316', '1', 'ROLE_USER', '34', '人员点检异常明细');
INSERT INTO `authority_menu` VALUES ('317', '1', 'ROLE_USER', '35', '设备异常趋势分析');
INSERT INTO `authority_menu` VALUES ('318', '1', 'ROLE_USER', '37', '报表展示');
INSERT INTO `authority_menu` VALUES ('319', '1', 'ROLE_USER', '38', '报表展示');
INSERT INTO `authority_menu` VALUES ('320', '1', 'ROLE_USER', '40', '任务管理');
INSERT INTO `authority_menu` VALUES ('321', '1', 'ROLE_USER', '41', '添加计划');
INSERT INTO `authority_menu` VALUES ('322', '1', 'ROLE_USER', '42', '计划列表');
INSERT INTO `authority_menu` VALUES ('323', '1', 'ROLE_USER', '43', '任务派发');
INSERT INTO `authority_menu` VALUES ('324', '1', 'ROLE_ADMIN', '44', '图片上传测试');
INSERT INTO `authority_menu` VALUES ('325', '1', 'ROLE_USER', '44', '图片上传测试');
INSERT INTO `authority_menu` VALUES ('326', '1', 'ROLE_ADMIN', '45', '获取版本');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_power
-- ----------------------------
INSERT INTO `authority_power` VALUES ('4', '2', '1', '/rs/**', 'ROLE_ADMIN');
INSERT INTO `authority_power` VALUES ('5', '2', '2', '/user.html', 'ROLE_ADMIN');
INSERT INTO `authority_power` VALUES ('6', '2', '3', '/admin.html', 'ROLE_ADMIN');
INSERT INTO `authority_power` VALUES ('7', '2', '4', '/index.html', 'ROLE_ADMIN');
INSERT INTO `authority_power` VALUES ('8', '2', '15', 'cas/**', 'ROLE_ADMIN');
INSERT INTO `authority_power` VALUES ('9', '1', '1', '/rs/**', 'ROLE_USER');
INSERT INTO `authority_power` VALUES ('10', '1', '4', '/index.html', 'ROLE_USER');
INSERT INTO `authority_power` VALUES ('11', '1', '16', '/index.jsp', 'ROLE_USER');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '1', '1', '2014-05-19', '启用', '1');
INSERT INTO `department` VALUES ('2', '设备部', '设备部描述', '2014-06-21', '启用', '1');
INSERT INTO `department` VALUES ('3', '检验部', '', '2014-06-21', '启用', '1');
INSERT INTO `department` VALUES ('4', '检查部门', '检查部门', '2014-07-21', '启用', '3');
INSERT INTO `department` VALUES ('5', 'sdcfaewcf', 'dsd', '2014-08-23', '启用', '1');

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
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('13', '门座式起重机#01', 'menzuo001', '门座式起重机#01', '1', '11', '/inspectManagementResource/deviceImage/1/13.jpg');
INSERT INTO `device` VALUES ('15', '门座式起重机#03', 'menzuo003', '门座式起重机#03', '1', '11', null);
INSERT INTO `device` VALUES ('16', '门座式起重机#04', 'menzuo004', '门座式起重机#04', '1', '11', null);
INSERT INTO `device` VALUES ('17', '轮胎式集装箱门式起重机#01', 'luntai001', '轮胎式集装箱门式起重机#01', '1', '12', '/inspectManagementResource/deviceImage/1/17.jpg');
INSERT INTO `device` VALUES ('18', '轮胎式集装箱门式起重机#02', 'luntai002', '轮胎式集装箱门式起重机#02', '1', '12', null);
INSERT INTO `device` VALUES ('19', '轮胎式集装箱门式起重机#03', 'luntai003', '轮胎式集装箱门式起重机#03', '1', '12', null);
INSERT INTO `device` VALUES ('20', '岸边式集装箱起重机#01', 'anbian001', '岸边式集装箱起重机#01', '1', '13', null);
INSERT INTO `device` VALUES ('21', '流动式起重机01', 'C_LDSQZJ01', '测试流动式起重机械', '1', '14', null);
INSERT INTO `device` VALUES ('22', '测试用门式起重机', 'CSYMSQZJ001', '', '3', '18', null);
INSERT INTO `device` VALUES ('23', 'dvz', 'v', 'zdsv', '1', '11', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of devicetype
-- ----------------------------
INSERT INTO `devicetype` VALUES ('11', '门座式起重机', 'menzuo001', '门座式起重机', '1');
INSERT INTO `devicetype` VALUES ('12', '轮胎式集装箱门式起重机', 'luntai001', '轮胎式集装箱门式起重机', '1');
INSERT INTO `devicetype` VALUES ('13', '岸边式集装箱起重机', 'anbian001', '岸边式集装箱起重机', '1');
INSERT INTO `devicetype` VALUES ('14', '流动式起重机械', 'LDSQZJX', '流动式起重机械', '1');
INSERT INTO `devicetype` VALUES ('15', '客、载货梯', 'KHT', '客、载货梯', '2');
INSERT INTO `devicetype` VALUES ('18', '门座式起重机', 'MZSQZJ', '门座式起重机', '3');
INSERT INTO `devicetype` VALUES ('20', '测试2', '2', '222', '1');

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
  `tel` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('4', '赵伟', '123456', '男', '机修人员', '启用', '1', '2', '6', '15527370289');
INSERT INTO `employee` VALUES ('5', '王福明', '123456', '男', '门机司机', '启用', '1', '2', '7', '15527370289');
INSERT INTO `employee` VALUES ('6', '常建', '123456', '男', '机械技术员', '启用', '1', '2', '8', '15527370289');
INSERT INTO `employee` VALUES ('7', '庞伟', '123456', '男', '电气技术员', '启用', '1', '3', '9', '15527370289');
INSERT INTO `employee` VALUES ('8', '秦小娟', '123456', '女', '电气技术员', '启用', '1', '3', '10', '15527370289');
INSERT INTO `employee` VALUES ('9', '孙伟', '111', '男', '定保员', '启用', '1', '3', '11', '15527370289');
INSERT INTO `employee` VALUES ('10', '晋中', '123456', '男', '减速机点检员', '启用', '1', '3', '12', '15527370289');
INSERT INTO `employee` VALUES ('11', '测试刘', '123456', '男', 'LDS01', '启用', '1', '2', '15', '15527370289');
INSERT INTO `employee` VALUES ('12', '测试刘B', '654321', '男', 'LDS02', '启用', '1', '3', '16', '15527370289');
INSERT INTO `employee` VALUES ('13', 'manager', '123456', '男', '点检管理员', '启用', '1', '2', '17', '15527370289');
INSERT INTO `employee` VALUES ('14', '刘点检', '1', '男', '点检员', '启用', '3', '4', '20', '15527370289');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employeerole
-- ----------------------------
INSERT INTO `employeerole` VALUES ('1', '机修人员', '', '启用', '1', '2');
INSERT INTO `employeerole` VALUES ('2', '门机司机', '', '启用', '1', '2');
INSERT INTO `employeerole` VALUES ('3', '机械技术员', '机械技术员描述', '启用', '1', '1');
INSERT INTO `employeerole` VALUES ('4', '电气技术员', '电气技术员', '启用', '1', '1');
INSERT INTO `employeerole` VALUES ('5', '定保员', '定保员', '启用', '1', '1');
INSERT INTO `employeerole` VALUES ('6', '减速机点检员', '减速机点检员', '启用', '1', '1');
INSERT INTO `employeerole` VALUES ('7', 'LDS01', '', '启用', '1', '1');
INSERT INTO `employeerole` VALUES ('8', 'LDS02', '', '启用', '1', '2');
INSERT INTO `employeerole` VALUES ('9', '点检管理员', '', '启用', '1', '1');
INSERT INTO `employeerole` VALUES ('10', '点检员', '点检员', '启用', '3', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employeerole_inspecttable
-- ----------------------------
INSERT INTO `employeerole_inspecttable` VALUES ('1', '1', '机修人员', '1', '机修人员点检表', '1');
INSERT INTO `employeerole_inspecttable` VALUES ('2', '2', '门机司机', '2', '门机司机点检表', '1');
INSERT INTO `employeerole_inspecttable` VALUES ('3', '3', '机械技术员', '3', '门机队机械技术员点检表', '1');
INSERT INTO `employeerole_inspecttable` VALUES ('4', '4', '电气技术员', '4', '门机技术员电气日常点检表', '1');
INSERT INTO `employeerole_inspecttable` VALUES ('5', '5', '定保员', '6', '门机周一定保专项点检卡片', '1');
INSERT INTO `employeerole_inspecttable` VALUES ('6', '6', '减速机点检员', '5', '门机减速机专项点检卡', '1');
INSERT INTO `employeerole_inspecttable` VALUES ('7', '7', 'LDS01', '7', '流动式起重机械定期检查表', '1');
INSERT INTO `employeerole_inspecttable` VALUES ('8', '8', 'LDS02', '7', '流动式起重机械定期检查表', '1');
INSERT INTO `employeerole_inspecttable` VALUES ('9', '9', '点检管理员', '1', '机修人员点检表', '1');
INSERT INTO `employeerole_inspecttable` VALUES ('10', '9', '点检管理员', '2', '门机司机日常点检表', '1');
INSERT INTO `employeerole_inspecttable` VALUES ('11', '9', '点检管理员', '3', '门机队机械技术员点检表', '1');
INSERT INTO `employeerole_inspecttable` VALUES ('12', '9', '点检管理员', '4', '门机技术员电气日常点检表', '1');
INSERT INTO `employeerole_inspecttable` VALUES ('13', '9', '点检管理员', '5', '门机减速机专项点检卡', '1');
INSERT INTO `employeerole_inspecttable` VALUES ('14', '9', '点检管理员', '6', '门机周一定保专项点检卡片', '1');
INSERT INTO `employeerole_inspecttable` VALUES ('15', '10', '点检员', '12', '门座式起重机每班检查表', '3');
INSERT INTO `employeerole_inspecttable` VALUES ('16', '10', '点检员', '13', '门座式起重机每月检查表', '3');
INSERT INTO `employeerole_inspecttable` VALUES ('17', '10', '点检员', '14', '门座式起重机每季度检查表', '3');
INSERT INTO `employeerole_inspecttable` VALUES ('18', '10', '点检员', '15', '门座式起重机每年检查表', '3');

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_employeerole
-- ----------------------------
INSERT INTO `employee_employeerole` VALUES ('3', '4', '赵伟', '1', '机修人员', '1');
INSERT INTO `employee_employeerole` VALUES ('4', '5', '王福明', '2', '门机司机', '1');
INSERT INTO `employee_employeerole` VALUES ('5', '6', '常建', '3', '机械技术员', '1');
INSERT INTO `employee_employeerole` VALUES ('6', '7', '庞伟', '4', '电气技术员', '1');
INSERT INTO `employee_employeerole` VALUES ('7', '8', '秦小娟', '4', '电气技术员', '1');
INSERT INTO `employee_employeerole` VALUES ('8', '9', '孙伟', '5', '定保员', '1');
INSERT INTO `employee_employeerole` VALUES ('9', '10', '晋中', '6', '减速机点检员', '1');
INSERT INTO `employee_employeerole` VALUES ('10', '11', '测试刘', '7', 'LDS01', '1');
INSERT INTO `employee_employeerole` VALUES ('11', '12', '测试刘B', '8', 'LDS02', '1');
INSERT INTO `employee_employeerole` VALUES ('12', '13', 'manager', '9', '点检管理员', '1');
INSERT INTO `employee_employeerole` VALUES ('13', '14', '刘点检', '10', '点检员', '3');

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
-- Table structure for `image_upload`
-- ----------------------------
DROP TABLE IF EXISTS `image_upload`;
CREATE TABLE `image_upload` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tableRecordId` bigint(20) NOT NULL,
  `itemRecordId` bigint(20) NOT NULL,
  `itemId` bigint(20) NOT NULL,
  `createTime` datetime NOT NULL,
  `appId` bigint(20) NOT NULL,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image_upload
-- ----------------------------
INSERT INTO `image_upload` VALUES ('16', '85', '2897', '23', '2014-09-02 09:53:24', '1', '/inspectManagementResource/inspectExceptionImage/b.jpg');
INSERT INTO `image_upload` VALUES ('17', '1', '2054', '1', '2014-09-16 16:09:05', '1', '/inspectManagementResource/inspectExceptionImage/1_QQå¾ç20140916090004.jpg');
INSERT INTO `image_upload` VALUES ('18', '6667', '44', '2222', '2014-09-16 18:34:43', '1', '/inspectManagementResource/inspectExceptionImage/6667_QQå¾ç20140916090004.jpg');

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectarea
-- ----------------------------
INSERT INTO `inspectarea` VALUES ('5', '行走区域', '行走区域', '2014-06-23', 'xingzou001', '11', '1');
INSERT INTO `inspectarea` VALUES ('6', '转盘区域', '转盘区域', '2014-06-23', 'zhuanpan001', '11', '1');
INSERT INTO `inspectarea` VALUES ('7', '司机室区域', '司机室区域', '2014-06-23', 'sijishi001', '11', '1');
INSERT INTO `inspectarea` VALUES ('8', '臂架区域', '臂架区域', '2014-06-23', 'bijia001', '11', '1');
INSERT INTO `inspectarea` VALUES ('9', '金属结构', '所有金属结构', '2014-06-30', 'LDS001', '14', '1');
INSERT INTO `inspectarea` VALUES ('10', '主要零部件', '主要部件', '2014-06-30', 'LDS002', '14', '1');
INSERT INTO `inspectarea` VALUES ('11', '安全装置', '安全装置', '2014-06-30', 'LDS003', '14', '1');
INSERT INTO `inspectarea` VALUES ('12', '液压、电气系统', '液压系统和电气系统', '2014-06-30', 'LDS004', '14', '1');
INSERT INTO `inspectarea` VALUES ('13', '机房', '机房', '2014-07-17', 'JF', '15', '2');
INSERT INTO `inspectarea` VALUES ('14', '底坑', '底坑', '2014-07-17', 'DK', '15', '2');
INSERT INTO `inspectarea` VALUES ('15', '轿厢', '轿厢', '2014-07-17', 'JX', '15', '2');
INSERT INTO `inspectarea` VALUES ('16', '井道', '井道', '2014-07-17', 'JD', '15', '2');
INSERT INTO `inspectarea` VALUES ('17', '层门', '层门', '2014-07-17', 'CM', '15', '2');
INSERT INTO `inspectarea` VALUES ('18', '行走区域', '0', '2014-07-19', '00000', '14', '1');
INSERT INTO `inspectarea` VALUES ('19', '运行机构', '运行机构', '2014-07-21', '01', '18', '3');
INSERT INTO `inspectarea` VALUES ('20', '起升机构', '起升机构', '2014-07-21', '02', '18', '3');
INSERT INTO `inspectarea` VALUES ('21', '变幅机构', '变幅机构', '2014-07-21', '03', '18', '3');
INSERT INTO `inspectarea` VALUES ('22', '回转机构', '回转机构', '2014-07-21', '04', '18', '3');
INSERT INTO `inspectarea` VALUES ('23', '金属结构', '金属结构', '2014-07-21', '05', '18', '3');
INSERT INTO `inspectarea` VALUES ('25', '电气系统', '电气系统', '2014-07-21', '06', '18', '3');
INSERT INTO `inspectarea` VALUES ('27', '其他', '其他', '2014-07-21', '07', '18', '3');

-- ----------------------------
-- Table structure for `inspectchoice`
-- ----------------------------
DROP TABLE IF EXISTS `inspectchoice`;
CREATE TABLE `inspectchoice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `choiceValue` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectchoice
-- ----------------------------
INSERT INTO `inspectchoice` VALUES ('1', '正常', '1');
INSERT INTO `inspectchoice` VALUES ('2', '异常', '1');
INSERT INTO `inspectchoice` VALUES ('3', '无', '1');
INSERT INTO `inspectchoice` VALUES ('4', '正常', '2');
INSERT INTO `inspectchoice` VALUES ('5', '异常', '2');
INSERT INTO `inspectchoice` VALUES ('6', '正常', '3');
INSERT INTO `inspectchoice` VALUES ('7', '异常', '3');
INSERT INTO `inspectchoice` VALUES ('8', 'vzdn', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=1084 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectitem
-- ----------------------------
INSERT INTO `inspectitem` VALUES ('23', '行走台车', '行走台车', '2014-06-23', '1', '5', '004', '0', '1');
INSERT INTO `inspectitem` VALUES ('24', '驱动装置', '', '2014-06-23', '1', '5', '005', '0', '1');
INSERT INTO `inspectitem` VALUES ('25', '行走轮', '', '2014-06-23', '1', '6', '006', '0', '1');
INSERT INTO `inspectitem` VALUES ('26', '极限开关', '极限开关', '2014-06-23', '1', '5', '007', '0', '1');
INSERT INTO `inspectitem` VALUES ('27', '报警装置', '', '2014-06-23', '1', '5', '008', '0', '1');
INSERT INTO `inspectitem` VALUES ('28', '钢丝绳绳端', '', '2014-06-23', '1', '5', '009', '0', '1');
INSERT INTO `inspectitem` VALUES ('29', '吊钩', '', '2014-06-23', '1', '5', '010', '0', '1');
INSERT INTO `inspectitem` VALUES ('30', '抓斗', '', '2014-06-23', '1', '5', '011', '0', '1');
INSERT INTO `inspectitem` VALUES ('31', '绞点', '', '2014-06-23', '1', '5', '012', '0', '1');
INSERT INTO `inspectitem` VALUES ('32', '栏杆及踏板', '', '2014-06-23', '1', '5', '013', '0', '1');
INSERT INTO `inspectitem` VALUES ('33', '钢结构', '', '2014-06-23', '1', '5', '014', '0', '1');
INSERT INTO `inspectitem` VALUES ('34', '梯子栏杆', '', '2014-06-23', '1', '5', '015', '0', '1');
INSERT INTO `inspectitem` VALUES ('35', '齿轮齿圈', '', '2014-06-23', '1', '6', 'z001', '0', '1');
INSERT INTO `inspectitem` VALUES ('36', '旋转大轴承', '', '2014-06-23', '1', '6', 'z002', '0', '1');
INSERT INTO `inspectitem` VALUES ('37', '极限联轴器', '', '2014-06-23', '1', '6', 'z003', '0', '1');
INSERT INTO `inspectitem` VALUES ('38', '电机及风机', '', '2014-06-23', '1', '6', 'z004', '0', '1');
INSERT INTO `inspectitem` VALUES ('39', '绞点', '', '2014-06-23', '1', '6', 'z005', '0', '1');
INSERT INTO `inspectitem` VALUES ('40', '栏杆及踏板', '', '2014-06-23', '1', '6', 'z006', '0', '1');
INSERT INTO `inspectitem` VALUES ('41', '钢结构', '', '2014-06-23', '1', '6', 'z007', '0', '1');
INSERT INTO `inspectitem` VALUES ('42', '梯子栏杆', '', '2014-06-23', '1', '6', 'z008', '0', '1');
INSERT INTO `inspectitem` VALUES ('43', '减速箱', '', '2014-06-23', '1', '7', 's001', '0', '1');
INSERT INTO `inspectitem` VALUES ('44', '旋转制动器', '', '2014-06-23', '1', '7', 's002', '0', '1');
INSERT INTO `inspectitem` VALUES ('45', '旋转锚定', '', '2014-06-23', '1', '7', 's003', '0', '1');
INSERT INTO `inspectitem` VALUES ('46', '钢丝绳', '', '2014-06-23', '1', '7', 's004', '0', '1');
INSERT INTO `inspectitem` VALUES ('47', '卷筒', '', '2014-06-23', '1', '7', 's005', '0', '1');
INSERT INTO `inspectitem` VALUES ('48', '减速箱', '', '2014-06-23', '1', '7', 's006', '0', '1');
INSERT INTO `inspectitem` VALUES ('49', '联轴器', '', '2014-06-23', '1', '7', 's007', '0', '1');
INSERT INTO `inspectitem` VALUES ('50', '制动器', '', '2014-06-23', '1', '7', 's008', '0', '1');
INSERT INTO `inspectitem` VALUES ('51', '电机及风机', '', '2014-06-23', '1', '7', 's009', '0', '1');
INSERT INTO `inspectitem` VALUES ('52', '绞点', '', '2014-06-23', '1', '7', 's010', '0', '1');
INSERT INTO `inspectitem` VALUES ('53', '栏杆及踏板', '', '2014-06-23', '1', '7', 's011', '0', '1');
INSERT INTO `inspectitem` VALUES ('54', '钢结构', '', '2014-06-23', '1', '7', 's012', '0', '1');
INSERT INTO `inspectitem` VALUES ('55', '梯子栏杆', '', '2014-06-23', '1', '7', 's013', '0', '1');
INSERT INTO `inspectitem` VALUES ('56', '钢丝绳', '', '2014-06-23', '1', '8', 'b001', '0', '1');
INSERT INTO `inspectitem` VALUES ('57', '滑轮', '', '2014-06-23', '1', '8', 'b002', '0', '1');
INSERT INTO `inspectitem` VALUES ('58', '齿轮齿条', '', '2014-06-23', '1', '8', 'b003', '0', '1');
INSERT INTO `inspectitem` VALUES ('59', '减速箱', '', '2014-06-23', '1', '8', 'b004', '0', '1');
INSERT INTO `inspectitem` VALUES ('60', '联轴器', '', '2014-06-23', '1', '8', 'b005', '0', '1');
INSERT INTO `inspectitem` VALUES ('61', '制动器', '', '2014-06-23', '1', '8', 'b006', '0', '1');
INSERT INTO `inspectitem` VALUES ('62', '电机及风机', '', '2014-06-23', '1', '8', 'b007', '0', '1');
INSERT INTO `inspectitem` VALUES ('63', '绞点', '', '2014-06-23', '1', '8', 'b008', '0', '1');
INSERT INTO `inspectitem` VALUES ('64', '栏杆及踏板', '', '2014-06-23', '1', '8', 'b009', '0', '1');
INSERT INTO `inspectitem` VALUES ('65', '钢结构', '', '2014-06-23', '1', '8', 'b010', '0', '1');
INSERT INTO `inspectitem` VALUES ('66', '梯子栏杆', '', '2014-06-23', '1', '8', 'b011', '0', '1');
INSERT INTO `inspectitem` VALUES ('67', '手动铁鞋、锚定', '', '2014-06-26', '2', '5', 'sd001', '0', '1');
INSERT INTO `inspectitem` VALUES ('70', '行走台车', '', '2014-06-26', '2', '5', 'xz001', '0', '1');
INSERT INTO `inspectitem` VALUES ('73', '电动铁鞋', '', '2014-06-26', '2', '5', 'dd001', '0', '1');
INSERT INTO `inspectitem` VALUES ('76', '行走防撞缓冲器', '', '2014-06-26', '2', '5', 'fz001', '0', '1');
INSERT INTO `inspectitem` VALUES ('79', '行走电机护栏', '', '2014-06-26', '2', '5', 'xzd001', '0', '1');
INSERT INTO `inspectitem` VALUES ('82', '行走减速箱', '', '2014-06-26', '2', '5', 'xz001', '0', '1');
INSERT INTO `inspectitem` VALUES ('85', '行走开式齿轮', '', '2014-06-26', '2', '5', 'xingzouk001', '0', '1');
INSERT INTO `inspectitem` VALUES ('88', '抓斗', '', '2014-06-26', '2', '5', 'zhuadou001', '0', '1');
INSERT INTO `inspectitem` VALUES ('91', '钢丝绳及卸扣', '', '2014-06-26', '2', '5', 'gang001', '0', '1');
INSERT INTO `inspectitem` VALUES ('94', '各主要绞点', '', '2014-06-26', '2', '5', 'jie001', '0', '1');
INSERT INTO `inspectitem` VALUES ('97', '梯子及栏杆', '', '2014-06-26', '2', '5', 'tizi001', '0', '1');
INSERT INTO `inspectitem` VALUES ('100', '高压电缆及电缆坑', '', '2014-06-26', '2', '5', 'gaoya001', '0', '1');
INSERT INTO `inspectitem` VALUES ('103', '行走声光报警', '', '2014-06-26', '2', '5', 'xing001', '0', '1');
INSERT INTO `inspectitem` VALUES ('106', '旋转齿轮、齿圈', '', '2014-06-26', '2', '6', 'xuanzhuan001', '0', '1');
INSERT INTO `inspectitem` VALUES ('109', '主要绞点', '', '2014-06-26', '2', '6', 'z001', '0', '1');
INSERT INTO `inspectitem` VALUES ('112', '梯子及栏杆', '', '2014-06-26', '2', '6', 't001', '0', '1');
INSERT INTO `inspectitem` VALUES ('115', '机上照光灯', '', '2014-06-26', '2', '6', 'j001', '0', '1');
INSERT INTO `inspectitem` VALUES ('118', '旋转制动器', '', '2014-06-26', '2', '7', 'z001', '0', '1');
INSERT INTO `inspectitem` VALUES ('121', '旋转减速器', '', '2014-06-26', '2', '7', 'x001', '0', '1');
INSERT INTO `inspectitem` VALUES ('124', '旋转电机、风机', '', '2014-06-26', '2', '7', 'xz001', '0', '1');
INSERT INTO `inspectitem` VALUES ('127', '起升制动器', '', '2014-06-26', '2', '7', 'qs001', '0', '1');
INSERT INTO `inspectitem` VALUES ('130', '卷筒及钢丝绳', '', '2014-06-26', '2', '7', 'jt001', '0', '1');
INSERT INTO `inspectitem` VALUES ('133', '起升减速箱', '', '2014-06-26', '2', '7', 'qsj001', '0', '1');
INSERT INTO `inspectitem` VALUES ('136', '起升电机、风机', '', '2014-06-26', '2', '7', 'qsd001', '0', '1');
INSERT INTO `inspectitem` VALUES ('139', '起升钢丝绳', '', '2014-06-26', '2', '7', 'qshengang', '0', '1');
INSERT INTO `inspectitem` VALUES ('142', '集中滑油', '', '2014-06-26', '2', '7', 'jz001', '0', '1');
INSERT INTO `inspectitem` VALUES ('145', '附属工具', '', '2014-06-26', '2', '7', 'fshu001', '0', '1');
INSERT INTO `inspectitem` VALUES ('148', '梯子及栏杆', '', '2014-06-26', '2', '7', 'tz001', '0', '1');
INSERT INTO `inspectitem` VALUES ('151', '机房司机室卫生', '', '2014-06-26', '2', '7', 'jf001', '0', '1');
INSERT INTO `inspectitem` VALUES ('154', '支持、开闭上升限位', '', '2014-06-26', '2', '7', 'sx001', '0', '1');
INSERT INTO `inspectitem` VALUES ('157', '联动台指示灯', '', '2014-06-26', '2', '7', 'ld001', '0', '1');
INSERT INTO `inspectitem` VALUES ('160', '电气室空调', '', '2014-06-26', '2', '7', 'dianqi001', '0', '1');
INSERT INTO `inspectitem` VALUES ('163', '机上照光灯', '', '2014-06-26', '2', '7', 'jis001', '0', '1');
INSERT INTO `inspectitem` VALUES ('166', '卸扣 右支持', '', '2014-06-26', '2', '7', 'xiekou001', '0', '1');
INSERT INTO `inspectitem` VALUES ('169', '各主要绞点', '', '2014-06-26', '2', '7', 'zhu001', '0', '1');
INSERT INTO `inspectitem` VALUES ('172', '机房顶出绳口', '', '2014-06-26', '2', '8', 'jifang001', '0', '1');
INSERT INTO `inspectitem` VALUES ('175', '变幅电机、风机', '', '2014-06-26', '2', '8', 'bianfu001', '0', '1');
INSERT INTO `inspectitem` VALUES ('178', '变幅制动器', '', '2014-06-26', '2', '8', 'bianfuzhi001', '0', '1');
INSERT INTO `inspectitem` VALUES ('181', '变幅减速器', '', '2014-06-26', '2', '8', 'bianfujian001', '0', '1');
INSERT INTO `inspectitem` VALUES ('184', '变幅齿轮、齿条、压轮', '', '2014-06-26', '2', '8', 'chi001', '0', '1');
INSERT INTO `inspectitem` VALUES ('187', '变幅联轴节', '', '2014-06-26', '2', '8', 'bianfu001', '0', '1');
INSERT INTO `inspectitem` VALUES ('190', 'S轮处', '', '2014-06-26', '2', '8', 's001', '0', '1');
INSERT INTO `inspectitem` VALUES ('193', '变幅增幅、减幅限位', '', '2014-06-26', '2', '8', 'bianfu001', '0', '1');
INSERT INTO `inspectitem` VALUES ('196', '机上照光灯', '', '2014-06-26', '2', '8', 'jishang001', '0', '1');
INSERT INTO `inspectitem` VALUES ('199', '合斗时无异响，斗口无变形', '', '2014-06-26', '3', '5', 'he001', '0', '1');
INSERT INTO `inspectitem` VALUES ('202', '平衡梁、平衡块平衡良好，抓斗斗体无变形', '', '2014-06-26', '3', '5', 'ping001', '0', '1');
INSERT INTO `inspectitem` VALUES ('205', '钢丝绳头连接处铝套、重型套环、卸扣无变形', '', '2014-06-26', '3', '5', 'gang001', '0', '1');
INSERT INTO `inspectitem` VALUES ('208', '检查钢丝绳无断丝，楔头处无断丝', '', '2014-06-26', '3', '5', 'jian001', '0', '1');
INSERT INTO `inspectitem` VALUES ('211', '钢丝绳无断丝，绳头检查同上，钩头无变形', '', '2014-06-26', '3', '5', 'gangs001', '0', '1');
INSERT INTO `inspectitem` VALUES ('214', '电缆卷盘无变形，行走时可靠卷放缆', '', '2014-06-26', '3', '5', 'dian001', '0', '1');
INSERT INTO `inspectitem` VALUES ('217', '行走电机风机无变形，无松动', '', '2014-06-26', '3', '5', 'xing001', '0', '1');
INSERT INTO `inspectitem` VALUES ('220', '行走电机、惯性制动器无异响', '', '2014-06-26', '3', '5', 'xingzou001', '0', '1');
INSERT INTO `inspectitem` VALUES ('223', '行走减速箱无漏油', '', '2014-06-26', '3', '5', 'xj001', '0', '1');
INSERT INTO `inspectitem` VALUES ('226', '行走开式齿轮无缺油', '', '2014-06-26', '3', '5', 'xk001', '0', '1');
INSERT INTO `inspectitem` VALUES ('229', '电动防炮器动作可靠', '', '2014-06-26', '3', '5', 'do001', '0', '1');
INSERT INTO `inspectitem` VALUES ('232', '手动铁楔无丢失，防风拉锁无锈蚀', '', '2014-06-26', '3', '5', 'shoudong001', '0', '1');
INSERT INTO `inspectitem` VALUES ('235', '防撞缓冲器无变形及损坏', '', '2014-06-26', '3', '5', 'fangchuang001', '0', '1');
INSERT INTO `inspectitem` VALUES ('238', '手动锚定能可靠锚定', '', '2014-06-26', '3', '5', 'shoud001', '0', '1');
INSERT INTO `inspectitem` VALUES ('241', '电气箱无碰撞变形', '', '2014-06-26', '3', '5', 'diaoqi001', '0', '1');
INSERT INTO `inspectitem` VALUES ('244', '行走电机风机无变形、无松动', '', '2014-06-26', '3', '5', 'xingzou001', '0', '1');
INSERT INTO `inspectitem` VALUES ('247', '行走减速箱无漏油', '', '2014-06-26', '3', '5', 'xjl001', '0', '1');
INSERT INTO `inspectitem` VALUES ('250', '行走开式齿轮无缺油', '', '2014-06-26', '3', '5', 'xkc001', '0', '1');
INSERT INTO `inspectitem` VALUES ('253', '电动防跑器动作可靠', '', '2014-06-26', '3', '5', 'dfp001', '0', '1');
INSERT INTO `inspectitem` VALUES ('256', '防撞缓冲器无变形及损坏', '', '2014-06-26', '3', '5', 'fangz001', '0', '1');
INSERT INTO `inspectitem` VALUES ('259', '行走电机风机无变形、无松动', '', '2014-06-26', '3', '5', 'xd001', '0', '1');
INSERT INTO `inspectitem` VALUES ('262', '行走开式齿轮无漏油', '', '2014-06-26', '3', '5', 'x001', '0', '1');
INSERT INTO `inspectitem` VALUES ('265', '电动防跑器动作可靠', '', '2014-06-26', '3', '5', 'diandongfpao', '0', '1');
INSERT INTO `inspectitem` VALUES ('268', '防撞缓冲器无变形及损坏', '', '2014-06-26', '3', '5', 'fangzhuan001', '0', '1');
INSERT INTO `inspectitem` VALUES ('271', '手动锚定可靠锚定动作', '', '2014-06-26', '3', '5', 'sm001', '0', '1');
INSERT INTO `inspectitem` VALUES ('274', '电气箱无碰撞变形', '', '2014-06-26', '3', '5', 'dianqi001', '0', '1');
INSERT INTO `inspectitem` VALUES ('277', '行走电机风机无变形', '', '2014-06-26', '3', '5', 'x001', '0', '1');
INSERT INTO `inspectitem` VALUES ('280', '行走电机、惯性制动器无异响', '', '2014-06-26', '3', '5', 'x002', '0', '1');
INSERT INTO `inspectitem` VALUES ('283', '电动防跑器动作可靠', '', '2014-06-26', '3', '5', 'df001', '0', '1');
INSERT INTO `inspectitem` VALUES ('286', '梯子、栏杆无变形，无松动', '', '2014-06-26', '3', '5', 't002', '0', '1');
INSERT INTO `inspectitem` VALUES ('289', '大法兰M42螺栓无松动', '', '2014-06-26', '3', '5', 'df001', '0', '1');
INSERT INTO `inspectitem` VALUES ('292', '门架内无漏水，无异物，各箱柜无晃动', '', '2014-06-26', '3', '5', 'menjia001', '0', '1');
INSERT INTO `inspectitem` VALUES ('295', '齿圈，小齿轮啮合良好，无漏油，无异响', '', '2014-06-26', '3', '6', 'chiquan001', '0', '1');
INSERT INTO `inspectitem` VALUES ('298', 'M42双头螺杆无松动', '', '2014-06-26', '3', '6', 'm001', '0', '1');
INSERT INTO `inspectitem` VALUES ('301', '旋转及小齿轮接油盘污物不过多', '', '2014-06-26', '3', '6', 'xuanzhaun001', '0', '1');
INSERT INTO `inspectitem` VALUES ('304', '旋转刹车使用正常', '', '2014-06-26', '3', '7', 'xuanc001', '0', '1');
INSERT INTO `inspectitem` VALUES ('307', '司机室及机器房门和门锁活动灵活，使用可靠', '', '2014-06-26', '3', '7', 'sj001', '0', '1');
INSERT INTO `inspectitem` VALUES ('310', '进门处旋转电机、风机无异响', '', '2014-06-26', '3', '7', 'jinmen001', '0', '1');
INSERT INTO `inspectitem` VALUES ('313', '旋转刹车片间隙均匀且不过薄，缓冲胶圈无老化', '', '2014-06-26', '3', '7', 'xuanz001', '0', '1');
INSERT INTO `inspectitem` VALUES ('316', '旋转减速机油位正常且无漏油', '', '2014-06-26', '3', '7', 'xuanzhuan001', '0', '1');
INSERT INTO `inspectitem` VALUES ('319', '旋转减速箱地脚螺栓无松动', '', '2014-06-26', '3', '7', 'x001', '0', '1');
INSERT INTO `inspectitem` VALUES ('322', '油桶内不缺油，且机房内各润滑管道接口无漏油', '', '2014-06-26', '3', '7', 'youdong001', '0', '1');
INSERT INTO `inspectitem` VALUES ('325', '集中润滑参数设置正确且运行一个循环工作正常', '', '2014-06-26', '3', '7', 'jiz001', '0', '1');
INSERT INTO `inspectitem` VALUES ('328', '旋转电机、风机无异响', '', '2014-06-26', '3', '7', 'xd001', '0', '1');
INSERT INTO `inspectitem` VALUES ('331', '旋转刹车片间隙均匀且不过薄，缓冲胶圈无老化', '', '2014-06-26', '3', '7', 'xc001', '0', '1');
INSERT INTO `inspectitem` VALUES ('334', '旋转减速机油位正常且无漏油', '', '2014-06-26', '3', '7', 'xj001', '0', '1');
INSERT INTO `inspectitem` VALUES ('337', '盘式制动器两侧间隙均匀且制动片不过薄', '', '2014-06-26', '3', '7', 'p001', '0', '1');
INSERT INTO `inspectitem` VALUES ('340', '制动器各绞点润滑良好', '', '2014-06-26', '3', '7', 'zdong001', '0', '1');
INSERT INTO `inspectitem` VALUES ('343', '行走左行、右行限位', '', '2014-06-26', '4', '5', '001', '0', '1');
INSERT INTO `inspectitem` VALUES ('346', '行走锚定限位', '', '2014-06-26', '4', '5', 'xm001', '0', '1');
INSERT INTO `inspectitem` VALUES ('349', '行走防爬限位', '', '2014-06-26', '4', '5', 'xingzoup001', '0', '1');
INSERT INTO `inspectitem` VALUES ('352', '行走电机', '', '2014-06-26', '4', '5', 'xd001', '0', '1');
INSERT INTO `inspectitem` VALUES ('355', '行走部分线管', '', '2014-06-26', '4', '5', 'xg001', '0', '1');
INSERT INTO `inspectitem` VALUES ('358', '高压电缆及电缆架', '', '2014-06-26', '4', '5', 'gaoya001', '0', '1');
INSERT INTO `inspectitem` VALUES ('361', '电缆卷盘及导缆架', '', '2014-06-26', '4', '5', 'dianlan001', '0', '1');
INSERT INTO `inspectitem` VALUES ('364', '行走声光报警', '', '2014-06-26', '4', '5', 'xingzou001', '0', '1');
INSERT INTO `inspectitem` VALUES ('367', '行走声光报警', '', '2014-06-26', '4', '5', 'xsg001', '0', '1');
INSERT INTO `inspectitem` VALUES ('370', '中心受电器', '', '2014-06-26', '4', '6', 'zxi001', '0', '1');
INSERT INTO `inspectitem` VALUES ('373', '高压房', '', '2014-06-26', '4', '6', 'gaoya001', '0', '1');
INSERT INTO `inspectitem` VALUES ('376', '起升/开闭制动器打开限位', '', '2014-06-26', '4', '7', 'kaibi001', '0', '1');
INSERT INTO `inspectitem` VALUES ('379', '起升/开闭上升、下降限位', '', '2014-06-26', '4', '7', 'x001', '0', '1');
INSERT INTO `inspectitem` VALUES ('382', '开闭电机及风机', '', '2014-06-26', '4', '7', 'xi001', '0', '1');
INSERT INTO `inspectitem` VALUES ('385', '各电器柜', '', '2014-06-26', '4', '7', 'dian001', '0', '1');
INSERT INTO `inspectitem` VALUES ('388', '电器柜照明', '', '2014-06-26', '4', '7', 'dianqigui001', '0', '1');
INSERT INTO `inspectitem` VALUES ('391', '照明变压器', '', '2014-06-26', '4', '7', 'zhoaming001', '0', '1');
INSERT INTO `inspectitem` VALUES ('394', '旋转电机及风机', '', '2014-06-26', '4', '7', 'xuanzhuan001', '0', '1');
INSERT INTO `inspectitem` VALUES ('397', '旋转电机刹车', '', '2014-06-26', '4', '7', 'dc001', '0', '1');
INSERT INTO `inspectitem` VALUES ('400', '联动台', '', '2014-06-26', '4', '7', 'liandongtai001', '0', '1');
INSERT INTO `inspectitem` VALUES ('403', '司机室空调', '', '2014-06-26', '4', '7', 'siji001', '0', '1');
INSERT INTO `inspectitem` VALUES ('406', '监控器及重量幅度显示器', '', '2014-06-26', '4', '7', 'jiankong001', '0', '1');
INSERT INTO `inspectitem` VALUES ('409', '电气空调', '', '2014-06-26', '4', '7', 'dianqik001', '0', '1');
INSERT INTO `inspectitem` VALUES ('412', '司机室配电箱', '', '2014-06-26', '4', '7', 'spd001', '0', '1');
INSERT INTO `inspectitem` VALUES ('415', '开闭斗脚踏', '', '2014-06-26', '4', '7', 'kb001', '0', '1');
INSERT INTO `inspectitem` VALUES ('418', '机上照明灯', '', '2014-06-26', '4', '8', 'ji001', '0', '1');
INSERT INTO `inspectitem` VALUES ('421', '航空灯', '', '2014-06-26', '4', '8', 'h001', '0', '1');
INSERT INTO `inspectitem` VALUES ('424', '变幅增幅、减幅限位', '', '2014-06-26', '4', '8', 'bian001', '0', '1');
INSERT INTO `inspectitem` VALUES ('427', '1#走行减速箱', '', '2014-06-26', '5', '5', '001', '0', '1');
INSERT INTO `inspectitem` VALUES ('430', '2#减速机', '', '2014-06-26', '5', '5', '002', '0', '1');
INSERT INTO `inspectitem` VALUES ('433', '3#走行减速机', '', '2014-06-26', '5', '5', '003', '0', '1');
INSERT INTO `inspectitem` VALUES ('436', '4#走行减速机', '', '2014-06-26', '5', '5', '004', '0', '1');
INSERT INTO `inspectitem` VALUES ('439', '5#走行减速机', '', '2014-06-26', '5', '5', '005', '0', '1');
INSERT INTO `inspectitem` VALUES ('442', '6#走行减速机', '', '2014-06-26', '5', '5', '006', '0', '1');
INSERT INTO `inspectitem` VALUES ('445', '7#走行减速机', '', '2014-06-26', '5', '5', '007', '0', '1');
INSERT INTO `inspectitem` VALUES ('448', '8#走行减速机', '', '2014-06-26', '5', '5', '008', '0', '1');
INSERT INTO `inspectitem` VALUES ('451', '左回转减速机', '', '2014-06-26', '5', '5', 'zuo001', '0', '1');
INSERT INTO `inspectitem` VALUES ('454', '右回转减速机', '', '2014-06-26', '5', '5', 'you001', '0', '1');
INSERT INTO `inspectitem` VALUES ('457', '支持绳减速机', '', '2014-06-26', '5', '5', 'zhichi001', '0', '1');
INSERT INTO `inspectitem` VALUES ('460', '开闭绳减速机', '', '2014-06-26', '5', '5', 'kainb001', '0', '1');
INSERT INTO `inspectitem` VALUES ('463', '变幅用减速机', '', '2014-06-26', '5', '5', 'bfj001', '0', '1');
INSERT INTO `inspectitem` VALUES ('466', '1#走行减速机', '', '2014-06-26', '6', '5', '001', '0', '1');
INSERT INTO `inspectitem` VALUES ('469', '2#走行减速机', '', '2014-06-26', '6', '5', '002', '0', '1');
INSERT INTO `inspectitem` VALUES ('472', '绳头 左开闭', '', '2014-06-26', '6', '5', 'h001', '0', '1');
INSERT INTO `inspectitem` VALUES ('475', '卸扣 左开闭', '', '2014-06-26', '6', '5', 'x001', '0', '1');
INSERT INTO `inspectitem` VALUES ('478', '行走驱动齿轮', '', '2014-06-26', '6', '5', 'xq001', '0', '1');
INSERT INTO `inspectitem` VALUES ('481', '各电动铁鞋绞点', '', '2014-06-26', '6', '5', 'dian001', '0', '1');
INSERT INTO `inspectitem` VALUES ('484', '各机构制动器底座螺栓', '', '2014-06-26', '6', '5', 'gj001', '0', '1');
INSERT INTO `inspectitem` VALUES ('487', '各机构电机底座螺栓', '', '2014-06-26', '6', '5', 'gjd001', '0', '1');
INSERT INTO `inspectitem` VALUES ('490', '各机构制动器底座螺栓', '', '2014-06-26', '6', '6', 'zls001', '0', '1');
INSERT INTO `inspectitem` VALUES ('493', '各机构电机底座螺栓', '', '2014-06-26', '6', '6', '001', '0', '1');
INSERT INTO `inspectitem` VALUES ('496', '开闭绳减速机', '', '2014-06-26', '6', '7', 'kaibi001', '0', '1');
INSERT INTO `inspectitem` VALUES ('499', '压板左开闭', '', '2014-06-26', '6', '7', 'yaban001', '0', '1');
INSERT INTO `inspectitem` VALUES ('502', '压板 右开闭', '', '2014-06-26', '6', '7', 'yyou001', '0', '1');
INSERT INTO `inspectitem` VALUES ('505', '压板 右支持', '', '2014-06-26', '6', '7', 'yayzhi001', '0', '1');
INSERT INTO `inspectitem` VALUES ('508', '压板 左支持', '', '2014-06-26', '6', '7', 'yayzuo001', '0', '1');
INSERT INTO `inspectitem` VALUES ('511', 'S轮段 磨损 右开闭', '', '2014-06-26', '6', '7', 's001', '0', '1');
INSERT INTO `inspectitem` VALUES ('514', 'S轮段 磨损 左支持', '', '2014-06-26', '6', '7', 's002', '0', '1');
INSERT INTO `inspectitem` VALUES ('517', 'S轮段 磨损 右支持', '', '2014-06-26', '6', '7', 's003', '0', '1');
INSERT INTO `inspectitem` VALUES ('520', 'S轮段 断丝 右开闭', '', '2014-06-26', '6', '7', 's004', '0', '1');
INSERT INTO `inspectitem` VALUES ('523', 'S轮段 断丝 左支持', '', '2014-06-26', '6', '7', 's005', '0', '1');
INSERT INTO `inspectitem` VALUES ('526', 'S轮段 断丝 右支持', '', '2014-06-26', '6', '7', 's006', '0', '1');
INSERT INTO `inspectitem` VALUES ('529', '象鼻梁段 磨损 右开闭', '', '2014-06-26', '6', '7', 'xiangbi007', '0', '1');
INSERT INTO `inspectitem` VALUES ('532', '象鼻梁段 磨损 左支持', '', '2014-06-26', '6', '7', 'x001', '0', '1');
INSERT INTO `inspectitem` VALUES ('535', '象鼻梁段 磨损 右支持', '', '2014-06-26', '6', '7', 'x002', '0', '1');
INSERT INTO `inspectitem` VALUES ('538', '象鼻梁段 断丝 右开闭', '', '2014-06-26', '6', '7', 'x003', '0', '1');
INSERT INTO `inspectitem` VALUES ('541', '象鼻梁段 断丝 右支持', '', '2014-06-26', '6', '7', 'x004', '0', '1');
INSERT INTO `inspectitem` VALUES ('544', '象鼻梁段 断丝 右支持', '', '2014-06-26', '6', '7', 'x005', '0', '1');
INSERT INTO `inspectitem` VALUES ('547', '绳头 右开闭', '', '2014-06-26', '6', '7', 'sty001', '0', '1');
INSERT INTO `inspectitem` VALUES ('550', '绳头 左支持', '', '2014-06-26', '6', '7', 'stz001', '0', '1');
INSERT INTO `inspectitem` VALUES ('553', '绳头 右支持', '', '2014-06-26', '6', '7', 'sty002', '0', '1');
INSERT INTO `inspectitem` VALUES ('556', '卸扣 右开闭', '', '2014-06-26', '6', '7', 'xyk001', '0', '1');
INSERT INTO `inspectitem` VALUES ('559', '卸扣 左支持', '', '2014-06-26', '6', '7', 'xiek001', '0', '1');
INSERT INTO `inspectitem` VALUES ('562', '集中润滑油箱', '', '2014-06-26', '6', '7', 'jry001', '0', '1');
INSERT INTO `inspectitem` VALUES ('565', '集中润滑管路泄露检查', '', '2014-06-26', '6', '7', 'jrxj001', '0', '1');
INSERT INTO `inspectitem` VALUES ('568', '大齿圈', '', '2014-06-26', '6', '7', 'dchiquan001', '0', '1');
INSERT INTO `inspectitem` VALUES ('571', '开闭减速箱输入输出轴', '', '2014-06-26', '6', '7', 'kaibi001', '0', '1');
INSERT INTO `inspectitem` VALUES ('574', '支持减速箱输入输出轴', '', '2014-06-26', '6', '7', 'zhichijian001', '0', '1');
INSERT INTO `inspectitem` VALUES ('577', '开闭减速箱高速联轴箱', '', '2014-06-26', '6', '7', 'kaibi001', '0', '1');
INSERT INTO `inspectitem` VALUES ('580', '开闭卷筒联轴箱', '', '2014-06-26', '6', '7', 'kaibi002', '0', '1');
INSERT INTO `inspectitem` VALUES ('583', '支持减速箱高速联轴箱', '', '2014-06-26', '6', '7', 'zhic001', '0', '1');
INSERT INTO `inspectitem` VALUES ('586', '支持卷筒联轴器', '', '2014-06-26', '6', '7', 'zhichi002', '0', '1');
INSERT INTO `inspectitem` VALUES ('589', '开闭卷筒压绳器', '', '2014-06-26', '6', '7', 'ky001', '0', '1');
INSERT INTO `inspectitem` VALUES ('592', '支持卷筒压绳器', '', '2014-06-26', '6', '7', 'zhic001', '0', '1');
INSERT INTO `inspectitem` VALUES ('595', '起升制动器绞点', '', '2014-06-26', '6', '7', 'qish001', '0', '1');
INSERT INTO `inspectitem` VALUES ('598', '开闭制动器绞点', '', '2014-06-26', '6', '7', 'kaibi001', '0', '1');
INSERT INTO `inspectitem` VALUES ('601', '回转制动器绞点', '', '2014-06-26', '6', '7', 'huizhuan001', '0', '1');
INSERT INTO `inspectitem` VALUES ('604', '脚踏制动器绞点', '', '2014-06-26', '6', '7', 'jiao001', '0', '1');
INSERT INTO `inspectitem` VALUES ('607', '大臂支座下左绞点开裂', '', '2014-06-26', '6', '7', 'b001', '0', '1');
INSERT INTO `inspectitem` VALUES ('610', '大臂支座下右绞点开裂', '', '2014-06-26', '6', '7', 'b002', '0', '1');
INSERT INTO `inspectitem` VALUES ('613', '大臂上右绞点开裂', '', '2014-06-26', '6', '7', 'bs001', '0', '1');
INSERT INTO `inspectitem` VALUES ('616', '大臂上左绞点开裂', '', '2014-06-26', '6', '7', 'bs002', '0', '1');
INSERT INTO `inspectitem` VALUES ('619', '机房底根部开裂', '', '2014-06-26', '6', '7', 'jf001', '0', '1');
INSERT INTO `inspectitem` VALUES ('622', '大齿圈根部开裂', '', '2014-06-26', '6', '7', 'chiquan001', '0', '1');
INSERT INTO `inspectitem` VALUES ('625', '各机构制动器底座螺栓', '', '2014-06-26', '6', '7', 'jgou001', '0', '1');
INSERT INTO `inspectitem` VALUES ('628', '各机构电机底座螺栓', '', '2014-06-26', '6', '7', 'jgoudian001', '0', '1');
INSERT INTO `inspectitem` VALUES ('631', '支持开闭卷筒支座及轴承盖螺栓', '', '2014-06-26', '6', '7', 'h001', '0', '1');
INSERT INTO `inspectitem` VALUES ('634', '变幅用减速机', '', '2014-06-26', '6', '8', 'b001', '0', '1');
INSERT INTO `inspectitem` VALUES ('637', 'S轮段 磨损 左开闭', '', '2014-06-26', '6', '8', 's001', '0', '1');
INSERT INTO `inspectitem` VALUES ('640', 'S轮段 断丝 左开闭', '', '2014-06-26', '6', '8', 's002', '0', '1');
INSERT INTO `inspectitem` VALUES ('643', '象鼻梁端 左开闭 磨损', '', '2014-06-26', '6', '8', 'xiang001', '0', '1');
INSERT INTO `inspectitem` VALUES ('646', '象鼻梁端 左开闭 断丝', '', '2014-06-26', '6', '8', 'xiang002', '0', '1');
INSERT INTO `inspectitem` VALUES ('649', '变幅齿圈', '', '2014-06-26', '6', '8', 'bianfu001', '0', '1');
INSERT INTO `inspectitem` VALUES ('652', '变幅减速箱输入输出轴', '', '2014-06-26', '6', '8', 'bianfu002', '0', '1');
INSERT INTO `inspectitem` VALUES ('655', '象鼻梁钢丝绳托辊', '', '2014-06-26', '6', '8', 'xiangbi001', '0', '1');
INSERT INTO `inspectitem` VALUES ('658', '负荷取力装置绞点', '', '2014-06-26', '6', '8', 'fuhe001', '0', '1');
INSERT INTO `inspectitem` VALUES ('661', '变幅制动器绞点', '', '2014-06-26', '6', '8', 'bianfu003', '0', '1');
INSERT INTO `inspectitem` VALUES ('664', '象鼻梁尾部各U型槽根部开裂', '', '2014-06-26', '6', '8', 'xiang001', '0', '1');
INSERT INTO `inspectitem` VALUES ('667', '象鼻梁头部各U型槽根部开裂', '', '2014-06-26', '6', '8', 'xiang002', '0', '1');
INSERT INTO `inspectitem` VALUES ('670', '各机构制动器底座螺栓', '', '2014-06-26', '6', '8', 'jigou001', '0', '1');
INSERT INTO `inspectitem` VALUES ('673', '各机构电机底座螺栓', '', '2014-06-26', '6', '8', 'jigou002', '0', '1');
INSERT INTO `inspectitem` VALUES ('676', '变幅摇架支座及轴承盖螺栓', '', '2014-06-26', '6', '8', 'bianfu003', '0', '1');
INSERT INTO `inspectitem` VALUES ('677', '司机室外观封闭无损伤', '', '2014-06-30', '7', '9', '1', '1', '1');
INSERT INTO `inspectitem` VALUES ('678', '门窗、门锁无损坏，门窗开闭无异常', '', '2014-06-30', '7', '9', '2', '1', '1');
INSERT INTO `inspectitem` VALUES ('679', '照明正常，标牌、标识无污损和丢失', '', '2014-06-30', '7', '9', '3', '1', '1');
INSERT INTO `inspectitem` VALUES ('680', '方向盘、操纵杆操作灵活，各按钮开关有效、不缺零件', '', '2014-06-30', '7', '9', '4', '1', '1');
INSERT INTO `inspectitem` VALUES ('681', '脚踏板行程合适，座椅无破损，调节机构无卡阻', '', '2014-06-30', '7', '9', '5', '1', '1');
INSERT INTO `inspectitem` VALUES ('682', '仪表盘无破损、模糊，显示清晰', '', '2014-06-30', '7', '9', '6', '1', '1');
INSERT INTO `inspectitem` VALUES ('683', '后视镜、监控电视屏，力矩限制器显示屏无损伤', '', '2014-06-30', '7', '9', '7', '0', '1');
INSERT INTO `inspectitem` VALUES ('684', '机房环境', '', '2014-07-17', '8', '13', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('685', '曳引机', '', '2014-07-17', '8', '13', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('686', '手动紧急装置', '', '2014-07-17', '8', '13', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('687', '制动器各销轴', '', '2014-07-17', '8', '13', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('688', '制动器间隙', '', '2014-07-17', '8', '13', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('689', '编码器', '', '2014-07-17', '8', '13', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('690', '限速器各销轴', '', '2014-07-17', '8', '13', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('691', '轿顶（轿厢）', '', '2014-07-17', '8', '16', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('692', '轿顶检修开关', '', '2014-07-17', '8', '16', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('693', '轿厢急修开关', '', '2014-07-17', '8', '16', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('694', '导靴上油杯', '', '2014-07-17', '8', '16', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('695', '对重块、压板', '', '2014-07-17', '8', '16', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('696', '井道照明', '', '2014-07-17', '8', '16', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('697', '轿厢报警系统', '', '2014-07-18', '8', '15', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('698', '轿厢照明风扇', '', '2014-07-18', '8', '15', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('699', '应急照明', '', '2014-07-18', '8', '15', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('700', '轿内显示', '', '2014-07-18', '8', '15', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('701', '指令按钮', '', '2014-07-18', '8', '15', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('702', '层站召唤', '', '2014-07-18', '8', '15', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('703', '层楼显示', '', '2014-07-18', '8', '15', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('704', '轿厢检修开关', '', '2014-07-18', '8', '15', '8', '0', '2');
INSERT INTO `inspectitem` VALUES ('705', '轿厢急停开关', '', '2014-07-18', '8', '15', '9', '0', '2');
INSERT INTO `inspectitem` VALUES ('706', '轿厢平层', '', '2014-07-18', '8', '15', '10', '0', '2');
INSERT INTO `inspectitem` VALUES ('707', '门锁电气触点', '', '2014-07-18', '8', '15', '11', '0', '2');
INSERT INTO `inspectitem` VALUES ('708', '轿门安全装置', '', '2014-07-18', '8', '15', '12', '0', '2');
INSERT INTO `inspectitem` VALUES ('709', '轿门运行', '', '2014-07-18', '8', '15', '13', '0', '2');
INSERT INTO `inspectitem` VALUES ('710', '底坑环境', '', '2014-07-18', '8', '14', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('711', '底坑急停开关', '', '2014-07-18', '8', '14', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('712', '层门地坎', '', '2014-07-18', '8', '17', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('713', '自动关门装置', '', '2014-07-18', '8', '17', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('714', '门锁自动复位', '', '2014-07-18', '8', '17', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('715', '门锁电气触点', '', '2014-07-18', '8', '17', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('716', '锁紧元件啮合', '', '2014-07-18', '8', '17', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('717', '手动紧急装置', '', '2014-07-18', '9', '13', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('718', '减速机润滑油', '', '2014-07-18', '9', '13', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('719', '制动衬', '', '2014-07-18', '9', '13', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('720', '曳引机', '', '2014-07-18', '9', '13', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('721', '曳引轮槽', '', '2014-07-18', '9', '13', '8', '0', '2');
INSERT INTO `inspectitem` VALUES ('722', '曳引钢丝绳', '', '2014-07-18', '9', '13', '9', '0', '2');
INSERT INTO `inspectitem` VALUES ('723', '编码器', '', '2014-07-18', '9', '13', '10', '0', '2');
INSERT INTO `inspectitem` VALUES ('724', '脉冲发生器', '', '2014-07-18', '9', '13', '11', '0', '2');
INSERT INTO `inspectitem` VALUES ('725', '选层器触点', '', '2014-07-18', '9', '13', '12', '0', '2');
INSERT INTO `inspectitem` VALUES ('726', '限速器各销轴', '', '2014-07-18', '9', '13', '13', '0', '2');
INSERT INTO `inspectitem` VALUES ('727', '限速器轮槽', '', '2014-07-18', '9', '13', '14', '0', '2');
INSERT INTO `inspectitem` VALUES ('728', '限速器钢丝绳', '', '2014-07-18', '9', '13', '15', '0', '2');
INSERT INTO `inspectitem` VALUES ('734', '机房环境', '', '2014-07-19', '9', '13', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('735', '制动器各销轴', '', '2014-07-19', '9', '13', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('736', '制动器间隙', '', '2014-07-19', '9', '13', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('737', '轿顶（轿厢）', '', '2014-07-19', '9', '16', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('738', '轿顶检修开关', '', '2014-07-19', '9', '16', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('739', '轿顶急停开关', '', '2014-07-19', '9', '16', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('740', '导靴上油杯', '', '2014-07-19', '9', '16', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('741', '对重块、压板', '', '2014-07-19', '9', '16', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('742', '井道照明', '', '2014-07-19', '9', '16', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('743', '靴衬、滚轮', '', '2014-07-19', '9', '16', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('744', '轿厢平层', '', '2014-07-19', '9', '15', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('745', '轿厢报警系统', '', '2014-07-19', '9', '15', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('746', '轿厢照明风扇', '', '2014-07-19', '9', '15', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('747', '轿厢应急照明', '', '2014-07-19', '9', '15', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('748', '轿内显示', '', '2014-07-19', '9', '15', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('749', '指令按钮', '', '2014-07-19', '9', '15', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('750', '层站召唤', '', '2014-07-19', '9', '15', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('751', '层楼显示', '', '2014-07-19', '9', '15', '8', '0', '2');
INSERT INTO `inspectitem` VALUES ('752', '轿厢检修开关', '', '2014-07-19', '9', '15', '9', '0', '2');
INSERT INTO `inspectitem` VALUES ('753', '轿厢急停开关', '', '2014-07-19', '9', '15', '10', '0', '2');
INSERT INTO `inspectitem` VALUES ('754', '门锁电气触点', '', '2014-07-19', '9', '15', '11', '0', '2');
INSERT INTO `inspectitem` VALUES ('755', '轿门安全装置', '', '2014-07-19', '9', '15', '12', '0', '2');
INSERT INTO `inspectitem` VALUES ('756', '轿门运行', '', '2014-07-19', '9', '15', '13', '0', '2');
INSERT INTO `inspectitem` VALUES ('757', '电气安全装置', '', '2014-07-19', '9', '15', '14', '0', '2');
INSERT INTO `inspectitem` VALUES ('758', '底坑环境', '', '2014-07-19', '9', '14', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('759', '底坑急停开关', '', '2014-07-19', '9', '14', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('760', '消防开关', '', '2014-07-19', '9', '14', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('761', '耗能缓冲器', '', '2014-07-19', '9', '14', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('762', '限速器张紧轮', '', '2014-07-19', '9', '14', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('763', '电气安全装置', '', '2014-07-19', '9', '14', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('764', '层门地坎', '', '2014-07-19', '9', '17', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('765', '自动关门装置', '', '2014-07-19', '9', '17', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('766', '门锁自动复位', '', '2014-07-19', '9', '17', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('767', '门锁电气触点', '', '2014-07-19', '9', '17', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('768', '锁紧元件啮合', '', '2014-07-19', '9', '17', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('769', '层门轿门系统', '', '2014-07-19', '9', '17', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('770', '层门门导靴', '', '2014-07-19', '9', '17', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('771', '机房环境', '', '2014-07-19', '10', '13', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('772', '手动紧急装置', '', '2014-07-19', '10', '13', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('773', '减速机润滑油', '', '2014-07-19', '10', '13', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('774', '联轴器螺栓', '', '2014-07-19', '10', '13', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('775', '制动器各销轴', '', '2014-07-19', '10', '13', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('776', '制动器间隙', '', '2014-07-19', '10', '13', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('777', '制动衬', '', '2014-07-19', '10', '13', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('778', '检测开关', '', '2014-07-19', '10', '13', '8', '0', '2');
INSERT INTO `inspectitem` VALUES ('779', '曳引机', '', '2014-07-19', '10', '13', '9', '0', '2');
INSERT INTO `inspectitem` VALUES ('780', '曳引轮槽', '', '2014-07-19', '10', '13', '10', '0', '2');
INSERT INTO `inspectitem` VALUES ('781', '曳引钢丝绳', '', '2014-07-19', '10', '13', '11', '0', '2');
INSERT INTO `inspectitem` VALUES ('782', '曳引轮轴承部', '', '2014-07-19', '10', '13', '12', '0', '2');
INSERT INTO `inspectitem` VALUES ('783', '导向轮轴承部', '', '2014-07-19', '10', '13', '13', '0', '2');
INSERT INTO `inspectitem` VALUES ('784', '曳引绳', '', '2014-07-19', '10', '13', '14', '0', '2');
INSERT INTO `inspectitem` VALUES ('785', '补偿绳', '', '2014-07-19', '10', '13', '15', '0', '2');
INSERT INTO `inspectitem` VALUES ('786', '曳引绳头组合', '', '2014-07-19', '10', '13', '16', '0', '2');
INSERT INTO `inspectitem` VALUES ('787', '编码器', '', '2014-07-19', '10', '13', '17', '0', '2');
INSERT INTO `inspectitem` VALUES ('788', '脉冲发生器', '', '2014-07-19', '10', '13', '18', '0', '2');
INSERT INTO `inspectitem` VALUES ('789', '选层器触点', '', '2014-07-19', '10', '13', '19', '0', '2');
INSERT INTO `inspectitem` VALUES ('790', '柜内接线端子', '', '2014-07-19', '10', '13', '20', '0', '2');
INSERT INTO `inspectitem` VALUES ('791', '控制柜各仪表', '', '2014-07-19', '10', '13', '21', '0', '2');
INSERT INTO `inspectitem` VALUES ('792', '限速器各销轴', '', '2014-07-19', '10', '13', '22', '0', '2');
INSERT INTO `inspectitem` VALUES ('793', '限速器轮槽', '', '2014-07-19', '10', '13', '23', '0', '2');
INSERT INTO `inspectitem` VALUES ('794', '限速器钢丝绳', '', '2014-07-19', '10', '13', '24', '0', '2');
INSERT INTO `inspectitem` VALUES ('795', '轿顶（轿厢）', '', '2014-07-19', '10', '16', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('796', '轿顶检修开关', '', '2014-07-19', '10', '16', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('797', '轿顶急停开关', '', '2014-07-19', '10', '16', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('798', '导靴上油杯', '', '2014-07-19', '10', '16', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('799', '对重块、压板', '', '2014-07-19', '10', '16', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('800', '井道照明', '', '2014-07-19', '10', '16', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('801', '靴衬、滚轮', '', '2014-07-19', '10', '16', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('802', '井道反绳轮轴', '', '2014-07-19', '10', '16', '8', '0', '2');
INSERT INTO `inspectitem` VALUES ('803', '对重反绳轮轴', '', '2014-07-19', '10', '16', '9', '0', '2');
INSERT INTO `inspectitem` VALUES ('804', '轿顶反绳轮轴', '', '2014-07-19', '10', '16', '10', '0', '2');
INSERT INTO `inspectitem` VALUES ('805', '补偿链结合处', '', '2014-07-19', '10', '16', '11', '0', '2');
INSERT INTO `inspectitem` VALUES ('806', '上下极限开关', '', '2014-07-19', '10', '16', '12', '0', '2');
INSERT INTO `inspectitem` VALUES ('807', '轿厢平层', '', '2014-07-19', '10', '15', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('808', '轿厢报警系统', '', '2014-07-19', '10', '15', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('809', '轿厢照明风扇', '', '2014-07-19', '10', '15', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('810', '轿厢应急照明', '', '2014-07-19', '10', '15', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('811', '轿内显示', '', '2014-07-19', '10', '15', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('812', '指令按钮', '', '2014-07-19', '10', '15', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('813', '层站召唤', '', '2014-07-19', '10', '15', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('814', '层楼显示', '', '2014-07-19', '10', '15', '8', '0', '2');
INSERT INTO `inspectitem` VALUES ('815', '轿厢检修开关', '', '2014-07-19', '10', '15', '9', '0', '2');
INSERT INTO `inspectitem` VALUES ('816', '轿厢急停开关', '', '2014-07-19', '10', '15', '10', '0', '2');
INSERT INTO `inspectitem` VALUES ('817', '门锁电气触点', '', '2014-07-19', '10', '15', '11', '0', '2');
INSERT INTO `inspectitem` VALUES ('818', '轿门安全装置', '', '2014-07-19', '10', '15', '12', '0', '2');
INSERT INTO `inspectitem` VALUES ('819', '轿门运行', '', '2014-07-19', '10', '15', '13', '0', '2');
INSERT INTO `inspectitem` VALUES ('820', '电气安全装置', '', '2014-07-19', '10', '15', '14', '0', '2');
INSERT INTO `inspectitem` VALUES ('821', '底坑环境', '', '2014-07-19', '10', '14', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('822', '底坑急停开关', '', '2014-07-19', '10', '14', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('823', '消防开关', '', '2014-07-19', '10', '14', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('824', '耗能缓冲器', '', '2014-07-19', '10', '14', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('825', '限速器张紧轮', '', '2014-07-19', '10', '14', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('826', '电气安全装置', '', '2014-07-19', '10', '14', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('827', '对重缓冲器', '', '2014-07-19', '10', '14', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('828', '层门地坎', '', '2014-07-19', '10', '17', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('829', '自动关门装置', '', '2014-07-19', '10', '17', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('830', '门锁自动复位', '', '2014-07-19', '10', '17', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('831', '门锁电气触点', '', '2014-07-19', '10', '17', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('832', '锁紧元件啮合', '', '2014-07-19', '10', '17', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('833', '层门轿门系统', '', '2014-07-19', '10', '17', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('834', '层门门导靴', '', '2014-07-19', '10', '17', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('835', '层门轿门门扇', '', '2014-07-19', '10', '17', '8', '0', '2');
INSERT INTO `inspectitem` VALUES ('836', '机房环境', '', '2014-07-19', '11', '13', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('837', '手动紧急装置', '', '2014-07-19', '11', '13', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('838', '上行超速保护', '', '2014-07-19', '11', '13', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('839', '减速机润滑油', '', '2014-07-19', '11', '13', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('840', '联轴器螺栓', '', '2014-07-19', '11', '13', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('841', '制动器各销轴', '', '2014-07-19', '11', '13', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('842', '制动器间隙', '', '2014-07-19', '11', '13', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('843', '制动衬', '', '2014-07-19', '11', '13', '8', '0', '2');
INSERT INTO `inspectitem` VALUES ('844', '检测开关', '', '2014-07-19', '11', '13', '9', '0', '2');
INSERT INTO `inspectitem` VALUES ('845', '制动器铁芯', '', '2014-07-19', '11', '13', '10', '0', '2');
INSERT INTO `inspectitem` VALUES ('846', '制动弹簧压缩', '', '2014-07-19', '11', '13', '11', '0', '2');
INSERT INTO `inspectitem` VALUES ('847', '曳引机', '', '2014-07-19', '11', '13', '12', '0', '2');
INSERT INTO `inspectitem` VALUES ('848', '曳引轮槽', '', '2014-07-19', '11', '13', '13', '0', '2');
INSERT INTO `inspectitem` VALUES ('849', '曳引钢丝绳', '', '2014-07-19', '11', '13', '14', '0', '2');
INSERT INTO `inspectitem` VALUES ('850', '曳引轮轴承部', '', '2014-07-19', '11', '13', '15', '0', '2');
INSERT INTO `inspectitem` VALUES ('851', '导向轮轴承部', '', '2014-07-19', '11', '13', '16', '0', '2');
INSERT INTO `inspectitem` VALUES ('852', '曳引绳', '', '2014-07-19', '11', '13', '17', '0', '2');
INSERT INTO `inspectitem` VALUES ('853', '补偿绳', '', '2014-07-19', '11', '13', '18', '0', '2');
INSERT INTO `inspectitem` VALUES ('854', '曳引绳头组合', '', '2014-07-19', '11', '13', '19', '0', '2');
INSERT INTO `inspectitem` VALUES ('855', '编码器', '', '2014-07-19', '11', '13', '20', '0', '2');
INSERT INTO `inspectitem` VALUES ('856', '脉冲发生器', '', '2014-07-19', '11', '13', '21', '0', '2');
INSERT INTO `inspectitem` VALUES ('857', '选层器触点', '', '2014-07-19', '11', '13', '22', '0', '2');
INSERT INTO `inspectitem` VALUES ('858', '柜内接线端子', '', '2014-07-19', '11', '13', '23', '0', '2');
INSERT INTO `inspectitem` VALUES ('859', '控制柜各仪表', '', '2014-07-19', '11', '13', '24', '0', '2');
INSERT INTO `inspectitem` VALUES ('860', '控制柜接触器', '', '2014-07-19', '11', '13', '25', '0', '2');
INSERT INTO `inspectitem` VALUES ('861', '控制柜继电器', '', '2014-07-19', '11', '13', '26', '0', '2');
INSERT INTO `inspectitem` VALUES ('862', '导电回路性能', '', '2014-07-19', '11', '13', '27', '0', '2');
INSERT INTO `inspectitem` VALUES ('863', '限速器各销轴', '', '2014-07-19', '11', '13', '28', '0', '2');
INSERT INTO `inspectitem` VALUES ('864', '限速器轮槽', '', '2014-07-19', '11', '13', '29', '0', '2');
INSERT INTO `inspectitem` VALUES ('865', '限速器钢丝绳', '', '2014-07-19', '11', '13', '30', '0', '2');
INSERT INTO `inspectitem` VALUES ('866', '安全钳试验', '', '2014-07-19', '11', '13', '31', '0', '2');
INSERT INTO `inspectitem` VALUES ('867', '轿顶（轿厢）', '', '2014-07-19', '11', '16', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('868', '轿顶检修开关', '', '2014-07-19', '11', '16', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('869', '轿顶急停开关', '', '2014-07-19', '11', '16', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('870', '随行电缆', '', '2014-07-19', '11', '16', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('871', '导靴上油杯', '', '2014-07-19', '11', '16', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('872', '对重块、压板', '', '2014-07-19', '11', '16', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('873', '井道照明', '', '2014-07-19', '11', '16', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('874', '靴衬、滚轮', '', '2014-07-19', '11', '16', '8', '0', '2');
INSERT INTO `inspectitem` VALUES ('875', '井道反绳轮轴', '', '2014-07-19', '11', '16', '9', '0', '2');
INSERT INTO `inspectitem` VALUES ('876', '对重反绳轮轴', '', '2014-07-19', '11', '16', '10', '0', '2');
INSERT INTO `inspectitem` VALUES ('877', '轿顶反绳轮轴', '', '2014-07-19', '11', '16', '11', '0', '2');
INSERT INTO `inspectitem` VALUES ('878', '补偿链结合处', '', '2014-07-19', '11', '16', '12', '0', '2');
INSERT INTO `inspectitem` VALUES ('879', '上下极限开关', '', '2014-07-19', '11', '16', '13', '0', '2');
INSERT INTO `inspectitem` VALUES ('880', '轿厢导轨支架', '', '2014-07-19', '11', '16', '14', '0', '2');
INSERT INTO `inspectitem` VALUES ('881', '对重导轨支架', '', '2014-07-19', '11', '16', '15', '0', '2');
INSERT INTO `inspectitem` VALUES ('882', '轿厢导轨', '', '2014-07-19', '11', '16', '16', '0', '2');
INSERT INTO `inspectitem` VALUES ('883', '对重导轨', '', '2014-07-19', '11', '16', '17', '0', '2');
INSERT INTO `inspectitem` VALUES ('884', '轿厢平层', '', '2014-07-19', '11', '15', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('885', '轿厢报警系统', '', '2014-07-19', '11', '15', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('886', '轿厢照明风扇', '', '2014-07-19', '11', '15', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('887', '轿厢应急照明', '', '2014-07-19', '11', '15', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('888', '轿内显示', '', '2014-07-19', '11', '15', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('889', '指令按钮', '', '2014-07-19', '11', '15', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('890', '层站召唤', '', '2014-07-19', '11', '15', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('891', '层楼显示', '', '2014-07-19', '11', '15', '8', '0', '2');
INSERT INTO `inspectitem` VALUES ('892', '各部安装螺栓', '', '2014-07-19', '11', '15', '9', '0', '2');
INSERT INTO `inspectitem` VALUES ('893', '轿厢检修开关', '', '2014-07-19', '11', '15', '10', '0', '2');
INSERT INTO `inspectitem` VALUES ('894', '轿厢急停开关', '', '2014-07-19', '11', '15', '11', '0', '2');
INSERT INTO `inspectitem` VALUES ('895', '门锁电气触点', '', '2014-07-19', '11', '15', '12', '0', '2');
INSERT INTO `inspectitem` VALUES ('896', '轿门安全装置', '', '2014-07-19', '11', '15', '13', '0', '2');
INSERT INTO `inspectitem` VALUES ('897', '轿门运行', '', '2014-07-19', '11', '15', '14', '0', '2');
INSERT INTO `inspectitem` VALUES ('898', '电气安全装置', '', '2014-07-19', '11', '15', '15', '0', '2');
INSERT INTO `inspectitem` VALUES ('899', '轿厢称重装置', '', '2014-07-19', '11', '14', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('900', '轿底安装螺栓', '', '2014-07-19', '11', '14', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('901', '安全钳钳座', '', '2014-07-19', '11', '14', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('902', '底坑环境', '', '2014-07-19', '11', '14', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('903', '底坑急停开关', '', '2014-07-19', '11', '14', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('904', '消防开关', '', '2014-07-19', '11', '14', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('905', '耗能缓冲器', '', '2014-07-19', '11', '14', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('906', '限速器张紧轮', '', '2014-07-19', '11', '14', '8', '0', '2');
INSERT INTO `inspectitem` VALUES ('907', '电气安全装置', '', '2014-07-19', '11', '14', '9', '0', '2');
INSERT INTO `inspectitem` VALUES ('908', '对重缓冲器', '', '2014-07-19', '11', '14', '10', '0', '2');
INSERT INTO `inspectitem` VALUES ('909', '缓冲器', '', '2014-07-19', '11', '14', '11', '0', '2');
INSERT INTO `inspectitem` VALUES ('910', '层面地坎', '', '2014-07-19', '11', '17', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('911', '自动关门装置', '', '2014-07-19', '11', '17', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('912', '门锁自动复位', '', '2014-07-19', '11', '17', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('913', '门锁电气触点', '', '2014-07-19', '11', '17', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('914', '锁紧元件啮合', '', '2014-07-19', '11', '17', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('915', '层门轿门系统', '', '2014-07-19', '11', '17', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('916', '层门门导靴', '', '2014-07-19', '11', '17', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('917', '层门轿门门扇', '', '2014-07-19', '11', '17', '8', '0', '2');
INSERT INTO `inspectitem` VALUES ('918', '层门装置地坎', '', '2014-07-19', '11', '17', '9', '0', '2');
INSERT INTO `inspectitem` VALUES ('926', '行走限位开关、碰尺、止档、缓冲器、扫轨板状态', '', '2014-07-21', '12', '19', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('927', '运行机构性能测试', '', '2014-07-21', '12', '19', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('928', '制动器零件磨损、制动间隙调整、电磁推杆行程调整', '', '2014-07-21', '12', '20', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('929', '起升高度限位器的有效、可靠', '', '2014-07-21', '12', '20', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('930', '起升机构性能测试', '', '2014-07-21', '12', '20', '3', '0', '3');
INSERT INTO `inspectitem` VALUES ('931', '制动器零件磨损、制动间隙调整、电磁推杆行程调整', '', '2014-07-21', '12', '21', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('932', '电液或液压制动推动器性能、油量', '', '2014-07-21', '12', '21', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('933', '变幅限位器和变幅指示的有效、可靠', '', '2014-07-21', '12', '21', '3', '0', '3');
INSERT INTO `inspectitem` VALUES ('934', '变幅机构性能测试', '', '2014-07-21', '12', '21', '4', '0', '3');
INSERT INTO `inspectitem` VALUES ('935', '回转机构性能测试', '', '2014-07-21', '12', '22', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('936', '金属结构的变形、裂纹、损伤', '', '2014-07-21', '12', '23', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('937', '整机油漆碰伤、龟裂、剥落及锈蚀检查', '', '2014-07-21', '12', '23', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('938', '操作控制系统的灵活性和准确性', '', '2014-07-21', '12', '25', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('939', '电气连锁及紧急开关的可靠性', '', '2014-07-21', '12', '25', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('940', '照明', '', '2014-07-21', '12', '25', '3', '0', '3');
INSERT INTO `inspectitem` VALUES ('941', '制动器零件磨损、制动间隙调整、电磁推杆行程调整', '', '2014-07-21', '13', '19', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('942', '联轴器橡胶圈、柱销、销孔的磨损、失效，螺丝松动', '', '2014-07-21', '13', '19', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('943', '减速器地脚固定螺栓的紧固', '', '2014-07-21', '13', '19', '3', '0', '3');
INSERT INTO `inspectitem` VALUES ('944', '行走车轮磨损、啃轨、轨道测量及润滑、调整', '', '2014-07-21', '13', '19', '4', '0', '3');
INSERT INTO `inspectitem` VALUES ('945', '电缆卷筒及电动机碳刷磨损、刷架变形、失效', '', '2014-07-21', '13', '19', '5', '0', '3');
INSERT INTO `inspectitem` VALUES ('946', '拖式电缆的破损', '', '2014-07-21', '13', '19', '6', '0', '3');
INSERT INTO `inspectitem` VALUES ('947', '电液或液压制动推动器性能、油量', '', '2014-07-21', '13', '20', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('948', '联轴器橡胶圈、柱销、销孔的磨损、失效，螺丝松动', '', '2014-07-21', '13', '20', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('949', '起升钢丝绳磨损、断丝、固定、润滑', '', '2014-07-21', '13', '20', '3', '0', '3');
INSERT INTO `inspectitem` VALUES ('950', '滑轮的磨损、裂纹、支撑轴承的运转', '', '2014-07-21', '13', '20', '4', '0', '3');
INSERT INTO `inspectitem` VALUES ('951', '吊钩及组件的磨损、变形及轴承润滑', '', '2014-07-21', '13', '20', '5', '0', '3');
INSERT INTO `inspectitem` VALUES ('952', '电动机碳刷磨损、刷架变形、失效', '', '2014-07-21', '13', '20', '6', '0', '3');
INSERT INTO `inspectitem` VALUES ('953', '联轴器橡胶圈、柱销、销孔的磨损、失效，螺丝松动', '', '2014-07-21', '13', '21', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('954', '变幅钢丝绳磨损、断丝、固定、润滑', '', '2014-07-21', '13', '21', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('955', '滑轮的磨损、裂纹、支撑轴承的运转', '', '2014-07-21', '13', '21', '3', '0', '3');
INSERT INTO `inspectitem` VALUES ('956', '电动机碳刷磨损、刷架变形、失效', '', '2014-07-21', '13', '21', '4', '0', '3');
INSERT INTO `inspectitem` VALUES ('957', '回转驱动小齿轮与回转盘针齿啮合情况', '', '2014-07-21', '13', '22', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('958', '回转大轴承及开式齿轮运转、润滑情况（圆筒式）', '', '2014-07-21', '13', '22', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('959', '制动器零件磨损、制动间隙调整、电磁推杆行程调整', '', '2014-07-21', '13', '22', '3', '0', '3');
INSERT INTO `inspectitem` VALUES ('960', '电液或液压制动推动器性能、油量', '', '2014-07-21', '13', '22', '4', '0', '3');
INSERT INTO `inspectitem` VALUES ('961', '联轴器检查调整', '', '2014-07-21', '13', '22', '5', '0', '3');
INSERT INTO `inspectitem` VALUES ('962', '电动机碳刷磨损、刷架变形、失效', '', '2014-07-21', '13', '22', '6', '0', '3');
INSERT INTO `inspectitem` VALUES ('963', '电气保护的可靠性', '', '2014-07-21', '13', '25', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('964', '电器元件及电线电缆的松动、破损、老化、烧蚀', '', '2014-07-21', '13', '25', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('965', '集电器碳刷磨损、刷架变形、失效', '', '2014-07-21', '13', '25', '3', '0', '3');
INSERT INTO `inspectitem` VALUES ('966', '防护罩盖损坏、缺失', '', '2014-07-21', '13', '27', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('967', '电控箱是否损坏', '', '2014-07-21', '13', '27', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('968', '高度基础状态检查', '', '2014-07-21', '13', '27', '3', '0', '3');
INSERT INTO `inspectitem` VALUES ('969', '电液或液压制动推动器性能、油量', '', '2014-07-21', '14', '19', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('970', '门架、台车樑连接螺栓紧固', '', '2014-07-21', '14', '19', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('971', '卷筒两端轴承座固定', '', '2014-07-21', '14', '20', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('972', '力矩限制器的精度偏差调整', '', '2014-07-21', '14', '20', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('973', '电动机绝缘性能', '', '2014-07-21', '14', '20', '3', '0', '3');
INSERT INTO `inspectitem` VALUES ('974', '卷筒两端轴承座固定', '', '2014-07-21', '14', '21', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('975', '电动机绝缘性能', '', '2014-07-21', '14', '21', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('976', '回转轨道和滚轮磨损和紧固', '', '2014-07-21', '14', '22', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('977', '回转大轴承螺栓紧固', '', '2014-07-21', '14', '22', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('978', '电动机绝缘性能', '', '2014-07-21', '14', '22', '3', '0', '3');
INSERT INTO `inspectitem` VALUES ('979', '接地电阻值', '', '2014-07-21', '14', '25', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('980', '夹轨器是否损坏', '', '2014-07-21', '14', '27', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('981', '开式齿轮啮合情况、减速器拆洗换油，调整轴承间隙', '', '2014-07-21', '15', '19', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('982', '电动机绝缘性能', '', '2014-07-21', '15', '19', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('983', '减速机拆洗换油、调整轴承间隙', '', '2014-07-21', '15', '20', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('984', '减速机拆洗换油、调整轴承间隙', '', '2014-07-21', '15', '21', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('985', '蜗轮蜗杆传动箱拆洗换油、调整轴承间隙（低架式）', '', '2014-07-21', '15', '22', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('986', '立式减速器运转情况、清洗换油', '', '2014-07-21', '15', '22', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('987', '主要构件的焊缝检查', '', '2014-07-21', '15', '23', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('988', '金属结构的变形、裂纹、损伤', '', '2014-07-21', '15', '23', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('989', '整机油漆碰伤、龟裂、剥落及锈蚀检查', '', '2014-07-21', '15', '23', '3', '0', '3');
INSERT INTO `inspectitem` VALUES ('990', '台车、门架、圆筒、转柱、回转支撑、人字架、臂架等连接螺栓的紧固', '', '2014-07-21', '15', '23', '4', '0', '3');
INSERT INTO `inspectitem` VALUES ('991', '电气室、机房、司机室的密闭、牢固', '', '2014-07-21', '15', '27', '1', '0', '3');
INSERT INTO `inspectitem` VALUES ('992', '梯子、栏杆、走道、平台、踢脚板变形、缺失', '', '2014-07-21', '15', '27', '2', '0', '3');
INSERT INTO `inspectitem` VALUES ('993', '线路的绝缘性', '', '2014-07-21', '15', '27', '3', '0', '3');
INSERT INTO `inspectitem` VALUES ('994', '使用登记资料', '', '2014-07-22', '16', '13', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('995', '安全技术档案', '', '2014-07-22', '16', '13', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('996', '管理规章制度', '', '2014-07-22', '16', '13', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('997', '维护保养合同', '', '2014-07-22', '16', '13', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('998', '作业人员证件', '', '2014-07-22', '16', '13', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('999', '通道设置', '', '2014-07-22', '16', '13', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('1000', '通道照明', '', '2014-07-22', '16', '13', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('1001', '通道门高度', '', '2014-07-22', '16', '13', '8', '1', '2');
INSERT INTO `inspectitem` VALUES ('1002', '通道门宽度', '', '2014-07-22', '16', '13', '9', '1', '2');
INSERT INTO `inspectitem` VALUES ('1003', '机房温度', '', '2014-07-22', '16', '13', '10', '1', '2');
INSERT INTO `inspectitem` VALUES ('1004', '电源电压', '', '2014-07-22', '16', '13', '11', '1', '2');
INSERT INTO `inspectitem` VALUES ('1005', '照明及开关', '', '2014-07-22', '16', '13', '12', '0', '2');
INSERT INTO `inspectitem` VALUES ('1006', '断错相保护', '', '2014-07-22', '16', '13', '13', '0', '2');
INSERT INTO `inspectitem` VALUES ('1007', '电路控制关系', '', '2014-07-22', '16', '13', '14', '0', '2');
INSERT INTO `inspectitem` VALUES ('1008', '接地连接', '', '2014-07-22', '16', '13', '15', '0', '2');
INSERT INTO `inspectitem` VALUES ('1009', '电气安全绝缘', '', '2014-07-22', '16', '13', '16', '1', '2');
INSERT INTO `inspectitem` VALUES ('1010', '动力电气绝缘', '', '2014-07-22', '16', '13', '17', '1', '2');
INSERT INTO `inspectitem` VALUES ('1011', '照明电气绝缘', '', '2014-07-22', '16', '13', '18', '1', '2');
INSERT INTO `inspectitem` VALUES ('1012', '工作状况', '', '2014-07-22', '16', '13', '19', '0', '2');
INSERT INTO `inspectitem` VALUES ('1013', '轮槽磨损', '', '2014-07-22', '16', '13', '20', '0', '2');
INSERT INTO `inspectitem` VALUES ('1014', '手动紧急装置', '', '2014-07-22', '16', '13', '21', '0', '2');
INSERT INTO `inspectitem` VALUES ('1015', '电动紧急装置', '', '2014-07-22', '16', '13', '22', '0', '2');
INSERT INTO `inspectitem` VALUES ('1016', '应急救援程序', '', '2014-07-22', '16', '13', '23', '0', '2');
INSERT INTO `inspectitem` VALUES ('1017', '电气安全装置', '', '2014-07-22', '16', '13', '24', '0', '2');
INSERT INTO `inspectitem` VALUES ('1018', '动作速度校验', '', '2014-07-22', '16', '13', '25', '0', '2');
INSERT INTO `inspectitem` VALUES ('1019', '超时保护试验', '', '2014-07-22', '16', '13', '26', '0', '2');
INSERT INTO `inspectitem` VALUES ('1020', '安全钳试验', '', '2014-07-22', '16', '13', '27', '0', '2');
INSERT INTO `inspectitem` VALUES ('1021', '对重联动试验', '', '2014-07-22', '16', '13', '28', '0', '2');
INSERT INTO `inspectitem` VALUES ('1022', '曳引力试验', '', '2014-07-22', '16', '13', '29', '0', '2');
INSERT INTO `inspectitem` VALUES ('1023', '上行制动试验', '', '2014-07-22', '16', '13', '30', '0', '2');
INSERT INTO `inspectitem` VALUES ('1024', '运行试验', '', '2014-07-22', '16', '13', '31', '0', '2');
INSERT INTO `inspectitem` VALUES ('1025', '下行制动试验', '', '2014-07-22', '16', '13', '32', '0', '2');
INSERT INTO `inspectitem` VALUES ('1026', '接地故障保护', '', '2014-07-22', '16', '13', '33', '0', '2');
INSERT INTO `inspectitem` VALUES ('1027', '安全门电气安全装置', '', '2014-07-22', '16', '16', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('1028', '安全门门锁', '', '2014-07-22', '16', '16', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('1029', '检修门电气安全装置', '', '2014-07-22', '16', '16', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('1030', '检修门门锁', '', '2014-07-22', '16', '16', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('1031', '井道照明', '', '2014-07-22', '16', '16', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('1032', '随行电缆', '', '2014-07-22', '16', '16', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('1033', '限速绳张紧', '', '2014-07-22', '16', '16', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('1034', '上下极限开关', '', '2014-07-22', '16', '16', '8', '0', '2');
INSERT INTO `inspectitem` VALUES ('1035', '轿顶检修开关', '', '2014-07-22', '16', '16', '9', '0', '2');
INSERT INTO `inspectitem` VALUES ('1036', '轿顶急停开关', '', '2014-07-22', '16', '16', '10', '0', '2');
INSERT INTO `inspectitem` VALUES ('1037', '最高控制权限', '', '2014-07-22', '16', '16', '11', '0', '2');
INSERT INTO `inspectitem` VALUES ('1038', '轿顶电气安全装置', '', '2014-07-22', '16', '16', '12', '0', '2');
INSERT INTO `inspectitem` VALUES ('1039', '轿顶电气停止装置', '', '2014-07-22', '16', '16', '13', '0', '2');
INSERT INTO `inspectitem` VALUES ('1040', '悬挂装置状况', '', '2014-07-22', '16', '16', '14', '0', '2');
INSERT INTO `inspectitem` VALUES ('1041', '补偿装置状况', '', '2014-07-22', '16', '16', '15', '0', '2');
INSERT INTO `inspectitem` VALUES ('1042', '端部固定', '', '2014-07-22', '16', '16', '16', '0', '2');
INSERT INTO `inspectitem` VALUES ('1043', '补偿绳端固定', '', '2014-07-22', '16', '16', '17', '0', '2');
INSERT INTO `inspectitem` VALUES ('1044', '补偿绳安全装置', '', '2014-07-22', '16', '16', '18', '0', '2');
INSERT INTO `inspectitem` VALUES ('1045', '补偿绳防跳', '', '2014-07-22', '16', '16', '19', '0', '2');
INSERT INTO `inspectitem` VALUES ('1046', '松绳、链保护', '', '2014-07-22', '16', '16', '20', '0', '2');
INSERT INTO `inspectitem` VALUES ('1047', '选择部件防护', '', '2014-07-22', '16', '16', '21', '0', '2');
INSERT INTO `inspectitem` VALUES ('1048', '门窗安全装置', '', '2014-07-22', '16', '15', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('1049', '对重固定', '', '2014-07-22', '16', '15', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('1050', '轿厢控制条件', '', '2014-07-22', '16', '15', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('1051', '紧急照明', '', '2014-07-22', '16', '15', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('1052', '紧急报警', '', '2014-07-22', '16', '15', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('1053', '地坎护脚板', '', '2014-07-22', '16', '15', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('1054', '超载保护装置', '', '2014-07-22', '16', '15', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('1055', '运行试验', '', '2014-07-22', '16', '15', '8', '0', '2');
INSERT INTO `inspectitem` VALUES ('1056', '门扇间隙', '', '2014-07-22', '16', '15', '9', '0', '2');
INSERT INTO `inspectitem` VALUES ('1057', '玻璃门标记', '', '2014-07-22', '16', '15', '10', '0', '2');
INSERT INTO `inspectitem` VALUES ('1058', '玻璃门固定件', '', '2014-07-22', '16', '15', '11', '0', '2');
INSERT INTO `inspectitem` VALUES ('1059', '防拖拽措施', '', '2014-07-22', '16', '15', '12', '0', '2');
INSERT INTO `inspectitem` VALUES ('1060', '门夹人保护', '', '2014-07-22', '16', '15', '13', '0', '2');
INSERT INTO `inspectitem` VALUES ('1061', '门运行导向', '', '2014-07-22', '16', '15', '14', '0', '2');
INSERT INTO `inspectitem` VALUES ('1062', '轿门紧锁装置', '', '2014-07-22', '16', '15', '15', '0', '2');
INSERT INTO `inspectitem` VALUES ('1063', '轿门闭合装置', '', '2014-07-22', '16', '15', '16', '0', '2');
INSERT INTO `inspectitem` VALUES ('1064', '最不利点间隙', '', '2014-07-22', '16', '15', '17', '0', '2');
INSERT INTO `inspectitem` VALUES ('1065', '底坑环境', '', '2014-07-22', '16', '14', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('1066', '停止装置', '', '2014-07-22', '16', '14', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('1067', '缓冲器固定', '', '2014-07-22', '16', '14', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('1068', '缓冲器电气安全装置', '', '2014-07-22', '16', '14', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('1069', '对重复位时间', '', '2014-07-22', '16', '14', '5', '1', '2');
INSERT INTO `inspectitem` VALUES ('1070', '自动关闭装置', '', '2014-07-22', '16', '17', '1', '0', '2');
INSERT INTO `inspectitem` VALUES ('1071', '紧急开锁装置', '', '2014-07-22', '16', '17', '2', '0', '2');
INSERT INTO `inspectitem` VALUES ('1072', '门锁紧型式', '', '2014-07-22', '16', '17', '3', '0', '2');
INSERT INTO `inspectitem` VALUES ('1073', '元件啮合长度', '', '2014-07-22', '16', '17', '4', '0', '2');
INSERT INTO `inspectitem` VALUES ('1074', '锁紧安全装置', '', '2014-07-22', '16', '17', '5', '0', '2');
INSERT INTO `inspectitem` VALUES ('1075', '机电连锁', '', '2014-07-22', '16', '17', '6', '0', '2');
INSERT INTO `inspectitem` VALUES ('1076', '闭合安全装置', '', '2014-07-22', '16', '17', '7', '0', '2');
INSERT INTO `inspectitem` VALUES ('1077', '消防功能试验', '', '2014-07-22', '16', '17', '8', '0', '2');
INSERT INTO `inspectitem` VALUES ('1078', '门扇间隙', '', '2014-07-22', '16', '17', '9', '0', '2');
INSERT INTO `inspectitem` VALUES ('1079', '最不利点间隙', '', '2014-07-22', '16', '17', '10', '0', '2');
INSERT INTO `inspectitem` VALUES ('1080', '门刀间隙', '', '2014-07-22', '16', '17', '11', '0', '2');
INSERT INTO `inspectitem` VALUES ('1081', '滚轮间隙', '', '2014-07-22', '16', '17', '12', '0', '2');
INSERT INTO `inspectitem` VALUES ('1082', 'cffccv', 'f', '2014-08-23', '1', '5', 'f', '0', '1');
INSERT INTO `inspectitem` VALUES ('1083', 'zxxx', 'f', '2014-08-23', '1', '5', '1', '0', '1');

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
  `createTime` datetime DEFAULT NULL,
  `inspectTime` datetime DEFAULT NULL,
  `maintainId` bigint(20) DEFAULT NULL,
  `maintainSuggest` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2985 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectitemrecord
-- ----------------------------
INSERT INTO `inspectitemrecord` VALUES ('2941', '1', '5', '23', '2', '异常', '86', '6', '13', '1', '聊几句', '2014-09-02 11:02:42', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2942', '1', '5', '24', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:42', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2943', '1', '6', '25', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:42', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2944', '1', '5', '26', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:42', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2945', '1', '5', '27', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:42', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2946', '1', '5', '28', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:42', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2947', '1', '5', '29', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:42', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2948', '1', '5', '30', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:42', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2949', '1', '5', '31', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2950', '1', '5', '32', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2951', '1', '5', '33', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2952', '1', '5', '34', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2953', '1', '6', '35', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2954', '1', '6', '36', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2955', '1', '6', '37', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2956', '1', '6', '38', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2957', '1', '6', '39', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2958', '1', '6', '40', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2959', '1', '6', '41', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2960', '1', '6', '42', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2961', '1', '7', '43', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2962', '1', '7', '44', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2963', '1', '7', '45', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2964', '1', '7', '46', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2965', '1', '7', '47', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2966', '1', '7', '48', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2967', '1', '7', '49', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2968', '1', '7', '50', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2969', '1', '7', '51', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2970', '1', '7', '52', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2971', '1', '7', '53', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2972', '1', '7', '54', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2973', '1', '7', '55', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2974', '1', '8', '56', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2975', '1', '8', '57', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2976', '1', '8', '58', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:43', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2977', '1', '8', '59', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:44', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2978', '1', '8', '60', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:44', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2979', '1', '8', '61', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:44', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2980', '1', '8', '62', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:44', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2981', '1', '8', '63', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:44', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2982', '1', '8', '64', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:44', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2983', '1', '8', '65', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:44', '2014-08-15 22:06:48', '0', null);
INSERT INTO `inspectitemrecord` VALUES ('2984', '1', '8', '66', '1', '正常', '86', '6', '13', '1', null, '2014-09-02 11:02:44', '2014-08-15 22:06:48', '0', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=2815 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectitem_choice
-- ----------------------------
INSERT INTO `inspectitem_choice` VALUES ('1', '17', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('2', '17', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('3', '17', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('4', '17', '4', '1');
INSERT INTO `inspectitem_choice` VALUES ('5', '18', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('6', '18', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('7', '18', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('8', '18', '4', '1');
INSERT INTO `inspectitem_choice` VALUES ('32', '23', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('33', '23', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('34', '23', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('35', '24', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('36', '24', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('37', '24', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('44', '27', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('45', '27', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('46', '27', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('47', '28', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('48', '28', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('49', '28', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('50', '29', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('51', '29', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('52', '29', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('53', '30', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('54', '30', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('55', '30', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('56', '31', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('57', '31', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('58', '31', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('59', '32', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('60', '32', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('61', '32', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('62', '33', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('63', '33', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('64', '33', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('65', '34', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('66', '34', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('67', '34', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('68', '35', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('69', '35', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('70', '35', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('71', '36', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('72', '36', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('73', '36', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('74', '37', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('75', '37', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('76', '37', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('77', '38', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('78', '38', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('79', '38', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('80', '39', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('81', '39', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('82', '39', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('83', '40', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('84', '40', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('85', '40', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('86', '41', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('87', '41', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('88', '41', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('89', '42', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('90', '42', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('91', '42', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('92', '43', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('93', '43', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('94', '43', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('95', '44', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('96', '44', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('97', '44', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('98', '45', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('99', '45', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('100', '45', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('101', '46', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('102', '46', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('103', '46', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('104', '47', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('105', '47', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('106', '47', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('107', '48', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('108', '48', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('109', '48', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('110', '49', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('111', '49', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('112', '49', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('113', '50', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('114', '50', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('115', '50', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('116', '51', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('117', '51', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('118', '51', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('119', '52', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('120', '52', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('121', '52', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('122', '53', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('123', '53', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('124', '53', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('125', '54', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('126', '54', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('127', '54', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('128', '55', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('129', '55', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('130', '55', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('131', '56', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('132', '56', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('133', '56', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('134', '57', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('135', '57', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('136', '57', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('137', '58', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('138', '58', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('139', '58', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('140', '59', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('141', '59', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('142', '59', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('143', '60', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('144', '60', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('145', '60', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('146', '61', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('147', '61', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('148', '61', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('149', '62', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('150', '62', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('151', '62', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('152', '63', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('153', '63', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('154', '63', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('155', '64', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('156', '64', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('157', '64', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('158', '65', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('159', '65', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('160', '65', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('161', '66', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('162', '66', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('163', '66', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('166', '67', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('169', '67', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('172', '67', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('175', '70', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('178', '70', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('181', '70', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('184', '73', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('187', '73', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('190', '73', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('193', '76', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('196', '76', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('199', '76', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('202', '79', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('205', '79', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('208', '79', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('211', '82', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('214', '82', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('217', '82', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('220', '85', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('223', '85', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('226', '85', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('229', '88', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('232', '88', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('235', '88', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('238', '91', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('241', '91', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('244', '91', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('247', '94', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('250', '94', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('253', '94', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('256', '97', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('259', '97', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('262', '97', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('265', '100', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('268', '100', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('271', '100', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('274', '103', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('277', '103', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('280', '103', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('283', '106', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('286', '106', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('289', '106', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('292', '109', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('295', '109', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('298', '109', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('301', '112', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('304', '112', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('307', '112', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('310', '115', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('313', '115', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('316', '115', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('319', '118', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('322', '118', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('325', '118', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('328', '121', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('331', '121', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('334', '121', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('337', '124', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('340', '124', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('343', '124', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('346', '127', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('349', '127', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('352', '127', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('355', '130', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('358', '130', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('361', '130', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('364', '133', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('367', '133', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('370', '133', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('373', '136', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('376', '136', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('379', '136', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('382', '139', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('385', '139', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('388', '139', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('391', '142', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('394', '142', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('397', '142', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('400', '145', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('403', '145', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('406', '145', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('409', '148', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('412', '148', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('415', '148', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('418', '151', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('421', '151', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('424', '151', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('427', '154', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('430', '154', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('433', '154', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('436', '157', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('439', '157', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('442', '157', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('445', '160', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('448', '160', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('451', '160', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('454', '163', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('457', '163', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('460', '163', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('463', '166', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('466', '166', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('469', '166', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('472', '169', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('475', '169', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('478', '169', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('481', '172', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('484', '172', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('487', '172', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('490', '175', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('493', '175', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('496', '175', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('499', '178', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('502', '178', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('505', '178', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('508', '181', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('511', '181', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('514', '181', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('517', '184', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('520', '184', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('523', '184', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('526', '187', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('529', '187', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('532', '187', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('535', '190', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('538', '190', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('541', '190', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('544', '193', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('547', '193', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('550', '193', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('553', '196', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('556', '196', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('559', '196', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('562', '199', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('565', '199', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('568', '199', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('571', '202', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('574', '202', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('577', '202', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('580', '205', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('583', '205', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('586', '205', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('589', '208', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('592', '208', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('595', '208', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('598', '211', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('601', '211', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('604', '211', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('607', '214', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('610', '214', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('613', '214', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('616', '217', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('619', '217', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('622', '217', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('625', '220', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('628', '220', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('631', '220', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('634', '223', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('637', '223', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('640', '223', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('643', '226', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('646', '226', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('649', '226', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('652', '229', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('655', '229', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('658', '229', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('661', '232', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('664', '232', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('667', '232', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('670', '235', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('673', '235', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('676', '235', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('679', '238', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('682', '238', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('685', '238', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('688', '241', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('691', '241', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('694', '241', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('697', '244', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('700', '244', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('703', '244', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('706', '247', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('709', '247', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('712', '247', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('715', '250', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('718', '250', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('721', '250', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('724', '253', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('727', '253', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('730', '253', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('733', '256', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('736', '256', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('739', '256', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('742', '259', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('745', '259', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('748', '259', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('751', '262', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('754', '262', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('757', '262', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('760', '265', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('763', '265', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('766', '265', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('769', '268', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('772', '268', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('775', '268', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('778', '271', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('781', '271', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('784', '271', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('787', '274', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('790', '274', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('793', '274', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('796', '277', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('799', '277', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('802', '277', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('805', '280', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('808', '280', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('811', '280', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('814', '283', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('817', '283', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('820', '283', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('823', '286', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('826', '286', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('829', '286', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('832', '289', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('835', '289', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('838', '289', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('841', '292', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('844', '292', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('847', '292', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('850', '295', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('853', '295', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('856', '295', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('859', '298', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('862', '298', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('865', '298', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('868', '301', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('871', '301', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('874', '301', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('877', '304', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('880', '304', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('883', '304', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('886', '307', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('889', '307', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('892', '307', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('895', '310', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('898', '310', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('901', '310', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('904', '313', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('907', '313', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('910', '313', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('913', '316', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('916', '316', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('919', '316', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('922', '319', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('925', '319', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('928', '319', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('931', '322', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('934', '322', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('937', '322', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('940', '325', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('943', '325', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('946', '325', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('949', '328', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('952', '328', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('955', '328', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('958', '331', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('961', '331', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('964', '331', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('967', '334', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('970', '334', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('973', '334', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('976', '337', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('979', '337', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('982', '337', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('985', '340', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('988', '340', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('991', '340', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('994', '343', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('997', '343', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1000', '343', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1003', '346', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1006', '346', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1009', '346', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1012', '349', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1015', '349', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1018', '349', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1021', '352', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1024', '352', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1027', '352', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1030', '355', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1033', '355', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1036', '355', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1039', '358', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1042', '358', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1045', '358', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1048', '361', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1051', '361', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1054', '361', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1057', '364', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1060', '364', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1063', '364', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1066', '367', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1069', '367', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1072', '367', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1075', '370', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1078', '370', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1081', '370', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1084', '373', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1087', '373', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1090', '373', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1093', '376', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1096', '376', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1099', '376', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1102', '379', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1105', '379', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1108', '379', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1111', '382', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1114', '382', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1117', '382', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1120', '385', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1123', '385', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1126', '385', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1129', '388', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1132', '388', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1135', '388', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1138', '391', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1141', '391', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1144', '391', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1147', '394', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1150', '394', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1153', '394', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1156', '397', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1159', '397', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1162', '397', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1165', '400', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1168', '400', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1171', '400', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1174', '403', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1177', '403', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1180', '403', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1183', '406', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1186', '406', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1189', '406', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1192', '409', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1195', '409', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1198', '409', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1201', '412', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1204', '412', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1207', '412', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1210', '415', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1213', '415', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1216', '415', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1219', '418', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1222', '418', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1225', '418', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1228', '421', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1231', '421', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1234', '421', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1237', '424', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1240', '424', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1243', '424', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1246', '427', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1249', '427', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1252', '427', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1255', '430', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1258', '430', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1261', '430', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1264', '433', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1267', '433', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1270', '433', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1273', '436', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1276', '436', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1279', '436', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1282', '439', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1285', '439', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1288', '439', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1291', '442', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1294', '442', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1297', '442', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1300', '445', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1303', '445', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1306', '445', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1309', '448', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1312', '448', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1315', '448', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1318', '451', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1321', '451', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1324', '451', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1327', '454', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1330', '454', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1333', '454', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1336', '457', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1339', '457', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1342', '457', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1345', '460', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1348', '460', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1351', '460', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1354', '463', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1357', '463', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1360', '463', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1363', '466', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1366', '466', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1369', '466', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1372', '469', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1375', '469', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1378', '469', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1381', '472', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1384', '472', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1387', '472', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1390', '475', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1393', '475', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1396', '475', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1399', '478', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1402', '478', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1405', '478', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1408', '481', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1411', '481', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1414', '481', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1417', '484', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1420', '484', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1423', '484', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1426', '487', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1429', '487', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1432', '487', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1435', '490', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1438', '490', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1441', '490', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1444', '493', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1447', '493', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1450', '493', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1453', '496', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1456', '496', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1459', '496', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1462', '499', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1465', '499', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1468', '499', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1471', '502', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1474', '502', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1477', '502', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1480', '505', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1483', '505', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1486', '505', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1489', '508', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1492', '508', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1495', '508', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1498', '511', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1501', '511', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1504', '511', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1507', '514', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1510', '514', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1513', '514', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1516', '517', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1519', '517', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1522', '517', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1525', '520', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1528', '520', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1531', '520', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1534', '523', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1537', '523', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1540', '523', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1543', '526', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1546', '526', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1549', '526', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1552', '529', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1555', '529', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1558', '529', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1561', '532', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1564', '532', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1567', '532', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1570', '535', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1573', '535', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1576', '535', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1579', '538', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1582', '538', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1585', '538', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1588', '541', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1591', '541', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1594', '541', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1597', '544', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1600', '544', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1603', '544', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1606', '547', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1609', '547', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1612', '547', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1615', '550', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1618', '550', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1621', '550', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1624', '553', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1627', '553', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1630', '553', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1633', '556', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1636', '556', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1639', '556', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1642', '559', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1645', '559', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1648', '559', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1651', '562', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1654', '562', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1657', '562', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1660', '565', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1663', '565', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1666', '565', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1669', '568', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1672', '568', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1675', '568', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1678', '571', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1681', '571', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1684', '571', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1687', '574', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1690', '574', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1693', '574', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1696', '577', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1699', '577', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1702', '577', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1705', '580', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1708', '580', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1711', '580', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1714', '583', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1717', '583', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1720', '583', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1723', '586', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1726', '586', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1729', '586', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1732', '589', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1735', '589', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1738', '589', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1741', '592', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1744', '592', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1747', '592', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1750', '595', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1753', '595', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1756', '595', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1759', '598', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1762', '598', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1765', '598', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1768', '601', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1771', '601', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1774', '601', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1777', '604', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1780', '604', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1783', '604', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1786', '607', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1789', '607', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1792', '607', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1795', '610', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1798', '610', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1801', '610', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1804', '613', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1807', '613', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1810', '613', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1813', '616', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1816', '616', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1819', '616', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1822', '619', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1825', '619', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1828', '619', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1831', '622', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1834', '622', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1837', '622', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1840', '625', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1843', '625', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1846', '625', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1849', '628', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1852', '628', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1855', '628', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1858', '631', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1861', '631', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1864', '631', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1867', '634', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1870', '634', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1873', '634', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1876', '637', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1879', '637', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1882', '637', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1885', '640', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1888', '640', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1891', '640', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1894', '643', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1897', '643', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1900', '643', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1903', '646', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1906', '646', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1909', '646', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1912', '649', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1915', '649', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1918', '649', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1921', '652', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1924', '652', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1927', '652', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1930', '655', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1933', '655', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1936', '655', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1939', '658', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1942', '658', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1945', '658', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1948', '661', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1951', '661', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1954', '661', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1957', '664', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1960', '664', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1963', '664', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1966', '667', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1969', '667', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1972', '667', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1975', '670', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1978', '670', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1981', '670', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1984', '673', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1987', '673', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1990', '673', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('1993', '676', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('1996', '676', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('1999', '676', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('2000', '683', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('2001', '683', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('2002', '683', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('2010', '686', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2011', '686', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2018', '697', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2019', '697', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2020', '698', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2021', '698', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2022', '691', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2023', '692', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2024', '692', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2026', '694', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2027', '695', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2028', '696', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2029', '696', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2030', '699', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2031', '699', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2032', '700', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2033', '700', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2034', '701', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2035', '701', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2036', '702', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2037', '702', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2038', '703', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2039', '703', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2040', '704', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2041', '704', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2042', '705', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2043', '705', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2044', '706', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2045', '706', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2046', '707', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2047', '707', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2048', '708', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2049', '708', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2050', '709', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2051', '709', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2052', '710', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2053', '710', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2054', '711', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2055', '711', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2056', '712', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2057', '712', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2058', '713', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2059', '713', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2060', '714', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2061', '714', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2062', '715', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2063', '715', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2064', '716', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2065', '716', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2066', '684', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2067', '685', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2068', '687', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2069', '688', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2070', '689', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2071', '690', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2072', '693', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2073', '717', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2074', '717', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2075', '718', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2076', '718', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2077', '719', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2078', '719', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2079', '720', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2080', '720', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2081', '721', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2082', '721', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2083', '722', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2084', '722', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2085', '723', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2086', '723', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2087', '724', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2088', '724', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2089', '725', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2090', '725', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2091', '726', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2092', '726', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2093', '727', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2094', '727', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2095', '728', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2096', '728', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2109', '25', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('2110', '25', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('2111', '25', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('2121', '734', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2122', '734', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2123', '735', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2124', '735', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2125', '736', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2126', '736', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2127', '737', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2128', '737', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2129', '738', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2130', '738', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2131', '739', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2132', '739', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2133', '740', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2134', '740', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2135', '741', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2136', '741', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2137', '742', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2138', '742', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2139', '743', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2140', '743', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2141', '744', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2142', '744', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2143', '745', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2144', '745', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2145', '746', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2146', '746', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2147', '747', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2148', '747', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2149', '748', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2150', '748', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2151', '749', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2152', '749', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2153', '750', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2154', '750', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2155', '751', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2156', '751', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2157', '752', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2158', '752', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2159', '753', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2160', '753', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2161', '754', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2162', '754', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2163', '755', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2164', '755', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2165', '756', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2166', '756', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2167', '757', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2168', '757', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2169', '758', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2170', '758', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2171', '759', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2172', '759', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2173', '760', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2174', '760', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2175', '761', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2176', '761', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2177', '762', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2178', '762', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2179', '763', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2180', '763', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2181', '764', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2182', '764', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2183', '765', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2184', '765', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2185', '766', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2186', '766', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2187', '767', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2188', '767', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2189', '768', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2190', '768', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2191', '769', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2192', '769', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2193', '770', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2194', '770', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2195', '771', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2196', '771', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2197', '772', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2198', '772', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2199', '773', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2200', '773', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2201', '774', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2202', '774', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2203', '775', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2204', '775', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2205', '776', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2206', '776', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2207', '777', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2208', '777', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2209', '778', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2210', '778', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2211', '779', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2212', '779', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2213', '780', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2214', '780', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2215', '781', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2216', '781', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2217', '782', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2218', '782', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2219', '783', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2220', '783', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2221', '784', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2222', '784', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2223', '785', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2224', '785', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2225', '786', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2226', '786', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2227', '787', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2228', '787', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2229', '788', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2230', '788', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2231', '789', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2232', '789', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2233', '790', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2234', '790', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2235', '791', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2236', '791', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2237', '792', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2238', '792', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2239', '793', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2240', '793', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2241', '794', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2242', '794', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2243', '795', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2244', '795', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2245', '796', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2246', '796', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2247', '797', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2248', '797', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2249', '798', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2250', '798', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2251', '799', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2252', '799', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2253', '800', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2254', '800', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2255', '801', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2256', '801', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2257', '802', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2258', '802', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2259', '803', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2260', '803', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2261', '804', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2262', '804', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2263', '805', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2264', '805', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2265', '806', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2266', '806', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2267', '807', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2268', '807', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2269', '808', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2270', '808', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2271', '809', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2272', '809', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2273', '810', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2274', '810', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2275', '811', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2276', '811', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2277', '812', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2278', '812', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2279', '813', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2280', '813', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2281', '814', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2282', '814', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2283', '815', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2284', '815', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2285', '816', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2286', '816', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2287', '817', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2288', '817', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2289', '818', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2290', '818', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2291', '819', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2292', '819', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2293', '820', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2294', '820', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2295', '821', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2296', '821', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2297', '822', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2298', '822', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2299', '823', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2300', '823', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2301', '824', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2302', '824', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2303', '825', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2304', '825', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2305', '826', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2306', '826', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2307', '827', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2308', '827', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2309', '828', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2310', '828', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2311', '829', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2312', '829', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2313', '830', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2314', '830', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2315', '831', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2316', '831', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2317', '832', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2318', '832', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2319', '833', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2320', '833', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2321', '834', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2322', '834', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2323', '835', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2324', '835', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2325', '836', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2326', '836', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2327', '837', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2328', '837', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2329', '838', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2330', '838', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2331', '839', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2332', '839', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2333', '840', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2334', '840', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2335', '841', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2336', '841', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2337', '842', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2338', '842', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2339', '843', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2340', '843', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2341', '844', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2342', '844', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2343', '845', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2344', '845', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2345', '846', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2346', '846', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2347', '847', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2348', '847', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2349', '848', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2350', '848', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2351', '849', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2352', '849', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2353', '850', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2354', '850', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2355', '851', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2356', '851', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2357', '852', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2358', '852', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2359', '853', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2360', '853', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2361', '854', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2362', '854', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2363', '855', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2364', '855', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2365', '856', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2366', '856', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2367', '857', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2368', '857', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2369', '858', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2370', '858', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2371', '859', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2372', '859', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2373', '860', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2374', '860', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2375', '861', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2376', '861', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2377', '862', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2378', '862', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2379', '863', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2380', '863', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2381', '864', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2382', '864', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2383', '865', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2384', '865', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2385', '866', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2386', '866', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2387', '867', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2388', '867', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2389', '868', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2390', '868', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2391', '869', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2392', '869', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2393', '870', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2394', '870', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2395', '871', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2396', '871', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2397', '872', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2398', '872', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2399', '873', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2400', '873', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2401', '874', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2402', '874', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2403', '875', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2404', '875', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2405', '876', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2406', '876', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2407', '877', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2408', '877', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2409', '878', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2410', '878', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2411', '879', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2412', '879', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2413', '880', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2414', '880', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2415', '881', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2416', '881', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2417', '882', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2418', '882', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2419', '883', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2420', '883', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2421', '884', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2422', '884', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2423', '885', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2424', '885', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2425', '886', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2426', '886', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2427', '887', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2428', '887', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2429', '888', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2430', '888', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2431', '889', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2432', '889', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2433', '890', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2434', '890', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2435', '891', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2436', '891', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2437', '892', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2438', '892', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2439', '893', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2440', '893', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2441', '894', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2442', '894', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2443', '895', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2444', '895', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2445', '896', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2446', '896', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2447', '897', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2448', '897', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2449', '898', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2450', '898', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2451', '899', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2452', '899', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2453', '900', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2454', '900', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2455', '901', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2456', '901', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2457', '902', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2458', '902', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2459', '903', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2460', '903', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2461', '904', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2462', '904', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2463', '905', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2464', '905', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2465', '906', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2466', '906', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2467', '907', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2468', '907', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2469', '908', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2470', '908', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2471', '909', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2472', '909', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2473', '910', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2474', '910', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2475', '911', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2476', '911', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2477', '912', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2478', '912', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2479', '913', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2480', '913', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2481', '914', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2482', '914', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2483', '915', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2484', '915', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2485', '916', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2486', '916', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2487', '917', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2488', '917', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2489', '918', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2490', '918', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2512', '926', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2513', '926', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2514', '927', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2515', '927', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2516', '928', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2517', '928', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2518', '929', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2519', '929', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2520', '930', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2521', '930', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2522', '931', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2523', '931', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2524', '932', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2525', '932', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2526', '933', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2527', '933', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2528', '934', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2529', '934', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2530', '935', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2531', '935', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2532', '936', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2533', '936', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2534', '937', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2535', '937', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2536', '938', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2537', '938', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2538', '939', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2539', '939', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2540', '940', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2541', '940', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2542', '941', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2543', '941', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2544', '942', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2545', '942', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2546', '943', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2547', '943', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2548', '944', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2549', '944', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2550', '945', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2551', '945', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2552', '946', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2553', '946', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2554', '947', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2555', '947', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2556', '948', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2557', '948', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2558', '949', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2559', '949', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2560', '950', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2561', '950', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2562', '951', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2563', '951', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2564', '952', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2565', '952', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2566', '953', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2567', '953', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2568', '954', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2569', '954', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2570', '955', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2571', '955', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2572', '956', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2573', '956', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2574', '957', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2575', '957', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2576', '958', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2577', '958', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2578', '959', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2579', '959', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2580', '960', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2581', '960', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2582', '961', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2583', '961', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2584', '962', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2585', '962', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2586', '963', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2587', '963', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2588', '964', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2589', '964', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2590', '965', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2591', '965', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2592', '966', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2593', '966', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2594', '967', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2595', '967', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2596', '968', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2597', '968', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2598', '969', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2599', '969', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2600', '970', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2601', '970', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2602', '971', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2603', '971', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2604', '972', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2605', '972', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2606', '973', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2607', '973', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2608', '974', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2609', '974', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2610', '975', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2611', '975', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2612', '976', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2613', '976', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2614', '977', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2615', '977', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2616', '978', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2617', '978', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2618', '979', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2619', '979', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2620', '980', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2621', '980', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2622', '981', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2623', '981', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2624', '982', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2625', '982', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2626', '983', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2627', '983', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2628', '984', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2629', '984', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2630', '985', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2631', '985', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2632', '986', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2633', '986', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2634', '987', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2635', '987', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2636', '988', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2637', '988', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2638', '989', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2639', '989', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2640', '990', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2641', '990', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2642', '991', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2643', '991', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2644', '992', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2645', '992', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2646', '993', '6', '3');
INSERT INTO `inspectitem_choice` VALUES ('2647', '993', '7', '3');
INSERT INTO `inspectitem_choice` VALUES ('2648', '994', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2649', '994', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2650', '995', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2651', '995', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2652', '996', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2653', '996', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2654', '997', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2655', '997', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2656', '998', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2657', '998', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2658', '999', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2659', '999', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2660', '1000', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2661', '1000', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2662', '1005', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2663', '1005', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2664', '1006', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2665', '1006', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2666', '1007', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2667', '1007', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2668', '1008', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2669', '1008', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2670', '1012', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2671', '1012', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2672', '1013', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2673', '1013', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2674', '1014', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2675', '1014', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2676', '1015', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2677', '1015', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2678', '1016', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2679', '1016', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2680', '1017', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2681', '1017', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2682', '1018', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2683', '1018', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2684', '1019', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2685', '1019', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2686', '1020', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2687', '1020', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2688', '1021', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2689', '1021', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2690', '1022', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2691', '1022', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2692', '1023', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2693', '1023', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2694', '1024', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2695', '1024', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2696', '1025', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2697', '1025', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2698', '1026', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2699', '1026', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2700', '1027', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2701', '1027', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2702', '1028', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2703', '1028', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2704', '1029', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2705', '1029', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2706', '1030', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2707', '1030', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2708', '1031', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2709', '1031', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2710', '1032', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2711', '1032', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2712', '1033', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2713', '1033', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2714', '1034', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2715', '1034', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2716', '1035', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2717', '1035', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2718', '1036', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2719', '1036', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2720', '1037', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2721', '1037', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2722', '1038', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2723', '1038', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2724', '1039', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2725', '1039', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2726', '1040', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2727', '1040', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2728', '1041', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2729', '1041', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2730', '1042', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2731', '1042', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2732', '1043', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2733', '1043', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2734', '1044', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2735', '1044', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2736', '1045', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2737', '1045', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2738', '1046', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2739', '1046', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2740', '1047', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2741', '1047', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2742', '1048', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2743', '1048', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2744', '1049', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2745', '1049', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2746', '1050', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2747', '1050', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2748', '1051', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2749', '1051', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2750', '1052', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2751', '1052', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2752', '1053', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2753', '1053', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2754', '1054', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2755', '1054', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2756', '1055', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2757', '1055', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2758', '1056', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2759', '1056', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2760', '1057', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2761', '1057', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2762', '1058', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2763', '1058', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2764', '1059', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2765', '1059', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2766', '1060', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2767', '1060', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2768', '1061', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2769', '1061', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2770', '1062', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2771', '1062', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2772', '1063', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2773', '1063', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2774', '1064', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2775', '1064', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2776', '1065', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2777', '1065', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2778', '1066', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2779', '1066', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2780', '1067', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2781', '1067', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2782', '1068', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2783', '1068', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2784', '1070', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2785', '1070', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2786', '1071', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2787', '1071', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2788', '1072', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2789', '1072', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2790', '1073', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2791', '1073', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2792', '1074', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2793', '1074', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2794', '1075', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2795', '1075', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2796', '1076', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2797', '1076', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2798', '1077', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2799', '1077', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2800', '1078', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2801', '1078', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2802', '1079', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2803', '1079', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2804', '1080', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2805', '1080', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2806', '1081', '4', '2');
INSERT INTO `inspectitem_choice` VALUES ('2807', '1081', '5', '2');
INSERT INTO `inspectitem_choice` VALUES ('2808', '1083', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('2809', '1083', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('2810', '1083', '3', '1');
INSERT INTO `inspectitem_choice` VALUES ('2811', '1083', '8', '1');
INSERT INTO `inspectitem_choice` VALUES ('2812', '26', '1', '1');
INSERT INTO `inspectitem_choice` VALUES ('2813', '26', '2', '1');
INSERT INTO `inspectitem_choice` VALUES ('2814', '26', '3', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspecttable
-- ----------------------------
INSERT INTO `inspecttable` VALUES ('1', '机修人员点检表', '2014-05-29', '', '1');
INSERT INTO `inspecttable` VALUES ('2', '门机司机日常点检表', '2014-05-29', '', '1');
INSERT INTO `inspecttable` VALUES ('3', '门机队机械技术员点检表', '2014-06-13', '', '1');
INSERT INTO `inspecttable` VALUES ('4', '门机技术员电气日常点检表', '2014-06-21', '', '1');
INSERT INTO `inspecttable` VALUES ('5', '门机减速机专项点检卡', '2014-06-21', '', '1');
INSERT INTO `inspecttable` VALUES ('6', '门机周一定保专项点检卡片', '2014-06-21', '', '1');
INSERT INTO `inspecttable` VALUES ('7', '流动式起重机械定期检查表', '2014-06-30', '流动式起重机械定期检查表', '1');
INSERT INTO `inspecttable` VALUES ('8', '客、载货梯半月巡检表', '2014-07-17', '客、载货梯半月巡检表', '2');
INSERT INTO `inspecttable` VALUES ('9', '客、载货梯季巡检表', '2014-07-18', '客、载货梯季巡检表', '2');
INSERT INTO `inspecttable` VALUES ('10', '客、载货梯半年巡检表', '2014-07-19', '客、载货梯半年巡检表', '2');
INSERT INTO `inspecttable` VALUES ('11', '客、载货梯年巡检表', '2014-07-19', '客、载货梯年巡检表', '2');
INSERT INTO `inspecttable` VALUES ('12', '门座式起重机每班检查表', '2014-07-21', '门座式起重机每班检查', '3');
INSERT INTO `inspecttable` VALUES ('13', '门座式起重机每月检查表', '2014-07-21', '门座式起重机每月检查表', '3');
INSERT INTO `inspecttable` VALUES ('14', '门座式起重机每季度检查表', '2014-07-21', '门座式起重机每季度检查表', '3');
INSERT INTO `inspecttable` VALUES ('15', '门座式起重机每年检查表', '2014-07-21', '门座式起重机每年检查表             ', '3');
INSERT INTO `inspecttable` VALUES ('16', '客、载货梯定期检验表', '2014-07-22', '        客、载货梯定期检验表          ', '2');

-- ----------------------------
-- Table structure for `inspecttablerecord`
-- ----------------------------
DROP TABLE IF EXISTS `inspecttablerecord`;
CREATE TABLE `inspecttablerecord` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `inspectTableId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `inspectTime` datetime DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `exceptioncount` bigint(20) DEFAULT NULL,
  `mongoId` varchar(255) DEFAULT NULL,
  `exceptionId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspecttablerecord
-- ----------------------------
INSERT INTO `inspecttablerecord` VALUES ('86', '1', '6', '2014-08-15 22:06:48', '2014-09-02', '1', '5405335443dee2bab9b290f9', null, '13', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspecttag
-- ----------------------------
INSERT INTO `inspecttag` VALUES ('5', '行走区域', '行走区域', '2014-06-23', 'xingzou', '5', '13', '1');
INSERT INTO `inspecttag` VALUES ('6', '转盘区域', '转盘区域', '2014-06-23', 'zhuangpan', '6', '13', '1');
INSERT INTO `inspecttag` VALUES ('7', '司机室区域', '司机室区域', '2014-06-23', 'siji', '7', '13', '1');
INSERT INTO `inspecttag` VALUES ('8', '臂架区域', '臂架区域', '2014-06-23', 'bijia', '8', '13', '1');
INSERT INTO `inspecttag` VALUES ('9', '金属结构', '设备01金属结构点', '2014-06-30', 'IC01', '9', '21', '1');
INSERT INTO `inspecttag` VALUES ('10', '主要零部件', '设备02', '2014-06-30', 'IC02', '10', '21', '1');
INSERT INTO `inspecttag` VALUES ('11', '安全装置', '设备03', '2014-06-30', 'IC03', '11', '21', '1');
INSERT INTO `inspecttag` VALUES ('12', '液压、电气系统', '设备04', '2014-06-30', 'IC04', '12', '21', '1');
INSERT INTO `inspecttag` VALUES ('13', 'CSYMSQZJ', 'CSYMSQZJ01', '2014-07-21', '01', '19', '22', '3');
INSERT INTO `inspecttag` VALUES ('14', 'CSYMSQZJ', 'CSYMSQZJ02', '2014-07-21', '02', '20', '22', '3');
INSERT INTO `inspecttag` VALUES ('15', 'CSYMSQZJ', 'CSYMSQZJ03', '2014-07-21', '03', '21', '22', '3');
INSERT INTO `inspecttag` VALUES ('16', 'CSYMSQZJ', 'CSYMSQZJ04', '2014-07-21', '04', '22', '22', '3');
INSERT INTO `inspecttag` VALUES ('17', 'CSYMSQZJ', 'CSYMSQZJ05', '2014-07-21', '05', '23', '22', '3');
INSERT INTO `inspecttag` VALUES ('18', 'CSYMSQZJ', 'CSYMSQZJ06', '2014-07-21', '06', '25', '22', '3');
INSERT INTO `inspecttag` VALUES ('19', 'CSYMSQZJ', 'CSYMSQZJ07', '2014-07-21', '07', '27', '22', '3');

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
-- Table structure for `inspectversion`
-- ----------------------------
DROP TABLE IF EXISTS `inspectversion`;
CREATE TABLE `inspectversion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `versionCode` bigint(20) DEFAULT NULL,
  `downloadAddress` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectversion
-- ----------------------------
INSERT INTO `inspectversion` VALUES ('1', '2', 'app/inspect-android.apk', '1');

-- ----------------------------
-- Table structure for `inspect_locate`
-- ----------------------------
DROP TABLE IF EXISTS `inspect_locate`;
CREATE TABLE `inspect_locate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `inspectStartTime` datetime DEFAULT NULL,
  `inspectEndTime` datetime DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  `devName` varchar(255) DEFAULT NULL,
  `inspectTableId` bigint(20) DEFAULT NULL,
  `inspectTableName` varchar(255) DEFAULT NULL,
  `lng` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `deviceNum` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspect_locate
-- ----------------------------
INSERT INTO `inspect_locate` VALUES ('1', '14', 'xiaozhujun', '2014-08-05 16:25:41', '2014-07-11 10:18:52', '13', '门座式起重机#01', '1', '机修人员点检表', '114.380494', '30.507115', '1', '已完成', '武汉市武昌区烽火村郁馨花园', '2014-08-05 19:23:41', null);
INSERT INTO `inspect_locate` VALUES ('2', '3', 'suihui', '2014-08-05 16:25:41', '2014-07-11 17:26:02', '14', '门座式起重机#02', '2', '门机司机日常点检表', '113.177633', '27.859618', '1', '已完成', '中国湖南株洲市荷塘区红旗中路伟大国际广场d座903号', '2014-08-05 20:28:41', null);
INSERT INTO `inspect_locate` VALUES ('3', '6', '赵伟', '2014-07-11 09:27:50', '2014-07-11 10:27:55', '15', '门座式起重机#03', '3', '门机队机械技术员点检表', '114.26731', '30.603794', '1', '已完成', '罗家咀路5', '2014-07-23 20:30:25', null);

-- ----------------------------
-- Table structure for `inspect_plan`
-- ----------------------------
DROP TABLE IF EXISTS `inspect_plan`;
CREATE TABLE `inspect_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `rule` varchar(255) DEFAULT NULL,
  `inspectTableId` bigint(20) DEFAULT NULL,
  `dayStart` date DEFAULT NULL,
  `dayEnd` date DEFAULT NULL,
  `timeStart` varchar(255) DEFAULT NULL,
  `timeEnd` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspect_plan
-- ----------------------------
INSERT INTO `inspect_plan` VALUES ('11', '机械师每日点检', '机械师每日点检', '* * *', '3', '2014-07-11', '2020-07-11', '8', '12', '0', '2014-07-11', '1');
INSERT INTO `inspect_plan` VALUES ('12', '司机每日点检', '司机每日点检', '* * *', '2', '2014-07-11', '2015-07-11', '8', '12', '0', '2014-07-11', '1');
INSERT INTO `inspect_plan` VALUES ('13', '电气师每日点检', '电气师每日点检', '* * *', '4', '2014-07-11', '2020-07-11', '8', '12', '0', '2014-07-11', '1');
INSERT INTO `inspect_plan` VALUES ('14', '每班巡查1', '每班巡查一次', '* * *', '12', '2014-07-21', '2015-07-21', '1', '8', '0', '2014-07-21', '3');
INSERT INTO `inspect_plan` VALUES ('15', '每班巡查2', '每班巡查一次', '* * *', '12', '2014-07-21', '2015-07-21', '8', '16', '0', '2014-07-21', '3');

-- ----------------------------
-- Table structure for `inspect_task`
-- ----------------------------
DROP TABLE IF EXISTS `inspect_task`;
CREATE TABLE `inspect_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `inspectPlanId` bigint(20) DEFAULT NULL,
  `inspectTableId` bigint(20) DEFAULT NULL,
  `inspectTableRecordId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  `faultCount` int(11) DEFAULT NULL,
  `inspectTime` datetime DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  `taskDate` datetime DEFAULT NULL,
  `timeStart` int(11) DEFAULT NULL,
  `timeEnd` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspect_task
-- ----------------------------
INSERT INTO `inspect_task` VALUES ('10', '12', '2', '0', '6', '13', '0', null, '2014-07-11 09:52:58', '0', '1', '2014-07-11 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('11', '13', '4', '0', '7', '14', '0', null, '2014-07-11 09:52:58', '0', '1', '2014-07-11 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('12', '11', '3', '0', '8', '15', '0', null, '2014-07-11 09:52:58', '0', '1', '2014-07-11 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('13', '12', '2', '0', '6', '13', '0', null, '2014-07-12 01:00:00', '0', '1', '2014-07-12 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('14', '13', '4', '0', '7', '14', '0', null, '2014-07-12 01:00:00', '0', '1', '2014-07-12 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('15', '11', '3', '0', '8', '15', '0', null, '2014-07-12 01:00:00', '0', '1', '2014-07-12 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('16', '12', '2', '0', '10', '17', '0', null, '2014-07-12 01:00:00', '0', '1', '2014-07-12 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('17', '12', '2', '0', '6', '13', '0', null, '2014-07-13 01:00:00', '0', '1', '2014-07-13 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('18', '13', '4', '0', '7', '14', '0', null, '2014-07-13 01:00:00', '0', '1', '2014-07-13 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('19', '11', '3', '0', '8', '15', '0', null, '2014-07-13 01:00:00', '0', '1', '2014-07-13 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('20', '12', '2', '0', '10', '17', '0', null, '2014-07-13 01:00:00', '0', '1', '2014-07-13 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('21', '12', '2', '0', '6', '13', '0', null, '2014-07-14 01:00:00', '0', '1', '2014-07-14 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('22', '13', '4', '0', '7', '14', '0', null, '2014-07-14 01:00:00', '0', '1', '2014-07-14 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('23', '11', '3', '0', '8', '15', '0', null, '2014-07-14 01:00:00', '0', '1', '2014-07-14 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('24', '12', '2', '0', '10', '17', '0', null, '2014-07-14 01:00:00', '0', '1', '2014-07-14 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('25', '12', '2', '36', '6', '13', '2', '2014-07-15 11:14:43', '2014-07-15 01:00:00', '1', '1', '2014-07-15 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('26', '13', '4', '0', '7', '14', '0', null, '2014-07-15 01:00:00', '0', '1', '2014-07-15 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('27', '11', '3', '0', '8', '15', '0', null, '2014-07-15 01:00:00', '0', '1', '2014-07-15 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('28', '12', '2', '0', '10', '17', '0', null, '2014-07-15 01:00:00', '0', '1', '2014-07-15 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('29', '12', '2', '38', '6', '13', '2', '2014-07-16 11:14:43', '2014-07-16 01:00:00', '1', '1', '2014-07-16 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('30', '13', '4', '0', '7', '14', '0', null, '2014-07-16 01:00:00', '0', '1', '2014-07-16 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('31', '11', '3', '0', '8', '15', '0', null, '2014-07-16 01:00:00', '0', '1', '2014-07-16 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('32', '12', '2', '0', '10', '17', '0', null, '2014-07-16 01:00:00', '0', '1', '2014-07-16 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('33', '12', '2', '39', '6', '13', '2', '2014-07-17 11:14:43', '2014-07-17 01:00:00', '1', '1', '2014-07-17 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('34', '13', '4', '0', '7', '14', '0', null, '2014-07-17 01:00:00', '0', '1', '2014-07-17 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('35', '11', '3', '0', '8', '15', '0', null, '2014-07-17 01:00:00', '0', '1', '2014-07-17 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('36', '12', '2', '0', '10', '17', '0', null, '2014-07-17 01:00:00', '0', '1', '2014-07-17 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('37', '12', '2', '40', '6', '13', '2', '2014-07-18 11:14:43', '2014-07-18 01:00:00', '1', '1', '2014-07-18 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('38', '13', '4', '0', '7', '14', '0', null, '2014-07-18 01:00:00', '0', '1', '2014-07-18 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('39', '11', '3', '0', '8', '15', '0', null, '2014-07-18 01:00:00', '0', '1', '2014-07-18 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('40', '12', '2', '0', '10', '17', '0', null, '2014-07-18 01:00:00', '0', '1', '2014-07-18 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('41', '12', '2', '41', '6', '13', '2', '2014-07-19 11:14:43', '2014-07-19 01:00:00', '1', '1', '2014-07-19 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('42', '13', '4', '0', '7', '14', '0', null, '2014-07-19 01:00:00', '0', '1', '2014-07-19 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('43', '11', '3', '0', '8', '15', '0', null, '2014-07-19 01:00:00', '0', '1', '2014-07-19 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('44', '12', '2', '0', '10', '17', '0', null, '2014-07-19 01:00:00', '0', '1', '2014-07-19 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('45', '12', '2', '0', '6', '13', '0', null, '2014-07-20 01:00:00', '0', '1', '2014-07-20 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('46', '13', '4', '0', '7', '14', '0', null, '2014-07-20 01:00:00', '0', '1', '2014-07-20 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('47', '11', '3', '0', '8', '15', '0', null, '2014-07-20 01:00:00', '0', '1', '2014-07-20 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('48', '12', '2', '0', '10', '17', '0', null, '2014-07-20 01:00:00', '0', '1', '2014-07-20 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('49', '12', '2', '0', '6', '13', '0', null, '2014-07-20 01:00:00', '0', '1', '2014-07-20 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('50', '13', '4', '0', '7', '14', '0', null, '2014-07-20 01:00:00', '0', '1', '2014-07-20 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('51', '11', '3', '0', '8', '15', '0', null, '2014-07-20 01:00:00', '0', '1', '2014-07-20 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('52', '12', '2', '0', '10', '17', '0', null, '2014-07-20 01:00:00', '0', '1', '2014-07-20 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('53', '12', '2', '45', '6', '13', '1', '2014-07-21 11:47:03', '2014-07-21 01:00:00', '1', '1', '2014-07-21 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('54', '13', '4', '0', '7', '14', '0', null, '2014-07-21 01:00:00', '0', '1', '2014-07-21 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('55', '11', '3', '0', '8', '15', '0', null, '2014-07-21 01:00:00', '0', '1', '2014-07-21 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('56', '12', '2', '0', '10', '17', '0', null, '2014-07-21 01:00:00', '0', '1', '2014-07-21 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('61', '12', '2', '47', '6', '13', '4', '2014-07-22 10:17:31', '2014-07-22 01:00:00', '1', '1', '2014-07-22 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('62', '13', '4', '0', '7', '14', '0', null, '2014-07-22 01:00:00', '0', '1', '2014-07-22 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('63', '11', '3', '0', '8', '15', '0', null, '2014-07-22 01:00:00', '0', '1', '2014-07-22 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('64', '12', '2', '0', '10', '17', '0', null, '2014-07-22 01:00:00', '0', '1', '2014-07-22 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('65', '12', '2', '53', '6', '13', '1', '2014-07-23 11:01:29', '2014-07-23 01:00:00', '1', '1', '2014-07-23 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('66', '13', '4', '0', '7', '14', '0', null, '2014-07-23 01:00:00', '0', '1', '2014-07-23 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('67', '11', '3', '0', '8', '15', '0', null, '2014-07-23 01:00:00', '0', '1', '2014-07-23 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('68', '12', '2', '0', '10', '17', '0', null, '2014-07-23 01:00:00', '0', '1', '2014-07-23 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('70', '12', '2', '0', '6', '13', '0', null, '2014-08-01 01:00:01', '0', '1', '2014-08-01 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('73', '13', '4', '0', '7', '14', '0', null, '2014-08-01 01:00:01', '0', '1', '2014-08-01 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('76', '11', '3', '0', '8', '15', '0', null, '2014-08-01 01:00:01', '0', '1', '2014-08-01 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('79', '12', '2', '0', '10', '17', '0', null, '2014-08-01 01:00:01', '0', '1', '2014-08-01 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('80', '12', '2', '0', '6', '13', '0', null, '2014-08-09 01:00:00', '0', '1', '2014-08-09 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('81', '11', '3', '0', '8', '15', '0', null, '2014-08-09 01:00:00', '0', '1', '2014-08-09 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('82', '12', '2', '0', '10', '17', '0', null, '2014-08-09 01:00:00', '0', '1', '2014-08-09 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('83', '12', '2', '0', '6', '13', '0', null, '2014-08-10 01:00:00', '0', '1', '2014-08-10 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('84', '11', '3', '0', '8', '15', '0', null, '2014-08-10 01:00:00', '0', '1', '2014-08-10 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('85', '12', '2', '0', '10', '17', '0', null, '2014-08-10 01:00:00', '0', '1', '2014-08-10 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('86', '12', '2', '0', '6', '13', '0', null, '2014-09-02 01:00:00', '0', '1', '2014-09-02 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('87', '11', '3', '0', '8', '15', '0', null, '2014-09-02 01:00:00', '0', '1', '2014-09-02 00:00:00', '8', '12');
INSERT INTO `inspect_task` VALUES ('88', '12', '2', '0', '10', '17', '0', null, '2014-09-02 01:00:00', '0', '1', '2014-09-02 00:00:00', '8', '12');

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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

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
INSERT INTO `menu` VALUES ('30', '0', '数据分析', '1', '', '菜单');
INSERT INTO `menu` VALUES ('31', '30', '设备异常总数', '2', 'inspectReport/deviceCount.html', '数据分析');
INSERT INTO `menu` VALUES ('32', '30', '设备异常明细', '2', 'inspectReport/deviceInfo.html', '数据分析');
INSERT INTO `menu` VALUES ('33', '30', '人员点检异常总数', '2', 'inspectReport/peopleCount.html', '数据分析');
INSERT INTO `menu` VALUES ('34', '30', '人员点检异常明细', '2', 'inspectReport/peopleInfo.html', '数据分析');
INSERT INTO `menu` VALUES ('35', '30', '设备异常趋势分析', '2', 'inspectReport/deviceHistory.html', '数据分析');
INSERT INTO `menu` VALUES ('37', '0', '报表展示', '1', '', '菜单');
INSERT INTO `menu` VALUES ('38', '37', '报表展示', '2', 'inspectReport/reportShow.html', '报表展示');
INSERT INTO `menu` VALUES ('40', '0', '任务管理', '1', '', '菜单');
INSERT INTO `menu` VALUES ('41', '40', '添加计划', '2', 'task/addTaskPlan.html', '任务管理');
INSERT INTO `menu` VALUES ('42', '40', '计划列表', '2', 'task/listTaskPlan.html', '任务管理');
INSERT INTO `menu` VALUES ('43', '40', '任务派发', '2', 'task/dispatchTaskPlan.html', '任务管理');
INSERT INTO `menu` VALUES ('44', '4', '图片上传测试', '2', 'test/uploadTest.html', '点检表管理');
INSERT INTO `menu` VALUES ('45', '4', '获取版本', '2', 'test/versionTest.html', '点检表管理');

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of power
-- ----------------------------
INSERT INTO `power` VALUES ('1', '/rs/**', 'resource', '所有rest服务');
INSERT INTO `power` VALUES ('2', '/user.html', 'url', null);
INSERT INTO `power` VALUES ('3', '/admin.html', 'url', null);
INSERT INTO `power` VALUES ('4', '/index.html', 'url', null);
INSERT INTO `power` VALUES ('15', 'cas/**', 'service', 'cas client test from android');
INSERT INTO `power` VALUES ('16', '/index.jsp', 'resource', '首页');

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', 'sunhui', 'e68fa2bc61b75b8a06766e25905052c7', '男', 'ROLE_USER', '1', '启用', null);
INSERT INTO `user` VALUES ('4', 'liujinxia', 'c99c1cbefe13019978d90cb442cb8f78', '女', 'ROLE_ADMIN', '1', '启用', null);
INSERT INTO `user` VALUES ('6', 'zhaowei', 'e10adc3949ba59abbe56e057f20f883e', '男', 'ROLE_ADMIN', '1', '启用', null);
INSERT INTO `user` VALUES ('7', 'wangfuming', 'e10adc3949ba59abbe56e057f20f883e', '男', 'ROLE_ADMIN', '1', '启用', null);
INSERT INTO `user` VALUES ('8', '常建', 'e10adc3949ba59abbe56e057f20f883e', '男', 'ROLE_USER', '1', '启用', null);
INSERT INTO `user` VALUES ('9', '庞伟', 'e10adc3949ba59abbe56e057f20f883e', '男', 'ROLE_USER', '1', '启用', null);
INSERT INTO `user` VALUES ('10', '秦小娟', 'e10adc3949ba59abbe56e057f20f883e', '女', 'ROLE_USER', '1', '启用', null);
INSERT INTO `user` VALUES ('11', '孙伟', '698d51a19d8a121ce581499d7b701668', '男', 'ROLE_USER', '1', '启用', null);
INSERT INTO `user` VALUES ('12', '晋中', 'e10adc3949ba59abbe56e057f20f883e', '男', 'ROLE_USER', '1', '启用', null);
INSERT INTO `user` VALUES ('14', 'xiaozhujun', 'e10adc3949ba59abbe56e057f20f883e', '男', 'ROLE_USER;ROLE_ADMIN', '1', '启用', '/inspectManagementResource/userImage/1/xiaozhujun.JPG');
INSERT INTO `user` VALUES ('15', '测试刘', 'e10adc3949ba59abbe56e057f20f883e', '男', 'ROLE_USER', '1', '启用', null);
INSERT INTO `user` VALUES ('16', '测试刘B', 'c33367701511b4f6020ec61ded352059', '男', 'ROLE_ADMIN', '1', '启用', null);
INSERT INTO `user` VALUES ('17', 'manager', 'e10adc3949ba59abbe56e057f20f883e', '男', 'ROLE_USER', '1', '启用', null);
INSERT INTO `user` VALUES ('18', 'zouzhi', '2d9f6bb41b753609ba0a4d5a47154363', '男', 'ROLE_USER;ROLE_ADMIN', '2', '启用', null);
INSERT INTO `user` VALUES ('19', 'zouzhi1', '0dc346ec81d7df49b58022e5e6e34e6f', '男', 'ROLE_USER;ROLE_ADMIN', '3', '启用', null);
INSERT INTO `user` VALUES ('20', '刘点检', 'c4ca4238a0b923820dcc509a6f75849b', '男', 'ROLE_USER', '3', '启用', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_authority
-- ----------------------------
INSERT INTO `user_authority` VALUES ('6', '3', '1', 'sunhui', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('7', '4', '2', 'liujinxia', 'ROLE_ADMIN');
INSERT INTO `user_authority` VALUES ('9', '6', '2', 'zhaowei', 'ROLE_ADMIN');
INSERT INTO `user_authority` VALUES ('10', '7', '2', '王福明', 'ROLE_ADMIN');
INSERT INTO `user_authority` VALUES ('11', '8', '1', '常建', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('12', '9', '1', '庞伟', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('13', '10', '1', '秦小娟', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('14', '11', '1', '孙伟', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('15', '12', '1', '晋中', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('17', '15', '1', '测试刘', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('18', '16', '2', '测试刘B', 'ROLE_ADMIN');
INSERT INTO `user_authority` VALUES ('19', '17', '1', 'manager', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('20', '18', '1', 'zouzhi', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('21', '18', '2', 'zouzhi', 'ROLE_ADMIN');
INSERT INTO `user_authority` VALUES ('22', '14', '1', 'xiaozhujun', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('23', '14', '2', 'xiaozhujun', 'ROLE_ADMIN');
INSERT INTO `user_authority` VALUES ('24', '19', '1', 'zouzhi1', 'ROLE_USER');
INSERT INTO `user_authority` VALUES ('25', '19', '2', 'zouzhi1', 'ROLE_ADMIN');
INSERT INTO `user_authority` VALUES ('26', '20', '1', '刘点检', 'ROLE_USER');

-- ----------------------------
-- Table structure for `user_inspect_plan`
-- ----------------------------
DROP TABLE IF EXISTS `user_inspect_plan`;
CREATE TABLE `user_inspect_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  `inspectPlanId` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `appId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_inspect_plan
-- ----------------------------
INSERT INTO `user_inspect_plan` VALUES ('9', '6', '13', '12', '0', '2014-07-11', '1');
INSERT INTO `user_inspect_plan` VALUES ('10', '7', '14', '13', '0', '2014-07-11', '1');
INSERT INTO `user_inspect_plan` VALUES ('11', '8', '15', '11', '0', '2014-07-11', '1');
INSERT INTO `user_inspect_plan` VALUES ('12', '10', '17', '12', '0', '2014-07-11', '1');
