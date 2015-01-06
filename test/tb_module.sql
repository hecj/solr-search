/*
Navicat MySQL Data Transfer

Source Server         : solrsearch
Source Server Version : 50130
Source Host           : localhost:3306
Source Database       : solrsearch

Target Server Type    : MYSQL
Target Server Version : 50130
File Encoding         : 65001

Date: 2015-01-06 17:35:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_module`
-- ----------------------------
DROP TABLE IF EXISTS `tb_module`;
CREATE TABLE `tb_module` (
  `moduleId` int(11) NOT NULL,
  `attributes` varchar(255) DEFAULT NULL,
  `icons` varchar(255) DEFAULT NULL,
  `leaf` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`moduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_module
-- ----------------------------
INSERT INTO `tb_module` VALUES ('10', null, null, '', null, null, null);
INSERT INTO `tb_module` VALUES ('1001', null, null, '', null, '10', null);
INSERT INTO `tb_module` VALUES ('1002', null, null, '', null, '10', '0');
INSERT INTO `tb_module` VALUES ('1003', null, null, '', null, '10', null);
INSERT INTO `tb_module` VALUES ('100201', null, null, '', null, '1002', null);
