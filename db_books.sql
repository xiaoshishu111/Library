/*
 Navicat Premium Data Transfer

 Source Server         : mysql57
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : db_books

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 14/07/2020 20:55:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `bookId` varchar(64) COLLATE utf8_bin NOT NULL,
  `bookName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `bookAuthor` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_book
-- ----------------------------
BEGIN;
INSERT INTO `t_book` VALUES ('20a9ec97-f567-4c79-9fcb-a09604957a04', 'a', 'a', 21.00, 0);
INSERT INTO `t_book` VALUES ('3b3a007b-c74f-4fb8-9cdb-3dfd62529d24', '11', '2332', 13.00, 1);
INSERT INTO `t_book` VALUES ('48f8044f-19d8-4c51-a2ec-2a82e1ebdf95', 'ffsd', 'sdf', 412.00, 0);
INSERT INTO `t_book` VALUES ('49db7efb-ca9a-4e8f-b0f0-8adc38e174af', '21311', '2', 2.00, 0);
INSERT INTO `t_book` VALUES ('52b62090-4c29-48c8-a63a-8a97d19c79d2', '42', '134', 12.00, 0);
INSERT INTO `t_book` VALUES ('539d3137-66e7-459a-8f4f-9d6e2c141ee2', 'gds21', 'asd', 31.00, 0);
INSERT INTO `t_book` VALUES ('57818a95-75a9-478a-9e22-6c89e4369928', 'dsfs', 'aasd', 12.00, 0);
INSERT INTO `t_book` VALUES ('5833cc57-2e02-42a9-a2d8-011e8451ad2c', 'asd', 'asd', 1.00, 0);
INSERT INTO `t_book` VALUES ('58c4c1d3-613e-4132-9a09-53458d12866a', '21', '22', 11.00, 0);
INSERT INTO `t_book` VALUES ('5f00dbbe-1c34-4768-9a55-b7ac7554e67b', '123', '123', 12.00, 0);
INSERT INTO `t_book` VALUES ('63608366-d131-4f02-8c4e-14a3409711bb', 'asd', 'asd', 231.00, 0);
INSERT INTO `t_book` VALUES ('82f1d551-c9b3-47d8-b858-a8f500d35b4e', '123', '21', 12.00, 0);
INSERT INTO `t_book` VALUES ('937a3d86-59e4-463d-aa07-921f3e3551ec', 'dwdw', 'wd', 123.00, 0);
INSERT INTO `t_book` VALUES ('9a7ae6ad-a608-46d1-973d-8b35ed585ee2', 'jjj', 'jj', 88.00, 0);
INSERT INTO `t_book` VALUES ('a547fd94-affa-432f-b3db-563370e07aca', '312', 'wqwe', 213.00, 0);
INSERT INTO `t_book` VALUES ('aa384264-22f1-4730-bd63-85c28757e1ee', '123', '232', 123.00, 0);
INSERT INTO `t_book` VALUES ('bcf300d3-64ac-443e-b13f-7ee4d09601e2', '3333', '333', 333.00, 0);
INSERT INTO `t_book` VALUES ('bfd94175-baf3-4b90-8e58-9fcb85b9f5c8', '2321888', '213', 22.00, 1);
INSERT INTO `t_book` VALUES ('d4a95243-073e-4613-a627-c9bc7e4bf839', '123', '123', 123.00, 0);
INSERT INTO `t_book` VALUES ('d5ee7351-24d6-4d58-bb4b-6fe272bb5b98', '1', '2', 3.00, 0);
INSERT INTO `t_book` VALUES ('dfcf6b6e-163d-4ca9-ae97-2ffd0d47a46f', '23', '12', 33.00, 0);
INSERT INTO `t_book` VALUES ('e6ddeb62-46be-4801-999b-e7203a24804c', '213', '231', 123.00, 0);
INSERT INTO `t_book` VALUES ('ee26a3a4-08bd-4ab7-85cf-59437c9adbab', '1231', '231', 231.00, 0);
INSERT INTO `t_book` VALUES ('f073cd55-e628-4381-baa7-a9cc03f58dc8', 'www', 'www', 321.00, 0);
INSERT INTO `t_book` VALUES ('f1d50563-22fa-494f-9968-89bd118829c9', '213', '123', 111.00, 0);
INSERT INTO `t_book` VALUES ('fb33b9c7-dc6f-48a5-b338-2821035ebcfd', '大', '1', 1.00, 0);
COMMIT;

-- ----------------------------
-- Table structure for t_lend
-- ----------------------------
DROP TABLE IF EXISTS `t_lend`;
CREATE TABLE `t_lend` (
  `lendId` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_danish_ci NOT NULL,
  `readerId` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `bookId` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `begintime` timestamp NULL DEFAULT NULL,
  `endtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`lendId`),
  UNIQUE KEY `readerId` (`readerId`,`bookId`,`begintime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_lend
-- ----------------------------
BEGIN;
INSERT INTO `t_lend` VALUES ('11', '22', '22', '2020-07-11 16:38:56', '2020-07-14 16:39:01');
COMMIT;

-- ----------------------------
-- Table structure for t_reader
-- ----------------------------
DROP TABLE IF EXISTS `t_reader`;
CREATE TABLE `t_reader` (
  `readerId` varchar(255) COLLATE utf8_bin NOT NULL,
  `readerName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `readerAccount` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `readerPassword` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `readerAuthorty` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`readerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_reader
-- ----------------------------
BEGIN;
INSERT INTO `t_reader` VALUES ('1b17b8dd-cfd2-4834-b760-6ef4bc91db0c', NULL, '123', '13', 0);
INSERT INTO `t_reader` VALUES ('77248425-ce0f-4bdc-bd4f-64b99428343e', '222', '111', '11', 1);
INSERT INTO `t_reader` VALUES ('c0e0e4e4-8e7e-4b9e-8ebe-9a9ec048961a', '111', 'qqq', '111', 0);
INSERT INTO `t_reader` VALUES ('d06f2fd5-4158-4dcc-ba88-a013f6780da2', '22', '22', '22', 0);
INSERT INTO `t_reader` VALUES ('f24288bb-5959-4591-803d-883c98d0b70a', '111', '12', '111', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('12345', 'xiaoliu');
INSERT INTO `t_user` VALUES ('123457', 'xiaoliu');
INSERT INTO `t_user` VALUES ('1234578', 'xiaoliu');
INSERT INTO `t_user` VALUES ('12345789', 'xiaoliu');
INSERT INTO `t_user` VALUES ('3', '李四');
INSERT INTO `t_user` VALUES ('333', 'xiaoliu');
INSERT INTO `t_user` VALUES ('333224232', 'xiaoliu');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
