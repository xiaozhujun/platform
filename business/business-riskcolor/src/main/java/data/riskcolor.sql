/*
MySQL Data Transfer
Source Host: localhost
Source Database: riskmanagement
Target Host: localhost
Target Database: riskmanagement
Date: 2014/4/13 15:22:13
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for riskcolor
-- ----------------------------
DROP TABLE IF EXISTS `riskcolor`;
CREATE TABLE `riskcolor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `riskvalue` float(10,2) DEFAULT NULL,
  `riskcolor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=519 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `riskcolor` VALUES ('512', '2.00', '#FFFFFF');
INSERT INTO `riskcolor` VALUES ('513', '5.00', '#FFFFFF');
INSERT INTO `riskcolor` VALUES ('514', '4.00', '#FFFFFF');
INSERT INTO `riskcolor` VALUES ('515', '3.00', '#FFFFFF');
INSERT INTO `riskcolor` VALUES ('516', '1.00', '#FFFFFF');
INSERT INTO `riskcolor` VALUES ('517', '6.00', '#FFFFFF');
