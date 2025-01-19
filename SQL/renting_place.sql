/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : localhost:3306
 Source Schema         : renting_place

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 19/01/2025 13:57:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `city_first` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '城市首个字母',
  `city_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '城市名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES (1, 'C', '长沙');
INSERT INTO `city` VALUES (2, 'Z', '株洲');
INSERT INTO `city` VALUES (3, 'X', '湘潭');
INSERT INTO `city` VALUES (4, 'H', '衡阳');
INSERT INTO `city` VALUES (5, 'S', '邵阳');
INSERT INTO `city` VALUES (6, 'Y', '岳阳');
INSERT INTO `city` VALUES (7, 'C', '常德');
INSERT INTO `city` VALUES (8, 'Z', '张家界');
INSERT INTO `city` VALUES (9, 'Y', '益阳');
INSERT INTO `city` VALUES (10, 'C', '郴州');
INSERT INTO `city` VALUES (11, 'Y', '永州');
INSERT INTO `city` VALUES (12, 'H', '怀化');
INSERT INTO `city` VALUES (13, 'L', '娄底');
INSERT INTO `city` VALUES (14, 'X', '湘西');

SET FOREIGN_KEY_CHECKS = 1;
