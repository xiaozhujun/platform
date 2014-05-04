/*
MySQL Data Transfer
Source Host: localhost
Source Database: riskmanagement
Target Host: localhost
Target Database: riskmanagement
Date: 2014/3/26 8:57:25
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` int(10) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `description` varchar(20) DEFAULT NULL,
  `picurl` varchar(50) DEFAULT NULL,
  `riskvalue` int(10) DEFAULT NULL,
  `cid` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `device` VALUES ('1', '电梯1', '描述1', null, '1', '1');
INSERT INTO `device` VALUES ('2', '电梯2', '描述2', null, '2', '1');
INSERT INTO `device` VALUES ('3', '起重机1', '描述3', null, '3', '1');
INSERT INTO `device` VALUES ('4', '电梯3', '描述4', null, '2', '2');
INSERT INTO `device` VALUES ('5', '起重机2', '描述5', null, '4', '2');
INSERT INTO `device` VALUES ('6', '起重机3', '描述6', null, '2', '3');
INSERT INTO `device` VALUES ('7', '起重机4', '描述7', null, '4', '3');
INSERT INTO `device` VALUES ('8', '起重机5', '描述8', null, '4', '4');
INSERT INTO `device` VALUES ('9', '电梯4', '描述9', null, '4', '4');
INSERT INTO `device` VALUES ('10', '起重机6', '描述10', null, '3', '4');
INSERT INTO `device` VALUES ('11', '起重机7', '描述11', null, '2', '4');
