/*
 Navicat MySQL Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : mybatis

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 06/09/2019 19:34:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `用户名` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES (1, 'admin', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for isClock
-- ----------------------------
DROP TABLE IF EXISTS `isClock`;
CREATE TABLE `isClock` (
  `username` varchar(255) NOT NULL,
  `date` datetime DEFAULT NULL,
  `isClock` int(10) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `isClock` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of isClock
-- ----------------------------
BEGIN;
INSERT INTO `isClock` VALUES ('18110501123', NULL, 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for stat
-- ----------------------------
DROP TABLE IF EXISTS `stat`;
CREATE TABLE `stat` (
  `id` int(10) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `isClock` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `className` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `isClock` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, '18110501122', '123456', 'jk1806');
INSERT INTO `user` VALUES (2, '18110501123', '123456', 'jk1806');
INSERT INTO `user` VALUES (3, '18110501124', '123456', 'jk1806');
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `className` varchar(255) DEFAULT NULL COMMENT '班级',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `phoneNum` varchar(255) DEFAULT NULL COMMENT '手机号',
  `dormId` varchar(255) DEFAULT NULL COMMENT '宿舍号',
  `feature` blob COMMENT '特征值',
  PRIMARY KEY (`username`),
  KEY `username` (`username`),
  CONSTRAINT `user_info` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
