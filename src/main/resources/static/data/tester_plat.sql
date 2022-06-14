/*
Navicat MySQL Data Transfer

Source Server         : 测试
Source Server Version : 50638
Source Host           : 118.31.184.240:3306
Source Database       : tester_plat

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2022-06-14 10:59:03
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

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
-- Table structure for case_api_relation
-- ----------------------------
DROP TABLE IF EXISTS `case_api_relation`;
CREATE TABLE `case_api_relation` (
  `api_id` int(11) DEFAULT NULL,
  `case_id` int(11) DEFAULT NULL,
  `status` int(255) DEFAULT NULL,
  `api_info` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for page_element
-- ----------------------------
DROP TABLE IF EXISTS `page_element`;
CREATE TABLE `page_element` (
  `element_name` varchar(255) DEFAULT NULL,
  `element_type` varchar(255) DEFAULT NULL,
  `location_way` varchar(255) DEFAULT NULL,
  `location_value` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `page_id` int(11) DEFAULT NULL,
  `conditions` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for plan_param
-- ----------------------------
DROP TABLE IF EXISTS `plan_param`;
CREATE TABLE `plan_param` (
  `plan_id` int(11) DEFAULT NULL,
  `api_ids` varchar(255) DEFAULT NULL,
  `case_ids` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for plan_result
-- ----------------------------
DROP TABLE IF EXISTS `plan_result`;
CREATE TABLE `plan_result` (
  `plan_id` int(11) DEFAULT NULL,
  `plan_name` varchar(255) DEFAULT NULL,
  `result_status` int(255) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL,
  `api_success_count` int(255) DEFAULT NULL,
  `api_failed_count` int(255) DEFAULT NULL,
  `case_success_count` int(11) DEFAULT NULL,
  `case_failed_count` int(11) DEFAULT NULL,
  `plan_type` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for plan_result_detail
-- ----------------------------
DROP TABLE IF EXISTS `plan_result_detail`;
CREATE TABLE `plan_result_detail` (
  `plan_result_id` int(11) DEFAULT NULL,
  `case_id` int(11) DEFAULT NULL,
  `case_number` int(11) DEFAULT NULL,
  `api_id` bigint(20) DEFAULT NULL,
  `api_info` varchar(255) DEFAULT NULL,
  `result` int(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `result_console` varchar(255) DEFAULT NULL,
  `case_name` varchar(255) DEFAULT NULL,
  `assert_result` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

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
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_name` varchar(255) DEFAULT NULL,
  `permission_url` text,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

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
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for ui_web_case
-- ----------------------------
DROP TABLE IF EXISTS `ui_web_case`;
CREATE TABLE `ui_web_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `type` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for web_case_step
-- ----------------------------
DROP TABLE IF EXISTS `web_case_step`;
CREATE TABLE `web_case_step` (
  `case_id` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `step_describe` varchar(255) DEFAULT NULL,
  `action_id` int(11) DEFAULT NULL,
  `action_summary` varchar(255) DEFAULT NULL,
  `element_id` int(11) DEFAULT NULL,
  `status` int(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `action_value` varchar(255) DEFAULT NULL,
  `assert_type` int(11) DEFAULT NULL,
  `assert_value` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for web_page
-- ----------------------------
DROP TABLE IF EXISTS `web_page`;
CREATE TABLE `web_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `del_flag` int(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
