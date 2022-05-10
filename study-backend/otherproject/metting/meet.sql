/*
 Navicat Premium Data Transfer

 Source Server         : pwd 123456
 Source Server Type    : MySQL
 Source Server Version : 100121
 Source Host           : localhost:3306
 Source Schema         : meet

 Target Server Type    : MySQL
 Target Server Version : 100121
 File Encoding         : 65001

 Date: 17/11/2020 11:48:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for m_api
-- ----------------------------
DROP TABLE IF EXISTS `m_api`;
CREATE TABLE `m_api`  (
  `api_id` int(10) NOT NULL AUTO_INCREMENT,
  `api_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'api名称',
  `api_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'api路径',
  `api_methd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求方法',
  `api_sort` int(5) NOT NULL COMMENT '排序',
  PRIMARY KEY (`api_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of m_api
-- ----------------------------
INSERT INTO `m_api` VALUES (6, '会议室列表', '/meet/list', 'GET', 1);
INSERT INTO `m_api` VALUES (7, '会议室预约', '/meet/order', 'POST', 2);
INSERT INTO `m_api` VALUES (8, '预约审核', '/meet/check', 'POST', 3);
INSERT INTO `m_api` VALUES (9, '所有api', '/**', 'GET,POST,PUT,DELETE', 0);

-- ----------------------------
-- Table structure for m_role
-- ----------------------------
DROP TABLE IF EXISTS `m_role`;
CREATE TABLE `m_role`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role_name` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of m_role
-- ----------------------------
INSERT INTO `m_role` VALUES (1, '用户');
INSERT INTO `m_role` VALUES (3, '管理员');
INSERT INTO `m_role` VALUES (2, '领导');

-- ----------------------------
-- Table structure for m_role_api
-- ----------------------------
DROP TABLE IF EXISTS `m_role_api`;
CREATE TABLE `m_role_api`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role_name` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `api_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of m_role_api
-- ----------------------------
INSERT INTO `m_role_api` VALUES (1, '管理员', '/room-order/list');
INSERT INTO `m_role_api` VALUES (2, '管理员', '/room-order/verify');
INSERT INTO `m_role_api` VALUES (3, '管理员', '/room-order/passList');
INSERT INTO `m_role_api` VALUES (4, '管理员', '/room/allList');
INSERT INTO `m_role_api` VALUES (5, '管理员', '/room/update/status/**');
INSERT INTO `m_role_api` VALUES (6, '管理员', '/room-type/list');
INSERT INTO `m_role_api` VALUES (7, '用户', '/room/list');
INSERT INTO `m_role_api` VALUES (8, '用户', '/room/get/*');
INSERT INTO `m_role_api` VALUES (9, '用户', '/room-type/get/*');
INSERT INTO `m_role_api` VALUES (10, '用户', '/room-type/list');
INSERT INTO `m_role_api` VALUES (11, '领导', '/room-order/passList');
INSERT INTO `m_role_api` VALUES (12, '领导', '/user/get');
INSERT INTO `m_role_api` VALUES (13, '用户', 'room-order/cancel/*');
INSERT INTO `m_role_api` VALUES (14, '用户', '/room-order/myorder');
INSERT INTO `m_role_api` VALUES (15, '用户', '/room-order/passOrder');
INSERT INTO `m_role_api` VALUES (16, '用户', '/room-order/free');
INSERT INTO `m_role_api` VALUES (17, '用户', '/user/get');
INSERT INTO `m_role_api` VALUES (18, '用户', 'room-order/orderDetail/*');
INSERT INTO `m_role_api` VALUES (19, '用户', '/room-order/order');
INSERT INTO `m_role_api` VALUES (20, '管理员', '/room/del/*');
INSERT INTO `m_role_api` VALUES (21, '管理员', '/room-type/del/*');
INSERT INTO `m_role_api` VALUES (22, '管理员', '/user/list');
INSERT INTO `m_role_api` VALUES (23, '管理员', '/user/del/*');
INSERT INTO `m_role_api` VALUES (24, '管理员', '/user/update/status/**');
INSERT INTO `m_role_api` VALUES (25, '管理员', '/user/excel');
INSERT INTO `m_role_api` VALUES (26, '管理员', '/room/add');
INSERT INTO `m_role_api` VALUES (27, '管理员', '/room-type/getDetail/*');
INSERT INTO `m_role_api` VALUES (28, '管理员', '/room-type/add');
INSERT INTO `m_role_api` VALUES (29, '管理员', '/user/getDetail/*');
INSERT INTO `m_role_api` VALUES (31, '管理员', '/user/update/**');
INSERT INTO `m_role_api` VALUES (32, '管理员', '/user/add');
INSERT INTO `m_role_api` VALUES (35, '用户', '/user/update/phone');
INSERT INTO `m_role_api` VALUES (36, '用户', '/user/update/pwd');
INSERT INTO `m_role_api` VALUES (37, '管理员', '/user/update/phone');
INSERT INTO `m_role_api` VALUES (38, '管理员', '/user/update/pwd');
INSERT INTO `m_role_api` VALUES (39, '领导', '/user/update/phone');
INSERT INTO `m_role_api` VALUES (40, '领导', '/user/update/pwd');
INSERT INTO `m_role_api` VALUES (41, '用户', '/room-order/currentOrder/*');
INSERT INTO `m_role_api` VALUES (42, '用户', '/room-order/orderDetail/*');
INSERT INTO `m_role_api` VALUES (43, '领导', '/room-order/views');
INSERT INTO `m_role_api` VALUES (44, '管理员', '/room-order/views');
INSERT INTO `m_role_api` VALUES (45, '管理员', '/user/reset/*');
INSERT INTO `m_role_api` VALUES (46, '用户', '/room-order/cancel/*');

-- ----------------------------
-- Table structure for m_room
-- ----------------------------
DROP TABLE IF EXISTS `m_room`;
CREATE TABLE `m_room`  (
  `mno` int(10) NOT NULL AUTO_INCREMENT COMMENT '会议室编号',
  `contain` int(10) NOT NULL COMMENT '容纳人数',
  `position` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会议地点',
  `pic` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会议室图片',
  `name` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会议室名称',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '会议室描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `enabled` smallint(6) NOT NULL DEFAULT 1 COMMENT '是否开启：1开启，0关闭',
  `device` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备',
  `type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议室类型名称',
  PRIMARY KEY (`mno`) USING BTREE,
  INDEX `type_name`(`type_name`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  CONSTRAINT `m_room_ibfk_1` FOREIGN KEY (`type_name`) REFERENCES `m_room_type` (`room_type_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of m_room
-- ----------------------------
INSERT INTO `m_room` VALUES (1, 200, '九章楼2-201', 'http://10.18.44.234:8084/room/pic/1f37167d-3f74-4bb7-9e3a-ebab97cb788d.jpg', '会议室1', '多媒体会议室', '2020-07-26 22:41:36', 1, '无', '小会议室');
INSERT INTO `m_room` VALUES (2, 200, '九章楼2-202', 'http://10.18.44.234:8084/room/pic/1f37167d-3f74-4bb7-9e3a-ebab97cb788d.jpg', '会议室2', 'null', '2020-07-26 22:41:38', 1, '无', '智慧教室');
INSERT INTO `m_room` VALUES (3, 200, '九章楼2-203', 'http://10.18.44.234:8084/room/pic/1f37167d-3f74-4bb7-9e3a-ebab97cb788d.jpg', '会议室3', 'null', '2020-07-26 22:41:42', 1, '无', '创新工坊');
INSERT INTO `m_room` VALUES (4, 200, '九章楼2-204', 'http://10.18.44.234:8084/room/pic/1f37167d-3f74-4bb7-9e3a-ebab97cb788d.jpg', '会议室4', NULL, '2020-07-26 22:41:45', 1, NULL, '创新实验室');
INSERT INTO `m_room` VALUES (5, 200, '九章楼2-205', 'http://10.18.44.234:8084/room/pic/1f37167d-3f74-4bb7-9e3a-ebab97cb788d.jpg', '会议室5', NULL, '2020-07-26 22:41:45', 1, NULL, '智慧教室');
INSERT INTO `m_room` VALUES (6, 200, '九章楼2-206', 'http://10.18.44.234:8084/room/pic/1f37167d-3f74-4bb7-9e3a-ebab97cb788d.jpg', '会议室6', NULL, '2020-07-26 22:41:45', 1, NULL, '创新工坊');
INSERT INTO `m_room` VALUES (9, 111, '1111', 'http://10.18.44.234:8084/room/pic/1f37167d-3f74-4bb7-9e3a-ebab97cb788d.jpg', '会议室10', NULL, '2020-09-13 18:24:49', 1, '无', '创新实验室');
INSERT INTO `m_room` VALUES (10, 111, '1111', 'http://10.18.44.234:8084/room/pic/1f37167d-3f74-4bb7-9e3a-ebab97cb788d.jpg', '会议室9', NULL, '2020-09-13 18:24:49', 1, '无', '小会议室');
INSERT INTO `m_room` VALUES (11, 111, '1111', 'http://10.18.44.234:8084/room/pic/1f37167d-3f74-4bb7-9e3a-ebab97cb788d.jpg', '会议室8', '1111', '2020-09-13 18:24:49', 1, '无', '创新工坊');
INSERT INTO `m_room` VALUES (12, 111, '1111', 'http://10.18.44.234:8084/room/pic/1f37167d-3f74-4bb7-9e3a-ebab97cb788d.jpg', '会议室7', 'null', '2020-09-13 18:24:49', 1, '无', '智慧教室');
INSERT INTO `m_room` VALUES (13, 3000, '天堂', 'http://10.18.44.234:8084/room/pic/16ccc60f-13f3-40a7-afa4-14f9c61e568d.jpg', '第一个会议室', '无', '2020-09-13 20:26:35', 1, '头顶光圈', '创新工坊');
INSERT INTO `m_room` VALUES (14, 3000, '天堂', 'http://10.18.44.234:8084/room/pic/22ab4b76-7bdf-4d42-b933-3e7e3ed8bbef.png', '第三个会议室', '无', '2020-09-22 19:25:44', 1, '头顶光圈', '创新工坊');
INSERT INTO `m_room` VALUES (15, 30000, '天堂第一层11', 'http://localhost:8084/room/pic/78f29b3f-0953-48ed-8e34-300fad9e090d.jpg', '第二个会议室', '无', '2020-09-22 19:29:02', 0, '头顶光圈', '小会议室');

-- ----------------------------
-- Table structure for m_room_order
-- ----------------------------
DROP TABLE IF EXISTS `m_room_order`;
CREATE TABLE `m_room_order`  (
  `ono` int(10) NOT NULL AUTO_INCREMENT,
  `username` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请人',
  `create_time` datetime(0) NOT NULL COMMENT '预约时间',
  `verify_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` smallint(6) NOT NULL DEFAULT 0 COMMENT '审核状态：0审核中，1审核通过，-1审核不通过，-2用户取消，-3超时',
  `start_time` bigint(20) NOT NULL COMMENT '开始时间(时间戳表示，方便后台计算)',
  `end_time` bigint(20) NOT NULL COMMENT '结束时间(时间戳表示，方便后台计算)',
  `start_end_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用于开始时间和结束时间的展示',
  `person_number` int(10) NOT NULL COMMENT '使用人数',
  `password` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '门禁密码',
  `reject_reason` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '拒绝理由',
  `apply_reason` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请理由',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会议室名称',
  `user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  PRIMARY KEY (`ono`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  CONSTRAINT `m_room_order_ibfk_2` FOREIGN KEY (`name`) REFERENCES `m_room` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of m_room_order
-- ----------------------------
INSERT INTO `m_room_order` VALUES (1, '092718133', '2020-09-19 13:39:00', NULL, -2, 1601265600000, 1601269200000, '2020-09-28 12:00至13:00', 12, NULL, NULL, '系统查验', '会议室1', '朱学昌');
INSERT INTO `m_room_order` VALUES (2, '092718133', '2020-09-19 14:43:38', NULL, -2, 1601449200000, 1601452800000, '2020-09-30 15:00至16:00', 50, '', NULL, '测试一下', '会议室1', '朱学昌');
INSERT INTO `m_room_order` VALUES (3, '092718133', '2020-09-20 21:32:41', NULL, 1, 1601449200000, 1601452800000, '2020-09-30 15:00至16:00', 50, '111111', NULL, '查验', '会议室1', '朱学昌');
INSERT INTO `m_room_order` VALUES (4, '092718133', '2020-09-21 11:08:25', '2020-09-21 12:12:37', 1, 1601413200000, 1601420400000, '2020-09-30 05:00至07:00', 11, '111111', '', 'test', '会议室2', '朱学昌');
INSERT INTO `m_room_order` VALUES (5, '092718133', '2020-09-21 11:35:46', '2020-09-21 12:12:43', 1, 1601456400000, 1601463600000, '2020-09-30 17:00至19:00', 11, '111111', '', 'ttt', '会议室6', '朱学昌');
INSERT INTO `m_room_order` VALUES (6, '092718133', '2020-09-21 12:11:25', '2020-09-21 12:12:51', 1, 1601452800000, 1601456400000, '2020-09-30 16:00至17:00', 11, '111111', '', 'tt', '会议室5', '朱学昌');
INSERT INTO `m_room_order` VALUES (7, '092718133', '2020-09-21 12:11:55', '2020-09-21 12:12:55', 1, 1601359200000, 1601362800000, '2020-09-29 14:00至15:00', 35, '111111', '', 'tedt', '会议室7', '朱学昌');
INSERT INTO `m_room_order` VALUES (8, '092718133', '2020-09-21 12:12:24', '2020-09-21 12:12:59', 1, 1601517600000, 1601521200000, '2020-10-01 10:00至11:00', 33, '111111', '', 'test', '会议室10', '朱学昌');
INSERT INTO `m_room_order` VALUES (11, '092718133', '2020-09-22 22:28:07', '2020-09-27 12:26:46', -1, 1600936015000, 1600946877000, '2020-09-24 16:26至19:27', 4, '', '', '。。', '会议室4', '朱学昌');
INSERT INTO `m_room_order` VALUES (12, '092718133', '2020-09-23 20:15:40', '2020-09-27 13:59:54', -3, 1600912800000, 1600916400000, '2020-09-24 10:00至11:00', 12, '', '', 'test', '会议室1', '朱学昌');
INSERT INTO `m_room_order` VALUES (13, '092718133', '2020-09-26 22:25:57', '2020-10-04 13:54:32', -1, 1601136000000, 1601143200000, '2020-09-27 00:00至02:00', 1212313, '', '时间不对', '充分代表的', '会议室2', '朱学昌');
INSERT INTO `m_room_order` VALUES (14, '092718133', '2020-09-27 12:46:36', '2020-10-04 13:55:04', -1, 1601204400000, 1601211600000, '2020-09-27 19:00至21:00', 12, '', '太晚了，洗洗睡吧', '充分代表的wwewewew', '会议室1', '朱学昌');
INSERT INTO `m_room_order` VALUES (15, '092718133', '2020-09-27 13:25:29', '2020-10-04 14:00:38', 1, 1601290800000, 1601305200000, '2020-09-28 19:00至23:00', 11, '111111', '', 'test', '会议室1', '朱学昌');
INSERT INTO `m_room_order` VALUES (16, '092718133', '2020-10-04 14:01:33', NULL, -2, 1602043200000, 1602046800000, '2020-10-07 12:00至13:00', 40, NULL, NULL, '班会', '会议室1', '朱学昌');
INSERT INTO `m_room_order` VALUES (17, '092718133', '2020-10-04 15:17:31', NULL, -2, 1601960400000, 1601967600000, '2020-10-06 13:00至15:00', 44, NULL, NULL, '班会', '会议室1', '朱学昌');
INSERT INTO `m_room_order` VALUES (18, '092718133', '2020-10-04 15:21:26', '2020-10-04 15:21:47', 1, 1602226800000, 1602234000000, '2020-10-09 15:00至17:00', 55, '0000', 'test', 'test', '会议室1', '朱学昌');
INSERT INTO `m_room_order` VALUES (19, '092718133', '2020-10-07 09:42:23', '2020-10-07 13:33:01', 1, 1602048600000, 1602052200000, '2020-10-07 13:30至14:30', 99, '123456', '', '测试', '第二个会议室  ', '朱学昌');
INSERT INTO `m_room_order` VALUES (20, '092718101', '2020-10-07 12:11:44', '2020-10-07 12:59:16', 1, 1602072000000, 1602075600000, '2020-10-07 20:00至21:00', 99, '111111', '', 'test', '会议室1', '请问·');
INSERT INTO `m_room_order` VALUES (21, '092718101', '2020-10-07 12:46:40', NULL, -2, 1602064800000, 1602068400000, '2020-10-07 18:00至19:00', 11, NULL, NULL, '时间测试', '会议室5', '请问·');
INSERT INTO `m_room_order` VALUES (22, '092718133', '2020-10-07 13:31:46', '2020-10-07 13:58:19', 1, 1602129600000, 1602133200000, '2020-10-08 12:00至13:00', 40, '333333', '', 'test', '会议室5', '朱学昌');
INSERT INTO `m_room_order` VALUES (23, '092718133', '2020-10-07 13:46:38', '2020-11-17 11:22:49', -3, 1602219600000, 1602223200000, '2020-10-09 13:00至14:00', 11, '', '', 'test', '会议室2', '朱学昌');

-- ----------------------------
-- Table structure for m_room_type
-- ----------------------------
DROP TABLE IF EXISTS `m_room_type`;
CREATE TABLE `m_room_type`  (
  `tno` int(10) NOT NULL AUTO_INCREMENT,
  `room_type_name` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`tno`) USING BTREE,
  INDEX `tno`(`tno`, `room_type_name`) USING BTREE,
  INDEX `room_type_name`(`room_type_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of m_room_type
-- ----------------------------
INSERT INTO `m_room_type` VALUES (1, '小会议室', '2020-07-29 13:39:23');
INSERT INTO `m_room_type` VALUES (2, '智慧教室', '2020-07-29 13:39:26');
INSERT INTO `m_room_type` VALUES (3, '创新工坊', '2020-07-29 13:39:28');
INSERT INTO `m_room_type` VALUES (4, '创新实验室', '2020-07-29 13:39:31');

-- ----------------------------
-- Table structure for m_user
-- ----------------------------
DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user`  (
  `uno` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号或教师好',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `email` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `dept` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系',
  `enabled` smallint(6) NOT NULL DEFAULT 1 COMMENT '用户状态：1可用，0冻结',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `phone` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机',
  `login` smallint(1) NOT NULL DEFAULT 5 COMMENT '登陆次数',
  PRIMARY KEY (`uno`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of m_user
-- ----------------------------
INSERT INTO `m_user` VALUES (1, '092718133', '$2a$10$UKoWTUncyazs/Dx7Rro6lOZweVzuEHUcxunGTDt5fs8VxaV6FjbHK', '朱学昌', '1494135711@qq.com', 'test', 1, '2020-09-27 14:08:31', '18626131652', 5);
INSERT INTO `m_user` VALUES (54, '092718116', '$2a$10$1x7qjQt24CDV5xzpGHESxOog2Qoim06bqxOv8J/shPUllp52Ktjxy', 'excel4', '1494135711@qq.com', '计算机科学与工程学院', 1, '2020-10-04 13:18:17', '18626131653', 5);
INSERT INTO `m_user` VALUES (55, '092718115', '$2a$10$JFouniH/ZwXLlrKagWMd2eaU/XZbT0GkzuBCq2R/1ibXTN6bNFLxy', 'excel1', '1494135711@qq.com', '计算机科学与工程学院', 1, NULL, '18626131653', 5);
INSERT INTO `m_user` VALUES (56, '092718114', '$2a$10$Cg1ZM/qkEOqCCyUrEELJleSCyhVFwxAhBcBsRf0nKu6XcSaj0ekoW', 'excel2', '1494135711@qq.com', '计算机科学与工程学院', 1, NULL, '18626131653', 5);
INSERT INTO `m_user` VALUES (57, '092718113', '$2a$10$7ljWNLYd8zeO/f4pcBTRyOxHpMlTjRXlfxs6DXwG3X57efa6puYIq', 'excel3', '1494135711@qq.com', '计算机科学与工程学院', 1, NULL, '18626131653', 5);
INSERT INTO `m_user` VALUES (63, '092718107', '$2a$10$tyH9f10ZQberrAGECzBBuufmz9mKHTGS2YOXYNRFEEZbiYrkEwvyu', 'string222', '1494135711@qq.com', '计算机科学与工程学院', 1, NULL, '18626131653', 5);
INSERT INTO `m_user` VALUES (64, '092718106', '$2a$10$mxuWM.Pili.Z7QDcriLi4u1JmgibTk5ywE5L136wepOOX817Z1WJW', '2', '1494135711@qq.com', '计算机科学与工程学院', 1, NULL, '18626131653', 5);
INSERT INTO `m_user` VALUES (65, '092718105', '$2a$10$eIoJmc1A1b/FJCqjXqSoOuszIuGA5Xkn29aA8ggMGYN/bCmUx8H7e', '3', '1494135711@qq.com', '计算机科学与工程学院', 1, NULL, '18626131653', 5);
INSERT INTO `m_user` VALUES (66, '092718117', '$2a$10$eJqGNoCgOIOwzsSIMY2Qve223cadiDk9TU9BmXEU4Z6Okcswsaqo6', '4', '1494135711@qq.com', '计算机科学与工程学院', 1, NULL, '18626131653', 5);
INSERT INTO `m_user` VALUES (67, '092718101', '$2a$10$zv3gre8vtzMdY4YFB0Qs1u41srnwA9/s4EVXI.j3nSVmXAeYez9K6', '请问·', '1494135711@qq.com', '计算机科学与工程学院', 1, NULL, '17777777777', 5);
INSERT INTO `m_user` VALUES (68, '092718102', '$2a$10$xtjgJzT5yeyN9gyzftyWWeAHcVpQLbK43WC/tgzDbquRo/bJm.gy6', 'tsss', '1494135711@qq.com', '计算机科学与工程学院', 1, NULL, '18626131653', 5);
INSERT INTO `m_user` VALUES (69, '092718144', '$2a$10$7DSnaZs6RbJY3HXO3pQm9OKu.hQUFLBGdHh1q0yH57QkjWN5VNnOu', 'excel4', '1494135711@qq.com', '计算机科学与工程学院', 1, '2020-10-04 13:35:25', '18626131653', 5);
INSERT INTO `m_user` VALUES (70, '092718111', '$2a$10$dla9yb7bSabwQCQ.hCnWSuXaIKMfekxNpa7jdEaFvvJvgcdq8Yf4C', 'admin', '1494135711@qq.com', '计算机科学与工程学院', 1, NULL, '110', 5);
INSERT INTO `m_user` VALUES (71, '092718110', '$2a$10$g8AJLDfD25MCRVJpY8Lz1uUHIwRafJtnwOooz2N4Ka9y7bVe1y2ca', 'lead', '1494135711@qq.com', '计算机科学与工程学院', 1, NULL, '110', 5);
INSERT INTO `m_user` VALUES (72, '092718109', '$2a$10$fBykyzJAOQqANjUdAi4PtubGTQ3NE4CU8X5kMi8Xa8HZQmeyAJhDi', 'test1', '1494135711@qq.com', '计算机科学与工程学院', 1, NULL, '18626131653', 5);
INSERT INTO `m_user` VALUES (73, '092718108', '$2a$10$IV.iRnb5tTXfRKNV.2EG9eCz6dALfrnZ7LW/IozQrr1pyH97wHS6q', 'string', '1494135711@qq.com', '计算机科学与工程学院', 1, NULL, '18626131653', 5);
INSERT INTO `m_user` VALUES (74, '092718104', '$2a$10$BOafTIKs.IpfJCU93CbuCuebbMlucKqGtG2yM8wnq5KGrg07r/LJe', 'excel', '1494135711@qq.com', '计算机科学与工程学院', 1, NULL, '110', 5);
INSERT INTO `m_user` VALUES (75, '092718103', '$2a$10$1sfJxLlR79ksCahYQX4ssOhNq.HhuSpVVkqVNRgiXF6SfVI2Ly8M2', 'user12222', '1494135711@qq.com', '计算机科学与工程学院', 1, '2020-10-04 13:16:38', '18626131653', 5);
INSERT INTO `m_user` VALUES (86, '092718112', '$2a$10$S2QQeoSCxQp8kchOU7JDrOS9DrmiNQO1ECKmEbFseaAJgBuydVAdy', 'excel4555', '1494135711@qq.com', '计算机科学与工程学院', 1, '2020-10-04 15:20:30', '18626131653', 5);
INSERT INTO `m_user` VALUES (87, '092718140', '$2a$10$laEdSuFPjxNxg2aEIGjEwuOFnxNodny96NLnbfMtlNf/Q0IwTJP5W', 'test22223', '1494135711@qq.com', '计算机科学与工程学院', 1, '2020-11-17 11:41:47', '18626131653', 5);
INSERT INTO `m_user` VALUES (89, '092718142', '$2a$10$uNeWQrogYJccenso33Vnee9UiMU10V1oK7xPMPDSUC7Po/J.oXf3y', 'ggggg', '1494135711@qq.com', '计算机科学与工程学院', 1, '2020-10-07 13:41:39', '18626131653', 5);

-- ----------------------------
-- Table structure for m_user_locked
-- ----------------------------
DROP TABLE IF EXISTS `m_user_locked`;
CREATE TABLE `m_user_locked`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `lock_start` datetime(0) NOT NULL COMMENT '锁定开始时间',
  `lock_end` datetime(0) NOT NULL COMMENT '锁定结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of m_user_locked
-- ----------------------------
INSERT INTO `m_user_locked` VALUES (1, 'admin', '2020-08-10 16:45:56', '2020-08-10 17:00:56');
INSERT INTO `m_user_locked` VALUES (2, '092718133', '2020-08-18 11:23:19', '2020-08-18 11:38:19');
INSERT INTO `m_user_locked` VALUES (3, '092718133', '2020-08-18 11:43:46', '2020-08-18 11:58:46');
INSERT INTO `m_user_locked` VALUES (4, '092718133', '2020-09-18 10:30:27', '2020-09-18 10:30:27');
INSERT INTO `m_user_locked` VALUES (5, 'test', '2020-09-21 17:08:43', '2020-09-21 17:23:43');
INSERT INTO `m_user_locked` VALUES (7, '092718133', '2020-09-27 11:08:22', '2020-09-27 11:23:22');
INSERT INTO `m_user_locked` VALUES (8, '092718133', '2020-10-10 20:43:39', '2020-10-10 20:58:39');
INSERT INTO `m_user_locked` VALUES (9, '092718133', '2020-10-10 20:43:38', '2020-10-10 20:58:38');
INSERT INTO `m_user_locked` VALUES (10, '092718133', '2020-10-10 20:43:39', '2020-10-10 20:58:39');
INSERT INTO `m_user_locked` VALUES (11, '092718133', '2020-10-10 20:43:39', '2020-10-10 20:58:39');
INSERT INTO `m_user_locked` VALUES (12, '092718133', '2020-10-10 20:43:38', '2020-10-10 20:58:38');
INSERT INTO `m_user_locked` VALUES (13, '092718133', '2020-10-10 20:43:39', '2020-10-10 20:58:39');
INSERT INTO `m_user_locked` VALUES (14, '092718133', '2020-10-10 20:43:38', '2020-10-10 20:58:38');
INSERT INTO `m_user_locked` VALUES (15, '092718133', '2020-10-10 20:43:39', '2020-10-10 20:58:39');
INSERT INTO `m_user_locked` VALUES (16, '092718133', '2020-10-10 20:43:39', '2020-10-10 20:58:39');
INSERT INTO `m_user_locked` VALUES (17, '092718133', '2020-10-10 20:43:38', '2020-10-10 20:58:38');
INSERT INTO `m_user_locked` VALUES (18, '092718133', '2020-10-10 20:43:45', '2020-10-10 20:58:45');
INSERT INTO `m_user_locked` VALUES (19, '092718133', '2020-10-10 20:43:46', '2020-10-10 20:58:46');
INSERT INTO `m_user_locked` VALUES (20, '092718133', '2020-10-10 20:43:46', '2020-10-10 20:58:46');
INSERT INTO `m_user_locked` VALUES (21, '092718133', '2020-10-10 20:43:46', '2020-10-10 20:58:46');
INSERT INTO `m_user_locked` VALUES (22, '092718133', '2020-10-10 20:43:46', '2020-10-10 20:58:46');
INSERT INTO `m_user_locked` VALUES (23, '092718133', '2020-10-10 20:43:46', '2020-10-10 20:58:46');
INSERT INTO `m_user_locked` VALUES (24, '092718133', '2020-10-10 20:43:46', '2020-10-10 20:58:46');
INSERT INTO `m_user_locked` VALUES (25, '092718133', '2020-10-10 20:43:46', '2020-10-10 20:58:46');
INSERT INTO `m_user_locked` VALUES (26, '092718133', '2020-10-10 20:43:47', '2020-10-10 20:58:47');
INSERT INTO `m_user_locked` VALUES (27, '092718133', '2020-10-10 20:43:47', '2020-10-10 20:58:47');
INSERT INTO `m_user_locked` VALUES (28, '092718133', '2020-10-10 20:43:47', '2020-10-10 20:58:47');
INSERT INTO `m_user_locked` VALUES (29, '092718133', '2020-10-10 20:43:47', '2020-10-10 20:58:47');
INSERT INTO `m_user_locked` VALUES (30, '092718133', '2020-10-10 20:43:52', '2020-10-10 20:58:52');
INSERT INTO `m_user_locked` VALUES (31, '092718133', '2020-10-10 20:43:52', '2020-10-10 20:58:52');
INSERT INTO `m_user_locked` VALUES (32, '092718133', '2020-10-10 20:43:52', '2020-10-10 20:58:52');
INSERT INTO `m_user_locked` VALUES (33, '092718133', '2020-10-10 20:43:52', '2020-10-10 20:58:52');
INSERT INTO `m_user_locked` VALUES (34, '092718133', '2020-10-10 20:43:52', '2020-10-10 20:58:52');
INSERT INTO `m_user_locked` VALUES (35, '092718133', '2020-10-10 20:43:52', '2020-10-10 20:58:52');
INSERT INTO `m_user_locked` VALUES (36, '092718133', '2020-10-10 20:43:52', '2020-10-10 20:58:52');
INSERT INTO `m_user_locked` VALUES (37, '092718133', '2020-10-10 20:43:52', '2020-10-10 20:58:52');
INSERT INTO `m_user_locked` VALUES (38, '092718133', '2020-10-10 20:43:53', '2020-10-10 20:58:53');
INSERT INTO `m_user_locked` VALUES (39, '092718133', '2020-10-10 20:43:53', '2020-10-10 20:58:53');
INSERT INTO `m_user_locked` VALUES (40, '092718133', '2020-10-10 20:43:53', '2020-10-10 20:58:53');
INSERT INTO `m_user_locked` VALUES (41, '092718133', '2020-10-10 20:43:53', '2020-10-10 20:58:53');
INSERT INTO `m_user_locked` VALUES (42, '092718133', '2020-10-10 20:43:54', '2020-10-10 20:58:54');
INSERT INTO `m_user_locked` VALUES (43, '092718133', '2020-10-10 20:43:57', '2020-10-10 20:58:57');
INSERT INTO `m_user_locked` VALUES (44, '092718133', '2020-10-10 20:43:57', '2020-10-10 20:58:57');
INSERT INTO `m_user_locked` VALUES (45, '092718133', '2020-10-10 20:43:57', '2020-10-10 20:58:57');
INSERT INTO `m_user_locked` VALUES (46, '092718133', '2020-10-10 20:43:57', '2020-10-10 20:58:57');
INSERT INTO `m_user_locked` VALUES (47, '092718133', '2020-10-10 20:43:58', '2020-10-10 20:58:58');
INSERT INTO `m_user_locked` VALUES (48, '092718133', '2020-10-10 20:43:58', '2020-10-10 20:58:58');
INSERT INTO `m_user_locked` VALUES (49, '092718133', '2020-10-10 20:43:58', '2020-10-10 20:58:58');
INSERT INTO `m_user_locked` VALUES (50, '092718133', '2020-10-10 20:43:58', '2020-10-10 20:58:58');
INSERT INTO `m_user_locked` VALUES (51, '092718133', '2020-10-10 20:43:58', '2020-10-10 20:58:58');
INSERT INTO `m_user_locked` VALUES (52, '092718133', '2020-10-10 20:43:58', '2020-10-10 20:58:58');
INSERT INTO `m_user_locked` VALUES (53, '092718133', '2020-10-10 20:43:58', '2020-10-10 20:58:58');
INSERT INTO `m_user_locked` VALUES (54, '092718133', '2020-10-10 20:44:01', '2020-10-10 20:59:01');
INSERT INTO `m_user_locked` VALUES (55, '092718133', '2020-10-10 20:44:01', '2020-10-10 20:59:01');
INSERT INTO `m_user_locked` VALUES (56, '092718133', '2020-10-10 20:44:01', '2020-10-10 20:59:01');
INSERT INTO `m_user_locked` VALUES (57, '092718133', '2020-10-10 20:44:02', '2020-10-10 20:59:02');
INSERT INTO `m_user_locked` VALUES (58, '092718133', '2020-10-10 20:44:02', '2020-10-10 20:59:02');
INSERT INTO `m_user_locked` VALUES (59, '092718133', '2020-10-10 20:44:02', '2020-10-10 20:59:02');
INSERT INTO `m_user_locked` VALUES (60, '092718133', '2020-10-10 20:44:02', '2020-10-10 20:59:02');
INSERT INTO `m_user_locked` VALUES (61, '092718133', '2020-10-10 20:44:02', '2020-10-10 20:59:02');
INSERT INTO `m_user_locked` VALUES (62, '092718133', '2020-10-10 20:44:02', '2020-10-10 20:59:02');
INSERT INTO `m_user_locked` VALUES (63, '092718133', '2020-10-10 20:44:02', '2020-10-10 20:59:02');
INSERT INTO `m_user_locked` VALUES (64, '092718133', '2020-10-10 20:44:03', '2020-10-10 20:59:03');
INSERT INTO `m_user_locked` VALUES (65, '092718133', '2020-10-10 20:44:18', '2020-10-10 20:59:18');
INSERT INTO `m_user_locked` VALUES (66, '092718133', '2020-11-17 11:04:35', '2020-11-17 11:19:35');

-- ----------------------------
-- Table structure for m_user_log
-- ----------------------------
DROP TABLE IF EXISTS `m_user_log`;
CREATE TABLE `m_user_log`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `login_time` datetime(0) NOT NULL COMMENT '登陆时间',
  `ipaddress` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆ip地址',
  `logcontent` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 842 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of m_user_log
-- ----------------------------
INSERT INTO `m_user_log` VALUES (411, 'test', '2020-09-21 17:32:46', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (412, 'test', '2020-09-21 17:32:56', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (413, '092718133', '2020-09-21 18:52:50', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (414, '092718133', '2020-09-21 19:01:00', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (415, 'test', '2020-09-21 19:19:09', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (416, '092718133', '2020-09-21 19:58:41', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (417, '092718133', '2020-09-22 15:26:42', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (418, '092718133', '2020-09-22 19:01:43', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (419, '092718133', '2020-09-22 20:08:23', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (420, '092718133', '2020-09-22 20:08:26', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (421, '092718133', '2020-09-22 20:34:34', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (422, '092718133', '2020-09-22 21:52:24', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (423, '092718133', '2020-09-22 21:53:50', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (424, '092718133', '2020-09-22 22:05:19', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (425, '092718133', '2020-09-22 22:05:23', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (426, '092718133', '2020-09-22 22:09:48', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (427, '092718133', '2020-09-22 22:15:29', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (428, '092718133', '2020-09-22 22:17:03', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (429, '092718133', '2020-09-22 22:17:11', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (430, '092718133', '2020-09-22 22:19:53', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (431, '092718133', '2020-09-22 22:23:34', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (432, '092718133', '2020-09-22 22:42:17', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (433, '092718133', '2020-09-23 20:10:57', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (434, '092718133', '2020-09-23 20:12:17', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (435, '092718133', '2020-09-23 20:13:52', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (436, '092718133', '2020-09-23 20:14:39', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (437, '092718133', '2020-09-23 20:16:46', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (438, '092718133', '2020-09-24 16:58:29', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (439, '092718133', '2020-09-24 16:58:53', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (440, '092718133', '2020-09-24 17:00:06', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (441, '092718133', '2020-09-24 17:17:15', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (442, '092718133', '2020-09-24 17:18:19', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (443, '092718133', '2020-09-24 20:26:51', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (444, '092718133', '2020-09-24 20:54:29', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (445, '', '2020-09-24 21:18:55', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (446, '092718133', '2020-09-24 21:19:01', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (447, '092718133', '2020-09-26 21:59:36', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (448, '092718133', '2020-09-26 21:59:49', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (449, '092718133', '2020-09-26 21:59:51', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (450, '092718133', '2020-09-26 21:59:52', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (451, '092718133', '2020-09-26 21:59:53', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (452, '092718133', '2020-09-26 21:59:54', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (453, '092718133', '2020-09-26 21:59:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (454, '092718133', '2020-09-26 22:01:35', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (455, '092718133', '2020-09-26 22:01:37', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (456, '092718133', '2020-09-26 22:01:44', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (457, '092718133', '2020-09-26 22:01:51', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (458, '092718133', '2020-09-26 22:09:46', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (459, '092718133', '2020-09-26 22:09:52', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (460, '092718133', '2020-09-26 22:11:10', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (461, '092718133', '2020-09-26 22:13:52', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (462, '092718133', '2020-09-26 22:16:24', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (463, '092718133', '2020-09-26 22:16:47', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (464, '092718133', '2020-09-26 22:16:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (465, '092718133', '2020-09-26 22:17:12', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (466, '092718133', '2020-09-26 22:17:21', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (467, '092718133', '2020-09-26 22:17:26', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (468, '092718133', '2020-09-26 22:27:27', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (469, '092718133', '2020-09-26 22:27:36', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (470, '092718133', '2020-09-27 11:08:17', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (471, '092718133', '2020-09-27 11:08:18', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (472, '092718133', '2020-09-27 11:08:19', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (473, '092718133', '2020-09-27 11:08:20', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (474, '092718133', '2020-09-27 11:08:21', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (475, '092718133', '2020-09-27 11:08:22', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (476, '092718133', '2020-09-27 11:13:31', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (477, '092718133', '2020-09-27 11:13:37', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (478, '092718133', '2020-09-27 11:30:07', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (479, '092718133', '2020-09-27 11:40:34', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (480, '092718133', '2020-09-27 11:58:26', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (481, '092718133', '2020-09-27 12:18:39', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (482, '092718133', '2020-09-27 12:27:32', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (483, '092718133', '2020-09-27 12:30:17', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (484, '092718133', '2020-09-27 14:22:50', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (485, '092718133', '2020-09-27 14:24:56', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (486, '092718133', '2020-09-27 14:27:51', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (487, '092718133', '2020-09-27 14:30:13', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (488, '092718133', '2020-10-04 13:06:12', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (489, '092718133', '2020-10-04 13:55:38', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (490, '092718133', '2020-10-04 14:00:05', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (491, '092718133', '2020-10-04 14:06:12', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (492, '092718133', '2020-10-07 09:26:21', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (493, '092718133', '2020-10-07 09:27:13', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (494, '092718101', '2020-10-07 09:43:36', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (495, '092718133', '2020-10-07 12:48:54', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (496, '092718133', '2020-10-07 13:04:01', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (497, '092718133', '2020-10-07 13:28:20', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (498, '092718133', '2020-10-07 13:29:10', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (499, '092718140', '2020-10-07 13:41:57', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (500, '092718140', '2020-10-07 13:42:00', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (501, '092718133', '2020-10-07 13:42:53', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (502, '092718133', '2020-10-07 13:43:25', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (503, '092718133', '2020-10-07 13:43:33', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (504, '092718133', '2020-10-07 13:45:04', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (505, '092718133', '2020-10-07 13:46:03', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (506, '092718140', '2020-10-07 13:48:26', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (507, '092718140', '2020-10-07 13:48:27', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (508, '092718140', '2020-10-07 13:48:32', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (509, '092718140', '2020-10-07 13:48:35', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (510, '092718133', '2020-10-07 13:49:08', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (511, '092718133', '2020-10-07 13:49:13', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (512, '092718133', '2020-10-07 13:49:49', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (513, '092718140', '2020-10-07 13:50:04', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (514, '092718133', '2020-10-07 13:51:08', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (515, '092718133', '2020-10-07 13:56:26', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (516, '092718133', '2020-10-07 13:57:30', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (517, '092718133', '2020-10-07 13:59:08', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (518, '092718133', '2020-10-07 14:01:24', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (519, '092718133', '2020-10-07 14:02:41', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (520, '092718133', '2020-10-07 14:02:43', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (521, '092718133', '2020-10-10 15:48:37', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (522, '092718133', '2020-10-10 15:48:37', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (523, '092718133', '2020-10-10 15:48:39', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (524, '092718133', '2020-10-10 16:24:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (525, '092718133', '2020-10-10 16:54:49', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (526, '092718133', '2020-10-10 16:55:06', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (527, '092718133', '2020-10-10 16:55:31', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (528, '092718133', '2020-10-10 16:55:38', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (529, '092718133', '2020-10-10 20:43:32', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (530, '092718133', '2020-10-10 20:43:32', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (531, '092718133', '2020-10-10 20:43:32', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (532, '092718133', '2020-10-10 20:43:32', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (533, '092718133', '2020-10-10 20:43:32', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (534, '092718133', '2020-10-10 20:43:32', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (535, '092718133', '2020-10-10 20:43:32', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (536, '092718133', '2020-10-10 20:43:32', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (537, '092718133', '2020-10-10 20:43:33', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (538, '092718133', '2020-10-10 20:43:33', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (539, '092718133', '2020-10-10 20:43:34', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (540, '092718133', '2020-10-10 20:43:35', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (541, '092718133', '2020-10-10 20:43:35', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (542, '092718133', '2020-10-10 20:43:35', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (543, '092718133', '2020-10-10 20:43:34', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (544, '092718133', '2020-10-10 20:43:35', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (545, '092718133', '2020-10-10 20:43:35', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (546, '092718133', '2020-10-10 20:43:35', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (547, '092718133', '2020-10-10 20:43:35', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (548, '092718133', '2020-10-10 20:43:35', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (549, '092718133', '2020-10-10 20:43:36', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (550, '092718133', '2020-10-10 20:43:36', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (551, '092718133', '2020-10-10 20:43:36', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (552, '092718133', '2020-10-10 20:43:36', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (553, '092718133', '2020-10-10 20:43:37', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (554, '092718133', '2020-10-10 20:43:36', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (555, '092718133', '2020-10-10 20:43:37', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (556, '092718133', '2020-10-10 20:43:37', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (557, '092718133', '2020-10-10 20:43:37', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (558, '092718133', '2020-10-10 20:43:37', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (559, '092718133', '2020-10-10 20:43:37', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (560, '092718133', '2020-10-10 20:43:37', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (561, '092718133', '2020-10-10 20:43:37', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (562, '092718133', '2020-10-10 20:43:37', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (563, '092718133', '2020-10-10 20:43:38', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (564, '092718133', '2020-10-10 20:43:38', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (565, '092718133', '2020-10-10 20:43:38', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (566, '092718133', '2020-10-10 20:43:38', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (567, '092718133', '2020-10-10 20:43:38', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (568, '092718133', '2020-10-10 20:43:38', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (569, '092718133', '2020-10-10 20:43:40', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (570, '092718133', '2020-10-10 20:43:40', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (571, '092718133', '2020-10-10 20:43:40', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (572, '092718133', '2020-10-10 20:43:40', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (573, '092718133', '2020-10-10 20:43:40', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (574, '092718133', '2020-10-10 20:43:40', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (575, '092718133', '2020-10-10 20:43:40', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (576, '092718133', '2020-10-10 20:43:40', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (577, '092718133', '2020-10-10 20:43:40', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (578, '092718133', '2020-10-10 20:43:40', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (579, '092718133', '2020-10-10 20:43:41', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (580, '092718133', '2020-10-10 20:43:41', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (581, '092718133', '2020-10-10 20:43:41', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (582, '092718133', '2020-10-10 20:43:41', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (583, '092718133', '2020-10-10 20:43:41', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (584, '092718133', '2020-10-10 20:43:41', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (585, '092718133', '2020-10-10 20:43:41', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (586, '092718133', '2020-10-10 20:43:41', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (587, '092718133', '2020-10-10 20:43:41', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (588, '092718133', '2020-10-10 20:43:41', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (589, '092718133', '2020-10-10 20:43:42', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (590, '092718133', '2020-10-10 20:43:42', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (591, '092718133', '2020-10-10 20:43:42', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (592, '092718133', '2020-10-10 20:43:42', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (593, '092718133', '2020-10-10 20:43:43', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (594, '092718133', '2020-10-10 20:43:43', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (595, '092718133', '2020-10-10 20:43:43', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (596, '092718133', '2020-10-10 20:43:43', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (597, '092718133', '2020-10-10 20:43:43', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (598, '092718133', '2020-10-10 20:43:43', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (599, '092718133', '2020-10-10 20:43:43', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (600, '092718133', '2020-10-10 20:43:43', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (601, '092718133', '2020-10-10 20:43:43', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (602, '092718133', '2020-10-10 20:43:43', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (603, '092718133', '2020-10-10 20:43:44', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (604, '092718133', '2020-10-10 20:43:44', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (605, '092718133', '2020-10-10 20:43:44', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (606, '092718133', '2020-10-10 20:43:44', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (607, '092718133', '2020-10-10 20:43:44', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (608, '092718133', '2020-10-10 20:43:44', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (609, '092718133', '2020-10-10 20:43:44', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (610, '092718133', '2020-10-10 20:43:44', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (611, '092718133', '2020-10-10 20:43:44', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (612, '092718133', '2020-10-10 20:43:44', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (613, '092718133', '2020-10-10 20:43:45', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (614, '092718133', '2020-10-10 20:43:45', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (615, '092718133', '2020-10-10 20:43:45', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (616, '092718133', '2020-10-10 20:43:45', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (617, '092718133', '2020-10-10 20:43:45', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (618, '092718133', '2020-10-10 20:43:45', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (619, '092718133', '2020-10-10 20:43:45', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (620, '092718133', '2020-10-10 20:43:45', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (621, '092718133', '2020-10-10 20:43:45', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (622, '092718133', '2020-10-10 20:43:45', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (623, '092718133', '2020-10-10 20:43:46', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (624, '092718133', '2020-10-10 20:43:46', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (625, '092718133', '2020-10-10 20:43:47', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (626, '092718133', '2020-10-10 20:43:47', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (627, '092718133', '2020-10-10 20:43:47', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (628, '092718133', '2020-10-10 20:43:47', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (629, '092718133', '2020-10-10 20:43:47', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (630, '092718133', '2020-10-10 20:43:47', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (631, '092718133', '2020-10-10 20:43:47', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (632, '092718133', '2020-10-10 20:43:47', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (633, '092718133', '2020-10-10 20:43:48', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (634, '092718133', '2020-10-10 20:43:48', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (635, '092718133', '2020-10-10 20:43:48', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (636, '092718133', '2020-10-10 20:43:48', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (637, '092718133', '2020-10-10 20:43:48', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (638, '092718133', '2020-10-10 20:43:48', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (639, '092718133', '2020-10-10 20:43:48', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (640, '092718133', '2020-10-10 20:43:48', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (641, '092718133', '2020-10-10 20:43:48', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (642, '092718133', '2020-10-10 20:43:48', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (643, '092718133', '2020-10-10 20:43:49', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (644, '092718133', '2020-10-10 20:43:49', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (645, '092718133', '2020-10-10 20:43:49', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (646, '092718133', '2020-10-10 20:43:49', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (647, '092718133', '2020-10-10 20:43:49', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (648, '092718133', '2020-10-10 20:43:49', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (649, '092718133', '2020-10-10 20:43:49', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (650, '092718133', '2020-10-10 20:43:49', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (651, '092718133', '2020-10-10 20:43:49', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (652, '092718133', '2020-10-10 20:43:49', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (653, '092718133', '2020-10-10 20:43:49', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (654, '092718133', '2020-10-10 20:43:50', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (655, '092718133', '2020-10-10 20:43:50', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (656, '092718133', '2020-10-10 20:43:50', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (657, '092718133', '2020-10-10 20:43:50', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (658, '092718133', '2020-10-10 20:43:50', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (659, '092718133', '2020-10-10 20:43:50', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (660, '092718133', '2020-10-10 20:43:50', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (661, '092718133', '2020-10-10 20:43:50', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (662, '092718133', '2020-10-10 20:43:50', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (663, '092718133', '2020-10-10 20:43:50', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (664, '092718133', '2020-10-10 20:43:51', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (665, '092718133', '2020-10-10 20:43:51', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (666, '092718133', '2020-10-10 20:43:51', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (667, '092718133', '2020-10-10 20:43:51', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (668, '092718133', '2020-10-10 20:43:51', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (669, '092718133', '2020-10-10 20:43:51', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (670, '092718133', '2020-10-10 20:43:51', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (671, '092718133', '2020-10-10 20:43:51', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (672, '092718133', '2020-10-10 20:43:51', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (673, '092718133', '2020-10-10 20:43:51', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (674, '092718133', '2020-10-10 20:43:51', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (675, '092718133', '2020-10-10 20:43:51', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (676, '092718133', '2020-10-10 20:43:51', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (677, '092718133', '2020-10-10 20:43:52', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (678, '092718133', '2020-10-10 20:43:52', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (679, '092718133', '2020-10-10 20:43:52', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (680, '092718133', '2020-10-10 20:43:52', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (681, '092718133', '2020-10-10 20:43:52', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (682, '092718133', '2020-10-10 20:43:52', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (683, '092718133', '2020-10-10 20:43:52', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (684, '092718133', '2020-10-10 20:43:52', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (685, '092718133', '2020-10-10 20:43:52', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (686, '092718133', '2020-10-10 20:43:53', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (687, '092718133', '2020-10-10 20:43:53', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (688, '092718133', '2020-10-10 20:43:53', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (689, '092718133', '2020-10-10 20:43:53', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (690, '092718133', '2020-10-10 20:43:53', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (691, '092718133', '2020-10-10 20:43:53', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (692, '092718133', '2020-10-10 20:43:53', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (693, '092718133', '2020-10-10 20:43:53', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (694, '092718133', '2020-10-10 20:43:54', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (695, '092718133', '2020-10-10 20:43:54', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (696, '092718133', '2020-10-10 20:43:54', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (697, '092718133', '2020-10-10 20:43:54', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (698, '092718133', '2020-10-10 20:43:54', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (699, '092718133', '2020-10-10 20:43:54', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (700, '092718133', '2020-10-10 20:43:54', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (701, '092718133', '2020-10-10 20:43:54', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (702, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (703, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (704, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (705, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (706, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (707, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (708, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (709, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (710, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (711, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (712, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (713, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (714, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (715, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (716, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (717, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (718, '092718133', '2020-10-10 20:43:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (719, '092718133', '2020-10-10 20:43:56', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (720, '092718133', '2020-10-10 20:43:56', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (721, '092718133', '2020-10-10 20:43:56', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (722, '092718133', '2020-10-10 20:43:56', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (723, '092718133', '2020-10-10 20:43:56', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (724, '092718133', '2020-10-10 20:43:56', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (725, '092718133', '2020-10-10 20:43:56', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (726, '092718133', '2020-10-10 20:43:56', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (727, '092718133', '2020-10-10 20:43:56', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (728, '092718133', '2020-10-10 20:43:56', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (729, '092718133', '2020-10-10 20:43:57', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (730, '092718133', '2020-10-10 20:43:57', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (731, '092718133', '2020-10-10 20:43:57', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (732, '092718133', '2020-10-10 20:43:57', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (733, '092718133', '2020-10-10 20:43:57', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (734, '092718133', '2020-10-10 20:43:57', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (735, '092718133', '2020-10-10 20:43:57', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (736, '092718133', '2020-10-10 20:43:57', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (737, '092718133', '2020-10-10 20:43:57', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (738, '092718133', '2020-10-10 20:43:57', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (739, '092718133', '2020-10-10 20:43:58', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (740, '092718133', '2020-10-10 20:43:58', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (741, '092718133', '2020-10-10 20:43:58', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (742, '092718133', '2020-10-10 20:43:58', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (743, '092718133', '2020-10-10 20:43:58', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (744, '092718133', '2020-10-10 20:43:58', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (745, '092718133', '2020-10-10 20:43:58', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (746, '092718133', '2020-10-10 20:43:58', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (747, '092718133', '2020-10-10 20:43:58', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (748, '092718133', '2020-10-10 20:43:58', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (749, '092718133', '2020-10-10 20:43:58', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (750, '092718133', '2020-10-10 20:43:59', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (751, '092718133', '2020-10-10 20:43:59', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (752, '092718133', '2020-10-10 20:43:59', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (753, '092718133', '2020-10-10 20:43:59', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (754, '092718133', '2020-10-10 20:43:59', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (755, '092718133', '2020-10-10 20:43:59', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (756, '092718133', '2020-10-10 20:43:59', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (757, '092718133', '2020-10-10 20:43:59', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (758, '092718133', '2020-10-10 20:43:59', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (759, '092718133', '2020-10-10 20:43:59', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (760, '092718133', '2020-10-10 20:43:59', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (761, '092718133', '2020-10-10 20:43:59', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (762, '092718133', '2020-10-10 20:43:59', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (763, '092718133', '2020-10-10 20:43:59', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (764, '092718133', '2020-10-10 20:44:00', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (765, '092718133', '2020-10-10 20:44:00', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (766, '092718133', '2020-10-10 20:44:00', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (767, '092718133', '2020-10-10 20:44:00', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (768, '092718133', '2020-10-10 20:44:00', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (769, '092718133', '2020-10-10 20:44:00', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (770, '092718133', '2020-10-10 20:44:00', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (771, '092718133', '2020-10-10 20:44:00', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (772, '092718133', '2020-10-10 20:44:00', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (773, '092718133', '2020-10-10 20:44:00', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (774, '092718133', '2020-10-10 20:44:00', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (775, '092718133', '2020-10-10 20:44:00', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (776, '092718133', '2020-10-10 20:44:00', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (777, '092718133', '2020-10-10 20:44:00', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (778, '092718133', '2020-10-10 20:44:01', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (779, '092718133', '2020-10-10 20:44:01', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (780, '092718133', '2020-10-10 20:44:01', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (781, '092718133', '2020-10-10 20:44:01', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (782, '092718133', '2020-10-10 20:44:01', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (783, '092718133', '2020-10-10 20:44:01', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (784, '092718133', '2020-10-10 20:44:01', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (785, '092718133', '2020-10-10 20:44:01', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (786, '092718133', '2020-10-10 20:44:01', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (787, '092718133', '2020-10-10 20:44:02', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (788, '092718133', '2020-10-10 20:44:02', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (789, '092718133', '2020-10-10 20:44:02', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (790, '092718133', '2020-10-10 20:44:02', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (791, '092718133', '2020-10-10 20:44:02', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (792, '092718133', '2020-10-10 20:44:02', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (793, '092718133', '2020-10-10 20:44:02', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (794, '092718133', '2020-10-10 20:44:02', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (795, '092718133', '2020-10-10 20:44:02', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (796, '092718133', '2020-10-10 20:44:02', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (797, '092718133', '2020-10-10 20:44:03', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (798, '092718133', '2020-10-10 20:44:03', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (799, '092718133', '2020-10-10 20:44:03', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (800, '092718133', '2020-10-10 20:44:03', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (801, '092718133', '2020-10-10 20:44:03', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (802, '092718133', '2020-10-10 20:44:03', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (803, '092718133', '2020-10-10 20:44:03', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (804, '092718133', '2020-10-10 20:44:03', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (805, '092718133', '2020-10-10 20:44:03', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (806, '092718133', '2020-10-10 20:44:03', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (807, '092718133', '2020-10-10 20:44:03', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (808, '092718133', '2020-10-10 20:44:04', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (809, '092718133', '2020-10-10 20:44:04', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (810, '092718133', '2020-10-10 20:44:04', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (811, '092718133', '2020-10-10 20:44:04', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (812, '092718133', '2020-10-10 20:44:04', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (813, '092718133', '2020-10-10 20:44:04', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (814, '092718133', '2020-10-10 20:44:04', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (815, '092718133', '2020-10-10 20:44:04', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (816, '092718133', '2020-10-10 20:44:04', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (817, '092718133', '2020-10-10 20:44:04', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (818, '092718133', '2020-10-10 20:44:04', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (819, '092718133', '2020-10-10 20:44:05', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (820, '092718133', '2020-10-10 20:44:05', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (821, '092718133', '2020-10-10 20:44:05', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (822, '092718133', '2020-10-10 20:44:05', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (823, '092718133', '2020-10-10 20:44:05', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (824, '092718133', '2020-10-10 20:44:05', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (825, '092718133', '2020-10-10 20:44:05', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (826, '092718133', '2020-10-10 20:44:17', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (827, '092718133', '2020-10-10 20:44:17', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (828, '092718133', '2020-10-10 20:44:31', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (829, '092718133', '2020-11-17 10:57:58', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (830, '092718133', '2020-11-17 10:58:11', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (831, '092718133', '2020-11-17 10:58:19', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (832, '092718133', '2020-11-17 10:58:33', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (833, '092718133', '2020-11-17 10:59:55', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (834, '092718133', '2020-11-17 11:00:13', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (835, '092718133', '2020-11-17 11:02:23', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (836, '092718133', '2020-11-17 11:04:35', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (837, '092718133', '2020-11-17 11:21:13', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (838, '092718133', '2020-11-17 11:21:18', 'localhost', '登陆失败');
INSERT INTO `m_user_log` VALUES (839, '092718133', '2020-11-17 11:21:22', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (840, '092718133', '2020-11-17 11:22:39', 'localhost', '登陆成功');
INSERT INTO `m_user_log` VALUES (841, '092718133', '2020-11-17 11:47:55', 'localhost', '登陆成功');

-- ----------------------------
-- Table structure for m_user_role
-- ----------------------------
DROP TABLE IF EXISTS `m_user_role`;
CREATE TABLE `m_user_role`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `role_name` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_name`(`role_name`) USING BTREE,
  INDEX `m_user_role_ibfk_2`(`username`) USING BTREE,
  CONSTRAINT `m_user_role_ibfk_1` FOREIGN KEY (`role_name`) REFERENCES `m_role` (`role_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `m_user_role_ibfk_2` FOREIGN KEY (`username`) REFERENCES `m_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 189 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of m_user_role
-- ----------------------------
INSERT INTO `m_user_role` VALUES (124, '092718133', '用户');
INSERT INTO `m_user_role` VALUES (125, '092718133', '管理员');
INSERT INTO `m_user_role` VALUES (126, '092718133', '领导');
INSERT INTO `m_user_role` VALUES (149, '092718115', '用户');
INSERT INTO `m_user_role` VALUES (150, '092718114', '用户');
INSERT INTO `m_user_role` VALUES (151, '092718113', '用户');
INSERT INTO `m_user_role` VALUES (157, '092718107', '用户');
INSERT INTO `m_user_role` VALUES (158, '092718106', '用户');
INSERT INTO `m_user_role` VALUES (159, '092718105', '用户');
INSERT INTO `m_user_role` VALUES (161, '092718101', '用户');
INSERT INTO `m_user_role` VALUES (162, '092718102', '用户');
INSERT INTO `m_user_role` VALUES (164, '092718111', '用户');
INSERT INTO `m_user_role` VALUES (165, '092718110', '用户');
INSERT INTO `m_user_role` VALUES (166, '092718109', '用户');
INSERT INTO `m_user_role` VALUES (167, '092718108', '用户');
INSERT INTO `m_user_role` VALUES (168, '092718117', '用户');
INSERT INTO `m_user_role` VALUES (170, '092718103', '用户');
INSERT INTO `m_user_role` VALUES (176, '092718144', '用户');
INSERT INTO `m_user_role` VALUES (182, '092718112', '用户');
INSERT INTO `m_user_role` VALUES (184, '092718142', '用户');
INSERT INTO `m_user_role` VALUES (186, '092718140', '用户');
INSERT INTO `m_user_role` VALUES (187, '092718140', '管理员');
INSERT INTO `m_user_role` VALUES (188, '092718140', '领导');

SET FOREIGN_KEY_CHECKS = 1;
