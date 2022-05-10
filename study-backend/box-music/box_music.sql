/*
 Navicat Premium Data Transfer

 Source Server         : 106.15.184.155
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : 106.15.184.155:3306
 Source Schema         : box_music

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 26/04/2022 07:26:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for api
-- ----------------------------
DROP TABLE IF EXISTS `api`;
CREATE TABLE `api`  (
  `api_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `parent_api_id` int(0) NULL DEFAULT NULL COMMENT '父级资源id',
  `api_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
  `api_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源路径',
  `api_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `api_type` int(0) NOT NULL COMMENT '资源类型（0：菜单，1：资源）',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`api_id`) USING BTREE,
  UNIQUE INDEX `api_id`(`api_id`, `api_path`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api
-- ----------------------------
INSERT INTO `api` VALUES (1, 0, '用户', '', '*', 0, '2022-02-24 11:20:49', NULL);
INSERT INTO `api` VALUES (2, 1, '个人中心', '', '*', 0, '2022-02-24 11:22:17', NULL);
INSERT INTO `api` VALUES (3, 2, '获取用户信息', '/user/getUserInfo', '*', 1, '2022-02-24 11:22:45', NULL);
INSERT INTO `api` VALUES (4, 2, '更新用户信息', '/user/updateUserInfo', '*', 1, '2022-02-24 11:23:20', '2022-02-24 11:24:07');
INSERT INTO `api` VALUES (5, 2, '修改密码', '/user/modifyUserPassword', '*', 1, '2022-02-24 11:24:04', NULL);
INSERT INTO `api` VALUES (6, 0, '管理员', NULL, '*', 0, '2022-02-24 11:24:28', NULL);
INSERT INTO `api` VALUES (7, 6, '用户管理', NULL, '*', 0, '2022-02-24 11:26:33', '2022-02-24 11:27:07');
INSERT INTO `api` VALUES (8, 7, '添加用户', '/user/addUser', '*', 1, '2022-02-24 11:27:02', NULL);
INSERT INTO `api` VALUES (9, 7, '更新用户', '/user/updateUserByUserId', '*', 1, '2022-02-24 11:28:05', NULL);
INSERT INTO `api` VALUES (10, 7, '删除用户', '/user/deleteUserByUserId', '*', 1, '2022-02-24 11:28:27', NULL);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '歌曲分类id',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '歌曲分类名称',
  `category_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '歌曲分类图片',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE INDEX `field_name`(`category_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music`  (
  `music_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '音乐id',
  `music_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '音乐名称',
  `music_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '音乐封面',
  `lyrics` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '歌词',
  `album` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专辑',
  `genre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流派',
  `duration` double NOT NULL COMMENT '时长',
  `size` int(0) NULL DEFAULT NULL COMMENT '大小',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '音乐最高品质',
  `music_format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '音乐最高品质格式',
  `bitrate` int(0) NOT NULL COMMENT '音乐最高品质码率',
  `views` int(0) NULL DEFAULT NULL COMMENT '播放量',
  `music_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '音乐地址',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`music_id`) USING BTREE,
  UNIQUE INDEX `music_name`(`music_title`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of music
-- ----------------------------
INSERT INTO `music` VALUES (1, '漫长的告白', NULL, NULL, NULL, NULL, 4.35, NULL, '无损', 'flac', 999000, NULL, 'G/music', '2022-02-27 12:00:09', NULL, NULL);
INSERT INTO `music` VALUES (2, '来世愿', NULL, NULL, NULL, NULL, 4.38, NULL, '无损', 'flac', 999000, NULL, 'G/music', '2022-02-27 12:00:09', NULL, NULL);
INSERT INTO `music` VALUES (3, '小满', NULL, NULL, NULL, NULL, 4.32, NULL, '无损', 'flac', 999000, NULL, 'G/music', '2022-02-27 12:00:09', NULL, NULL);

-- ----------------------------
-- Table structure for music_category
-- ----------------------------
DROP TABLE IF EXISTS `music_category`;
CREATE TABLE `music_category`  (
  `music_category_id` int(0) NOT NULL COMMENT '音乐分类id',
  `music_id` int(0) NOT NULL COMMENT '音乐id',
  `category_id` int(0) NOT NULL COMMENT '分类id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`music_category_id`) USING BTREE,
  UNIQUE INDEX `music_id`(`music_id`, `category_id`) USING BTREE,
  INDEX `music_category_ibfk_2`(`category_id`) USING BTREE,
  CONSTRAINT `music_category_ibfk_1` FOREIGN KEY (`music_id`) REFERENCES `music` (`music_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `music_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of music_category
-- ----------------------------

-- ----------------------------
-- Table structure for music_playlist
-- ----------------------------
DROP TABLE IF EXISTS `music_playlist`;
CREATE TABLE `music_playlist`  (
  `music_playlist_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '音乐歌单id',
  `music_id` int(0) NOT NULL COMMENT '音乐id',
  `playlist_id` int(0) NOT NULL COMMENT '歌单id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`music_playlist_id`) USING BTREE,
  UNIQUE INDEX `music_id`(`music_id`, `playlist_id`) USING BTREE,
  INDEX `playlist_id`(`playlist_id`) USING BTREE,
  CONSTRAINT `music_playlist_ibfk_1` FOREIGN KEY (`music_id`) REFERENCES `music` (`music_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `music_playlist_ibfk_2` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`playlist_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of music_playlist
-- ----------------------------

-- ----------------------------
-- Table structure for music_singer
-- ----------------------------
DROP TABLE IF EXISTS `music_singer`;
CREATE TABLE `music_singer`  (
  `music_singer_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '音乐歌手id',
  `music_id` int(0) NOT NULL COMMENT '音乐id',
  `singer_id` int(0) NOT NULL COMMENT '歌手id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`music_singer_id`) USING BTREE,
  UNIQUE INDEX `music_id`(`music_id`, `singer_id`) USING BTREE,
  INDEX `singer_id`(`singer_id`) USING BTREE,
  CONSTRAINT `music_singer_ibfk_1` FOREIGN KEY (`music_id`) REFERENCES `music` (`music_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `music_singer_ibfk_2` FOREIGN KEY (`singer_id`) REFERENCES `singer` (`singer_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of music_singer
-- ----------------------------
INSERT INTO `music_singer` VALUES (1, 1, 1, '2022-02-27 12:02:08', NULL);
INSERT INTO `music_singer` VALUES (2, 2, 2, '2022-02-27 12:02:14', NULL);
INSERT INTO `music_singer` VALUES (3, 3, 2, '2022-02-27 12:02:19', NULL);

-- ----------------------------
-- Table structure for playlist
-- ----------------------------
DROP TABLE IF EXISTS `playlist`;
CREATE TABLE `playlist`  (
  `playlist_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '歌单id',
  `playlist_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '歌单名称',
  `playlist_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌单封面',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`playlist_id`) USING BTREE,
  UNIQUE INDEX `playlist_name`(`playlist_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of playlist
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'user', '2022-02-24 09:03:38', NULL);
INSERT INTO `role` VALUES (2, 'admin', '2022-02-24 09:03:43', NULL);

-- ----------------------------
-- Table structure for role_api
-- ----------------------------
DROP TABLE IF EXISTS `role_api`;
CREATE TABLE `role_api`  (
  `role_api_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '角色接口id',
  `role_id` int(0) NOT NULL COMMENT '角色id',
  `api_id` int(0) NOT NULL COMMENT '请求接口id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`role_api_id`) USING BTREE,
  UNIQUE INDEX `role_id`(`role_id`, `api_id`) USING BTREE,
  INDEX `api_id`(`api_id`) USING BTREE,
  CONSTRAINT `role_api_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_api_ibfk_2` FOREIGN KEY (`api_id`) REFERENCES `api` (`api_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_api
-- ----------------------------
INSERT INTO `role_api` VALUES (1, 1, 1, '2022-02-24 11:29:34', NULL);
INSERT INTO `role_api` VALUES (2, 1, 2, '2022-02-24 11:29:43', NULL);
INSERT INTO `role_api` VALUES (3, 1, 3, '2022-02-24 11:29:49', NULL);
INSERT INTO `role_api` VALUES (4, 1, 4, '2022-02-24 11:29:54', NULL);
INSERT INTO `role_api` VALUES (5, 1, 5, '2022-02-24 11:30:09', NULL);
INSERT INTO `role_api` VALUES (6, 2, 6, '2022-02-24 11:30:20', NULL);
INSERT INTO `role_api` VALUES (7, 2, 7, '2022-02-24 11:30:26', NULL);
INSERT INTO `role_api` VALUES (8, 2, 8, '2022-02-24 11:30:34', NULL);
INSERT INTO `role_api` VALUES (9, 2, 9, '2022-02-24 11:30:39', NULL);
INSERT INTO `role_api` VALUES (10, 2, 10, '2022-02-24 11:30:51', NULL);

-- ----------------------------
-- Table structure for singer
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer`  (
  `singer_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '歌手id',
  `singer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '歌手名称',
  `singer_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌手图片',
  `total_views` int(0) NULL DEFAULT NULL COMMENT '歌手歌曲总播放量',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`singer_id`) USING BTREE,
  UNIQUE INDEX `singer_name`(`singer_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of singer
-- ----------------------------
INSERT INTO `singer` VALUES (1, '双笙', NULL, NULL, '2022-02-27 12:01:41', NULL, NULL);
INSERT INTO `singer` VALUES (2, '陈元汐', NULL, NULL, '2022-02-27 12:01:51', NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `user_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `status` int(0) NOT NULL COMMENT '用户状态（0：冻结，1：正常）',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (4, 'admin', '$2a$10$i5ynC3BIGr/gHTIXTJS0UePCpdaKKwtFEm5arSRdhv7efB00dwl/a', '男', 22, '18558612103', '853539461@qq.com', NULL, 1, '2022-02-24 08:22:12', '2022-02-24 11:43:15', 0);
INSERT INTO `user` VALUES (7, 'user', '$2a$10$RY3ynlEmuKZPtwzhRIWqBOEw9.mHCLbg35RQIojzYnhA5KDJ3ovMq', NULL, NULL, NULL, NULL, NULL, 1, '2022-02-25 00:53:14', NULL, 0);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_role_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户角色id',
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `role_id` int(0) NOT NULL COMMENT '角色id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`user_role_id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`, `role_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 4, 2, '2022-02-24 09:04:06', NULL);
INSERT INTO `user_role` VALUES (2, 4, 1, '2022-02-24 11:17:07', NULL);
INSERT INTO `user_role` VALUES (3, 7, 1, '2022-02-25 00:54:00', NULL);

SET FOREIGN_KEY_CHECKS = 1;
