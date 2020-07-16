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

 Date: 16/07/2020 22:22:24
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
INSERT INTO `t_book` VALUES ('3b3a007b-c74f-4fb8-9cdb-3dfd62529d24', '11we', '2332', 13.00, 1);
INSERT INTO `t_book` VALUES ('48f8044f-19d8-4c51-a2ec-2a82e1ebdf95', 'ffsd', 'sdf', 412.00, 1);
INSERT INTO `t_book` VALUES ('49db7efb-ca9a-4e8f-b0f0-8adc38e174af', '21311', '2', 2.00, 1);
INSERT INTO `t_book` VALUES ('52b62090-4c29-48c8-a63a-8a97d19c79d2', '42', '134', 12.00, 0);
INSERT INTO `t_book` VALUES ('57818a95-75a9-478a-9e22-6c89e4369928', 'dsfs', 'aasd', 12.00, 1);
INSERT INTO `t_book` VALUES ('5833cc57-2e02-42a9-a2d8-011e8451ad2c', 'asd', 'asd', 1.00, 0);
INSERT INTO `t_book` VALUES ('63608366-d131-4f02-8c4e-14a3409711bb', 'asd', 'asd', 231.00, 0);
INSERT INTO `t_book` VALUES ('82f1d551-c9b3-47d8-b858-a8f500d35b4e', '123', '21', 12.00, 0);
INSERT INTO `t_book` VALUES ('937a3d86-59e4-463d-aa07-921f3e3551ec', 'dwdw', 'wd', 123.00, 1);
INSERT INTO `t_book` VALUES ('9a7ae6ad-a608-46d1-973d-8b35ed585ee2', 'jjj', 'jj', 88.00, 0);
INSERT INTO `t_book` VALUES ('a547fd94-affa-432f-b3db-563370e07aca', '312', 'wqwe', 213.00, 0);
INSERT INTO `t_book` VALUES ('aa384264-22f1-4730-bd63-85c28757e1ee', '123', '232', 123.00, 0);
INSERT INTO `t_book` VALUES ('bcf300d3-64ac-443e-b13f-7ee4d09601e2', '3333', '333', 333.00, 0);
INSERT INTO `t_book` VALUES ('bfd94175-baf3-4b90-8e58-9fcb85b9f5c8', '2321888', '213', 22.00, 1);
INSERT INTO `t_book` VALUES ('d4a95243-073e-4613-a627-c9bc7e4bf839', '123', '123', 123.00, 0);
INSERT INTO `t_book` VALUES ('d5ee7351-24d6-4d58-bb4b-6fe272bb5b98', '1', '2', 3.00, 0);
INSERT INTO `t_book` VALUES ('d80fe16f-6225-4bec-89e5-53ba46689983', 'q', 'q', 2.00, 0);
INSERT INTO `t_book` VALUES ('d9469686-f2d5-477e-8de1-a6dcf610f8ec', 'sda', 'sda', 213.00, 0);
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
INSERT INTO `t_lend` VALUES ('09bf3069-1762-4f62-8830-69800e02eee7', '77248425-ce0f-4bdc-bd4f-64b99428343e', '48f8044f-19d8-4c51-a2ec-2a82e1ebdf95', '2020-07-16 20:41:41', '2020-07-16 21:47:03');
INSERT INTO `t_lend` VALUES ('11', '22', '22', '2020-07-11 16:38:56', '2020-07-14 16:39:01');
INSERT INTO `t_lend` VALUES ('20ceb17d-98a2-4812-9a51-c5c240ce5fa1', '77248425-ce0f-4bdc-bd4f-64b99428343e', '49db7efb-ca9a-4e8f-b0f0-8adc38e174af', '2020-07-16 20:42:49', '2020-07-16 21:50:25');
INSERT INTO `t_lend` VALUES ('5bd51fca-c17a-47fa-b5ef-4b4e6144c12b', '77248425-ce0f-4bdc-bd4f-64b99428343e', '57818a95-75a9-478a-9e22-6c89e4369928', '2020-07-16 22:05:36', NULL);
INSERT INTO `t_lend` VALUES ('ebd9b949-299b-4555-a8e5-d1235d3bf3bd', '77248425-ce0f-4bdc-bd4f-64b99428343e', '937a3d86-59e4-463d-aa07-921f3e3551ec', '2020-07-16 20:43:06', '2020-07-16 21:52:02');
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
INSERT INTO `t_reader` VALUES ('11214933-079f-4e07-8175-dd52679bbfc8', '12as', '123', '123', 0);
INSERT INTO `t_reader` VALUES ('17d8988a-6bad-4a15-83f6-6579703e9676', '123', '231', 'ddd', 0);
INSERT INTO `t_reader` VALUES ('30003422-2c5b-4574-8870-3ea10be889a7', 's', 's', 's', 0);
INSERT INTO `t_reader` VALUES ('31f8a93a-f83c-4f45-ac4b-379f4083e452', '3k', '2', '3', 0);
INSERT INTO `t_reader` VALUES ('3280ef19-3ccb-445c-8045-d52b1b97d315', '12', '21', '12', 0);
INSERT INTO `t_reader` VALUES ('3661b0b7-7651-4223-9421-746750cf67aa', '3121', '213', '321', 0);
INSERT INTO `t_reader` VALUES ('38fc5436-42de-42ce-8cfd-05ea5e002194', 'aa', 'aa', 'aa', 0);
INSERT INTO `t_reader` VALUES ('3f4eb833-a8f4-49be-8d0a-3b1707e543c1', 'sss', 'sss', 'ss', 0);
INSERT INTO `t_reader` VALUES ('457a2c7e-d019-464c-bfa7-f6a060b198bc', '3rrr', '23', '3', 0);
INSERT INTO `t_reader` VALUES ('499ce24e-e508-49be-adde-169d5f9e0b6c', '123', '123', '123', 0);
INSERT INTO `t_reader` VALUES ('527aeb0d-9a38-4510-97c3-b88ce301bd4f', 'sadsaasdas', '112', '11', 0);
INSERT INTO `t_reader` VALUES ('54a1702b-1d47-4d63-a111-5cfbf21d8dda', 'aaaaa', 'aaaaa', 'aaaaa', 0);
INSERT INTO `t_reader` VALUES ('5782d8f1-f3a2-48be-80f3-16d6f881efb9', 'ff', 'ff', 'fff', 0);
INSERT INTO `t_reader` VALUES ('58bd1232-fefc-48e1-83f4-32264ba295a9', '123123', '3123', '123', 0);
INSERT INTO `t_reader` VALUES ('5a118da0-51d7-41f3-92bb-1147378473ca', 'fsd', 'dsf', 'sdf', 0);
INSERT INTO `t_reader` VALUES ('5afcb18d-a91b-45b7-90d1-895195ebd6e7', 'm', 'ss', 's', 0);
INSERT INTO `t_reader` VALUES ('616bd251-0477-4593-bfbc-379699783399', '阿斯顿', '12', '12', 0);
INSERT INTO `t_reader` VALUES ('6c9705fd-7897-4391-a7e9-edf3a0824bf0', 'xx', 'xx', 'xx', 0);
INSERT INTO `t_reader` VALUES ('6e4f7e29-d408-4d40-a887-206e33f3626d', 'sdf', 'sdf', 'sdf', 0);
INSERT INTO `t_reader` VALUES ('734073c8-916c-4218-9422-79d466b9a300', 's', 's', 's', 0);
INSERT INTO `t_reader` VALUES ('76a47500-8242-4628-b3a3-32bea8b9861c', 'ff', 'ff', 'ff', 0);
INSERT INTO `t_reader` VALUES ('77248425-ce0f-4bdc-bd4f-64b99428343e', '222', '111', '11', 1);
INSERT INTO `t_reader` VALUES ('89031155-8c7f-4899-b33a-5d1447535af8', 'sadfasas', '11', '11', 0);
INSERT INTO `t_reader` VALUES ('932a7cbe-e548-4f99-b488-68626e672347', 'we', 'q', 's', 0);
INSERT INTO `t_reader` VALUES ('9751cb4f-d8fa-47bf-8aae-32e373b7183b', '11d', 'd', 'd', 0);
INSERT INTO `t_reader` VALUES ('9a272bf0-8c97-47b7-be53-123a723d6b40', 'dsfas', '1212', '11', 0);
INSERT INTO `t_reader` VALUES ('9e63ae38-e301-4c53-bd5d-80479185e1b6', 'ss', 'ss', 'ss', 0);
INSERT INTO `t_reader` VALUES ('9ef2fa25-6667-45d5-ab08-af3f19f18dd0', '123', '123', '132', 0);
INSERT INTO `t_reader` VALUES ('a2ab908c-6551-4df4-a2ae-d40b11d9f522', 'piop', '1', '1', 0);
INSERT INTO `t_reader` VALUES ('a6644b05-50f5-4f37-856f-99069d7b92c9', 'dd', 'dd', 'dd', 0);
INSERT INTO `t_reader` VALUES ('a7bd2cc7-c325-4dcb-8437-79860e1242a6', 'ss', 'ss', 'ss', 0);
INSERT INTO `t_reader` VALUES ('b059930f-c166-4ac6-95dc-412e3bc2a21b', '123', '312', '3', 0);
INSERT INTO `t_reader` VALUES ('b1de18ac-706e-4027-9c68-282d9bd6fdfc', 'd', 'd', '', 0);
INSERT INTO `t_reader` VALUES ('b27b113d-f05b-457d-8e53-3034a40c74c9', 'd', 'd', 'd', 0);
INSERT INTO `t_reader` VALUES ('c0e0e4e4-8e7e-4b9e-8ebe-9a9ec048961a', '111', 'qqq', '111', 0);
INSERT INTO `t_reader` VALUES ('c69f2416-24af-4367-8e10-92ac6bdc7712', 'sa', 'ad', 'ad', 0);
INSERT INTO `t_reader` VALUES ('c6ccab9d-1bd1-4412-83a5-5d94c78a53df', 'dd', 'dd', 'dd', 0);
INSERT INTO `t_reader` VALUES ('d06f2fd5-4158-4dcc-ba88-a013f6780da2', '22', '22', '22', 0);
INSERT INTO `t_reader` VALUES ('d644ed76-af44-4be1-ab90-f01499a31e13', 'dd', 'das', 'asasd', 0);
INSERT INTO `t_reader` VALUES ('d92a75d5-266e-47df-8a44-fa3d3a51a2ed', 'd', 'd', 'd', 0);
INSERT INTO `t_reader` VALUES ('db7d3384-deb4-440e-a9ad-28e37ecba24d', 'ssa', 'sa', 'sa', 0);
INSERT INTO `t_reader` VALUES ('e18ce3d0-ddfb-45e9-8732-3b642ae6798d', 'wq', 'we', 'cc', 0);
INSERT INTO `t_reader` VALUES ('e51df839-9b8d-4278-8d0d-0e9cf1061c23', 'w', 'w', 'w', 0);
INSERT INTO `t_reader` VALUES ('e79bb9f2-3039-43a8-ada6-bcc5f9e9cd5d', '1122', '13', '132', 0);
INSERT INTO `t_reader` VALUES ('ebd4538e-aaa6-42bb-bf85-8c28ad3764c7', '123', '123', '123', 0);
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
