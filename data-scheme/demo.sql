CREATE DATABASE `service_demo` /*!40100 DEFAULT CHARACTER SET utf8 */

drop table if exists `t_dict`;

CREATE TABLE `t_dict` (
  `id` VARCHAR(32) NOT NULL,
  `value` VARCHAR(128) DEFAULT NULL COMMENT '值',
  `label` VARCHAR(128) DEFAULT NULL COMMENT '标签',
  `type` VARCHAR(64) DEFAULT NULL COMMENT '类型',
  `sort` INT(11) NOT NULL COMMENT '排序',
  `description` VARCHAR(128) DEFAULT NULL COMMENT '描述',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注信息',
  `create_by` VARCHAR(32) DEFAULT NULL COMMENT '创建者',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` VARCHAR(32) DEFAULT NULL COMMENT '更新者',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` CHAR(1) DEFAULT '0' NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='字典表';