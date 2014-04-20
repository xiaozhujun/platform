
/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : riskmanagement

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2014-04-20 18:46:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `drole_address`
-- ----------------------------
DROP TABLE IF EXISTS `drole_address`;
CREATE TABLE `drole_address` (
  `id` bigint(20) NOT NULL,
  `droleId` bigint(20) DEFAULT NULL,
  `addressId` bigint(20) DEFAULT NULL,
  `droleName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of drole_address
-- ----------------------------
