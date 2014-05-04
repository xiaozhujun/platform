/*
MySQL Data Transfer
Source Host: localhost
Source Database: riskmanagement
Target Host: localhost
Target Database: riskmanagement
Date: 2014/3/26 8:57:33
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(10) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `description` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `company` VALUES ('1', '北京市特种设备检测中心', '描述1');
INSERT INTO `company` VALUES ('2', '中国特种设备检测研究院', '描述2');
INSERT INTO `company` VALUES ('3', '燃气集团有限责任公司特种设备检验所', '描述3');
INSERT INTO `company` VALUES ('4', '北京百昌特种设备贸易有限公司', '描述4');
