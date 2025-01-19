/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : localhost:3306
 Source Schema         : renting_account

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 19/01/2025 13:57:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
  `cell_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `state` int NOT NULL DEFAULT 1 COMMENT '状态:0冻结,1正常',
  `domain` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'u' COMMENT '范围(u:普通用户,r:管理员)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1002 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1001, 'root', 'FyZA3VCAdiU=', '男', '99999999999', 1, 'r');
INSERT INTO `account` VALUES (1002, 'user1', 'FyZA3VCAdiU=', '女', '13888888888', 1, 'u');
INSERT INTO `account` VALUES (1003, 'user2', 'FyZA3VCAdiU=', '男', '13999999999', 1, 'u');
INSERT INTO `account` VALUES (1004, 'user3', 'FyZA3VCAdiU=', '女', '13788888888', 1, 'u');
INSERT INTO `account` VALUES (1005, 'provider1', 'FyZA3VCAdiU=', '男', '13988888888', 1, 'u');
INSERT INTO `account` VALUES (1006, 'provider2', 'FyZA3VCAdiU=', '女', '13688888888', 1, 'u');
INSERT INTO `account` VALUES (1007, 'provider3', 'FyZA3VCAdiU=', '男', '13588888888', 1, 'u');

-- ----------------------------
-- Table structure for power
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power`  (
  `power_id` bigint NOT NULL COMMENT '主键',
  `power_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名称',
  `power_scope` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限范围',
  PRIMARY KEY (`power_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of power
-- ----------------------------
INSERT INTO `power` VALUES (1001, '管理员', 'all,provider,consumer');
INSERT INTO `power` VALUES (1002, '商家', 'provider,consumer');
INSERT INTO `power` VALUES (1003, '用户', 'consumer');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` bigint NOT NULL COMMENT '账户id',
  `power_id` bigint NOT NULL COMMENT '权限id',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `account_id`(`account_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 1001, 1001);
INSERT INTO `user` VALUES (2, 1002, 1003);
INSERT INTO `user` VALUES (3, 1003, 1003);
INSERT INTO `user` VALUES (4, 1004, 1003);
INSERT INTO `user` VALUES (5, 1005, 1002);
INSERT INTO `user` VALUES (6, 1006, 1002);
INSERT INTO `user` VALUES (7, 1007, 1002);

SET FOREIGN_KEY_CHECKS = 1;
