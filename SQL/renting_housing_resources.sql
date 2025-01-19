/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : localhost:3306
 Source Schema         : renting_housing_resources

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 19/01/2025 13:57:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for housing_brief_introduction
-- ----------------------------
DROP TABLE IF EXISTS `housing_brief_introduction`;
CREATE TABLE `housing_brief_introduction`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `housing_management_id` bigint NOT NULL COMMENT '房子Id',
  `television` int NOT NULL COMMENT '电视',
  `refrigerator` int NOT NULL COMMENT '冰箱',
  `washing` int NOT NULL COMMENT '洗衣机',
  `air_conditioner` int NOT NULL COMMENT '空调',
  `heater` int NOT NULL COMMENT '热水器',
  `bed` int NOT NULL COMMENT '床',
  `heating` int NOT NULL COMMENT '暖气',
  `broadband` int NOT NULL COMMENT '宽带',
  `wardrobe` int NOT NULL COMMENT '衣柜',
  `gas` int NOT NULL COMMENT '天然气',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of housing_brief_introduction
-- ----------------------------

-- ----------------------------
-- Table structure for housing_management
-- ----------------------------
DROP TABLE IF EXISTS `housing_management`;
CREATE TABLE `housing_management`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `city_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '城市',
  `housing_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '房屋标题',
  `address` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '详情地址',
  `group_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片组名',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片名称',
  `rent` double NOT NULL COMMENT '租金',
  `state` int NOT NULL COMMENT '状态:0(未租),1(已租)',
  `housing_mode_id` bigint NOT NULL COMMENT '出租方式',
  `room` int NOT NULL COMMENT '室',
  `hall` int NOT NULL COMMENT '厅',
  `see` bigint NULL DEFAULT 0 COMMENT '浏览人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of housing_management
-- ----------------------------
INSERT INTO `housing_management` VALUES (1, '长沙', '舒适两室一厅', '长沙市岳麓区岳麓大道', 'group1', 'house1.jpg', 2000, 0, 10001, 2, 1, 100);
INSERT INTO `housing_management` VALUES (2, '株洲', '豪华三室一厅', '株洲市天元区天元大道', 'group2', 'house2.jpg', 3000, 1, 10002, 3, 1, 200);
INSERT INTO `housing_management` VALUES (3, '湘潭', '整租一室一厅', '湘潭市岳塘区岳塘街', 'group3', 'house3.jpg', 1500, 0, 10001, 1, 1, 50);
INSERT INTO `housing_management` VALUES (4, '衡阳', '合租三室两厅', '衡阳市珠晖区珠晖街', 'group4', 'house4.jpg', 1800, 0, 10002, 3, 2, 120);
INSERT INTO `housing_management` VALUES (5, '岳阳', '温馨两室一厅', '岳阳市岳阳楼区岳阳街', 'group5', 'house5.jpg', 2200, 1, 10003, 2, 1, 80);

-- ----------------------------
-- Table structure for housing_mode
-- ----------------------------
DROP TABLE IF EXISTS `housing_mode`;
CREATE TABLE `housing_mode`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '出租方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10004 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of housing_mode
-- ----------------------------
INSERT INTO `housing_mode` VALUES (10001, '整租');
INSERT INTO `housing_mode` VALUES (10002, '合租');
INSERT INTO `housing_mode` VALUES (10003, '转租');

-- ----------------------------
-- Table structure for housing_user
-- ----------------------------
DROP TABLE IF EXISTS `housing_user`;
CREATE TABLE `housing_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` bigint NOT NULL COMMENT '账户Id',
  `housing_management_id` bigint NOT NULL COMMENT '房子id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of housing_user
-- ----------------------------
INSERT INTO `housing_user` VALUES (1, 1005, 1);
INSERT INTO `housing_user` VALUES (2, 1006, 2);
INSERT INTO `housing_user` VALUES (3, 1007, 3);
INSERT INTO `housing_user` VALUES (4, 1005, 4);
INSERT INTO `housing_user` VALUES (5, 1006, 5);

SET FOREIGN_KEY_CHECKS = 1;
