/*
Navicat MySQL Data Transfer

Source Server         : 测试
Source Server Version : 50638
Source Host           : 118.31.184.240:3306
Source Database       : tester_plat

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2023-01-05 14:15:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for action
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '行动名称',
  `type` int(255) DEFAULT NULL COMMENT '行动类型   0 WEB  1 App  2 API 3 前置后置',
  `action_key` varchar(255) DEFAULT NULL COMMENT '行动KEY',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `parent_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COMMENT='自动化行为表';

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
INSERT INTO `action` VALUES ('29', '线程等待', '1', 'SLEEP', '单位： ms', '28', '2022-07-01 11:40:52', '2022-07-26 15:50:47', '0');
INSERT INTO `action` VALUES ('30', '手势操作', '1', '', '', '7', '2022-07-01 11:42:07', '2022-07-01 11:42:07', '0');
INSERT INTO `action` VALUES ('31', '手势操作', '1', '', '', '0', '2022-07-01 11:42:26', '2022-07-01 11:42:26', '0');
INSERT INTO `action` VALUES ('32', '键盘输入', '1', 'pressKey', '多个字符用中文逗号  ， 隔开', '31', '2022-07-01 11:43:14', '2022-07-01 11:43:14', '0');
INSERT INTO `action` VALUES ('33', '键盘删除', '1', 'key_del', '可选 删除次数 Integer 默认一次', '31', '2022-07-01 11:44:35', '2022-07-01 11:44:35', '0');
INSERT INTO `action` VALUES ('34', 'toast捕捉', '1', 'catch_toast', '参数： toast内容（选填）', '28', '2022-07-01 11:45:21', '2022-07-26 15:51:11', '0');
INSERT INTO `action` VALUES ('35', '视图操作', '1', '', '', '0', '2022-07-01 11:45:43', '2022-07-01 11:45:43', '0');
INSERT INTO `action` VALUES ('36', '切换到H5视图', '1', 'switch_to_H5', '', '35', '2022-07-01 11:46:03', '2022-07-01 11:46:03', '0');
INSERT INTO `action` VALUES ('37', '切换到原生视图', '1', 'switch_to_native', '', '35', '2022-07-01 11:46:16', '2022-07-01 11:46:16', '0');
INSERT INTO `action` VALUES ('38', '元素操作', '1', '', '', '0', '2022-07-01 15:26:50', '2022-07-01 15:26:50', '0');
INSERT INTO `action` VALUES ('39', '点击元素', '1', 'Click', 'element--click', '38', '2022-07-01 15:27:06', '2022-07-26 15:49:56', '0');
INSERT INTO `action` VALUES ('43', 'input-元素清除', '1', 'Clear', '清除input输入框内元素', '38', '2022-07-21 17:28:16', '2022-07-22 15:11:31', '0');
INSERT INTO `action` VALUES ('44', '元素输入', '1', 'Input', 'input输入框内进行文本输入', '38', '2022-07-22 15:10:58', '2022-07-22 15:11:22', '0');
INSERT INTO `action` VALUES ('45', '向上滑动', '1', 'swipe_Top', '向上滑动手机屏幕 ↑', '31', '2022-07-26 10:19:58', '2022-07-26 15:26:42', '0');
INSERT INTO `action` VALUES ('46', '向下滑动', '1', 'swipe_bottom', '向下滑动手机屏幕 ↓', '31', '2022-07-26 15:25:00', '2022-07-26 15:26:00', '0');
INSERT INTO `action` VALUES ('47', '向左滑动', '1', 'swipe_left', '向左滑动手机屏幕 ←', '31', '2022-07-26 15:26:09', '2022-07-26 15:26:56', '0');
INSERT INTO `action` VALUES ('48', '向右滑动', '1', 'swipe_left', '向右滑动手机屏幕 →', '31', '2022-07-26 15:26:29', '2022-07-26 15:27:03', '0');
INSERT INTO `action` VALUES ('49', '元素输入', '1', 'Input', 'element', '38', '2022-07-26 15:52:10', '2022-07-26 15:52:10', '0');
INSERT INTO `action` VALUES ('50', '特殊操作', '1', 'special', '', '0', '2022-07-26 15:52:50', '2022-07-26 15:52:50', '0');
INSERT INTO `action` VALUES ('51', '坐标定位+点击', '1', 'Tap_Click', '参数实例： x,y   ( xy为 比例系数  、 x 轴 y 轴 坐标用 逗号，分开 )【比例系数 = 当前坐标/ 坐标总长度】', '50', '2022-07-26 15:54:27', '2022-07-26 17:52:07', '0');
INSERT INTO `action` VALUES ('52', '执行JS命令', '1', 'ExecJs', '执行JS命令', '50', '2022-07-27 10:16:45', '2022-07-27 10:16:45', '0');
INSERT INTO `action` VALUES ('53', '轻易花常用操作', '1', 'qyhOperation', '轻易花项目 接口数据库 常用操作封装', '0', '2023-01-05 13:50:07', '2023-01-05 13:50:07', '0');
INSERT INTO `action` VALUES ('54', '轻易花APP填单', '1', 'qyhApply', '轻易花APP填单 value--填单城市  不填写默认：南阳市', '53', '2023-01-05 13:50:37', '2023-01-05 14:14:04', '0');

-- ----------------------------
-- Table structure for api_case
-- ----------------------------
DROP TABLE IF EXISTS `api_case`;
CREATE TABLE `api_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL COMMENT '分类ID',
  `case_grade` varchar(10) DEFAULT NULL COMMENT '用例等级',
  `case_person` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `case_type` varchar(255) DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `loop_count` int(255) DEFAULT NULL,
  `fail_continue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='接口自动化用例表';

-- ----------------------------
-- Records of api_case
-- ----------------------------
INSERT INTO `api_case` VALUES ('3', '11', 'P3', null, null, '已完成', '[]', '1', null);

-- ----------------------------
-- Table structure for api_info
-- ----------------------------
DROP TABLE IF EXISTS `api_info`;
CREATE TABLE `api_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '接口名称',
  `labels` varchar(10) DEFAULT NULL COMMENT '接口标签 ',
  `project_id` int(11) DEFAULT NULL COMMENT '平台项目ID',
  `api_suite_id` int(11) DEFAULT NULL COMMENT '接口分类ID',
  `path` varchar(100) DEFAULT NULL COMMENT '接口路由地址URI',
  `method` varchar(10) DEFAULT NULL COMMENT '接口请求方式 post get',
  `type` int(11) DEFAULT NULL COMMENT '类型 0公共接口  1业务流接口',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` varchar(255) DEFAULT NULL COMMENT '接口状态   未完成 已完成',
  `req_header` varchar(255) DEFAULT NULL COMMENT 'API 请求头',
  `req_params` varchar(255) DEFAULT NULL COMMENT 'URL请求参数',
  `req_body_data` varchar(255) DEFAULT NULL COMMENT '请求体 form-data',
  `req_body_json` varchar(255) DEFAULT NULL COMMENT '请求体 - json',
  `req_body_type` varchar(10) DEFAULT NULL COMMENT '请求体类型 0 form-data  1 json',
  `req_extract` varchar(255) DEFAULT NULL COMMENT '请求提取参数',
  `req_assert` varchar(255) DEFAULT NULL COMMENT '接口断言',
  `debug_rsp` varchar(255) DEFAULT NULL COMMENT '接口响应值',
  `create_by` varchar(10) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) DEFAULT NULL COMMENT '修改人',
  `virtual_res_body` varchar(255) DEFAULT NULL COMMENT '虚拟返回对象（key value）',
  `virtual_res_body_json` varchar(255) DEFAULT NULL COMMENT '虚拟返回对象（Json）',
  `virtual_body_type` varchar(10) DEFAULT NULL COMMENT '//虚拟返回对象类型',
  `req_cookies` varchar(255) DEFAULT NULL COMMENT '请求cookie',
  `domain_switch` int(11) DEFAULT NULL COMMENT '是否启用域名 0 启用 1 不启用',
  `set_up_ids` varchar(100) DEFAULT NULL COMMENT '前置动作 ID列表',
  `tear_down_ids` varchar(100) DEFAULT NULL COMMENT '后置操作列表',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='API自动化-接口详情表';

-- ----------------------------
-- Records of api_info
-- ----------------------------
INSERT INTO `api_info` VALUES ('7', 'login', null, '3', '8', '/api/customer/v1/user/code/login?phone=17637898368&code=1234', 'POST', null, null, '已上线', '[{\"HeadersKey\":\"Content-Type\",\"HeadersValue\":\"application/x-www-form-urlencoded\"}]', '[{}]', '[{\"dataKey\":\"\",\"dataValue\":\"\"}]', '', '1', '[{\"dataSource\":\"bodyJson\",\"extractExpress\":\"data.token\",\"realType\":\"\",\"realValue\":\"\",\"varName\":\"token\"}]', '[{\"assertResult\":null,\"dataSource\":\"bodyJson\",\"expectRelation\":\"等于\",\"expectValue\":\"0\",\"extractExpress\":\"code\",\"realValue\":\"\"}]', null, 'system', 'system', '[{}]', '', '', '[{}]', '1', '[]', '[]', '2022-12-16 18:01:32', '2022-12-19 11:31:43');
INSERT INTO `api_info` VALUES ('8', 'loginSuccess', null, '3', '10', '/api/customer/v1/user/code/login?phone=17637898368&code=1234', 'POST', '1', null, '已上线', '[{\"HeadersKey\":\"Content-Type\",\"HeadersValue\":\"application/x-www-form-urlencoded\"}]', '[{}]', '[{\"dataKey\":\"\",\"dataValue\":\"\"}]', '', '1', '[{\"dataSource\":\"bodyJson\",\"extractExpress\":\"data.token\",\"realType\":\"\",\"realValue\":\"\",\"varName\":\"token\"}]', '[{\"assertResult\":null,\"dataSource\":\"bodyJson\",\"expectRelation\":\"等于\",\"expectValue\":\"0\",\"extractExpress\":\"code\",\"realValue\":\"\"}]', null, 'system', 'system', '[{}]', '', '', '[{}]', '1', '[]', '[]', '2022-12-16 18:01:32', '2022-12-16 18:01:32');
INSERT INTO `api_info` VALUES ('10', null, null, '3', '13', '/api/customer/v1/order/myOrders', 'GET', null, null, '已上线', '[{\"HeadersKey\":\"token\",\"HeadersValue\":\"${token}\"}]', '[{}]', '[{}]', null, null, '[{\"dataSource\":\"\",\"extractExpress\":\"\",\"realType\":\"\",\"realValue\":\"\",\"varName\":\"\"}]', '[{\"assertResult\":null,\"dataSource\":\"\",\"expectRelation\":\"\",\"expectValue\":\"\",\"extractExpress\":\"\",\"realValue\":\"\"}]', null, null, null, null, null, null, '[{}]', '1', '[]', '[]', '2022-12-19 16:20:05', '2022-12-19 16:20:05');
INSERT INTO `api_info` VALUES ('11', '订单列表', null, '3', '14', '/api/customer/v1/order/myOrders', 'GET', '1', null, '已上线', '[{\"HeadersKey\":\"token\",\"HeadersValue\":\"${token}\"}]', '[{}]', '[{}]', null, null, '[{\"dataSource\":\"\",\"extractExpress\":\"\",\"realType\":\"\",\"realValue\":\"\",\"varName\":\"\"}]', '[{\"assertResult\":null,\"dataSource\":\"\",\"expectRelation\":\"\",\"expectValue\":\"\",\"extractExpress\":\"\",\"realValue\":\"\"}]', null, null, null, null, null, null, '[{}]', '1', '[]', '[]', '2022-12-19 16:20:05', '2022-12-19 16:20:05');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='APP 启动配置表';

-- ----------------------------
-- Records of app_config
-- ----------------------------
INSERT INTO `app_config` VALUES ('1', '好单多多（old）', 'com.wanqiandaikuan.xddd', 'ui.activity.SplashActivity', '1', 'uiautomator2', '[]', '0', '2022-06-23 15:15:40', '2022-07-06 14:57:45');
INSERT INTO `app_config` VALUES ('2', '好单多多-展业', 'com.daiwo.zydd', 'ui.activity.SplashActivity', '1', 'uiautomator2', '[]', '0', '2022-07-21 13:50:42', '2022-07-21 13:51:39');

-- ----------------------------
-- Table structure for auto_config
-- ----------------------------
DROP TABLE IF EXISTS `auto_config`;
CREATE TABLE `auto_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `value` varchar(255) DEFAULT NULL COMMENT '配置值',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(255) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='自动化配置表';

-- ----------------------------
-- Records of auto_config
-- ----------------------------
INSERT INTO `auto_config` VALUES ('1', 'selenium_location', 'id,xpath,tap', null, '0', '2022-06-14 09:52:15', '2022-07-26 10:50:34', '0');
INSERT INTO `auto_config` VALUES ('2', 'elementType', 'button,input,alert,radio', null, '0', '2022-06-14 09:55:51', '2022-07-25 15:55:48', '0');

-- ----------------------------
-- Table structure for case_api_relation
-- ----------------------------
DROP TABLE IF EXISTS `case_api_relation`;
CREATE TABLE `case_api_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `api_id` int(11) DEFAULT NULL COMMENT '接口ID',
  `case_id` int(11) DEFAULT NULL COMMENT '用例 ID',
  `status` int(10) DEFAULT NULL COMMENT '状态',
  `api_info` text COMMENT '用例详情',
  `number` int(11) DEFAULT NULL COMMENT '排序值',
  `type` int(11) DEFAULT NULL COMMENT '用例接口类型  0 引用   1 复制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='API自动化 - 用例 接口关联表';

-- ----------------------------
-- Records of case_api_relation
-- ----------------------------
INSERT INTO `case_api_relation` VALUES ('1', '8', '3', '1', '{\"afterExec\":[],\"apiSuiteId\":10,\"beforeExec\":[],\"createBy\":\"system\",\"createTime\":1671184892000,\"debugRsp\":\"\",\"domain\":\"\",\"domainSwitch\":true,\"id\":8,\"labels\":\"\",\"log\":[],\"method\":\"POST\",\"name\":\"loginSuccess\",\"path\":\"/api/customer/v1/user/code/login?phone=17637898368&code=1234\",\"projectId\":3,\"remark\":\"\",\"reqAssert\":[{\"assertResult\":null,\"dataSource\":\"bodyJson\",\"expectRelation\":\"等于\",\"expectValue\":\"0\",\"extractExpress\":\"code\",\"realValue\":\"\"}],\"reqBodyData\":[{\"dataKey\":\"\",\"dataValue\":\"\"}],\"reqBodyJson\":\"\",\"reqBodyType\":\"1\",\"reqCookies\":[{}],\"reqExtract\":[{\"dataSource\":\"bodyJson\",\"extractExpress\":\"data.token\",\"realType\":\"\",\"realValue\":\"\",\"varName\":\"token\"}],\"reqHeader\":[{\"HeadersKey\":\"Content-Type\",\"HeadersValue\":\"application/x-www-form-urlencoded\"}],\"reqParams\":[{}],\"result\":null,\"rspAsserts\":[],\"rspBodyJson\":null,\"rspBodySize\":0,\"rspExtract\":[],\"rspHeaders\":[],\"rspStatusCode\":\"\",\"rspTime\":0,\"setUpIds\":[],\"status\":\"已上线\",\"tearDownIds\":[],\"textBody\":\"\",\"type\":1,\"updateBy\":\"system\",\"updateTime\":1671184892000,\"virtualBodyType\":\"\",\"virtualResBody\":[{}],\"virtualResBodyJson\":\"\"}', '1', '0');
INSERT INTO `case_api_relation` VALUES ('2', '11', '3', '1', '{\"afterExec\":[],\"apiSuiteId\":14,\"beforeExec\":[],\"createBy\":\"\",\"createTime\":1671438005000,\"debugRsp\":\"\",\"domain\":\"\",\"domainSwitch\":true,\"id\":11,\"labels\":\"\",\"log\":[],\"method\":\"GET\",\"name\":\"订单列表\",\"path\":\"/api/customer/v1/order/myOrders\",\"projectId\":3,\"remark\":\"\",\"reqAssert\":[{\"assertResult\":null,\"dataSource\":\"\",\"expectRelation\":\"\",\"expectValue\":\"\",\"extractExpress\":\"\",\"realValue\":\"\"}],\"reqBodyData\":[{}],\"reqBodyJson\":\"\",\"reqBodyType\":\"\",\"reqCookies\":[{}],\"reqExtract\":[{\"dataSource\":\"\",\"extractExpress\":\"\",\"realType\":\"\",\"realValue\":\"\",\"varName\":\"\"}],\"reqHeader\":[{\"HeadersKey\":\"token\",\"HeadersValue\":\"${token}\"}],\"reqParams\":[{}],\"result\":null,\"rspAsserts\":[],\"rspBodyJson\":null,\"rspBodySize\":0,\"rspExtract\":[],\"rspHeaders\":[],\"rspStatusCode\":\"\",\"rspTime\":0,\"setUpIds\":[],\"status\":\"已上线\",\"tearDownIds\":[],\"textBody\":\"\",\"type\":1,\"updateBy\":\"\",\"updateTime\":1671438005000,\"virtualBodyType\":\"\",\"virtualResBody\":[],\"virtualResBodyJson\":\"\"}', '2', '0');

-- ----------------------------
-- Table structure for case_category
-- ----------------------------
DROP TABLE IF EXISTS `case_category`;
CREATE TABLE `case_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(10) DEFAULT NULL COMMENT '分类名称',
  `remark` varchar(10) DEFAULT NULL COMMENT '备注',
  `parent_id` int(11) DEFAULT NULL COMMENT '父分类',
  `type` varchar(2) DEFAULT NULL COMMENT '0 文件夹 1 用例',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='API自动化--用例分类表';

-- ----------------------------
-- Records of case_category
-- ----------------------------
INSERT INTO `case_category` VALUES ('1', 'demo', null, '0', '0', '2022-12-19 14:23:23');
INSERT INTO `case_category` VALUES ('8', 'sonDemo', null, '1', '0', '2022-12-19 14:44:36');
INSERT INTO `case_category` VALUES ('11', 'apiCase1', null, '8', '1', '2022-12-19 14:48:11');

-- ----------------------------
-- Table structure for category_api
-- ----------------------------
DROP TABLE IF EXISTS `category_api`;
CREATE TABLE `category_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '分类名称',
  `method` varchar(10) DEFAULT NULL COMMENT 'API请求方式',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父 分类',
  `remark` varchar(200) DEFAULT NULL,
  `source_type` int(2) DEFAULT NULL COMMENT ' 0： 文件夹 1 ：接口',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='接口自动化--分类接口关联表';

-- ----------------------------
-- Records of category_api
-- ----------------------------
INSERT INTO `category_api` VALUES ('4', 'QYH', null, '0', null, '0', null, null, null, null);
INSERT INTO `category_api` VALUES ('5', '轻易花APP', null, '4', null, '0', null, null, null, null);
INSERT INTO `category_api` VALUES ('8', 'login', 'POST', '5', null, '1', null, null, null, null);
INSERT INTO `category_api` VALUES ('10', 'loginSuccess', 'POST', '8', null, '2', null, null, null, null);
INSERT INTO `category_api` VALUES ('11', '用户中心', null, '5', null, '0', null, null, null, null);
INSERT INTO `category_api` VALUES ('13', '订单列表', 'GET', '11', null, '1', null, null, null, null);
INSERT INTO `category_api` VALUES ('14', '订单列表', null, '13', null, '2', null, null, null, null);

-- ----------------------------
-- Table structure for db_config
-- ----------------------------
DROP TABLE IF EXISTS `db_config`;
CREATE TABLE `db_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `db_name` varchar(100) DEFAULT NULL COMMENT '数据库名称',
  `jdbc_url` varchar(200) DEFAULT NULL COMMENT 'JDBC地址',
  `account` varchar(50) DEFAULT NULL COMMENT '数据库账号',
  `pwd` varchar(50) DEFAULT NULL COMMENT '数据库密码',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `type` int(50) DEFAULT NULL COMMENT '数据库类型  0 mysql  1 redis',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='自动化数据库配置表';

-- ----------------------------
-- Records of db_config
-- ----------------------------
INSERT INTO `db_config` VALUES ('1', 'qyh测试环境数据库', 'jdbc:mysql://118.31.184.240:3306', 'root', '3wHNY2Bq', null, '0', '2022-07-12 15:47:47', '2022-07-12 15:48:07', '0');

-- ----------------------------
-- Table structure for ding_robot
-- ----------------------------
DROP TABLE IF EXISTS `ding_robot`;
CREATE TABLE `ding_robot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '机器人名称',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '机器人状态  0 启用  1 终止',
  `at_phone` varchar(50) DEFAULT NULL COMMENT '需要@的用户列表 多个用，隔开',
  `robot_type` int(2) DEFAULT NULL COMMENT '执行时机 0 测试计划执行前  1 测试计划执行后',
  `robot_address` varchar(200) NOT NULL COMMENT '钉钉机器人地址',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int(2) NOT NULL DEFAULT '0' COMMENT '0 未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='钉钉机器人提醒配置表';

-- ----------------------------
-- Records of ding_robot
-- ----------------------------
INSERT INTO `ding_robot` VALUES ('3', '测试计划执行 前', '0', '1', '0', 'https://oapi.dingtalk.com/robot/send?access_token=d015cf4a79a2eaa6a3cfa367efba3a0f5bd5fb8a2d8bad1ff93ebbef15cf324d', '2022-08-18 14:24:18', '2022-08-19 10:12:00', '0');
INSERT INTO `ding_robot` VALUES ('4', '测试计划执行后', '0', null, '1', 'https://oapi.dingtalk.com/robot/send?access_token=d015cf4a79a2eaa6a3cfa367efba3a0f5bd5fb8a2d8bad1ff93ebbef15cf324d', '2022-08-19 10:11:42', '2022-08-19 10:11:42', '0');

-- ----------------------------
-- Table structure for env_config
-- ----------------------------
DROP TABLE IF EXISTS `env_config`;
CREATE TABLE `env_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `env_name` varchar(255) DEFAULT NULL COMMENT '环境名称',
  `db_id` varchar(255) DEFAULT NULL COMMENT '数据库ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='环境配置表';

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
  `name` varchar(255) DEFAULT NULL COMMENT '路由名称',
  `url` varchar(255) DEFAULT NULL COMMENT '路由地址',
  `parent_id` int(11) DEFAULT NULL,
  `number` int(100) DEFAULT NULL COMMENT '排序值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COMMENT='平台路由配置表';

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
INSERT INTO `menu` VALUES ('35', '钉钉机器人', '/ding/robot', '4', '45');
INSERT INTO `menu` VALUES ('36', '验证码管理', '/tools/code', '5', '52');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='APP-执行机';

-- ----------------------------
-- Records of mobile_phone
-- ----------------------------
INSERT INTO `mobile_phone` VALUES ('1', 'OPPO', 'ANDROID', '8.1.0', '192.168.2.45', '71', '1', '0', '2022-06-23 14:49:56', '2022-12-15 14:35:10');
INSERT INTO `mobile_phone` VALUES ('2', 'XIAOMI', 'ANDROID', '9.0.0', '192.168.2.50', '29', '1', '0', '2022-07-21 11:53:17', '2022-12-15 14:35:08');
INSERT INTO `mobile_phone` VALUES ('3', 'OPPO Reno4', 'ANDROID', '12', '192.168.2.183', '100', '1', '0', '2022-12-14 14:06:11', '2022-12-14 14:08:20');

-- ----------------------------
-- Table structure for page_element
-- ----------------------------
DROP TABLE IF EXISTS `page_element`;
CREATE TABLE `page_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `element_name` varchar(50) DEFAULT NULL COMMENT '元素名称',
  `element_type` varchar(10) DEFAULT NULL COMMENT '元素类型',
  `location_way` varchar(50) DEFAULT NULL COMMENT '元素定位方式 xpath id .....',
  `location_value` varchar(100) DEFAULT NULL COMMENT '定位方式对应value',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `page_id` int(11) DEFAULT NULL COMMENT '页面ID',
  `conditions` varchar(255) DEFAULT NULL COMMENT '显示等待条件',
  `type` int(10) DEFAULT '1' COMMENT '自动化类别 ： 1 WEB  2 APP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='UI自动化-页面元素表';

-- ----------------------------
-- Records of page_element
-- ----------------------------
INSERT INTO `page_element` VALUES ('1', '账号输入框', 'input', 'xpath', '//*[@id=\"phone\"]', '', '2022-06-16 14:56:16', '2022-07-07 14:40:02', '2', 'visibilityOfElementLocated', '1');
INSERT INTO `page_element` VALUES ('2', '验证码输入框', 'input', 'xpath', '//*[@id=\"code\"]', '', '2022-06-16 14:56:19', '2022-07-07 14:40:03', '2', 'visibilityOfElementLocated', '1');
INSERT INTO `page_element` VALUES ('4', '温馨提示--同意', 'button', 'xpath', '//*[@resource-id=\"com.wanqiandaikuan.xddd:id/tvConfirm\"]', '', '2022-07-07 14:41:51', '2022-07-13 17:45:42', '8', 'elementToBeClickable', '2');
INSERT INTO `page_element` VALUES ('5', '切换密码登录', 'button', 'xpath', '//*[@resource-id=\"com.wanqiandaikuan.xddd:id/tv_pass_login\"]', '', '2022-07-07 14:47:47', '2022-07-13 17:45:42', '9', '', '2');
INSERT INTO `page_element` VALUES ('6', '手机号输入框', 'input', 'id', 'com.wanqiandaikuan.xddd:id/cetInput', '', '2022-07-07 14:56:30', '2022-07-13 17:45:43', '9', '', '2');
INSERT INTO `page_element` VALUES ('7', '密码输入框', 'input', 'id', 'com.wanqiandaikuan.xddd:id/etPassword', '', '2022-07-07 14:57:27', '2022-07-13 17:45:44', '9', '', '2');
INSERT INTO `page_element` VALUES ('8', '协议按钮', 'input', 'id', 'com.wanqiandaikuan.xddd:id/iv_select_yd', '', '2022-07-07 14:58:31', '2022-07-13 17:45:44', '9', '', '2');
INSERT INTO `page_element` VALUES ('9', '登录按钮', 'button', 'id', 'com.wanqiandaikuan.xddd:id/btnLogin', '', '2022-07-07 14:58:56', '2022-07-13 17:45:46', '9', '', '2');
INSERT INTO `page_element` VALUES ('10', '温馨提示--同意', 'button', 'id', 'com.daiwo.zydd:id/tvConfirm', '', '2022-07-21 13:59:17', '2022-07-21 13:59:17', '14', '', '1');
INSERT INTO `page_element` VALUES ('11', '切换密码登录', 'button', 'id', 'com.daiwo.zydd:id/tv_pass_login', '', '2022-07-21 14:00:14', '2022-07-21 14:00:14', '15', 'elementToBeClickable', '1');
INSERT INTO `page_element` VALUES ('12', '手机号', 'input', 'id', 'com.daiwo.zydd:id/cetInput', '', '2022-07-21 14:03:09', '2022-07-21 14:03:09', '15', '', '1');
INSERT INTO `page_element` VALUES ('13', '密码', 'input', 'id', 'com.daiwo.zydd:id/etPassword', '', '2022-07-21 14:03:09', '2022-07-21 14:03:09', '15', '', '1');
INSERT INTO `page_element` VALUES ('14', '协议按钮', 'input', 'id', 'com.daiwo.zydd:id/iv_select_yd', '', '2022-07-21 14:03:58', '2022-07-21 14:03:58', '15', '', '1');
INSERT INTO `page_element` VALUES ('15', '登录按钮', 'button', 'id', 'com.daiwo.zydd:id/btnLogin', '', '2022-07-21 14:04:56', '2022-07-21 14:04:56', '15', '', '1');
INSERT INTO `page_element` VALUES ('16', '平台公约协议', 'radio', 'id', 'com.daiwo.zydd:id/iv_select_confirm', '', '2022-07-25 15:52:31', '2022-07-25 15:52:31', '14', '', '1');
INSERT INTO `page_element` VALUES ('17', '平台公约--按钮', 'button', 'id', 'com.daiwo.zydd:id/btn_confirm', '', '2022-07-25 15:58:06', '2022-07-25 15:58:06', '14', '', '1');

-- ----------------------------
-- Table structure for plan_param
-- ----------------------------
DROP TABLE IF EXISTS `plan_param`;
CREATE TABLE `plan_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_id` int(11) DEFAULT NULL COMMENT '计划ID',
  `api_ids` varchar(255) DEFAULT NULL COMMENT '接口ID （接口自动化专属）',
  `case_ids` varchar(255) DEFAULT NULL COMMENT '用例ID ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='测试计划 -- 用例 关联表';

-- ----------------------------
-- Records of plan_param
-- ----------------------------
INSERT INTO `plan_param` VALUES ('1', '1', null, '[2]');
INSERT INTO `plan_param` VALUES ('2', '2', null, '[2]');
INSERT INTO `plan_param` VALUES ('3', '3', null, '[2]');
INSERT INTO `plan_param` VALUES ('4', '4', '[7]', '[]');
INSERT INTO `plan_param` VALUES ('5', '6', null, '[4]');
INSERT INTO `plan_param` VALUES ('7', '8', null, '[8]');

-- ----------------------------
-- Table structure for plan_result
-- ----------------------------
DROP TABLE IF EXISTS `plan_result`;
CREATE TABLE `plan_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_id` int(11) DEFAULT NULL COMMENT '计划ID',
  `plan_name` varchar(10) DEFAULT NULL COMMENT '计划名称',
  `plan_type` int(11) DEFAULT NULL COMMENT '计划类型  0 API  1WEB  2 APP',
  `result_status` int(10) DEFAULT NULL COMMENT '计划执行结果  0 执行中 1 成功 2 失败',
  `remark` varchar(10) DEFAULT NULL COMMENT '备注',
  `result` varchar(10) DEFAULT NULL,
  `api_success_count` int(10) DEFAULT NULL COMMENT '接口执行成功数',
  `api_failed_count` int(10) DEFAULT NULL COMMENT '接口执行失败数（API自动化专属）',
  `case_success_count` int(11) DEFAULT NULL COMMENT '用例执行成功数',
  `case_failed_count` int(11) DEFAULT NULL COMMENT '用例执行失败数',
  `result_address` varchar(50) DEFAULT NULL COMMENT '测试报告地址',
  `start_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '用例开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '用例结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COMMENT='测试计划执行结果表';

-- ----------------------------
-- Records of plan_result
-- ----------------------------
INSERT INTO `plan_result` VALUES ('40', '6', 'appPlan2', '2', '2', null, null, null, '0', '0', '1', null, '2022-07-13 16:45:21', '2022-07-13 16:45:37');
INSERT INTO `plan_result` VALUES ('41', '3', 'WebPlan1', '1', '1', null, null, null, null, '1', '0', null, '2022-07-19 10:34:05', '2022-07-19 10:34:08');
INSERT INTO `plan_result` VALUES ('42', '3', 'WebPlan1', '1', '1', null, null, null, null, '1', '0', null, '2022-07-19 10:42:11', '2022-07-19 10:42:19');
INSERT INTO `plan_result` VALUES ('43', '6', 'appPlan2', '2', '1', null, null, null, '0', '1', '0', null, '2022-07-19 11:44:37', '2022-07-19 11:44:57');
INSERT INTO `plan_result` VALUES ('45', '8', '好单多多-展业', '2', '2', null, null, null, '0', '0', '1', null, '2022-07-21 14:23:04', '2022-07-21 14:23:13');
INSERT INTO `plan_result` VALUES ('46', '8', '好单多多-展业', '2', '1', null, null, null, '0', '1', '0', null, '2022-07-25 15:26:39', '2022-07-25 15:26:55');
INSERT INTO `plan_result` VALUES ('47', '8', '好单多多-展业', '2', '1', null, null, null, '0', '1', '0', null, '2022-07-25 15:30:11', '2022-07-25 15:30:28');
INSERT INTO `plan_result` VALUES ('48', '3', 'WebPlan1', '1', '1', null, null, null, null, '1', '0', null, '2022-08-19 10:12:15', '2022-08-19 10:13:43');
INSERT INTO `plan_result` VALUES ('49', '3', 'WebPlan1', '1', '1', null, null, null, null, '1', '0', null, '2022-08-19 10:15:10', '2022-08-19 10:15:18');
INSERT INTO `plan_result` VALUES ('50', '3', 'WebPlan1', '1', '1', null, null, null, null, '1', '0', null, '2022-08-19 10:16:21', '2022-08-19 10:16:29');
INSERT INTO `plan_result` VALUES ('51', '3', 'WebPlan1', '1', '1', null, null, null, null, '1', '0', 'http://localhost:1996/#/web/report?id=51', '2022-08-19 11:52:37', '2022-08-19 11:53:09');
INSERT INTO `plan_result` VALUES ('52', '3', 'WebPlan1', '1', '1', null, null, null, null, '1', '0', 'http://localhost:1996/#/web/report?id=52', '2022-08-19 13:38:19', '2022-08-19 13:38:28');
INSERT INTO `plan_result` VALUES ('53', '3', 'WebPlan1', '1', '1', null, null, null, null, '1', '0', 'http://localhost:1996/#/web/report?id=53', '2022-08-19 13:51:49', '2022-08-19 13:51:57');
INSERT INTO `plan_result` VALUES ('54', '3', 'WebPlan1', '1', '1', null, null, null, null, '1', '0', 'http://192.168.2.124:1996/#/web/report?id=54', '2022-08-19 13:53:27', '2022-08-19 13:53:35');

-- ----------------------------
-- Table structure for plan_result_detail
-- ----------------------------
DROP TABLE IF EXISTS `plan_result_detail`;
CREATE TABLE `plan_result_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_result_id` int(11) DEFAULT NULL COMMENT '计划执行结果ID',
  `case_id` int(11) DEFAULT NULL COMMENT '计划对应用例ID',
  `case_number` int(11) DEFAULT NULL COMMENT '用例执行排序值',
  `api_id` bigint(20) DEFAULT NULL COMMENT '计划 apiID  （API自动化专属）',
  `api_info` text COMMENT 'API 详情 （API 自动化专属）',
  `result` int(255) DEFAULT NULL COMMENT '执行结果  0 成功  1 失败',
  `result_console` text COMMENT '用例执行结果详情',
  `case_name` varchar(255) DEFAULT NULL COMMENT '用例名称',
  `assert_result` text COMMENT '用例断言结果',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COMMENT='测试计划执行结果详情表';

-- ----------------------------
-- Records of plan_result_detail
-- ----------------------------
INSERT INTO `plan_result_detail` VALUES ('15', '40', '4', null, null, null, '0', '[{\"code\":0,\"msg\":\"步骤ID9执行成功--点击元素4\"},{\"code\":0,\"msg\":\"步骤ID10执行成功--点击元素5\"},{\"code\":0,\"msg\":\"步骤ID15执行成功--输入17637898368\"},{\"code\":0,\"msg\":\"步骤ID16执行成功--输入qwer12345\"},{\"code\":0,\"msg\":\"步骤ID13执行成功--点击元素8\"},{\"code\":0,\"msg\":\"步骤ID14执行成功--点击元素9\"},{\"code\":1,\"msg\":\"步骤ID18 捕捉toast失败\"},{\"code\":0,\"msg\":\"步骤ID17,执行成功----driver沉睡3000 ms\"}]', null, '[{\"assertType\":\"TOASTASSERT\",\"expectValue\":\"请输入正确的手机号或密码\",\"msg\":\"断言成功\",\"realityValue\":\"请输入正确的手机号或密码\",\"result\":true,\"stepId\":18}]', '2022-07-13 16:45:38');
INSERT INTO `plan_result_detail` VALUES ('16', '41', '2', null, null, null, '1', '[{\"code\":0,\"msg\":\"步骤ID1,执行成功---打开网址: http://testcrm.qyhnet.com/user/login\"}]', null, '[]', '2022-07-19 10:34:09');
INSERT INTO `plan_result_detail` VALUES ('17', '42', '2', null, null, null, '1', '[{\"code\":0,\"msg\":\"步骤ID1,执行成功---打开网址: http://testcrm.qyhnet.com/user/login\"},{\"code\":0,\"msg\":\"步骤ID2,执行成功--输入元素为:dujun\"},{\"code\":0,\"msg\":\"步骤ID3,执行成功----driver沉睡3000 ms\"}]', null, '[{\"assertType\":\"titleIs\",\"expectValue\":\"登录 - CRM管理系统\",\"msg\":\"\",\"realityValue\":\"登录 - CRM管理系统\",\"result\":true,\"stepId\":1}]', '2022-07-19 10:42:19');
INSERT INTO `plan_result_detail` VALUES ('18', '43', '4', null, null, null, '1', '[{\"code\":0,\"msg\":\"步骤ID9执行成功--点击元素4\"},{\"code\":0,\"msg\":\"步骤ID10执行成功--点击元素5\"},{\"code\":0,\"msg\":\"步骤ID15执行成功--输入17637898368\"},{\"code\":0,\"msg\":\"步骤ID16执行成功--输入qwer12345\"},{\"code\":0,\"msg\":\"步骤ID13执行成功--点击元素8\"},{\"code\":0,\"msg\":\"步骤ID14执行成功--点击元素9\"},{\"code\":0,\"msg\":\"步骤ID18 捕捉toast成功 toast: 请输入正确的手机号或密码\"},{\"code\":0,\"msg\":\"步骤ID17,执行成功----driver沉睡3000 ms\"}]', null, '[{\"assertType\":\"TOASTASSERT\",\"expectValue\":\"请输入正确的手机号或密码\",\"msg\":\"断言成功\",\"realityValue\":\"请输入正确的手机号或密码\",\"result\":true,\"stepId\":18}]', '2022-07-19 11:44:58');
INSERT INTO `plan_result_detail` VALUES ('20', '45', '8', null, null, null, '0', '[{\"code\":0,\"msg\":\"步骤ID19执行成功--点击元素10\"},{\"code\":0,\"msg\":\"步骤ID20,执行成功----driver沉睡2000 ms\"},{\"code\":0,\"msg\":\"步骤ID21执行成功--点击元素11\"},{\"code\":0,\"msg\":\"步骤ID22执行成功--输入17637898368\"},{\"code\":0,\"msg\":\"步骤ID23执行成功--输入test234\"},{\"code\":0,\"msg\":\"步骤ID24执行成功--点击元素14\"},{\"code\":0,\"msg\":\"步骤ID25执行成功--点击元素15\"}]', null, '[{\"assertType\":\"TOASTASSERT\",\"expectValue\":\"请输入正确的手机号或密码\",\"msg\":\"断言失败\",\"realityValue\":\"\",\"result\":false,\"stepId\":25}]', '2022-07-21 14:23:14');
INSERT INTO `plan_result_detail` VALUES ('21', '46', '8', null, null, null, '1', '[{\"code\":0,\"msg\":\"步骤ID19执行成功--点击元素10\"},{\"code\":0,\"msg\":\"步骤ID20,执行成功----driver沉睡2000 ms\"},{\"code\":0,\"msg\":\"步骤ID21执行成功--点击元素11\"},{\"code\":0,\"msg\":\"步骤ID22执行成功--输入17637898368\"},{\"code\":0,\"msg\":\"步骤ID23执行成功--输入test234\"},{\"code\":0,\"msg\":\"步骤ID24执行成功--点击元素14\"},{\"code\":0,\"msg\":\"步骤ID25执行成功--点击元素15\"},{\"code\":0,\"msg\":\"步骤ID28执行成功--input文本清除13\"},{\"code\":0,\"msg\":\"步骤ID29执行成功--输入qwer1234\"},{\"code\":0,\"msg\":\"步骤ID30执行成功--点击元素15\"},{\"code\":0,\"msg\":\"步骤ID31,执行成功----driver沉睡3000 ms\"}]', null, '[{\"assertType\":\"TOASTASSERT\",\"expectValue\":\"请输入正确的手机号或密码\",\"msg\":\"断言成功\",\"realityValue\":\"好单多多-展业：请输入正确的手机号或密码\",\"result\":true,\"stepId\":25}]', '2022-07-25 15:26:55');
INSERT INTO `plan_result_detail` VALUES ('22', '47', '8', null, null, null, '1', '[{\"code\":0,\"msg\":\"步骤ID19执行成功--点击元素10\"},{\"code\":0,\"msg\":\"步骤ID20,执行成功----driver沉睡2000 ms\"},{\"code\":0,\"msg\":\"步骤ID21执行成功--点击元素11\"},{\"code\":0,\"msg\":\"步骤ID22执行成功--输入17637898368\"},{\"code\":0,\"msg\":\"步骤ID23执行成功--输入test234\"},{\"code\":0,\"msg\":\"步骤ID24执行成功--点击元素14\"},{\"code\":0,\"msg\":\"步骤ID25执行成功--点击元素15\"},{\"code\":0,\"msg\":\"步骤ID28执行成功--input文本清除13\"},{\"code\":0,\"msg\":\"步骤ID29执行成功--输入qwer1234\"},{\"code\":0,\"msg\":\"步骤ID30执行成功--点击元素15\"},{\"code\":0,\"msg\":\"步骤ID31,执行成功----driver沉睡3000 ms\"}]', null, '[{\"assertType\":\"TOASTASSERT\",\"expectValue\":\"请输入正确的手机号或密码\",\"msg\":\"断言成功\",\"realityValue\":\"好单多多-展业：请输入正确的手机号或密码\",\"result\":true,\"stepId\":25}]', '2022-07-25 15:30:28');
INSERT INTO `plan_result_detail` VALUES ('23', '48', '2', null, null, null, '1', '[{\"code\":0,\"msg\":\"步骤ID1,执行成功---打开网址: http://testcrm.qyhnet.com/user/login\"},{\"code\":0,\"msg\":\"步骤ID2,执行成功--输入元素为:dujun\"},{\"code\":0,\"msg\":\"步骤ID3,执行成功----driver沉睡3000 ms\"}]', null, '[{\"assertType\":\"titleIs\",\"expectValue\":\"登录 - CRM管理系统\",\"msg\":\"\",\"realityValue\":\"登录 - CRM管理系统\",\"result\":true,\"stepId\":1}]', '2022-08-19 10:13:42');
INSERT INTO `plan_result_detail` VALUES ('24', '49', '2', null, null, null, '1', '[{\"code\":0,\"msg\":\"步骤ID1,执行成功---打开网址: http://testcrm.qyhnet.com/user/login\"},{\"code\":0,\"msg\":\"步骤ID2,执行成功--输入元素为:dujun\"},{\"code\":0,\"msg\":\"步骤ID3,执行成功----driver沉睡3000 ms\"}]', null, '[{\"assertType\":\"titleIs\",\"expectValue\":\"登录 - CRM管理系统\",\"msg\":\"\",\"realityValue\":\"登录 - CRM管理系统\",\"result\":true,\"stepId\":1}]', '2022-08-19 10:15:17');
INSERT INTO `plan_result_detail` VALUES ('25', '50', '2', null, null, null, '1', '[{\"code\":0,\"msg\":\"步骤ID1,执行成功---打开网址: http://testcrm.qyhnet.com/user/login\"},{\"code\":0,\"msg\":\"步骤ID2,执行成功--输入元素为:dujun\"},{\"code\":0,\"msg\":\"步骤ID3,执行成功----driver沉睡3000 ms\"}]', null, '[{\"assertType\":\"titleIs\",\"expectValue\":\"登录 - CRM管理系统\",\"msg\":\"\",\"realityValue\":\"登录 - CRM管理系统\",\"result\":true,\"stepId\":1}]', '2022-08-19 10:16:29');
INSERT INTO `plan_result_detail` VALUES ('26', '51', '2', null, null, null, '1', '[{\"code\":0,\"msg\":\"步骤ID1,执行成功---打开网址: http://testcrm.qyhnet.com/user/login\"},{\"code\":0,\"msg\":\"步骤ID2,执行成功--输入元素为:dujun\"},{\"code\":0,\"msg\":\"步骤ID3,执行成功----driver沉睡3000 ms\"}]', null, '[{\"assertType\":\"titleIs\",\"expectValue\":\"登录 - CRM管理系统\",\"msg\":\"\",\"realityValue\":\"登录 - CRM管理系统\",\"result\":true,\"stepId\":1}]', '2022-08-19 11:53:08');
INSERT INTO `plan_result_detail` VALUES ('27', '52', '2', null, null, null, '1', '[{\"code\":0,\"msg\":\"步骤ID1,执行成功---打开网址: http://testcrm.qyhnet.com/user/login\"},{\"code\":0,\"msg\":\"步骤ID2,执行成功--输入元素为:dujun\"},{\"code\":0,\"msg\":\"步骤ID3,执行成功----driver沉睡3000 ms\"}]', null, '[{\"assertType\":\"titleIs\",\"expectValue\":\"登录 - CRM管理系统\",\"msg\":\"\",\"realityValue\":\"登录 - CRM管理系统\",\"result\":true,\"stepId\":1}]', '2022-08-19 13:38:28');
INSERT INTO `plan_result_detail` VALUES ('28', '53', '2', null, null, null, '1', '[{\"code\":0,\"msg\":\"步骤ID1,执行成功---打开网址: http://testcrm.qyhnet.com/user/login\"},{\"code\":0,\"msg\":\"步骤ID2,执行成功--输入元素为:dujun\"},{\"code\":0,\"msg\":\"步骤ID3,执行成功----driver沉睡3000 ms\"}]', null, '[{\"assertType\":\"titleIs\",\"expectValue\":\"登录 - CRM管理系统\",\"msg\":\"\",\"realityValue\":\"登录 - CRM管理系统\",\"result\":true,\"stepId\":1}]', '2022-08-19 13:51:57');
INSERT INTO `plan_result_detail` VALUES ('29', '54', '2', null, null, null, '1', '[{\"code\":0,\"msg\":\"步骤ID1,执行成功---打开网址: http://testcrm.qyhnet.com/user/login\"},{\"code\":0,\"msg\":\"步骤ID2,执行成功--输入元素为:dujun\"},{\"code\":0,\"msg\":\"步骤ID3,执行成功----driver沉睡3000 ms\"}]', null, '[{\"assertType\":\"titleIs\",\"expectValue\":\"登录 - CRM管理系统\",\"msg\":\"\",\"realityValue\":\"登录 - CRM管理系统\",\"result\":true,\"stepId\":1}]', '2022-08-19 13:53:35');

-- ----------------------------
-- Table structure for plan_round
-- ----------------------------
DROP TABLE IF EXISTS `plan_round`;
CREATE TABLE `plan_round` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL COMMENT '操作名称',
  `action_id` int(11) DEFAULT NULL COMMENT 'Action行动ID',
  `action_key` varchar(10) DEFAULT NULL COMMENT 'Action行动key',
  `action_value` varchar(10) DEFAULT NULL COMMENT '行动值',
  `type` int(11) DEFAULT NULL COMMENT '0 前置  1 后置',
  `plan_id` int(11) DEFAULT NULL COMMENT '计划ID',
  `status` int(11) DEFAULT '1' COMMENT '状态 0 不执行  1 执行',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `operate_data` varchar(100) DEFAULT NULL,
  `params` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='计划前置后置操作表';

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
  `prt_name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `remark` varchar(20) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(10) NOT NULL DEFAULT '0' COMMENT '0  未删除  1 已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='平台项目表';

-- ----------------------------
-- Records of plat_project
-- ----------------------------
INSERT INTO `plat_project` VALUES ('1', 'CRM', '', '2022-06-13 10:43:09', '2022-12-16 17:40:59', '0');
INSERT INTO `plat_project` VALUES ('2', 'DRK', '', '2022-06-13 10:43:11', '2022-12-16 17:41:06', '0');
INSERT INTO `plat_project` VALUES ('3', 'QYH', '', '2022-12-16 17:40:44', '2022-12-16 17:41:18', '0');

-- ----------------------------
-- Table structure for prt_domain
-- ----------------------------
DROP TABLE IF EXISTS `prt_domain`;
CREATE TABLE `prt_domain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prt_id` int(11) DEFAULT NULL COMMENT '项目ID',
  `env_id` int(11) DEFAULT NULL COMMENT '环境ID',
  `domain` varchar(50) DEFAULT NULL COMMENT '域名信息',
  `remark` varchar(20) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` varchar(10) DEFAULT '0' COMMENT '0  启用  1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='项目域名表';

-- ----------------------------
-- Records of prt_domain
-- ----------------------------
INSERT INTO `prt_domain` VALUES ('1', '1', '1', 'http://testcrm.qyhnet.com/', null, '2022-06-13 10:46:41', '2022-06-13 10:47:15', '0');
INSERT INTO `prt_domain` VALUES ('3', '1', '2', 'https://crm.qyhnet', null, '2022-06-13 10:46:46', '2022-06-13 10:47:16', '0');
INSERT INTO `prt_domain` VALUES ('4', '3', '1', 'http://testmapi.qyihua.com', null, '2022-12-16 17:41:25', '2022-12-16 17:41:25', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(10) DEFAULT NULL COMMENT '角色名称',
  `permission_url` text COMMENT '角色路由',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` varchar(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='平台角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('2', '管理员', '[\"/interface\",\"/interface/list\",\"/case/list\",\"/case/plan\",\"/case/result\",\"/selenium\",\"/selenium/page\",\"/selenium/case\",\"/selenium/plan\",\"/selenium/report\",\"/Appium\",\"/appium/page\",\"/appium/case\",\"/appium/plan\",\"/appium/report\",\"/common\",\"/common/action\",\"/common/projects\",\"/common/appConfig\",\"/common/mobilePhone\",\"/ding/robot\",\"/tools\",\"/tools/crm\",\"/tools/code\",\"/user\",\"/user/userList\",\"/user/role\",\"/Tester\",\"/test\"]', '2022-06-15 16:01:50', '2022-06-15 16:01:57', '0');
INSERT INTO `role` VALUES ('3', 'APP测试', '[\"/Appium\",\"/appium/page\",\"/appium/case\",\"/appium/plan\",\"/appium/report\"]', '2022-07-13 16:49:35', '2022-07-13 16:49:35', '0');

-- ----------------------------
-- Table structure for run_plan
-- ----------------------------
DROP TABLE IF EXISTS `run_plan`;
CREATE TABLE `run_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL COMMENT '计划名称',
  `remark` varchar(10) DEFAULT NULL COMMENT '备注',
  `env_id` int(11) DEFAULT NULL COMMENT '环境ID',
  `try_count` int(2) DEFAULT NULL COMMENT '计划失败重试次数',
  `status` varchar(10) DEFAULT '0' COMMENT '定时任务状态  0 启用 1 不启用',
  `is_clock` int(11) DEFAULT NULL COMMENT '是否开启定时 0 不开启  1 开启',
  `clock` varchar(20) DEFAULT NULL COMMENT 'cron 表达式',
  `is_send_email` int(11) DEFAULT NULL COMMENT '是否发送邮件',
  `send_email` varchar(100) DEFAULT NULL COMMENT '邮件地址 （多个邮件用 ;  隔开）',
  `plan_type` int(11) DEFAULT NULL COMMENT '计划类型 0 API  1 WEb 2 APP',
  `clock_exec_count` int(10) DEFAULT NULL COMMENT '定时任务执行次数',
  `browser_type` varchar(10) DEFAULT NULL COMMENT '浏览器类型  （WEB） ',
  `app_id` int(10) DEFAULT NULL COMMENT '运行APPID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='平台测试计划表';

-- ----------------------------
-- Records of run_plan
-- ----------------------------
INSERT INTO `run_plan` VALUES ('3', 'WebPlan1', null, '1', '1', '0', '1', '0 0 0 1/1 * ?', '0', '', '1', null, 'Chrome', null, '2022-06-14 10:52:29', '2022-06-14 10:53:08', '0');
INSERT INTO `run_plan` VALUES ('4', 'ApiPlan', null, '1', '1', '0', '0', '', '0', '', '0', null, null, null, '2022-06-14 17:32:36', '2022-06-14 17:32:36', '0');
INSERT INTO `run_plan` VALUES ('6', '好单多多--old', null, '2', '1', '0', '0', '0 0 0 1/1 * ?', '0', '', '2', null, '', '1', '2022-07-06 10:06:50', '2022-07-21 14:12:39', '0');
INSERT INTO `run_plan` VALUES ('8', '好单多多-展业', null, '1', '1', '0', '0', '0 0 0 1/1 * ?', '1', 'dujun8368@dingtalk.com', '2', null, '', '2', '2022-07-21 14:13:11', '2022-07-25 15:26:05', '0');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1');

-- ----------------------------
-- Table structure for ui_web_case
-- ----------------------------
DROP TABLE IF EXISTS `ui_web_case`;
CREATE TABLE `ui_web_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL COMMENT '用例名称',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `type` int(11) DEFAULT NULL COMMENT '0  文件夹  1 用例',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `case_type` int(10) DEFAULT '1' COMMENT '1 WEB 2 App',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='UI自动化用例表';

-- ----------------------------
-- Records of ui_web_case
-- ----------------------------
INSERT INTO `ui_web_case` VALUES ('1', 'CRM 登录', '0', '0', '', '2022-06-14 10:06:25', '2022-06-14 10:06:28', '0', '1');
INSERT INTO `ui_web_case` VALUES ('2', 'case1', '1', '1', '', '2022-06-14 10:07:22', '2022-06-14 10:07:22', '0', '1');
INSERT INTO `ui_web_case` VALUES ('3', '老好单多多', '0', '0', '', '2022-06-28 16:59:12', '2022-07-21 13:55:59', '0', '2');
INSERT INTO `ui_web_case` VALUES ('4', '登录', '3', '1', '', '2022-06-28 17:00:43', '2022-06-28 17:00:43', '0', '2');
INSERT INTO `ui_web_case` VALUES ('6', '好单多多-展业', '0', '0', '', '2022-07-21 13:56:11', '2022-07-21 13:56:11', '0', '2');
INSERT INTO `ui_web_case` VALUES ('7', '登录', '6', '0', '', '2022-07-21 13:56:35', '2022-07-21 13:56:35', '0', '2');
INSERT INTO `ui_web_case` VALUES ('8', '用户名密码登录', '7', '1', '', '2022-07-21 13:56:48', '2022-07-21 17:25:52', '0', '2');
INSERT INTO `ui_web_case` VALUES ('11', '创建展位', '6', '0', '', '2022-07-25 14:47:26', '2022-07-25 14:47:26', '0', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) DEFAULT NULL COMMENT '账号',
  `password` varchar(100) DEFAULT NULL COMMENT '密码 （md5） ',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `locked` int(10) NOT NULL DEFAULT '0' COMMENT '是否锁定 0未锁定 1已锁定无法登陆',
  `role_id` int(10) DEFAULT NULL COMMENT '角色ID',
  `env_id` int(11) DEFAULT NULL COMMENT '环境ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `acc` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=60123 DEFAULT CHARSET=utf8mb4 COMMENT='平台用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'dujun', 'c60d82cfae878d88e506dac4b8da34cc', 'dujun', '0', '2', '1');
INSERT INTO `user` VALUES ('60122', 'shh', 'c60d82cfae878d88e506dac4b8da34cc', 'shh', '0', '2', null);

-- ----------------------------
-- Table structure for web_case_step
-- ----------------------------
DROP TABLE IF EXISTS `web_case_step`;
CREATE TABLE `web_case_step` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `case_id` int(11) DEFAULT NULL COMMENT '用例ID',
  `sort` int(11) DEFAULT NULL COMMENT '排序值',
  `step_describe` varchar(40) DEFAULT NULL COMMENT '步骤描述',
  `action_id` int(11) DEFAULT NULL COMMENT '行动ID',
  `action_summary` varchar(20) DEFAULT NULL COMMENT '行动描述',
  `action_value` varchar(100) DEFAULT NULL COMMENT '行动值',
  `element_id` int(11) DEFAULT NULL COMMENT '元素ID',
  `status` int(20) DEFAULT NULL COMMENT '状态 0 执行 1 不执行',
  `assert_type` varchar(20) DEFAULT NULL COMMENT '步骤断言类型 ',
  `assert_value` varchar(100) DEFAULT NULL COMMENT '步骤断言值',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COMMENT=' UI自动化-用户步骤表';

-- ----------------------------
-- Records of web_case_step
-- ----------------------------
INSERT INTO `web_case_step` VALUES ('1', '2', '1', '打开网址', '10', 'OPENURL', 'http://testcrm.qyhnet.com/user/login', null, '0', 'titleIs', '登录 - CRM管理系统', null, '2022-07-12 17:26:33');
INSERT INTO `web_case_step` VALUES ('2', '2', '2', '文本输入', '15', 'INPUT', '17637898368', '1', '0', null, null, null, '2022-12-19 14:11:58');
INSERT INTO `web_case_step` VALUES ('3', '2', '3', '强制等待', '17', 'SLEEP', '3000', null, '0', null, null, null, null);
INSERT INTO `web_case_step` VALUES ('9', '4', '1', '点击元素', '39', 'click', '', '4', '0', '', '', '2022-07-07 17:04:13', '2022-07-07 17:04:13');
INSERT INTO `web_case_step` VALUES ('10', '4', '2', '点击元素', '39', 'click', '', '5', '0', null, null, '2022-07-07 17:39:58', '2022-07-07 17:39:58');
INSERT INTO `web_case_step` VALUES ('13', '4', '6', '点击元素', '39', 'click', '', '8', '0', null, null, '2022-07-07 17:40:45', '2022-07-07 17:40:45');
INSERT INTO `web_case_step` VALUES ('14', '4', '7', '点击元素', '39', 'click', '', '9', '0', null, null, '2022-07-07 17:40:45', '2022-07-07 17:40:45');
INSERT INTO `web_case_step` VALUES ('15', '4', '4', '元素输入', '49', 'input', '17637898368', '6', '0', null, null, '2022-07-08 14:31:57', '2022-07-26 16:46:44');
INSERT INTO `web_case_step` VALUES ('16', '4', '5', '元素输入', '49', 'input', 'qwer12345', '7', '0', null, null, '2022-07-08 14:32:49', '2022-07-26 16:46:39');
INSERT INTO `web_case_step` VALUES ('17', '4', '8', '线程等待', '29', 'SLEEP', '3000', null, '0', null, null, '2022-07-08 17:46:06', '2022-07-08 17:46:06');
INSERT INTO `web_case_step` VALUES ('18', '4', '7', 'toast捕捉', '34', 'catch_toast', '请输入正确的手机号或密码', null, '0', 'TOASTASSERT', '请输入正确的手机号或密码', '2022-07-08 17:46:26', '2022-07-08 17:46:26');
INSERT INTO `web_case_step` VALUES ('19', '8', '1', '点击元素', '39', 'click', '', '10', '0', null, null, '2022-07-21 14:06:43', '2022-07-21 14:06:43');
INSERT INTO `web_case_step` VALUES ('20', '8', '2', '线程等待', '29', 'SLEEP', '2000', null, '0', null, null, '2022-07-21 14:07:10', '2022-07-21 14:07:10');
INSERT INTO `web_case_step` VALUES ('21', '8', '3', '点击元素', '39', 'click', '', '11', '0', null, null, '2022-07-21 14:07:22', '2022-07-21 14:07:22');
INSERT INTO `web_case_step` VALUES ('22', '8', '4', '元素输入', '49', 'input', '17637898368', '12', '0', null, null, '2022-07-21 14:07:38', '2022-07-26 16:46:31');
INSERT INTO `web_case_step` VALUES ('23', '8', '5', '元素输入', '49', 'input', 'test234', '13', '0', null, null, '2022-07-21 14:07:59', '2022-07-26 16:46:26');
INSERT INTO `web_case_step` VALUES ('24', '8', '6', '点击元素', '39', 'click', '', '14', '0', null, null, '2022-07-21 14:08:09', '2022-07-21 14:08:09');
INSERT INTO `web_case_step` VALUES ('25', '8', '7', '点击元素', '39', 'click', '', '15', '0', 'TOASTASSERT', '请输入正确的手机号或密码', '2022-07-21 14:08:20', '2022-07-21 14:08:20');
INSERT INTO `web_case_step` VALUES ('28', '8', '8', 'input-元素清除', '43', 'clear', '', '13', '0', null, null, '2022-07-21 17:50:41', '2022-07-21 17:50:41');
INSERT INTO `web_case_step` VALUES ('29', '8', '9', '元素输入', '49', 'input', 'qwer1234', '13', '0', null, null, '2022-07-22 11:39:24', '2022-07-26 16:46:23');
INSERT INTO `web_case_step` VALUES ('30', '8', '10', '点击元素', '39', 'click', '', '15', '0', null, null, '2022-07-22 11:40:42', '2022-07-22 11:40:42');
INSERT INTO `web_case_step` VALUES ('32', '8', '13', '点击元素', '39', 'Click', '', '16', '0', null, null, '2022-07-25 15:59:01', '2022-07-25 15:59:01');
INSERT INTO `web_case_step` VALUES ('35', '8', '12', '向上滑动', '45', 'swipeTop', '', null, '0', null, null, '2022-07-26 10:28:55', '2022-07-26 10:28:55');
INSERT INTO `web_case_step` VALUES ('36', '8', '13', '线程等待', '29', 'SLEEP', '3000', null, '0', null, null, '2022-07-26 10:29:08', '2022-07-26 10:29:08');
INSERT INTO `web_case_step` VALUES ('41', '8', '14', '坐标定位+点击', '51', 'Tap_Click', '0.46,0.9889', null, '0', null, null, '2022-07-26 16:34:08', '2022-07-27 09:46:57');
INSERT INTO `web_case_step` VALUES ('42', '8', '15', '线程等待', '29', 'SLEEP', '10000', null, '0', null, null, '2022-07-26 16:36:37', '2022-07-26 16:36:37');
INSERT INTO `web_case_step` VALUES ('43', '8', '11', '线程等待', '29', 'SLEEP', '3000', null, '0', null, null, '2022-07-26 17:13:27', '2022-07-26 17:13:27');

-- ----------------------------
-- Table structure for web_page
-- ----------------------------
DROP TABLE IF EXISTS `web_page`;
CREATE TABLE `web_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '页面名称',
  `parent_id` int(11) DEFAULT '0' COMMENT '父类页面ID',
  `type` int(11) DEFAULT '1' COMMENT ' 页面类型  1 WEB  2 App',
  `del_flag` int(255) NOT NULL DEFAULT '0' COMMENT '0 启用 1 删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='UI自动化-- 页面表';

-- ----------------------------
-- Records of web_page
-- ----------------------------
INSERT INTO `web_page` VALUES ('1', '轻易花--CRM', '0', '1', '0', '2022-07-25 15:49:04', '2022-07-25 15:49:20');
INSERT INTO `web_page` VALUES ('2', '登录页面', '1', '1', '0', '2022-07-25 15:49:04', '2022-07-25 15:49:20');
INSERT INTO `web_page` VALUES ('6', '好单多多-old', '0', '2', '0', '2022-07-25 15:49:04', '2022-07-25 15:49:20');
INSERT INTO `web_page` VALUES ('7', '登录', '6', '2', '0', '2022-07-25 15:49:04', '2022-07-25 15:49:20');
INSERT INTO `web_page` VALUES ('8', '启动页', '7', '2', '0', '2022-07-25 15:49:04', '2022-07-25 15:49:20');
INSERT INTO `web_page` VALUES ('9', '登录页', '7', '2', '0', '2022-07-25 15:49:04', '2022-07-25 15:49:20');
INSERT INTO `web_page` VALUES ('12', '好单多多-展业', '0', '2', '0', '2022-07-25 15:50:04', '2022-07-25 15:50:31');
INSERT INTO `web_page` VALUES ('13', '登录', '12', '2', '0', '2022-07-25 15:49:04', '2022-07-25 15:49:20');
INSERT INTO `web_page` VALUES ('14', '启动页', '13', '2', '0', '2022-07-25 15:49:04', '2022-07-25 15:49:20');
INSERT INTO `web_page` VALUES ('15', '登录页', '13', '2', '0', '2022-07-25 15:49:04', '2022-07-25 15:49:20');

-- ----------------------------
-- Procedure structure for repeatTest
-- ----------------------------
DROP PROCEDURE IF EXISTS `repeatTest`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `repeatTest`()
BEGIN

DECLARE i INT ; #申明变量
SET i = 0 ; #变量赋值
REPEAT
	INSERT INTO `tester_plat`.`user` (
		`account`,
		`password`,
		`nick_name`,
		`locked`,
		`role_id`,
		`env_id`
	)
VALUES
	(
		'test2',
		NULL,
		'test2',
		'0',
		NULL,
		NULL
	) ; #往maomao表添加数据
SET i = i + 1 ; #循环一次,i加一
UNTIL i > 40000
END
REPEAT
	; #结束循环的条件: 当i大于80时跳出repeat循环
	SELECT
		*
	FROM
		`user` ; #查看maomao表数据
	END
;;
DELIMITER ;
