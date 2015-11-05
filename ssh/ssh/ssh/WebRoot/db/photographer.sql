/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : photographer

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2014-12-15 10:25:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `about`
-- ----------------------------
DROP TABLE IF EXISTS `about`;
CREATE TABLE `about` (
  `AboutID` int(10) NOT NULL,
  `engConx` varchar(1000) DEFAULT NULL,
  `chiConx` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`AboutID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of about
-- ----------------------------
INSERT INTO `about` VALUES ('1', '        Years ago,through cities, Xiazhi occupied a place among the fashion industry, as a musician, or a tuner. Committed to photographing in the building, space, art, design, and related fields since 2010. Utilizing the combination of geometry and lighting, he shoots the images that evoke the sense of space.\n        Futhermore, through showing the relationship among construction, environment and people, he promotes the social conception of life and construction, and conveys the visual meaning and the contents of modern construction in a vigorous way.\n		\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	', '    多年前以音乐人，调音师身份混迹于时尚产业，穿梭于各个城市。\n    2010年正式从事摄影工作。全面致力于建筑，空间，艺术与设计等相关领域的创作。运用几何结构与光影的结合，拍摄能够唤起人们思想空间感的影像。同时能够通过建筑与环境和人物之间的相互关系，倡导生活与建筑本身的社会理念。更好的传达当代建筑的视觉意义与内涵。\n\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	\n	');

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `userID` int(11) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('qq', 'qq', '1');
INSERT INTO `admin` VALUES ('XiaZhi', 'NEXOPS15', '2');

-- ----------------------------
-- Table structure for `contact`
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `contactID` int(10) NOT NULL AUTO_INCREMENT,
  `engConx` varchar(1000) DEFAULT NULL,
  `chiConx` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`contactID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contact
-- ----------------------------
INSERT INTO `contact` VALUES ('1', '13511057315\nxiazhiimg@163.com\n13511057315\n', '13511057315\nxiazhiimage@163.com\n13511057315\n');

-- ----------------------------
-- Table structure for `fileinfo`
-- ----------------------------
DROP TABLE IF EXISTS `fileinfo`;
CREATE TABLE `fileinfo` (
  `fileID` int(11) NOT NULL AUTO_INCREMENT,
  `folderID` int(11) NOT NULL,
  `fileName` varchar(50) NOT NULL,
  `filePath` varchar(100) NOT NULL,
  PRIMARY KEY (`fileID`),
  KEY `folderID` (`folderID`),
  CONSTRAINT `folderID` FOREIGN KEY (`folderID`) REFERENCES `folder` (`folderID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of fileinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `folder`
-- ----------------------------
DROP TABLE IF EXISTS `folder`;
CREATE TABLE `folder` (
  `folderID` int(11) NOT NULL AUTO_INCREMENT,
  `parentFolderID` int(11) NOT NULL,
  `engFolderName` varchar(50) NOT NULL,
  `engRemark` varchar(200) DEFAULT NULL,
  `chiFolderName` varchar(50) NOT NULL,
  `chiRemark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`folderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of folder
-- ----------------------------

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `newsID` int(10) NOT NULL AUTO_INCREMENT,
  `engConx` varchar(500) DEFAULT NULL,
  `chiConx` varchar(1000) DEFAULT NULL,
  `imgPath` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`newsID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
