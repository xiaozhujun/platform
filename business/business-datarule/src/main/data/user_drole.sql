
/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : riskmanagement

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2014-04-20 18:45:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user_drole`
-- ----------------------------
DROP TABLE IF EXISTS `user_drole`;
CREATE TABLE `user_drole` (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `droleId` bigint(20) DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `droleName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of user_drole
-- ----------------------------
