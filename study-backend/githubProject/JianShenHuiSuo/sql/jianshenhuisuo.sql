/*
Navicat MySQL Data Transfer

Source Server         : 1
Source Server Version : 50511
Source Host           : localhost:3306
Source Database       : jianshenhuisuo

Target Server Type    : MYSQL
Target Server Version : 50511
File Encoding         : 65001

Date: 2020-05-08 16:40:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `huiyuankaleixing`
-- ----------------------------
DROP TABLE IF EXISTS `huiyuankaleixing`;
CREATE TABLE `huiyuankaleixing` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `kaleixing` varchar(50) DEFAULT NULL COMMENT '卡类型',
  `operatorId` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `itime` varchar(20) DEFAULT NULL COMMENT '操作时间',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `deleteFlag` int(1) DEFAULT '0' COMMENT '删除标识（0：正常；1：删除）',
  `erjiguanlianzd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of huiyuankaleixing
-- ----------------------------
INSERT INTO `huiyuankaleixing` VALUES ('1', '次卡', '1', '2020-04-0514:10:03 ', '', '0', '');
INSERT INTO `huiyuankaleixing` VALUES ('2', '月卡', '1', '2020-04-0514:10:08 ', '', '0', '');
INSERT INTO `huiyuankaleixing` VALUES ('3', '年卡', '1', '2020-04-05 14:10:12 ', '', '0', '');

-- ----------------------------
-- Table structure for `huiyuanxuanke`
-- ----------------------------
DROP TABLE IF EXISTS `huiyuanxuanke`;
CREATE TABLE `huiyuanxuanke` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `huiyuanzhanghao` varchar(50) DEFAULT NULL COMMENT '会员帐号',
  `kechengmingchen` varchar(50) DEFAULT NULL COMMENT '课程名称',
  `xuandingriqi` varchar(50) DEFAULT NULL COMMENT '选定日期',
  `operatorId` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `itime` varchar(20) DEFAULT NULL COMMENT '操作时间',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `deleteFlag` int(1) DEFAULT '0' COMMENT '删除标识（0：正常；1：删除）',
  `erjiguanlianzd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of huiyuanxuanke
-- ----------------------------
INSERT INTO `huiyuanxuanke` VALUES ('1', 'yonghu1', '室内高温yoga', '2020-04-02', '4', '2020-04-02 14:43:14 ', '', '0', '');
INSERT INTO `huiyuanxuanke` VALUES ('3', 'yonghu1', '瑜伽', '2020-04-04', '4', '2020-04-04 23:06:28 ', '瑜伽带我一个哦', '0', '');
INSERT INTO `huiyuanxuanke` VALUES ('4', 'yonghu1', '室内高温瑜伽', '2020-04-04', '4', '2020-04-04 23:06:40 ', '', '0', '');
INSERT INTO `huiyuanxuanke` VALUES ('19', 'jiaolian1', '1', '2020-04-10', '5', '2020-04-10 20:11:17 ', '', '0', '');
INSERT INTO `huiyuanxuanke` VALUES ('20', 'yonghu1', '室内高温瑜伽', '2020-04-15', '4', '2020-04-15 18:27:42 ', '', '0', '');
INSERT INTO `huiyuanxuanke` VALUES ('21', 'admin', '室内高温瑜伽', '2020-04-25', '1', '2020-04-15 20:29:18 ', '', '1', '');

-- ----------------------------
-- Table structure for `jianshenqixiexinxi`
-- ----------------------------
DROP TABLE IF EXISTS `jianshenqixiexinxi`;
CREATE TABLE `jianshenqixiexinxi` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `qixiemingchen` varchar(50) DEFAULT NULL COMMENT '器械名称',
  `caigoushijian` varchar(50) DEFAULT NULL COMMENT '采购时间',
  `jiage` varchar(50) DEFAULT NULL COMMENT '价格',
  `gongnenmiaoshu` varchar(200) DEFAULT NULL COMMENT '功能描述',
  `fuJian` varchar(200) DEFAULT NULL COMMENT '附件路径',
  `tuPian` varchar(200) DEFAULT NULL COMMENT '图片ID',
  `operatorId` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `itime` varchar(20) DEFAULT NULL COMMENT '操作时间',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `deleteFlag` int(1) DEFAULT '0' COMMENT '删除标识（0：正常；1：删除）',
  `erjiguanlianzd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jianshenqixiexinxi
-- ----------------------------
INSERT INTO `jianshenqixiexinxi` VALUES ('1', '健身自行车', '2020-04-04', '3800', '健身自行车', '', '202001', '1', '2020-04-04 17:40:44 ', '', '0', '');

-- ----------------------------
-- Table structure for `jiaoliandengji`
-- ----------------------------
DROP TABLE IF EXISTS `jiaoliandengji`;
CREATE TABLE `jiaoliandengji` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `jiaoliandengji` varchar(50) DEFAULT NULL COMMENT '教练等级',
  `operatorId` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `itime` varchar(20) DEFAULT NULL COMMENT '操作时间',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `deleteFlag` int(1) DEFAULT '0' COMMENT '删除标识（0：正常；1：删除）',
  `erjiguanlianzd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jiaoliandengji
-- ----------------------------
INSERT INTO `jiaoliandengji` VALUES ('1', '国家级', '1', '2020-04-12 02:37:46 ', '', '0', '');
INSERT INTO `jiaoliandengji` VALUES ('2', '省级', '1', '2020-04-12 02:37:48 ', '', '0', '');
INSERT INTO `jiaoliandengji` VALUES ('3', '普通', '1', '2020-04-12 02:37:50 ', '', '0', '');
INSERT INTO `jiaoliandengji` VALUES ('4', '世界级', '1', '2020-04-12 02:37:52 ', '', '0', '');

-- ----------------------------
-- Table structure for `jiaolianxinxi`
-- ----------------------------
DROP TABLE IF EXISTS `jiaolianxinxi`;
CREATE TABLE `jiaolianxinxi` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `jiaolianmingchen` varchar(50) DEFAULT NULL COMMENT '教练名称',
  `xingbie` varchar(50) DEFAULT NULL COMMENT '性别',
  `chushengriqi` varchar(50) DEFAULT NULL COMMENT '出生日期',
  `ruxingshijian` varchar(50) DEFAULT NULL COMMENT '入行时间',
  `congyejingli` varchar(200) DEFAULT NULL COMMENT '从业经历',
  `gerenjianjie` varchar(200) DEFAULT NULL COMMENT '个人简介',
  `lianxidianhua` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `fuJian` varchar(200) DEFAULT NULL COMMENT '附件路径',
  `tuPian` varchar(200) DEFAULT NULL COMMENT '图片ID',
  `operatorId` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `itime` varchar(20) DEFAULT NULL COMMENT '操作时间',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `deleteFlag` int(1) DEFAULT '0' COMMENT '删除标识（0：正常；1：删除）',
  `erjiguanlianzd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jiaolianxinxi
-- ----------------------------
INSERT INTO `jiaolianxinxi` VALUES ('1', 'jiaolian1', '男', '2000-01-01', '2020-04-04', '国家运动员', '国家运动员，健身教练资格。', '18888888888', 'D:/java/tomcat/webapps/ROOT/UploadFile/1.jpg', '202002', '1', '2020-04-12 02:29:42 ', '', '0', '');

-- ----------------------------
-- Table structure for `kechengleixing`
-- ----------------------------
DROP TABLE IF EXISTS `kechengleixing`;
CREATE TABLE `kechengleixing` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `kechengleixing` varchar(50) DEFAULT NULL COMMENT '课程类型',
  `operatorId` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `itime` varchar(20) DEFAULT NULL COMMENT '操作时间',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `deleteFlag` int(1) DEFAULT '0' COMMENT '删除标识（0：正常；1：删除）',
  `erjiguanlianzd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kechengleixing
-- ----------------------------
INSERT INTO `kechengleixing` VALUES ('1', '健身', '1', '2020-04-12 02:37:34 ', '1', '0', '');
INSERT INTO `kechengleixing` VALUES ('2', '瘦身', '1', '2020-04-12 02:37:37 ', '', '0', '');
INSERT INTO `kechengleixing` VALUES ('3', '养身', '1', '2020-04-12 02:37:40 ', '', '0', '');
INSERT INTO `kechengleixing` VALUES ('4', '塑身', '1', '2020-04-12 02:37:42 ', '', '0', '');

-- ----------------------------
-- Table structure for `lanmuguanli`
-- ----------------------------
DROP TABLE IF EXISTS `lanmuguanli`;
CREATE TABLE `lanmuguanli` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `lanmumingcheng` varchar(100) DEFAULT NULL COMMENT '栏目名称',
  `chuangjianren` varchar(20) DEFAULT NULL COMMENT '创建人',
  `operatorId` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `itime` varchar(20) DEFAULT NULL COMMENT '操作时间',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `deleteFlag` int(1) DEFAULT '0' COMMENT '删除标识（0：正常；1：删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lanmuguanli
-- ----------------------------
INSERT INTO `lanmuguanli` VALUES ('1', '网站信息', 'admin', '1', '2020-04-05 00:55:25 ', '', '0');
INSERT INTO `lanmuguanli` VALUES ('2', '活动促销', 'admin', '1', '2020-04-05 09:21:38', '', '0');
INSERT INTO `lanmuguanli` VALUES ('3', '健身知识', 'admin', '1', '2020-04-05 09:21:38', '', '0');

-- ----------------------------
-- Table structure for `luntanhudong`
-- ----------------------------
DROP TABLE IF EXISTS `luntanhudong`;
CREATE TABLE `luntanhudong` (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `fabiaotimu` varchar(50) DEFAULT NULL COMMENT '发表题目',
  `fabiaoneirong` varchar(200) DEFAULT NULL COMMENT '发表内容',
  `faburen` varchar(50) DEFAULT NULL COMMENT '发布人',
  `fabushijian` varchar(50) DEFAULT NULL COMMENT '发布时间',
  `fuJian` varchar(200) DEFAULT NULL COMMENT '附件路径',
  `operatorId` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `itime` varchar(20) DEFAULT NULL COMMENT '操作时间',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `deleteFlag` int(1) DEFAULT '0' COMMENT '删除标识（0：正常；1：删除）',
  `erjiguanlianzd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of luntanhudong
-- ----------------------------
INSERT INTO `luntanhudong` VALUES ('1', '冬季健身注意事项', '冬季健身注意事项知多少。', 'admin', '2020-04-02', '', '1', '2020-04-04 17:40:54 ', '', '0', '');
INSERT INTO `luntanhudong` VALUES ('3', '今天健身房营业吗', '想去健身', 'admin', '2020-04-03', '', '1', '2020-04-03 13:41:49 ', '', '0', '');
INSERT INTO `luntanhudong` VALUES ('4', '健身', '健身', 'admin', '2020-04-04', 'D:/java/tomcat/webapps/ROOT/UploadFile/2.jpg', '1', '2020-04-04 17:42:25 ', '', '0', '');
INSERT INTO `luntanhudong` VALUES ('8', '问题求助', '小伙伴们，这家健身房怎么样', 'yonghu1', '2020-04-14', '', '4', '2020-04-14 11:27:50 ', '', '0', '');
INSERT INTO `luntanhudong` VALUES ('9', '今天心情很棒', '有一起健身的嘛', 'yonghu1', '2020-04-15', '', '4', '2020-04-15 18:26:06 ', '', '0', '');
INSERT INTO `luntanhudong` VALUES ('10', '1', '1', 'admin', '2020-04-15', 'D:/java/tomcat/webapps/ROOT/UploadFile/1.jpg', '1', '2020-04-15 20:33:05 ', '', '1', '');

-- ----------------------------
-- Table structure for `menu_info`
-- ----------------------------
DROP TABLE IF EXISTS `menu_info`;
CREATE TABLE `menu_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_ename` varchar(50) DEFAULT NULL,
  `f_id` int(11) NOT NULL DEFAULT '0' COMMENT '父级菜单ID（fid为0表示为根节点）',
  `menu_todo` varchar(200) DEFAULT NULL COMMENT '菜单url',
  `menu_status` int(1) NOT NULL DEFAULT '1' COMMENT '菜单状态（1：正常； 0 ：非正常）',
  `menu_type` int(1) NOT NULL DEFAULT '1' COMMENT '菜单类型（0：系统菜单 ； 1：自定义普通菜单 ; 2 :自定义浏览菜单；3 :自定义审批菜单 ; 4:复制新增菜单）',
  `menu_index` int(11) DEFAULT '0' COMMENT '菜单显示序列',
  `menu_table` varchar(50) DEFAULT NULL COMMENT '菜单对应数据库表',
  `view_menu` int(1) NOT NULL DEFAULT '0' COMMENT '是否有浏览菜单（0：无 ； 1：有）',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `data_right` int(2) DEFAULT '0' COMMENT '数据权限（0：都可见；1：仅自己可见；2：自己和管理员可见）',
  `exDataRight` varchar(100) DEFAULT NULL COMMENT '附加数据权限',
  `export_excel` int(1) DEFAULT '0' COMMENT '是否需要导出excel（0：不需要；1：需要）',
  `fuJian` int(1) DEFAULT '0' COMMENT '是否需要附件（0：不需要；1：需要）',
  `frontS` int(1) DEFAULT '0',
  `shenpi` int(1) DEFAULT '0' COMMENT '审批（0：无审批 ； 1：有审批）',
  `is_default` int(1) DEFAULT '0' COMMENT '是否是默认菜单（0：不是；1：是）',
  `tuPian` int(1) DEFAULT '0' COMMENT '是否需要图片（0：不需要；1：需要）',
  `isErJi` int(1) DEFAULT '0' COMMENT '是否是二级页面（0：不是；1：是）',
  `erJiUrl` varchar(200) DEFAULT NULL COMMENT '二级页面url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_info
-- ----------------------------
INSERT INTO `menu_info` VALUES ('1', '系统管理', 'sysManage', '0', '/WEB-ROOT/app/sysManage/', '1', '0', '1', null, '0', '', '0', null, '0', '0', '0', '0', '0', '0', '0', null);
INSERT INTO `menu_info` VALUES ('2', '用户管理', 'systemUserManage', '1', '/WEB-ROOT/app/sysManage/systemUserManage.do', '1', '1', '1', 'user_info', '0', '', '0', null, '0', '0', '0', '0', '0', '0', '0', null);
INSERT INTO `menu_info` VALUES ('12', '个人信息', 'systemUserManage', '1', '/WEB-ROOT/app/sysManage/systemUserManage.do?from=self', '1', '0', '4', 'user_info', '0', null, '0', null, '0', '0', '0', '0', '0', '0', '0', null);
INSERT INTO `menu_info` VALUES ('13', '网站管理', 'wangzhanguanli', '0', '/WEB-ROOT/app/wangzhanguanli/', '1', '1', '0', null, '0', '', '0', null, '0', '0', '0', '0', '0', '0', '0', null);
INSERT INTO `menu_info` VALUES ('14', '栏目管理', 'lanmuguanli', '13', '/WEB-ROOT/app/wangzhanguanli/lanmuguanli.do?m=lanmuguanli', '1', '1', '1', 'lanmuguanli', '0', '', '0', null, '0', '0', '0', '0', '0', '0', '0', null);
INSERT INTO `menu_info` VALUES ('16', '首页幻灯片', 'shouyehuandengpian', '13', '/WEB-ROOT/app/wangzhanguanli/shouyehuandengpian.do?m=shouyehuandengpian', '1', '1', '3', 'shouyehuandengpian', '0', '', '0', null, '0', '1', '0', '0', '0', '0', '0', null);
INSERT INTO `menu_info` VALUES ('17', '文章管理', 'wenzhangguanli', '13', '/WEB-ROOT/app/wangzhanguanli/wenzhangguanli.do?m=wenzhangguanli', '1', '1', '2', 'wenzhangguanli', '0', '', '0', '', '0', '0', '0', '0', '0', '0', '0', null);
INSERT INTO `menu_info` VALUES ('18', '角色管理', 'systemRoleManage', '1', '/WEB-ROOT/app/sysManage/systemRoleManage.do?m=systemRoleManage', '1', '1', '2', 'systemRoleManage', '0', '', '0', '', '0', '0', '0', '0', '0', '0', '0', '');
INSERT INTO `menu_info` VALUES ('24', '健身基本设置', 'jianshenjibenshezhi', '0', '/WEB-ROOT/app/jianshenjibenshezhi/', '1', '1', '0', null, '0', '', null, null, null, null, '0', null, '0', null, null, null);
INSERT INTO `menu_info` VALUES ('25', '课程类型', 'kechengleixing', '24', '/WEB-ROOT/app/jianshenjibenshezhi/kechengleixing.do?m=kechengleixing', '1', '1', '0', 'kechengleixing', '0', '', '0', '', '0', '0', '0', '0', '0', '0', '0', '');
INSERT INTO `menu_info` VALUES ('26', '教练等级', 'jiaoliandengji', '24', '/WEB-ROOT/app/jianshenjibenshezhi/jiaoliandengji.do?m=jiaoliandengji', '1', '1', '0', 'jiaoliandengji', '0', '', '0', '', '0', '0', '0', '0', '0', '0', '0', '');
INSERT INTO `menu_info` VALUES ('27', '支付方式', 'zhifufangshi', '24', '/WEB-ROOT/app/jianshenjibenshezhi/zhifufangshi.do?m=zhifufangshi', '1', '1', '0', 'zhifufangshi', '0', '', '0', '', '0', '0', '0', '0', '0', '0', '0', '');
INSERT INTO `menu_info` VALUES ('28', '授课时间段', 'shoukeshijianduan', '24', '/WEB-ROOT/app/jianshenjibenshezhi/shoukeshijianduan.do?m=shoukeshijianduan', '1', '1', '0', 'shoukeshijianduan', '0', '', '0', '', '0', '0', '1', '0', '0', '0', '0', '');
INSERT INTO `menu_info` VALUES ('29', '会员卡类型', 'huiyuankaleixing', '24', '/WEB-ROOT/app/jianshenjibenshezhi/huiyuankaleixing.do?m=huiyuankaleixing', '1', '1', '0', 'huiyuankaleixing', '0', '', '0', '', '0', '0', '0', '0', '0', '0', '0', '');
INSERT INTO `menu_info` VALUES ('30', '会员信息管理', 'huiyuanxinxiguanli', '0', '/WEB-ROOT/app/huiyuanxinxiguanli/', '1', '1', '0', null, '0', '', null, null, null, null, '0', null, '0', null, null, null);
INSERT INTO `menu_info` VALUES ('34', '排课信息管理', 'paikexinxiguanli', '0', '/WEB-ROOT/app/paikexinxiguanli/', '1', '1', '0', null, '0', '', null, null, null, null, '0', null, '0', null, null, null);
INSERT INTO `menu_info` VALUES ('37', '教练信息管理', 'jiaolianxinxiguanli', '0', '/WEB-ROOT/app/jiaolianxinxiguanli/', '1', '1', '0', null, '0', '', null, null, null, null, '0', null, '0', null, null, null);
INSERT INTO `menu_info` VALUES ('38', '教练信息', 'jiaolianxinxi', '37', '/WEB-ROOT/app/jiaolianxinxiguanli/jiaolianxinxi.do?r=y&m=jiaolianxinxi', '1', '2', '0', 'jiaolianxinxi', '1', '', '0', '', '1', '1', '1', '0', '0', '1', '0', '');
INSERT INTO `menu_info` VALUES ('39', '教练管理', 'jiaolianxinxi', '37', '/WEB-ROOT/app/jiaolianxinxiguanli/jiaolianxinxi.do?m=jiaolianxinxi', '1', '1', '0', 'jiaolianxinxi', '1', '', '0', '', '1', '1', '0', '0', '0', '1', '0', '');
INSERT INTO `menu_info` VALUES ('40', '排课信息', 'paikexinxi', '34', '/WEB-ROOT/app/paikexinxiguanli/paikexinxi.do?r=y&m=paikexinxi', '1', '2', '0', 'paikexinxi', '1', '', '2', '', '1', '0', '1', '0', '0', '0', '0', '');
INSERT INTO `menu_info` VALUES ('41', '排课管理', 'paikexinxi', '34', '/WEB-ROOT/app/paikexinxiguanli/paikexinxi.do?m=paikexinxi', '1', '1', '0', 'paikexinxi', '1', '', '2', '', '1', '0', '0', '0', '0', '0', '0', '');
INSERT INTO `menu_info` VALUES ('42', '会员选课管理', 'huiyuanxuankeguanli', '0', '/WEB-ROOT/app/huiyuanxuankeguanli/', '1', '1', '0', null, '0', '', null, null, null, null, '0', null, '0', null, null, null);
INSERT INTO `menu_info` VALUES ('45', '消费签到管理', 'xiaofeiqiandaoguanli', '0', '/WEB-ROOT/app/xiaofeiqiandaoguanli/', '1', '1', '0', null, '0', '', null, null, null, null, '0', null, '0', null, null, null);
INSERT INTO `menu_info` VALUES ('46', '消费签到统计', 'xiaofeiqiandao', '65', '/WEB-ROOT/app/xiaofeiqiandaoguanli/xiaofeiqiandao.do?r=y&m=xiaofeiqiandao', '1', '2', '0', 'xiaofeiqiandao', '1', '', '2', '', '1', '0', '0', '0', '0', '0', '0', '');
INSERT INTO `menu_info` VALUES ('47', '消费签到信息', 'xiaofeiqiandao', '45', '/WEB-ROOT/app/xiaofeiqiandaoguanli/xiaofeiqiandao.do?m=xiaofeiqiandao', '1', '1', '0', 'xiaofeiqiandao', '1', '', '2', '', '1', '0', '0', '0', '0', '0', '0', '');
INSERT INTO `menu_info` VALUES ('48', '论坛互动管理', 'luntanhudongguanli', '0', '/WEB-ROOT/app/luntanhudongguanli/', '1', '1', '0', null, '0', '', null, null, null, null, '0', null, '0', null, null, null);
INSERT INTO `menu_info` VALUES ('49', '论坛互动', 'luntanhudong', '48', '/WEB-ROOT/app/luntanhudongguanli/luntanhudong.do?r=y&m=luntanhudong', '1', '2', '0', 'luntanhudong', '1', '', '2', '', '0', '1', '1', '0', '0', '0', '0', '');
INSERT INTO `menu_info` VALUES ('50', '论坛互动信息', 'luntanhudong', '48', '/WEB-ROOT/app/luntanhudongguanli/luntanhudong.do?m=luntanhudong', '1', '1', '0', 'luntanhudong', '1', '', '2', '', '0', '1', '0', '0', '0', '0', '0', '');
INSERT INTO `menu_info` VALUES ('55', '会员卡审批审批', 'zhucehuiyuan', '30', '/WEB-ROOT/app/huiyuanxinxiguanli/zhucehuiyuan.do?r=y&m=zhucehuiyuan&sp=1', '1', '3', '0', 'zhucehuiyuan', '1', '', '2', ' or yonghuzhanghao=\'\\\"+SysInfo.getLoginUserAcct(request, response)+\\\"\'  ', '1', '1', '0', '1', '0', '0', '0', '');
INSERT INTO `menu_info` VALUES ('56', '会员卡注册', 'zhucehuiyuan', '30', '/WEB-ROOT/app/huiyuanxinxiguanli/zhucehuiyuan.do?m=zhucehuiyuan', '1', '1', '0', 'zhucehuiyuan', '1', '', '2', ' or yonghuzhanghao=\'\\\"+SysInfo.getLoginUserAcct(request, response)+\\\"\'  ', '1', '1', '0', '1', '0', '0', '0', '');
INSERT INTO `menu_info` VALUES ('57', '会员选课', 'huiyuanxuanke', '42', '/WEB-ROOT/app/huiyuanxuankeguanli/huiyuanxuanke.do?r=y&m=huiyuanxuanke', '1', '2', '0', 'huiyuanxuanke', '1', '', '2', '', '0', '0', '1', '0', '0', '0', '0', '');
INSERT INTO `menu_info` VALUES ('58', '会员选课信息', 'huiyuanxuanke', '42', '/WEB-ROOT/app/huiyuanxuankeguanli/huiyuanxuanke.do?m=huiyuanxuanke', '1', '1', '0', 'huiyuanxuanke', '1', '', '2', '', '0', '0', '0', '0', '0', '0', '0', '');
INSERT INTO `menu_info` VALUES ('59', '健身器械管理', 'jianshenqixieguanli', '0', '/WEB-ROOT/app/jianshenqixieguanli/', '1', '1', '0', null, '0', '', null, null, null, null, '0', null, '0', null, null, null);
INSERT INTO `menu_info` VALUES ('60', '健身器械浏览', 'jianshenqixiexinxi', '59', '/WEB-ROOT/app/jianshenqixieguanli/jianshenqixiexinxi.do?r=y&m=jianshenqixiexinxi', '1', '2', '0', 'jianshenqixiexinxi', '1', '', '2', '', '1', '1', '0', '0', '0', '1', '0', '');
INSERT INTO `menu_info` VALUES ('61', '健身器械信息', 'jianshenqixiexinxi', '59', '/WEB-ROOT/app/jianshenqixieguanli/jianshenqixiexinxi.do?m=jianshenqixiexinxi', '1', '1', '0', 'jianshenqixiexinxi', '1', '', '2', '', '1', '1', '0', '0', '0', '1', '0', '');

-- ----------------------------
-- Table structure for `paikexinxi`
-- ----------------------------
DROP TABLE IF EXISTS `paikexinxi`;
CREATE TABLE `paikexinxi` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `jiaolianmingchen` varchar(50) DEFAULT NULL COMMENT '教练名称',
  `jiaoliandengji` varchar(50) DEFAULT NULL COMMENT '教练等级',
  `shoukemingchen` varchar(50) DEFAULT NULL COMMENT '授课名称',
  `kechengleixing` varchar(50) DEFAULT NULL COMMENT '课程类型',
  `shoukeshijian` varchar(50) DEFAULT NULL COMMENT '授课时间',
  `kechengmiaoshu` varchar(200) DEFAULT NULL COMMENT '课程描述',
  `operatorId` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `itime` varchar(20) DEFAULT NULL COMMENT '操作时间',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `deleteFlag` int(1) DEFAULT '0' COMMENT '删除标识（0：正常；1：删除）',
  `erjiguanlianzd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paikexinxi
-- ----------------------------
INSERT INTO `paikexinxi` VALUES ('1', 'jiaolian1', '国家级', '瑜伽', '健身', '周一（13:00 -- 15:00）', '瑜伽', '1', '2020-04-0214:15:52 ', '', '0', '');
INSERT INTO `paikexinxi` VALUES ('2', 'jiaolian1', '世界级', '室内高温瑜伽', '健身', '周一（15:00 -- 17:00）', '室内高温yoga，室内高温yoga。', '1', '2020-04-03 13:40:46 ', '', '0', '');
INSERT INTO `paikexinxi` VALUES ('3', 'jiaolian1', '世界级', '健身操', '塑身', '周一（13:00 -- 15:00）', '一起舞动起来吧', '4', '2020-04-04 23:10:09 ', '', '0', '');
INSERT INTO `paikexinxi` VALUES ('4', 'jiaolian1', '国家级', '1', '1', '周二随时预约', '1', '12', '2020-04-04 23:22:27 ', '1', '0', '');

-- ----------------------------
-- Table structure for `role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(10) NOT NULL COMMENT '角色ID',
  `menu_id` int(10) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('5', '1', '2');
INSERT INTO `role_menu` VALUES ('6', '1', '1');
INSERT INTO `role_menu` VALUES ('7', '1', '4');
INSERT INTO `role_menu` VALUES ('8', '1', '18');
INSERT INTO `role_menu` VALUES ('19', '3', '12');
INSERT INTO `role_menu` VALUES ('20', '3', '1');
INSERT INTO `role_menu` VALUES ('78', '4', '40');
INSERT INTO `role_menu` VALUES ('79', '4', '34');
INSERT INTO `role_menu` VALUES ('80', '4', '41');
INSERT INTO `role_menu` VALUES ('125', '2', '12');
INSERT INTO `role_menu` VALUES ('126', '2', '1');
INSERT INTO `role_menu` VALUES ('127', '2', '40');
INSERT INTO `role_menu` VALUES ('128', '2', '34');
INSERT INTO `role_menu` VALUES ('129', '2', '38');
INSERT INTO `role_menu` VALUES ('130', '2', '37');
INSERT INTO `role_menu` VALUES ('131', '2', '57');
INSERT INTO `role_menu` VALUES ('132', '2', '42');
INSERT INTO `role_menu` VALUES ('133', '2', '58');
INSERT INTO `role_menu` VALUES ('134', '2', '49');
INSERT INTO `role_menu` VALUES ('135', '2', '48');
INSERT INTO `role_menu` VALUES ('136', '2', '50');
INSERT INTO `role_menu` VALUES ('137', '2', '60');
INSERT INTO `role_menu` VALUES ('138', '2', '59');

-- ----------------------------
-- Table structure for `shoukeshijianduan`
-- ----------------------------
DROP TABLE IF EXISTS `shoukeshijianduan`;
CREATE TABLE `shoukeshijianduan` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `shijianduan` varchar(50) DEFAULT NULL COMMENT '时间段',
  `operatorId` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `itime` varchar(20) DEFAULT NULL COMMENT '操作时间',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `deleteFlag` int(1) DEFAULT '0' COMMENT '删除标识（0：正常；1：删除）',
  `erjiguanlianzd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoukeshijianduan
-- ----------------------------
INSERT INTO `shoukeshijianduan` VALUES ('1', '周一（13:00 -- 15:00）', '1', '2020-04-12 02:36:58 ', '', '0', '');
INSERT INTO `shoukeshijianduan` VALUES ('2', '周一（15:00 -- 17:00）', '1', '2020-04-12 02:37:04 ', '', '0', '');
INSERT INTO `shoukeshijianduan` VALUES ('3', '周二随时预约', '2', '2020-04-04 23:21:49 ', '', '0', '');
INSERT INTO `shoukeshijianduan` VALUES ('4', '123', '1', '2020-05-07 20:54:56 ', '', '1', '');

-- ----------------------------
-- Table structure for `shouyehuandengpian`
-- ----------------------------
DROP TABLE IF EXISTS `shouyehuandengpian`;
CREATE TABLE `shouyehuandengpian` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `tupianmingcheng` varchar(100) DEFAULT NULL COMMENT '图片名称',
  `tupianbianhao` varchar(200) DEFAULT NULL COMMENT '图片编号',
  `changd` varchar(10) DEFAULT NULL COMMENT '长度',
  `kuandu` varchar(10) DEFAULT NULL COMMENT '宽度',
  `fuJian` varchar(200) DEFAULT NULL COMMENT '附件路径',
  `operatorId` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `itime` varchar(20) DEFAULT NULL COMMENT '操作时间',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `deleteFlag` int(1) DEFAULT '0' COMMENT '删除标识（0：正常；1：删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shouyehuandengpian
-- ----------------------------
INSERT INTO `shouyehuandengpian` VALUES ('1', 'banner1.jpg', '1', '490', '250', 'D:/java/tomcat/webapps/ROOT/UploadFile/1.png', '1', '2020-04-07 23:25:54 ', '', '0');
INSERT INTO `shouyehuandengpian` VALUES ('2', 'banner2.jpg', '2', '490', '250', 'D:/java/tomcat/webapps/ROOT/UploadFile/banner2.jpg', '1', '2020-04-12 02:12:29 ', '', '0');
INSERT INTO `shouyehuandengpian` VALUES ('3', 'banner3.jpg', '3', '490', '250', 'D:/java/tomcat/webapps/ROOT/UploadFile/banner3.jpg', '1', '2020-04-12 02:14:05 ', '', '0');
INSERT INTO `shouyehuandengpian` VALUES ('4', 'banner4.jpg', '4', '490', '250', 'D:/java/tomcat/webapps/ROOT/UploadFile/banner4.jpg', '1', '2020-04-12 02:14:13 ', '', '0');

-- ----------------------------
-- Table structure for `systemrolemanage`
-- ----------------------------
DROP TABLE IF EXISTS `systemrolemanage`;
CREATE TABLE `systemrolemanage` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `jiaosemingchen` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `operatorId` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `itime` varchar(20) DEFAULT NULL COMMENT '操作时间',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `deleteFlag` int(1) DEFAULT '0' COMMENT '删除标识（0：正常；1：删除）',
  `erjiguanlianzd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemrolemanage
-- ----------------------------
INSERT INTO `systemrolemanage` VALUES ('1', '系统管理员', '1', '2020-04-04 11:00:32 ', '', '0', '');
INSERT INTO `systemrolemanage` VALUES ('2', '普通用户', '1', '2020-04-04 11:00:42 ', '', '0', '');
INSERT INTO `systemrolemanage` VALUES ('3', '会籍员', '1', '2020-04-14 10:32:52 ', '', '0', '');
INSERT INTO `systemrolemanage` VALUES ('4', '教练员', '1', '2020-04-14 14:11:14 ', '', '0', '');

-- ----------------------------
-- Table structure for `system_info`
-- ----------------------------
DROP TABLE IF EXISTS `system_info`;
CREATE TABLE `system_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `systemName` varchar(100) NOT NULL DEFAULT '后台管理系统' COMMENT '系统名称',
  `systemDesigner` varchar(50) NOT NULL DEFAULT '***' COMMENT '设计人名称',
  `loginPageStyle` varchar(50) NOT NULL DEFAULT '方案一' COMMENT '登陆界面样式',
  `systemSkin` varchar(50) NOT NULL DEFAULT '' COMMENT '系统主题皮肤',
  `systemSkinName` varchar(50) NOT NULL DEFAULT '默认主题' COMMENT '系统主题皮肤名称',
  `topPanDisplay` varchar(1) NOT NULL DEFAULT '1' COMMENT '上部面板是否显示（1：显示 ； 0：不显示）',
  `southPanDisplay` varchar(1) NOT NULL DEFAULT '1' COMMENT '下部面板是否显示（1：显示 ； 0：不显示）',
  `menuRegion` varchar(1) NOT NULL DEFAULT '1' COMMENT '菜单显示方位（1：左边 ； 2：右边）',
  `menuCollapse` varchar(1) NOT NULL DEFAULT '1' COMMENT '菜单折叠（1：折叠 ； 0：不折叠）',
  `menuAnimate` varchar(1) NOT NULL DEFAULT '1' COMMENT '菜单动态效果（1：使用 ； 0：不使用）',
  `menuBackground` varchar(1) NOT NULL DEFAULT '0' COMMENT '菜单背景色（1：使用；0：不使用）',
  `isApprove` varchar(1) NOT NULL DEFAULT '0' COMMENT '注册后审批（0：不需要； 1：需要）',
  `houTaiZhuTi` varchar(50) NOT NULL COMMENT '后台主题',
  `qianTaiZhuTi` varchar(50) NOT NULL COMMENT '前台主题',
  `buttonPlace` varchar(1) NOT NULL COMMENT '新增和删除按钮放置位置',
  `searchPlace` varchar(1) NOT NULL COMMENT '查询面板放置的位置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_info
-- ----------------------------
INSERT INTO `system_info` VALUES ('1', '健身俱乐部综合管理系统', '欢迎您使用 健身俱乐部综合管理系统', '4', 'xtheme-gray-extend.css', '默认主题', '1', '1', '1', '1', '1', '0', '0', '7/index.html', '9/index.html', '0', '1');

-- ----------------------------
-- Table structure for `table_guanlian`
-- ----------------------------
DROP TABLE IF EXISTS `table_guanlian`;
CREATE TABLE `table_guanlian` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(100) NOT NULL COMMENT '当前表',
  `field_name` varchar(50) NOT NULL COMMENT '本表字段名称',
  `guanlianbiao` varchar(100) DEFAULT NULL COMMENT '关联表名称',
  `guanlianziduan` varchar(50) DEFAULT NULL COMMENT '关联表字段',
  `guanlian` int(1) NOT NULL DEFAULT '0' COMMENT '是否需要关联（0：不需要； 1：需要）',
  `bitian` int(1) NOT NULL DEFAULT '1' COMMENT '是否必填（0：不必填； 1：必填）',
  `zhidu` int(1) NOT NULL DEFAULT '0' COMMENT '是否只读（0：不只读； 1：只读）',
  `morenzhi` varchar(50) DEFAULT NULL COMMENT '默认值',
  `ziduanleixing` varchar(50) DEFAULT 'varchar' COMMENT '字段类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_guanlian
-- ----------------------------
INSERT INTO `table_guanlian` VALUES ('4', 'lanmuguanli', 'lanmumingcheng', '', '', '0', '1', '0', '', null);
INSERT INTO `table_guanlian` VALUES ('5', 'lanmuguanli', 'chuangjianren', '', '', '0', '1', '0', '', null);
INSERT INTO `table_guanlian` VALUES ('13', 'shouyehuandengpian', 'tupianmingcheng', '', '', '0', '1', '0', '', null);
INSERT INTO `table_guanlian` VALUES ('14', 'shouyehuandengpian', 'tupianbianhao', '', '', '0', '1', '0', '', null);
INSERT INTO `table_guanlian` VALUES ('15', 'shouyehuandengpian', 'changd', '', '', '0', '1', '0', '', null);
INSERT INTO `table_guanlian` VALUES ('16', 'shouyehuandengpian', 'kuandu', '', '', '0', '1', '0', '', null);
INSERT INTO `table_guanlian` VALUES ('17', 'wenzhangguanli', 'wenzhangbiaoti', '', '', '0', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('18', 'wenzhangguanli', 'fubiaoti', '', '', '0', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('19', 'wenzhangguanli', 'zhengwen', '', '', '0', '1', '0', '', 'textarea');
INSERT INTO `table_guanlian` VALUES ('20', 'wenzhangguanli', 'luokuan', '', '', '0', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('21', 'wenzhangguanli', 'suoshulanmu', 'lanmuguanli', 'lanmumingcheng', '1', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('22', 'systemRoleManage', 'jiaosemingchen', '', '', '0', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('27', 'kechengleixing', 'kechengleixing', '', '', '0', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('28', 'jiaoliandengji', 'jiaoliandengji', '', '', '0', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('29', 'zhifufangshi', 'zhifufangshi', '', '', '0', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('30', 'shoukeshijianduan', 'shijianduan', '', '', '0', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('31', 'huiyuankaleixing', 'kaleixing', '', '', '0', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('47', 'jiaolianxinxi', 'jiaolianmingchen', 'user_info', 'userAcct', '1', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('48', 'jiaolianxinxi', 'xingbie', '', '', '0', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('49', 'jiaolianxinxi', 'chushengriqi', '', '', '0', '1', '0', '', 'date');
INSERT INTO `table_guanlian` VALUES ('50', 'jiaolianxinxi', 'ruxingshijian', '', '', '0', '1', '0', '', 'date');
INSERT INTO `table_guanlian` VALUES ('51', 'jiaolianxinxi', 'congyejingli', '', '', '0', '1', '0', '', 'textarea');
INSERT INTO `table_guanlian` VALUES ('52', 'jiaolianxinxi', 'gerenjianjie', '', '', '0', '1', '0', '', 'textarea');
INSERT INTO `table_guanlian` VALUES ('53', 'jiaolianxinxi', 'lianxidianhua', '', '', '0', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('54', 'paikexinxi', 'jiaolianmingchen', 'jiaolianxinxi', 'jiaolianmingchen', '1', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('55', 'paikexinxi', 'jiaoliandengji', 'jiaoliandengji', 'jiaoliandengji', '1', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('56', 'paikexinxi', 'shoukemingchen', '', '', '0', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('57', 'paikexinxi', 'kechengleixing', 'kechengleixing', 'kechengleixing', '1', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('58', 'paikexinxi', 'shoukeshijian', 'shoukeshijianduan', 'shijianduan', '1', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('59', 'paikexinxi', 'kechengmiaoshu', '', '', '0', '1', '0', '', 'textarea');
INSERT INTO `table_guanlian` VALUES ('63', 'xiaofeiqiandao', 'huiyuanzhanghao', 'zhucehuiyuan', 'yonghuzhanghao', '1', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('64', 'xiaofeiqiandao', 'xiaofeiriqi', '', '', '0', '1', '1', '', 'nowDate');
INSERT INTO `table_guanlian` VALUES ('65', 'xiaofeiqiandao', 'shouliren', '', '', '0', '1', '1', '', 'loginAcct');
INSERT INTO `table_guanlian` VALUES ('66', 'xiaofeiqiandao', 'shoulishijian', '', '', '0', '1', '1', '', 'nowDate');
INSERT INTO `table_guanlian` VALUES ('67', 'luntanhudong', 'fabiaotimu', '', '', '0', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('68', 'luntanhudong', 'fabiaoneirong', '', '', '0', '1', '0', '', 'textarea');
INSERT INTO `table_guanlian` VALUES ('69', 'luntanhudong', 'faburen', '', '', '0', '1', '1', '', 'loginAcct');
INSERT INTO `table_guanlian` VALUES ('70', 'luntanhudong', 'fabushijian', '', '', '0', '1', '1', '', 'nowDate');
INSERT INTO `table_guanlian` VALUES ('75', 'zhucehuiyuan', 'yonghuzhanghao', 'user_info', 'userAcct', '1', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('76', 'zhucehuiyuan', 'huiyuanleixing', 'huiyuankaleixing', 'kaleixing', '1', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('77', 'zhucehuiyuan', 'kaishishijian', '', '', '0', '0', '0', '', 'date');
INSERT INTO `table_guanlian` VALUES ('78', 'zhucehuiyuan', 'daoqishijian', '', '', '0', '0', '0', '', 'date');
INSERT INTO `table_guanlian` VALUES ('79', 'zhucehuiyuan', 'shengyucishu', '', '', '0', '0', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('80', 'zhucehuiyuan', 'zhifufangshi', 'zhifufangshi', 'zhifufangshi', '1', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('81', 'zhucehuiyuan', 'shouliren', '', '', '0', '1', '1', '', 'loginAcct');
INSERT INTO `table_guanlian` VALUES ('82', 'zhucehuiyuan', 'shoulishijian', '', '', '0', '1', '1', '', 'nowDate');
INSERT INTO `table_guanlian` VALUES ('83', 'zhucehuiyuan', 'shenpi', '', '', '0', '1', '1', '受理中', 'varchar');
INSERT INTO `table_guanlian` VALUES ('84', 'huiyuanxuanke', 'huiyuanzhanghao', '', '', '0', '1', '1', '', 'loginAcct');
INSERT INTO `table_guanlian` VALUES ('85', 'huiyuanxuanke', 'kechengmingchen', 'paikexinxi', 'shoukemingchen', '1', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('86', 'huiyuanxuanke', 'xuandingriqi', '', '', '0', '1', '1', '', 'nowDate');
INSERT INTO `table_guanlian` VALUES ('87', 'jianshenqixiexinxi', 'qixiemingchen', '', '', '0', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('88', 'jianshenqixiexinxi', 'caigoushijian', '', '', '0', '1', '0', '', 'date');
INSERT INTO `table_guanlian` VALUES ('89', 'jianshenqixiexinxi', 'jiage', '', '', '0', '1', '0', '', 'varchar');
INSERT INTO `table_guanlian` VALUES ('90', 'jianshenqixiexinxi', 'gongnenmiaoshu', '', '', '0', '1', '0', '', 'textarea');

-- ----------------------------
-- Table structure for `table_pic`
-- ----------------------------
DROP TABLE IF EXISTS `table_pic`;
CREATE TABLE `table_pic` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `picName` varchar(100) DEFAULT NULL COMMENT '图片名称',
  `picPath` varchar(200) DEFAULT NULL COMMENT '图片路径',
  `itime` varchar(20) DEFAULT NULL COMMENT '上传时间',
  `operatorId` varchar(11) DEFAULT NULL COMMENT '上传人ID',
  `detail` varchar(200) DEFAULT NULL COMMENT '备注',
  `tuPianIndex` varchar(50) NOT NULL COMMENT '图片所属记录ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_pic
-- ----------------------------
INSERT INTO `table_pic` VALUES ('1', 'banner2.jpg', 'E:/java/ide/myeclipse/workspace/JianShenHuiSuo/UploadFile/202001/banner2.jpg', '2017-12-21 09:18:16 ', '1', null, '202001');
INSERT INTO `table_pic` VALUES ('3', '1.jpg', 'E:/java/ide/myeclipse/workspace/JianShenHuiSuo/UploadFile/202002/1.jpg', '2018-01-02 23:38:06 ', '1', null, '202002');

-- ----------------------------
-- Table structure for `table_tongji`
-- ----------------------------
DROP TABLE IF EXISTS `table_tongji`;
CREATE TABLE `table_tongji` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) NOT NULL DEFAULT '' COMMENT '表名称',
  `field_name` varchar(50) NOT NULL DEFAULT '' COMMENT '字段名称',
  `tongji` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否需要统计（0：不需要； 1：需要）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_tongji
-- ----------------------------
INSERT INTO `table_tongji` VALUES ('4', 'lanmuguanli', 'lanmumingcheng', '0');
INSERT INTO `table_tongji` VALUES ('5', 'lanmuguanli', 'chuangjianren', '0');
INSERT INTO `table_tongji` VALUES ('13', 'shouyehuandengpian', 'tupianmingcheng', '0');
INSERT INTO `table_tongji` VALUES ('14', 'shouyehuandengpian', 'tupianbianhao', '0');
INSERT INTO `table_tongji` VALUES ('15', 'shouyehuandengpian', 'changd', '0');
INSERT INTO `table_tongji` VALUES ('16', 'shouyehuandengpian', 'kuandu', '0');
INSERT INTO `table_tongji` VALUES ('17', 'wenzhangguanli', 'wenzhangbiaoti', '0');
INSERT INTO `table_tongji` VALUES ('18', 'wenzhangguanli', 'fubiaoti', '0');
INSERT INTO `table_tongji` VALUES ('19', 'wenzhangguanli', 'zhengwen', '0');
INSERT INTO `table_tongji` VALUES ('20', 'wenzhangguanli', 'luokuan', '0');
INSERT INTO `table_tongji` VALUES ('21', 'wenzhangguanli', 'suoshulanmu', '0');
INSERT INTO `table_tongji` VALUES ('22', 'systemRoleManage', 'jiaosemingchen', '0');
INSERT INTO `table_tongji` VALUES ('27', 'kechengleixing', 'kechengleixing', '0');
INSERT INTO `table_tongji` VALUES ('28', 'jiaoliandengji', 'jiaoliandengji', '0');
INSERT INTO `table_tongji` VALUES ('29', 'zhifufangshi', 'zhifufangshi', '0');
INSERT INTO `table_tongji` VALUES ('30', 'shoukeshijianduan', 'shijianduan', '0');
INSERT INTO `table_tongji` VALUES ('31', 'huiyuankaleixing', 'kaleixing', '0');
INSERT INTO `table_tongji` VALUES ('47', 'jiaolianxinxi', 'jiaolianmingchen', '0');
INSERT INTO `table_tongji` VALUES ('48', 'jiaolianxinxi', 'xingbie', '0');
INSERT INTO `table_tongji` VALUES ('49', 'jiaolianxinxi', 'chushengriqi', '0');
INSERT INTO `table_tongji` VALUES ('50', 'jiaolianxinxi', 'ruxingshijian', '0');
INSERT INTO `table_tongji` VALUES ('51', 'jiaolianxinxi', 'congyejingli', '0');
INSERT INTO `table_tongji` VALUES ('52', 'jiaolianxinxi', 'gerenjianjie', '0');
INSERT INTO `table_tongji` VALUES ('53', 'jiaolianxinxi', 'lianxidianhua', '0');
INSERT INTO `table_tongji` VALUES ('54', 'paikexinxi', 'jiaolianmingchen', '0');
INSERT INTO `table_tongji` VALUES ('55', 'paikexinxi', 'jiaoliandengji', '0');
INSERT INTO `table_tongji` VALUES ('56', 'paikexinxi', 'shoukemingchen', '0');
INSERT INTO `table_tongji` VALUES ('57', 'paikexinxi', 'kechengleixing', '0');
INSERT INTO `table_tongji` VALUES ('58', 'paikexinxi', 'shoukeshijian', '0');
INSERT INTO `table_tongji` VALUES ('59', 'paikexinxi', 'kechengmiaoshu', '0');
INSERT INTO `table_tongji` VALUES ('63', 'xiaofeiqiandao', 'huiyuanzhanghao', '0');
INSERT INTO `table_tongji` VALUES ('64', 'xiaofeiqiandao', 'xiaofeiriqi', '0');
INSERT INTO `table_tongji` VALUES ('65', 'xiaofeiqiandao', 'shouliren', '0');
INSERT INTO `table_tongji` VALUES ('66', 'xiaofeiqiandao', 'shoulishijian', '0');
INSERT INTO `table_tongji` VALUES ('67', 'luntanhudong', 'fabiaotimu', '0');
INSERT INTO `table_tongji` VALUES ('68', 'luntanhudong', 'fabiaoneirong', '0');
INSERT INTO `table_tongji` VALUES ('69', 'luntanhudong', 'faburen', '0');
INSERT INTO `table_tongji` VALUES ('70', 'luntanhudong', 'fabushijian', '0');
INSERT INTO `table_tongji` VALUES ('75', 'zhucehuiyuan', 'yonghuzhanghao', '0');
INSERT INTO `table_tongji` VALUES ('76', 'zhucehuiyuan', 'huiyuanleixing', '0');
INSERT INTO `table_tongji` VALUES ('77', 'zhucehuiyuan', 'kaishishijian', '0');
INSERT INTO `table_tongji` VALUES ('78', 'zhucehuiyuan', 'daoqishijian', '0');
INSERT INTO `table_tongji` VALUES ('79', 'zhucehuiyuan', 'shengyucishu', '0');
INSERT INTO `table_tongji` VALUES ('80', 'zhucehuiyuan', 'zhifufangshi', '0');
INSERT INTO `table_tongji` VALUES ('81', 'zhucehuiyuan', 'shouliren', '0');
INSERT INTO `table_tongji` VALUES ('82', 'zhucehuiyuan', 'shoulishijian', '0');
INSERT INTO `table_tongji` VALUES ('83', 'zhucehuiyuan', 'shenpi', '0');
INSERT INTO `table_tongji` VALUES ('84', 'huiyuanxuanke', 'huiyuanzhanghao', '0');
INSERT INTO `table_tongji` VALUES ('85', 'huiyuanxuanke', 'kechengmingchen', '0');
INSERT INTO `table_tongji` VALUES ('86', 'huiyuanxuanke', 'xuandingriqi', '0');
INSERT INTO `table_tongji` VALUES ('87', 'jianshenqixiexinxi', 'qixiemingchen', '0');
INSERT INTO `table_tongji` VALUES ('88', 'jianshenqixiexinxi', 'caigoushijian', '0');
INSERT INTO `table_tongji` VALUES ('89', 'jianshenqixiexinxi', 'jiage', '0');
INSERT INTO `table_tongji` VALUES ('90', 'jianshenqixiexinxi', 'gongnenmiaoshu', '0');

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `Id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `userAcct` varchar(20) NOT NULL COMMENT '用户帐号',
  `userPass` varchar(20) NOT NULL COMMENT '用户密码',
  `deleteFlag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标识（0：正常 ； 1：删除）',
  `userName` varchar(20) NOT NULL COMMENT '用户名称',
  `userType` int(1) NOT NULL DEFAULT '2' COMMENT '用户类型（1：系统管理员；2：普通用户）',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态（0：未审批；1：审批通过）',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'admin', '049A4CE17FCDEA8C', '0', '系统管理员', '1', '1');
INSERT INTO `user_info` VALUES ('2', 'huijiyuan', '049A4CE17FCDEA8C', '0', '会籍员', '3', '1');
INSERT INTO `user_info` VALUES ('3', '2222', '049A4CE17FCDEA8C', '1', '录入2', '3', '1');
INSERT INTO `user_info` VALUES ('4', 'yonghu1', '049A4CE17FCDEA8C', '0', '普通用户', '2', '1');
INSERT INTO `user_info` VALUES ('5', 'jiaolian1', '049A4CE17FCDEA8C', '0', '教练', '4', '1');
INSERT INTO `user_info` VALUES ('6', 'yonghu2', '049A4CE17FCDEA8C', '1', 'yonghu2', '2', '1');
INSERT INTO `user_info` VALUES ('7', 'yonghu3', '049A4CE17FCDEA8C', '1', '普通用户', '2', '1');
INSERT INTO `user_info` VALUES ('8', '123', '9CB0FE7E39AC9F56', '1', 'leo', '2', '1');
INSERT INTO `user_info` VALUES ('9', '123456', '667A6BF1C8D08814', '1', '我是小白a ', '2', '1');
INSERT INTO `user_info` VALUES ('12', '4', '81C40388F347A1D6', '1', '4', '2', '1');
INSERT INTO `user_info` VALUES ('14', '11', 'E89DD8B3E17B3255', '1', '11', '2', '1');
INSERT INTO `user_info` VALUES ('15', '12', '95D9C5D0F010D003', '1', '12', '2', '1');
INSERT INTO `user_info` VALUES ('16', '111', '6679AF4DEADD3413', '1', '111', '2', '1');
INSERT INTO `user_info` VALUES ('18', '001', '93B7C5AFD4C99B9A', '1', '001', '2', '1');
INSERT INTO `user_info` VALUES ('19', '55555', '52CAF233729B5942', '1', '55555', '2', '1');
INSERT INTO `user_info` VALUES ('20', '555555', '03248D6668F6A057', '1', '1', '2', '1');
INSERT INTO `user_info` VALUES ('21', '66', '951C9B0BD092EE93', '1', '1', '2', '1');
INSERT INTO `user_info` VALUES ('22', '1234', 'C6DCF9C3673B51E2', '1', '1234', '2', '1');
INSERT INTO `user_info` VALUES ('23', '66', '951C9B0BD092EE93', '1', '66', '2', '1');
INSERT INTO `user_info` VALUES ('24', '123', '049A4CE17FCDEA8C', '1', '123', '2', '1');
INSERT INTO `user_info` VALUES ('25', '1234', 'C6DCF9C3673B51E2', '1', '1234', '2', '1');
INSERT INTO `user_info` VALUES ('26', '1111', 'C6DCF9C3673B51E2', '1', 'admin', '2', '1');
INSERT INTO `user_info` VALUES ('27', '11111', 'DEF7BDD1422904C6', '1', '11111', '2', '1');
INSERT INTO `user_info` VALUES ('28', '1', '049A4CE17FCDEA8C', '0', '1', '2', '1');
INSERT INTO `user_info` VALUES ('29', '2', '049A4CE17FCDEA8C', '0', '2', '2', '1');

-- ----------------------------
-- Table structure for `user_info_detail`
-- ----------------------------
DROP TABLE IF EXISTS `user_info_detail`;
CREATE TABLE `user_info_detail` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `userSex` varchar(1) DEFAULT NULL COMMENT '用户性别',
  `userAge` int(3) DEFAULT NULL COMMENT '用户年龄',
  `registerTime` varchar(10) DEFAULT NULL COMMENT '注册时间',
  `userId` int(10) DEFAULT NULL,
  `userPhone` varchar(20) DEFAULT NULL COMMENT '用户电话',
  `userName` varchar(10) DEFAULT NULL COMMENT '用户名称',
  `userDetail` varchar(100) DEFAULT NULL COMMENT '用户备注',
  `userDoc` varchar(200) DEFAULT NULL COMMENT '用户附件',
  `danwei` varchar(50) DEFAULT NULL,
  `bumen` varchar(50) DEFAULT NULL,
  `zhiwei` varchar(60) DEFAULT NULL,
  `idcard` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info_detail
-- ----------------------------
INSERT INTO `user_info_detail` VALUES ('1', '男', '22', '2020-04-01', '1', '18361226885', '系统管理员', '', '', '无', '', '', null);
INSERT INTO `user_info_detail` VALUES ('2', '男', null, '2020-04-01', '2', '', '会籍员', '', '', null, '', '', null);
INSERT INTO `user_info_detail` VALUES ('3', '男', null, '2020-04-01', '3', '', '录入2', '', '', null, '', '', null);
INSERT INTO `user_info_detail` VALUES ('4', '男', null, '2020-04-01', '4', '', '普通用户', '', '', null, '', '', null);
INSERT INTO `user_info_detail` VALUES ('5', '男', null, '2020-04-01', '5', '', '教练', '', '', null, '', '', null);
INSERT INTO `user_info_detail` VALUES ('6', '男', null, '2020-04-01', '6', '', 'yonghu2', '', '', null, '', '', null);
INSERT INTO `user_info_detail` VALUES ('7', '男', null, '2020-04-01', '7', '', '普通用户', '', '', null, '', '', null);
INSERT INTO `user_info_detail` VALUES ('8', '', null, '2020-04-02', '8', '', 'leo', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('9', '', null, '2020-04-02', '9', '', '我是小白a ', '', '', null, '', '', null);
INSERT INTO `user_info_detail` VALUES ('10', '', null, '2020-04-04', '10', '', '2', '', '', null, '', '密码2', null);
INSERT INTO `user_info_detail` VALUES ('11', '', null, '2020-04-04', '11', '', '3', '', '', null, '', '', null);
INSERT INTO `user_info_detail` VALUES ('12', '', null, '2020-04-04', '12', '', '4', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('13', '', '5', '2020-04-05', '13', '', '5', '', '', null, '', '', null);
INSERT INTO `user_info_detail` VALUES ('14', '', null, '2020-04-05', '13', '', '10', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('15', '', null, '2020-04-05', '14', '', '11', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('16', '', null, '2020-04-05', '15', '', '12', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('17', '', null, '2020-04-05', '16', '', '111', '', '', null, '', '', null);
INSERT INTO `user_info_detail` VALUES ('18', '', null, '2020-04-06', '17', '', '01', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('19', '', null, '2020-04-06', '18', '', '001', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('20', '', null, '2020-04-11', '19', '', '55555', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('21', '', null, '2020-04-11', '20', '', '1', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('22', '', null, '2020-04-11', '21', '', '1', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('23', '', null, '2020-04-12', '22', '', '1234', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('24', '男', null, '2020-04-12', '23', '', '66', '', '', null, '', '', null);
INSERT INTO `user_info_detail` VALUES ('25', '', null, '2020-04-14', '24', '', '123', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('26', '', null, '2020-04-15', '25', '', '1234', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('27', '', null, '2020-04-15', '26', '', 'admin', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('28', '', null, '2020-04-17', '6', '', '1', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('29', '', null, '2020-04-17', '7', '', '123', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('30', '', null, '2020-04-17', '8', '', '1234', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('31', '', null, '2020-04-18', '24', '', '12345678', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('32', '', null, '2020-04-18', '27', '', '11111', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('33', '', null, '2020-05-04', '28', '', '1', '', '', null, null, null, null);
INSERT INTO `user_info_detail` VALUES ('34', '', null, '2020-05-04', '29', '', '2', '', '', null, null, null, null);

-- ----------------------------
-- Table structure for `user_menu`
-- ----------------------------
DROP TABLE IF EXISTS `user_menu`;
CREATE TABLE `user_menu` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `menu_id` int(10) NOT NULL DEFAULT '0' COMMENT '菜单ID',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1160 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_menu
-- ----------------------------
INSERT INTO `user_menu` VALUES ('901', '1', '2');
INSERT INTO `user_menu` VALUES ('902', '1', '1');
INSERT INTO `user_menu` VALUES ('903', '1', '4');
INSERT INTO `user_menu` VALUES ('904', '1', '5');
INSERT INTO `user_menu` VALUES ('905', '1', '12');
INSERT INTO `user_menu` VALUES ('906', '1', '18');
INSERT INTO `user_menu` VALUES ('907', '1', '14');
INSERT INTO `user_menu` VALUES ('908', '1', '13');
INSERT INTO `user_menu` VALUES ('909', '1', '16');
INSERT INTO `user_menu` VALUES ('910', '1', '17');
INSERT INTO `user_menu` VALUES ('911', '1', '25');
INSERT INTO `user_menu` VALUES ('912', '1', '24');
INSERT INTO `user_menu` VALUES ('913', '1', '26');
INSERT INTO `user_menu` VALUES ('914', '1', '27');
INSERT INTO `user_menu` VALUES ('915', '1', '28');
INSERT INTO `user_menu` VALUES ('916', '1', '29');
INSERT INTO `user_menu` VALUES ('917', '1', '31');
INSERT INTO `user_menu` VALUES ('918', '1', '30');
INSERT INTO `user_menu` VALUES ('919', '1', '32');
INSERT INTO `user_menu` VALUES ('920', '1', '33');
INSERT INTO `user_menu` VALUES ('921', '1', '40');
INSERT INTO `user_menu` VALUES ('922', '1', '34');
INSERT INTO `user_menu` VALUES ('923', '1', '41');
INSERT INTO `user_menu` VALUES ('924', '1', '38');
INSERT INTO `user_menu` VALUES ('925', '1', '37');
INSERT INTO `user_menu` VALUES ('926', '1', '39');
INSERT INTO `user_menu` VALUES ('927', '1', '43');
INSERT INTO `user_menu` VALUES ('928', '1', '42');
INSERT INTO `user_menu` VALUES ('929', '1', '44');
INSERT INTO `user_menu` VALUES ('930', '1', '46');
INSERT INTO `user_menu` VALUES ('931', '1', '45');
INSERT INTO `user_menu` VALUES ('932', '1', '47');
INSERT INTO `user_menu` VALUES ('933', '1', '49');
INSERT INTO `user_menu` VALUES ('934', '1', '48');
INSERT INTO `user_menu` VALUES ('935', '1', '50');
INSERT INTO `user_menu` VALUES ('936', '1', '52');
INSERT INTO `user_menu` VALUES ('937', '1', '51');
INSERT INTO `user_menu` VALUES ('938', '1', '53');
INSERT INTO `user_menu` VALUES ('939', '1', '54');
INSERT INTO `user_menu` VALUES ('940', '1', '55');
INSERT INTO `user_menu` VALUES ('941', '1', '56');
INSERT INTO `user_menu` VALUES ('942', '1', '57');
INSERT INTO `user_menu` VALUES ('943', '1', '58');
INSERT INTO `user_menu` VALUES ('944', '1', '59');
INSERT INTO `user_menu` VALUES ('945', '1', '60');
INSERT INTO `user_menu` VALUES ('946', '1', '61');
INSERT INTO `user_menu` VALUES ('947', '1', '62');
INSERT INTO `user_menu` VALUES ('948', '1', '63');
INSERT INTO `user_menu` VALUES ('949', '1', '64');
INSERT INTO `user_menu` VALUES ('950', '1', '65');
INSERT INTO `user_menu` VALUES ('951', '1', '66');
INSERT INTO `user_menu` VALUES ('952', '1', '67');
INSERT INTO `user_menu` VALUES ('953', '1', '68');
INSERT INTO `user_menu` VALUES ('954', '4', '49');
INSERT INTO `user_menu` VALUES ('955', '4', '48');
INSERT INTO `user_menu` VALUES ('956', '4', '50');
INSERT INTO `user_menu` VALUES ('1069', '10', '12');
INSERT INTO `user_menu` VALUES ('1070', '10', '1');
INSERT INTO `user_menu` VALUES ('1071', '10', '56');
INSERT INTO `user_menu` VALUES ('1072', '10', '30');
INSERT INTO `user_menu` VALUES ('1073', '10', '40');
INSERT INTO `user_menu` VALUES ('1074', '10', '34');
INSERT INTO `user_menu` VALUES ('1075', '10', '38');
INSERT INTO `user_menu` VALUES ('1076', '10', '37');
INSERT INTO `user_menu` VALUES ('1077', '10', '57');
INSERT INTO `user_menu` VALUES ('1078', '10', '42');
INSERT INTO `user_menu` VALUES ('1079', '10', '47');
INSERT INTO `user_menu` VALUES ('1080', '10', '45');
INSERT INTO `user_menu` VALUES ('1081', '10', '49');
INSERT INTO `user_menu` VALUES ('1082', '10', '48');
INSERT INTO `user_menu` VALUES ('1083', '10', '50');
INSERT INTO `user_menu` VALUES ('1084', '10', '60');
INSERT INTO `user_menu` VALUES ('1085', '10', '59');
INSERT INTO `user_menu` VALUES ('1086', '11', '56');
INSERT INTO `user_menu` VALUES ('1087', '11', '30');
INSERT INTO `user_menu` VALUES ('1104', '12', '57');
INSERT INTO `user_menu` VALUES ('1105', '12', '42');
INSERT INTO `user_menu` VALUES ('1123', '5', '12');
INSERT INTO `user_menu` VALUES ('1124', '5', '1');
INSERT INTO `user_menu` VALUES ('1125', '5', '25');
INSERT INTO `user_menu` VALUES ('1126', '5', '24');
INSERT INTO `user_menu` VALUES ('1127', '5', '40');
INSERT INTO `user_menu` VALUES ('1128', '5', '34');
INSERT INTO `user_menu` VALUES ('1129', '5', '41');
INSERT INTO `user_menu` VALUES ('1130', '5', '38');
INSERT INTO `user_menu` VALUES ('1131', '5', '37');
INSERT INTO `user_menu` VALUES ('1132', '5', '57');
INSERT INTO `user_menu` VALUES ('1133', '5', '42');
INSERT INTO `user_menu` VALUES ('1134', '5', '58');
INSERT INTO `user_menu` VALUES ('1135', '5', '49');
INSERT INTO `user_menu` VALUES ('1136', '5', '48');
INSERT INTO `user_menu` VALUES ('1137', '5', '50');
INSERT INTO `user_menu` VALUES ('1138', '5', '60');
INSERT INTO `user_menu` VALUES ('1139', '5', '59');
INSERT INTO `user_menu` VALUES ('1140', '14', '40');
INSERT INTO `user_menu` VALUES ('1141', '14', '34');
INSERT INTO `user_menu` VALUES ('1144', '23', '12');
INSERT INTO `user_menu` VALUES ('1145', '23', '1');
INSERT INTO `user_menu` VALUES ('1146', '23', '40');
INSERT INTO `user_menu` VALUES ('1147', '23', '34');
INSERT INTO `user_menu` VALUES ('1148', '23', '38');
INSERT INTO `user_menu` VALUES ('1149', '23', '37');
INSERT INTO `user_menu` VALUES ('1150', '23', '57');
INSERT INTO `user_menu` VALUES ('1151', '23', '42');
INSERT INTO `user_menu` VALUES ('1152', '23', '58');
INSERT INTO `user_menu` VALUES ('1153', '23', '49');
INSERT INTO `user_menu` VALUES ('1154', '23', '48');
INSERT INTO `user_menu` VALUES ('1155', '23', '50');
INSERT INTO `user_menu` VALUES ('1156', '23', '60');
INSERT INTO `user_menu` VALUES ('1157', '23', '59');
INSERT INTO `user_menu` VALUES ('1158', '2', '56');
INSERT INTO `user_menu` VALUES ('1159', '2', '30');

-- ----------------------------
-- Table structure for `wenzhangguanli`
-- ----------------------------
DROP TABLE IF EXISTS `wenzhangguanli`;
CREATE TABLE `wenzhangguanli` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `wenzhangbiaoti` varchar(100) DEFAULT NULL COMMENT '文章标题',
  `fubiaoti` varchar(100) DEFAULT NULL COMMENT '副标题',
  `zhengwen` varchar(200) DEFAULT NULL COMMENT '正文',
  `luokuan` varchar(200) DEFAULT NULL COMMENT '落款',
  `suoshulanmu` varchar(100) DEFAULT NULL COMMENT '所属栏目',
  `operatorId` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `itime` varchar(20) DEFAULT NULL COMMENT '操作时间',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `deleteFlag` int(1) DEFAULT '0' COMMENT '删除标识（0：正常；1：删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wenzhangguanli
-- ----------------------------
INSERT INTO `wenzhangguanli` VALUES ('1', '欢迎来到刘天宇的健身俱乐部', '欢迎', '感谢光临!', 'admin', '网站信息', '1', '2020-04-12 02:37:26 ', '', '0');
INSERT INTO `wenzhangguanli` VALUES ('2', '夏季健身攻略', '攻略', '循序渐进最重要 长期不运动,身体各个器官的功能都处在一个较低的水平,故不能大强大训练！', 'admin', '健身知识', '1', '2020-04-05 16:13:58 ', '', '0');
INSERT INTO `wenzhangguanli` VALUES ('3', '疫情期间特惠！超值年卡！只要999元！', '超值', '由于疫情原因，增强全民体制，刘天宇健身俱乐部特推出超值年卡！', 'admin', '活动促销', '1', '2020-04-05 16:11:39 ', '', '0');

-- ----------------------------
-- Table structure for `xiaofeiqiandao`
-- ----------------------------
DROP TABLE IF EXISTS `xiaofeiqiandao`;
CREATE TABLE `xiaofeiqiandao` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `huiyuanzhanghao` varchar(50) DEFAULT NULL COMMENT '会员帐号',
  `xiaofeiriqi` varchar(50) DEFAULT NULL COMMENT '消费日期',
  `shouliren` varchar(50) DEFAULT NULL COMMENT '受理人',
  `shoulishijian` varchar(50) DEFAULT NULL COMMENT '受理时间',
  `operatorId` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `itime` varchar(20) DEFAULT NULL COMMENT '操作时间',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `deleteFlag` int(1) DEFAULT '0' COMMENT '删除标识（0：正常；1：删除）',
  `erjiguanlianzd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xiaofeiqiandao
-- ----------------------------
INSERT INTO `xiaofeiqiandao` VALUES ('10', 'yonghu1', '2020-04-17', 'admin', '2020-04-17', '1', '2020-04-17 23:30:13 ', '', '0', '');

-- ----------------------------
-- Table structure for `zhifufangshi`
-- ----------------------------
DROP TABLE IF EXISTS `zhifufangshi`;
CREATE TABLE `zhifufangshi` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `zhifufangshi` varchar(50) DEFAULT NULL COMMENT '支付方式',
  `operatorId` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `itime` varchar(20) DEFAULT NULL COMMENT '操作时间',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `deleteFlag` int(1) DEFAULT '0' COMMENT '删除标识（0：正常；1：删除）',
  `erjiguanlianzd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhifufangshi
-- ----------------------------
INSERT INTO `zhifufangshi` VALUES ('1', '在线支付', '1', '2020-04-12 02:37:55 ', '', '0', '');
INSERT INTO `zhifufangshi` VALUES ('2', '线下支付', '1', '2020-04-12 02:37:58 ', '', '0', '');

-- ----------------------------
-- Table structure for `zhucehuiyuan`
-- ----------------------------
DROP TABLE IF EXISTS `zhucehuiyuan`;
CREATE TABLE `zhucehuiyuan` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `yonghuzhanghao` varchar(50) DEFAULT NULL COMMENT '用户帐号',
  `huiyuanleixing` varchar(50) DEFAULT NULL COMMENT '会员类型',
  `kaishishijian` varchar(50) DEFAULT NULL COMMENT '开始时间',
  `daoqishijian` varchar(50) DEFAULT NULL COMMENT '到期时间',
  `shengyucishu` varchar(50) DEFAULT NULL COMMENT '剩余次数',
  `zhifufangshi` varchar(50) DEFAULT NULL COMMENT '支付方式',
  `shouliren` varchar(50) DEFAULT NULL COMMENT '受理人',
  `shoulishijian` varchar(50) DEFAULT NULL COMMENT '受理时间',
  `shenpi` varchar(50) DEFAULT NULL COMMENT '受理状态',
  `fuJian` varchar(100) DEFAULT NULL COMMENT '附件路径',
  `operatorId` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `itime` varchar(20) DEFAULT NULL COMMENT '操作时间',
  `detail` varchar(100) DEFAULT NULL COMMENT '备注',
  `deleteFlag` int(1) DEFAULT '0' COMMENT '删除标识（0：正常；1：删除）',
  `erjiguanlianzd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhucehuiyuan
-- ----------------------------
INSERT INTO `zhucehuiyuan` VALUES ('1', 'yonghu1', '年卡', '2020-04-15', '2021-04-14', '', '在线支付', 'admin', '2020-03-13', '审批通过', '', '1', '2020-04-15 19:17:43 ', '', '0', '');
INSERT INTO `zhucehuiyuan` VALUES ('3', '2', '次卡', '2020-05-04', '2020-05-04', '1', '在线支付', 'huijiyuan', '2020-05-04', '审批通过', '', '2', '2020-05-04 10:05:23 ', '', '0', '');
