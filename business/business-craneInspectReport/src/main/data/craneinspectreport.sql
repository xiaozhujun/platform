/*
MySQL Data Transfer
Source Host: localhost
Source Database: riskmanagement
Target Host: localhost
Target Database: riskmanagement
Date: 2014/3/30 23:10:43
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for craneinspectreport
-- ----------------------------
DROP TABLE IF EXISTS `craneinspectreport`;
CREATE TABLE `craneinspectreport` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reportnumber` varchar(255) DEFAULT NULL,
  `unitaddress` varchar(255) DEFAULT NULL,
  `addressid` bigint(20) DEFAULT NULL,
  `organizecode` varchar(255) DEFAULT NULL,
  `usepoint` varchar(255) DEFAULT NULL,
  `safemanager` varchar(255) DEFAULT NULL,
  `contactphone` varchar(255) DEFAULT NULL,
  `equipmentvariety` varchar(255) DEFAULT NULL,
  `unitnumber` varchar(255) DEFAULT NULL,
  `manufactureunit` varchar(255) DEFAULT NULL,
  `manufacturelicensenumber` varchar(255) DEFAULT NULL,
  `manufacturedate` date DEFAULT NULL,
  `specification` varchar(255) DEFAULT NULL,
  `pnumber` varchar(255) DEFAULT NULL,
  `worklevel` varchar(255) DEFAULT NULL,
  `lng` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4711 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
