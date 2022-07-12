/*
Navicat MySQL Data Transfer

Source Server         : 测试
Source Server Version : 50638
Source Host           : 118.31.184.240:3306
Source Database       : tester_plat

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2022-07-12 15:01:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for action
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` int(255) DEFAULT NULL,
  `action_key` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `parent_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of action
-- ----------------------------
INSERT INTO `action` VALUES ('1', '数据库操作', '3', '', '', '0', '2022-06-13 10:58:19', '2022-06-13 10:58:19', '0');
INSERT INTO `action` VALUES ('2', '常用操作', '3', '', '', '0', '2022-06-13 10:58:21', '2022-06-13 10:58:22', '0');
INSERT INTO `action` VALUES ('3', '执行python文件', '3', 'EXECPYTHON', '执行python文件', '2', '2022-06-13 11:02:59', '2022-06-13 11:02:59', '0');
INSERT INTO `action` VALUES ('4', '执行查询sql', '3', 'QUERYSQL', '执行查询sql', '1', '2022-06-13 11:03:23', '2022-06-13 11:03:23', '0');
INSERT INTO `action` VALUES ('5', '执行sql', '3', 'UPDATESQL', '执行sql', '1', '2022-06-13 11:03:51', '2022-06-13 11:03:51', '0');
INSERT INTO `action` VALUES ('6', '常用操作', '0', '', '', '0', '2022-06-14 10:16:11', '2022-06-14 10:16:11', '0');
INSERT INTO `action` VALUES ('7', '浏览器操作', '0', '', '', '0', '2022-06-14 10:16:28', '2022-06-14 10:16:28', '0');
INSERT INTO `action` VALUES ('8', '元素操作', '0', '', '', '0', '2022-06-14 10:17:16', '2022-06-14 10:17:16', '0');
INSERT INTO `action` VALUES ('9', 'DOM操作', '0', '', '', '0', '2022-06-14 10:17:48', '2022-06-14 10:17:48', '0');
INSERT INTO `action` VALUES ('10', '打开网址', '0', 'OPENURL', '打开网址', '7', '2022-06-14 10:18:27', '2022-06-14 10:18:27', '0');
INSERT INTO `action` VALUES ('11', 'driver后退', '0', 'BACK', 'driver后退', '7', '2022-06-14 10:18:54', '2022-06-14 10:18:54', '0');
INSERT INTO `action` VALUES ('12', 'driver前进', '0', 'FORWARD', 'driver前进', '7', '2022-06-14 10:19:07', '2022-06-14 10:19:07', '0');
INSERT INTO `action` VALUES ('13', '网址刷新', '0', 'REFRESH', '网址刷新', '7', '2022-06-14 10:19:22', '2022-06-14 10:19:22', '0');
INSERT INTO `action` VALUES ('14', '点击元素', '0', 'CLICK', '点击元素', '8', '2022-06-14 10:19:52', '2022-06-14 10:19:52', '0');
INSERT INTO `action` VALUES ('15', '文本输入', '0', 'INPUT', '文本输入', '8', '2022-06-14 10:20:08', '2022-06-14 10:20:08', '0');
INSERT INTO `action` VALUES ('16', '清除文本', '0', 'CLEAR', '清除文本', '8', '2022-06-14 10:20:31', '2022-06-14 10:20:31', '0');
INSERT INTO `action` VALUES ('17', '强制等待', '0', 'SLEEP', '强制等待', '6', '2022-06-14 10:20:53', '2022-06-14 10:20:53', '0');
INSERT INTO `action` VALUES ('18', '获取颜色', '0', 'GETCOLOR', '获取颜色', '8', '2022-06-14 10:21:12', '2022-06-14 10:21:12', '0');
INSERT INTO `action` VALUES ('19', '获取背景色', '0', 'GETBACKGROUNDCOLOR', '获取背景色', '8', '2022-06-14 10:21:24', '2022-06-14 10:21:24', '0');
INSERT INTO `action` VALUES ('20', '获取元素内显示文本', '0', 'GETTEXT', '获取元素内显示文本', '8', '2022-06-14 10:21:40', '2022-06-14 10:21:40', '0');
INSERT INTO `action` VALUES ('21', '元素的指定计算样式属性的值', '0', 'CSSVALUE', '元素的指定计算样式属性的值', '8', '2022-06-14 10:21:53', '2022-06-14 10:21:53', '0');
INSERT INTO `action` VALUES ('22', 'Alert弹窗发送文本', '0', 'SENDALERTKEYS', 'Alert弹窗发送文本', '9', '2022-06-14 10:22:15', '2022-06-14 10:22:15', '0');
INSERT INTO `action` VALUES ('23', '接收Alert弹窗', '0', 'ACCEPTALERT', '接收Alert弹窗', '9', '2022-06-14 10:22:28', '2022-06-14 10:22:28', '0');
INSERT INTO `action` VALUES ('24', '取消Alert弹窗', '0', 'DISMISSALERT', '取消Alert弹窗', '9', '2022-06-14 10:22:40', '2022-06-14 10:22:40', '0');
INSERT INTO `action` VALUES ('25', '按照name切换Frame', '0', 'SWITCHFRAMEBYNAME', '按照name切换Frame', '9', '2022-06-14 10:22:51', '2022-06-14 10:22:51', '0');
INSERT INTO `action` VALUES ('26', '按照元素切换Frame', '0', 'SWITCHFRAMEBYELEMENT', '按照元素切换Frame', '9', '2022-06-14 10:23:04', '2022-06-14 10:23:04', '0');
INSERT INTO `action` VALUES ('27', '切换回默认内容', '0', 'TODEFAULTCONTENT', '切换回默认内容', '9', '2022-06-14 10:23:18', '2022-06-14 10:23:18', '0');
INSERT INTO `action` VALUES ('28', '常用操作', '1', '', '', '0', '2022-07-01 11:40:29', '2022-07-01 11:40:29', '0');
INSERT INTO `action` VALUES ('29', '线程等待', '1', 'SLEEP', '', '28', '2022-07-01 11:40:52', '2022-07-01 11:40:52', '0');
INSERT INTO `action` VALUES ('30', '手势操作', '1', '', '', '7', '2022-07-01 11:42:07', '2022-07-01 11:42:07', '0');
INSERT INTO `action` VALUES ('31', '手势操作', '1', '', '', '0', '2022-07-01 11:42:26', '2022-07-01 11:42:26', '0');
INSERT INTO `action` VALUES ('32', '键盘输入', '1', 'pressKey', '多个字符用中文逗号  ， 隔开', '31', '2022-07-01 11:43:14', '2022-07-01 11:43:14', '0');
INSERT INTO `action` VALUES ('33', '键盘删除', '1', 'key_del', '可选 删除次数 Integer 默认一次', '31', '2022-07-01 11:44:35', '2022-07-01 11:44:35', '0');
INSERT INTO `action` VALUES ('34', 'toast捕捉', '1', 'catch_toast', '', '28', '2022-07-01 11:45:21', '2022-07-01 11:45:21', '0');
INSERT INTO `action` VALUES ('35', '视图操作', '1', '', '', '0', '2022-07-01 11:45:43', '2022-07-01 11:45:43', '0');
INSERT INTO `action` VALUES ('36', '切换到H5视图', '1', 'switch_to_H5', '', '35', '2022-07-01 11:46:03', '2022-07-01 11:46:03', '0');
INSERT INTO `action` VALUES ('37', '切换到原生视图', '1', 'switch_to_native', '', '35', '2022-07-01 11:46:16', '2022-07-01 11:46:16', '0');
INSERT INTO `action` VALUES ('38', '元素操作', '1', '', '', '0', '2022-07-01 15:26:50', '2022-07-01 15:26:50', '0');
INSERT INTO `action` VALUES ('39', '点击元素', '1', 'click', '', '38', '2022-07-01 15:27:06', '2022-07-01 15:27:06', '0');
INSERT INTO `action` VALUES ('40', '元素输入', '1', 'input', null, '28', '2022-07-08 14:18:32', '2022-07-08 14:20:27', '0');

-- ----------------------------
-- Table structure for api_case
-- ----------------------------
DROP TABLE IF EXISTS `api_case`;
CREATE TABLE `api_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL,
  `case_grade` varchar(10) DEFAULT NULL,
  `case_person` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `case_type` varchar(255) DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `loop_count` int(255) DEFAULT NULL,
  `fail_continue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of api_case
-- ----------------------------

-- ----------------------------
-- Table structure for api_info
-- ----------------------------
DROP TABLE IF EXISTS `api_info`;
CREATE TABLE `api_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `labels` varchar(255) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `api_suite_id` int(11) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `req_header` varchar(255) DEFAULT NULL,
  `req_params` varchar(255) DEFAULT NULL,
  `req_body_data` varchar(255) DEFAULT NULL,
  `req_body_json` varchar(255) DEFAULT NULL,
  `req_body_type` varchar(255) DEFAULT NULL,
  `req_extract` varchar(255) DEFAULT NULL,
  `req_assert` varchar(255) DEFAULT NULL,
  `debug_rsp` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `virtual_res_body` varchar(255) DEFAULT NULL,
  `virtual_res_body_json` varchar(255) DEFAULT NULL,
  `virtual_body_type` varchar(255) DEFAULT NULL,
  `req_cookies` varchar(255) DEFAULT NULL,
  `domain_switch` int(11) DEFAULT NULL,
  `set_up_ids` varchar(255) DEFAULT NULL,
  `tear_down_ids` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of api_info
-- ----------------------------
INSERT INTO `api_info` VALUES ('1', '内部后台登录', null, '1', '6', '/api/crm/admin/v1/system/staff/login', 'POST', null, null, '已完成', '[{\"HeadersKey\":\"Content-Type\",\"HeadersValue\":\"application/json;charset=UTF-8\"}]', '[{}]', '[{}]', '{\"account\":\"d\",\"password\":\"5d93ceb70e2bf5daa84ec3d0cd2c731a\",\"validate\":\"\"}', '2', '[{\"dataSource\":\"\",\"extractExpress\":\"\",\"realType\":\"\",\"realValue\":\"\",\"varName\":\"\"}]', '[{\"assertResult\":null,\"dataSource\":\"\",\"expectRelation\":\"\",\"expectValue\":\"\",\"extractExpress\":\"\",\"realValue\":\"\"}]', null, null, null, null, null, null, null, null, '[{}]', '1', '[2]', '[]');
INSERT INTO `api_info` VALUES ('2', '内部后台登录1', null, '1', '7', '/api/crm/admin/v1/system/staff/login', 'POST', '1', null, '已完成', '[{\"HeadersKey\":\"Content-Type\",\"HeadersValue\":\"application/json;charset=UTF-8\"}]', '[{}]', '[{}]', '{\"account\":\"d\",\"password\":\"5d93ceb70e2bf5daa84ec3d0cd2c731a\",\"validate\":\"\"}', '2', '[{\"dataSource\":\"\",\"extractExpress\":\"\",\"realType\":\"\",\"realValue\":\"\",\"varName\":\"\"}]', '[{\"assertResult\":null,\"dataSource\":\"\",\"expectRelation\":\"\",\"expectValue\":\"\",\"extractExpress\":\"\",\"realValue\":\"\"}]', null, null, null, null, null, null, null, null, '[{}]', '1', '[2]', '[]');

-- ----------------------------
-- Table structure for app_config
-- ----------------------------
DROP TABLE IF EXISTS `app_config`;
CREATE TABLE `app_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT 'App名称',
  `app_package` varchar(50) NOT NULL COMMENT 'App包名',
  `app_activity` varchar(50) NOT NULL COMMENT 'App启动页',
  `no_reset` int(2) NOT NULL DEFAULT '1' COMMENT '启动前是否重置APP    1 重置  0 不重置',
  `automation_name` varchar(20) NOT NULL DEFAULT 'uiautomator2' COMMENT '自动化执行引擎  appium uiaotomator1  uiautomator2',
  `others` varchar(255) DEFAULT NULL COMMENT '其他启动配置信息',
  `del_flag` int(2) DEFAULT '0' COMMENT '删除标志 0 未删除 1 已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COMMENT='APP 启动配置表';

-- ----------------------------
-- Records of app_config
-- ----------------------------
INSERT INTO `app_config` VALUES ('1', '好单多多（old）', 'com.wanqiandaikuan.xddd', 'ui.activity.SplashActivity', '1', 'uiautomator2', '[]', '0', '2022-06-23 15:15:40', '2022-07-06 14:57:45');

-- ----------------------------
-- Table structure for auto_config
-- ----------------------------
DROP TABLE IF EXISTS `auto_config`;
CREATE TABLE `auto_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` int(255) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auto_config
-- ----------------------------
INSERT INTO `auto_config` VALUES ('1', 'selenium_location', 'id,xpath', null, '0', '2022-06-14 09:52:15', '2022-06-14 09:54:30', '0');
INSERT INTO `auto_config` VALUES ('2', 'elementType', 'button,input,alert', null, '0', '2022-06-14 09:55:51', '2022-06-14 09:56:10', '0');

-- ----------------------------
-- Table structure for case_api_relation
-- ----------------------------
DROP TABLE IF EXISTS `case_api_relation`;
CREATE TABLE `case_api_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `api_id` int(11) DEFAULT NULL,
  `case_id` int(11) DEFAULT NULL,
  `status` int(255) DEFAULT NULL,
  `api_info` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of case_api_relation
-- ----------------------------

-- ----------------------------
-- Table structure for case_category
-- ----------------------------
DROP TABLE IF EXISTS `case_category`;
CREATE TABLE `case_category` (
  `remark` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of case_category
-- ----------------------------

-- ----------------------------
-- Table structure for category_api
-- ----------------------------
DROP TABLE IF EXISTS `category_api`;
CREATE TABLE `category_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `method` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `source_type` int(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of category_api
-- ----------------------------
INSERT INTO `category_api` VALUES ('4', '0', null, 'CRM', null, '0', null, null, null, null);
INSERT INTO `category_api` VALUES ('5', '4', null, '登录', null, '0', null, null, null, null);
INSERT INTO `category_api` VALUES ('6', '5', 'POST', '内部后台登录', null, '1', null, null, null, null);
INSERT INTO `category_api` VALUES ('7', '6', 'POST', '内部后台登录1', null, '2', null, null, null, null);

-- ----------------------------
-- Table structure for db_config
-- ----------------------------
DROP TABLE IF EXISTS `db_config`;
CREATE TABLE `db_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `db_name` varchar(255) DEFAULT NULL,
  `jdbc_url` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `type` int(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of db_config
-- ----------------------------
INSERT INTO `db_config` VALUES ('1', '测试环境数据库', 'jdbc:mysql://118.31.184.240:3306', 'root', '3wHNY2Bq', null, '0', null, '2022-06-13 15:00:58', '0');

-- ----------------------------
-- Table structure for env_config
-- ----------------------------
DROP TABLE IF EXISTS `env_config`;
CREATE TABLE `env_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `env_name` varchar(255) DEFAULT NULL,
  `db_id` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of env_config
-- ----------------------------
INSERT INTO `env_config` VALUES ('1', '测试', '[1]', null, '2022-06-13 11:36:28', '2022-06-13 11:36:29', '0');
INSERT INTO `env_config` VALUES ('2', 'online', '[]', null, '2022-06-13 11:36:31', '2022-06-13 11:36:32', '0');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `number` int(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '接口自动化', '/interface', null, '1');
INSERT INTO `menu` VALUES ('2', 'Web自动化', '/selenium', null, '2');
INSERT INTO `menu` VALUES ('3', '用户管理', '/user', null, '6');
INSERT INTO `menu` VALUES ('4', '公共配置', '/common', null, '4');
INSERT INTO `menu` VALUES ('5', '测试工具', '/tools', null, '5');
INSERT INTO `menu` VALUES ('9', '测试页面', '/Tester', null, '9');
INSERT INTO `menu` VALUES ('11', '接口列表', '/interface/list', '1', '11');
INSERT INTO `menu` VALUES ('12', '用例列表', '/case/list', '1', '12');
INSERT INTO `menu` VALUES ('13', '执行计划', '/case/plan', '1', '13');
INSERT INTO `menu` VALUES ('14', '执行结果', '/case/result', '1', '14');
INSERT INTO `menu` VALUES ('15', '页面元素', '/selenium/page', '2', '21');
INSERT INTO `menu` VALUES ('16', '用例管理', '/selenium/case', '2', '22');
INSERT INTO `menu` VALUES ('17', '测试计划', '/selenium/plan', '2', '23');
INSERT INTO `menu` VALUES ('18', '测试结果', '/selenium/report', '2', '24');
INSERT INTO `menu` VALUES ('19', '角色管理', '/user/role', '3', '32');
INSERT INTO `menu` VALUES ('20', '用户列表', '/user/userList', '3', '31');
INSERT INTO `menu` VALUES ('23', 'Action', '/common/action', '4', '41');
INSERT INTO `menu` VALUES ('24', '项目管理', '/common/projects', '4', '42');
INSERT INTO `menu` VALUES ('25', 'CRM工具', '/tools/crm', '5', '51');
INSERT INTO `menu` VALUES ('26', '测试demo', '/test', '9', '91');
INSERT INTO `menu` VALUES ('28', 'App管理', '/common/appConfig', '4', '43');
INSERT INTO `menu` VALUES ('29', '执行机管理', '/common/mobilePhone', '4', '44');
INSERT INTO `menu` VALUES ('30', 'App自动化', '/Appium', null, '3');
INSERT INTO `menu` VALUES ('31', '页面元素', '/appium/page', '30', '61');
INSERT INTO `menu` VALUES ('32', '用例管理', '/appium/case', '30', '62');
INSERT INTO `menu` VALUES ('33', '测试计划', '/appium/plan', '30', '63');
INSERT INTO `menu` VALUES ('34', '测试报告', '/appium/report', '30', '64');

-- ----------------------------
-- Table structure for mobile_phone
-- ----------------------------
DROP TABLE IF EXISTS `mobile_phone`;
CREATE TABLE `mobile_phone` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '手机名称',
  `plat_form` varchar(50) NOT NULL COMMENT '平台信息',
  `plat_version` varchar(50) NOT NULL COMMENT '平台版本',
  `ip` varchar(20) NOT NULL COMMENT 'IP地址',
  `level` varchar(10) DEFAULT NULL COMMENT '电量信息',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '手机状态--  0 上线  1 下线  2 使用中',
  `del_flag` int(2) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='APP-执行机';

-- ----------------------------
-- Records of mobile_phone
-- ----------------------------
INSERT INTO `mobile_phone` VALUES ('1', 'OPPO', 'ANDROID', '8.1.0', '192.168.2.45', '39', '0', '0', '2022-06-23 14:49:56', '2022-07-11 11:44:59');

-- ----------------------------
-- Table structure for page_element
-- ----------------------------
DROP TABLE IF EXISTS `page_element`;
CREATE TABLE `page_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `element_name` varchar(255) DEFAULT NULL,
  `element_type` varchar(255) DEFAULT NULL,
  `location_way` varchar(255) DEFAULT NULL,
  `location_value` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `page_id` int(11) DEFAULT NULL,
  `conditions` varchar(255) DEFAULT NULL,
  `type` int(10) DEFAULT '1' COMMENT '1 WEB  2 APP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of page_element
-- ----------------------------
INSERT INTO `page_element` VALUES ('1', '账号输入框', 'input', 'xpath', '//*[@id=\"account\"]', '', '2022-06-16 14:56:16', '2022-07-07 14:40:02', '2', 'visibilityOfElementLocated', '1');
INSERT INTO `page_element` VALUES ('2', '密码输入框', 'input', 'xpath', '//*[@id=\"password\"]', '', '2022-06-16 14:56:19', '2022-07-07 14:40:03', '2', 'visibilityOfElementLocated', '1');
INSERT INTO `page_element` VALUES ('4', '温馨提示--同意', 'button', 'xpath', '//*[@resource-id=\"com.wanqiandaikuan.xddd:id/tvConfirm\"]', '', '2022-07-07 14:41:51', '2022-07-07 14:41:51', '8', 'elementToBeClickable', '1');
INSERT INTO `page_element` VALUES ('5', '切换密码登录', 'button', 'xpath', '//*[@resource-id=\"com.wanqiandaikuan.xddd:id/tv_pass_login\"]', '', '2022-07-07 14:47:47', '2022-07-07 14:47:47', '9', '', '1');
INSERT INTO `page_element` VALUES ('6', '手机号输入框', 'input', 'id', 'com.wanqiandaikuan.xddd:id/cetInput', '', '2022-07-07 14:56:30', '2022-07-08 17:41:27', '9', '', '1');
INSERT INTO `page_element` VALUES ('7', '密码输入框', 'input', 'id', 'com.wanqiandaikuan.xddd:id/etPassword', '', '2022-07-07 14:57:27', '2022-07-07 14:57:27', '9', '', '1');
INSERT INTO `page_element` VALUES ('8', '协议按钮', 'input', 'id', 'com.wanqiandaikuan.xddd:id/iv_select_yd', '', '2022-07-07 14:58:31', '2022-07-07 14:58:31', '9', '', '1');
INSERT INTO `page_element` VALUES ('9', '登录按钮', 'button', 'id', 'com.wanqiandaikuan.xddd:id/btnLogin', '', '2022-07-07 14:58:56', '2022-07-07 14:58:56', '9', '', '1');

-- ----------------------------
-- Table structure for plan_param
-- ----------------------------
DROP TABLE IF EXISTS `plan_param`;
CREATE TABLE `plan_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_id` int(11) DEFAULT NULL,
  `api_ids` varchar(255) DEFAULT NULL,
  `case_ids` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of plan_param
-- ----------------------------
INSERT INTO `plan_param` VALUES ('1', '1', null, '[2]');
INSERT INTO `plan_param` VALUES ('2', '2', null, '[2]');
INSERT INTO `plan_param` VALUES ('3', '3', null, '[2]');
INSERT INTO `plan_param` VALUES ('4', '4', '[7]', '[]');
INSERT INTO `plan_param` VALUES ('5', '6', null, '[4]');

-- ----------------------------
-- Table structure for plan_result
-- ----------------------------
DROP TABLE IF EXISTS `plan_result`;
CREATE TABLE `plan_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_id` int(11) DEFAULT NULL,
  `plan_name` varchar(10) DEFAULT NULL,
  `plan_type` int(11) DEFAULT NULL,
  `result_status` int(10) DEFAULT NULL,
  `remark` varchar(10) DEFAULT NULL,
  `result` varchar(10) DEFAULT NULL,
  `api_success_count` int(10) DEFAULT NULL,
  `api_failed_count` int(10) DEFAULT NULL,
  `case_success_count` int(11) DEFAULT NULL,
  `case_failed_count` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of plan_result
-- ----------------------------

-- ----------------------------
-- Table structure for plan_result_detail
-- ----------------------------
DROP TABLE IF EXISTS `plan_result_detail`;
CREATE TABLE `plan_result_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_result_id` int(11) DEFAULT NULL,
  `case_id` int(11) DEFAULT NULL,
  `case_number` int(11) DEFAULT NULL,
  `api_id` bigint(20) DEFAULT NULL,
  `api_info` varchar(9999) DEFAULT NULL,
  `result` int(255) DEFAULT NULL,
  `result_console` varchar(255) DEFAULT NULL,
  `case_name` varchar(255) DEFAULT NULL,
  `assert_result` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of plan_result_detail
-- ----------------------------

-- ----------------------------
-- Table structure for plan_round
-- ----------------------------
DROP TABLE IF EXISTS `plan_round`;
CREATE TABLE `plan_round` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `action_id` int(11) DEFAULT NULL,
  `action_key` varchar(255) DEFAULT NULL,
  `action_value` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `plan_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `operate_data` varchar(255) DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of plan_round
-- ----------------------------
INSERT INTO `plan_round` VALUES ('1', 'GG', '4', null, null, '1', null, '0', '2022-06-13 16:12:57', '2022-06-13 16:43:00', '1', '1');
INSERT INTO `plan_round` VALUES ('2', 'GG', '4', null, null, '0', null, '0', '2022-06-13 16:12:57', '2022-06-13 16:12:57', '1', '1');

-- ----------------------------
-- Table structure for plat_project
-- ----------------------------
DROP TABLE IF EXISTS `plat_project`;
CREATE TABLE `plat_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prt_name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of plat_project
-- ----------------------------
INSERT INTO `plat_project` VALUES ('1', '内部后台', '', '2022-06-13 10:43:09', '2022-06-13 10:43:10', '0');
INSERT INTO `plat_project` VALUES ('2', '多融客', '', '2022-06-13 10:43:11', '2022-06-13 10:43:13', '0');

-- ----------------------------
-- Table structure for prt_domain
-- ----------------------------
DROP TABLE IF EXISTS `prt_domain`;
CREATE TABLE `prt_domain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prt_id` int(11) DEFAULT NULL,
  `env_id` int(11) DEFAULT NULL,
  `domain` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` varchar(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of prt_domain
-- ----------------------------
INSERT INTO `prt_domain` VALUES ('1', '1', '1', 'http://testcrm.qyhnet.com', null, '2022-06-13 10:46:41', '2022-06-13 10:47:15', '0');
INSERT INTO `prt_domain` VALUES ('3', '1', '2', 'https://crm.qyhnet.com', null, '2022-06-13 10:46:46', '2022-06-13 10:47:16', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `permission_url` text,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('2', 'test', '[\"/interface\",\"/interface/list\",\"/case/list\",\"/case/plan\",\"/case/result\",\"/selenium\",\"/selenium/page\",\"/selenium/case\",\"/selenium/plan\",\"/selenium/report\",\"/Appium\",\"/appium/page\",\"/appium/case\",\"/appium/plan\",\"/appium/report\",\"/common\",\"/common/action\",\"/common/projects\",\"/common/appConfig\",\"/common/mobilePhone\",\"/tools\",\"/tools/crm\",\"/user\",\"/user/userList\",\"/user/role\",\"/Tester\",\"/test\"]', '2022-06-15 16:01:50', '2022-06-15 16:01:57', '0');

-- ----------------------------
-- Table structure for run_plan
-- ----------------------------
DROP TABLE IF EXISTS `run_plan`;
CREATE TABLE `run_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `env_id` int(11) DEFAULT NULL,
  `try_count` int(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT '0',
  `is_clock` int(11) DEFAULT NULL,
  `clock` varchar(255) DEFAULT NULL,
  `is_send_email` int(11) DEFAULT NULL,
  `send_email` varchar(255) DEFAULT NULL,
  `plan_type` int(11) DEFAULT NULL,
  `clock_exec_count` int(255) DEFAULT NULL,
  `browser_type` varchar(255) DEFAULT NULL,
  `app_id` int(10) DEFAULT NULL COMMENT '运行APP',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of run_plan
-- ----------------------------
INSERT INTO `run_plan` VALUES ('3', 'WebPlan1', null, '1', '1', '0', '1', '0 0 0 1/1 * ?', '0', '', '1', null, 'Chrome', null, '2022-06-14 10:52:29', '2022-06-14 10:53:08', '0');
INSERT INTO `run_plan` VALUES ('4', 'ApiPlan', null, '1', '1', '0', '0', '', '0', '', '0', null, null, null, '2022-06-14 17:32:36', '2022-06-14 17:32:36', '0');
INSERT INTO `run_plan` VALUES ('6', 'appPlan2', null, '2', '1', '0', '0', '0 0 0 1/1 * ?', '0', '', '2', null, '', '1', '2022-07-06 10:06:50', '2022-07-06 10:06:50', '0');

-- ----------------------------
-- Table structure for ui_web_case
-- ----------------------------
DROP TABLE IF EXISTS `ui_web_case`;
CREATE TABLE `ui_web_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `type` int(11) DEFAULT NULL COMMENT '0  文件夹  1 用例',
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `case_type` int(10) DEFAULT '1' COMMENT '1 WEB 2 App',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ui_web_case
-- ----------------------------
INSERT INTO `ui_web_case` VALUES ('1', 'CRM 登录', '0', '0', '', '2022-06-14 10:06:25', '2022-06-14 10:06:28', '0', '1');
INSERT INTO `ui_web_case` VALUES ('2', 'case1', '1', '1', '', '2022-06-14 10:07:22', '2022-06-14 10:07:22', '0', '1');
INSERT INTO `ui_web_case` VALUES ('3', '轻易花APP', '0', '0', '', '2022-06-28 16:59:12', '2022-06-28 16:59:12', '0', '2');
INSERT INTO `ui_web_case` VALUES ('4', '登录', '3', '1', '', '2022-06-28 17:00:43', '2022-06-28 17:00:43', '0', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `locked` int(10) NOT NULL DEFAULT '0' COMMENT '是否锁定 0未锁定 1已锁定无法登陆',
  `role_id` int(10) DEFAULT NULL,
  `env_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'dujun', 'c60d82cfae878d88e506dac4b8da34cc', 'dujun', '0', '2', '1');

-- ----------------------------
-- Table structure for web_case_step
-- ----------------------------
DROP TABLE IF EXISTS `web_case_step`;
CREATE TABLE `web_case_step` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `case_id` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `step_describe` varchar(20) DEFAULT NULL,
  `action_id` int(11) DEFAULT NULL,
  `action_summary` varchar(20) DEFAULT NULL,
  `element_id` int(11) DEFAULT NULL,
  `status` int(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `action_value` varchar(20) DEFAULT NULL,
  `assert_type` varchar(10) DEFAULT NULL,
  `assert_value` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of web_case_step
-- ----------------------------
INSERT INTO `web_case_step` VALUES ('1', '2', '1', '打开网址', '10', 'OPENURL', null, '0', null, null, 'http://testcrm.qyhne', null, null);
INSERT INTO `web_case_step` VALUES ('2', '2', '2', '文本输入', '15', 'INPUT', '1', '0', null, null, 'dujun', null, null);
INSERT INTO `web_case_step` VALUES ('3', '2', '3', '强制等待', '17', 'SLEEP', null, '0', null, null, '3000', null, null);
INSERT INTO `web_case_step` VALUES ('9', '4', '1', '点击元素', '39', 'click', '4', '0', '2022-07-07 17:04:13', '2022-07-07 17:04:13', '', '', '');
INSERT INTO `web_case_step` VALUES ('10', '4', '2', '点击元素', '39', 'click', '5', '0', '2022-07-07 17:39:58', '2022-07-07 17:39:58', '', null, null);
INSERT INTO `web_case_step` VALUES ('13', '4', '6', '点击元素', '39', 'click', '8', '0', '2022-07-07 17:40:45', '2022-07-07 17:40:45', '', null, null);
INSERT INTO `web_case_step` VALUES ('14', '4', '7', '点击元素', '39', 'click', '9', '0', '2022-07-07 17:40:45', '2022-07-07 17:40:45', '', null, null);
INSERT INTO `web_case_step` VALUES ('15', '4', '4', '元素输入', '40', 'input', '6', '0', '2022-07-08 14:31:57', '2022-07-08 14:31:57', '17637898368', null, null);
INSERT INTO `web_case_step` VALUES ('16', '4', '5', '元素输入', '40', 'input', '7', '0', '2022-07-08 14:32:49', '2022-07-08 14:32:49', 'qwer12345', null, null);
INSERT INTO `web_case_step` VALUES ('17', '4', '8', '线程等待', '29', 'SLEEP', null, '0', '2022-07-08 17:46:06', '2022-07-08 17:46:06', '3000', null, null);
INSERT INTO `web_case_step` VALUES ('18', '4', '7', 'toast捕捉', '34', 'catch_toast', null, '0', '2022-07-08 17:46:26', '2022-07-08 17:46:26', '请输入正确的手机号或密码', null, null);

-- ----------------------------
-- Table structure for web_page
-- ----------------------------
DROP TABLE IF EXISTS `web_page`;
CREATE TABLE `web_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT '0',
  `del_flag` int(255) NOT NULL DEFAULT '0',
  `type` int(11) DEFAULT '1' COMMENT '1 WEB  2 App',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of web_page
-- ----------------------------
INSERT INTO `web_page` VALUES ('1', 'CRM', '0', '0', '1');
INSERT INTO `web_page` VALUES ('2', '登录', '1', '0', '1');
INSERT INTO `web_page` VALUES ('6', '轻易花APP', '0', '0', '2');
INSERT INTO `web_page` VALUES ('7', '登录', '6', '0', '2');
INSERT INTO `web_page` VALUES ('8', '启动页', '7', '0', '2');
INSERT INTO `web_page` VALUES ('9', '登录页', '7', '0', '2');
INSERT INTO `web_page` VALUES ('10', '7', '3', '0', '1');
INSERT INTO `web_page` VALUES ('11', '7', '10', '0', '1');
