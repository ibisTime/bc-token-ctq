DROP TABLE IF EXISTS `tcoin_usdt_transaction`;
CREATE TABLE `tcoin_usdt_transaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hash` varchar(255) DEFAULT NULL COMMENT '交易hash',
  `from` varchar(50) DEFAULT NULL COMMENT 'from地址',
  `to` varchar(50) DEFAULT NULL COMMENT 'to地址',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '数量',
  `fee` decimal(64,0) DEFAULT NULL COMMENT '手续费',
  `status` char(1) DEFAULT NULL COMMENT '推送状态 0-未推送 1-已推送',
  `version` int(10) DEFAULT NULL COMMENT '版本',
  `type_int` int(10) DEFAULT NULL COMMENT '交易类型(数字)',
  `type` varchar(20) DEFAULT NULL COMMENT '交易类型(字符串)',
  `property_id` int(10) DEFAULT NULL COMMENT '属性id',
  `block_hash` varchar(255) DEFAULT NULL COMMENT '区块hash',
  `block_height` int(10) DEFAULT NULL COMMENT '区块高度',
  `confirmations` int(10) DEFAULT NULL,
  `block_create_datetime` datetime DEFAULT NULL COMMENT '区块生成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `ctq`.`tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) VALUES ('0', 'curUsdtBlockNumber', '279175', 'admin', now(), 'USDT下次从哪个区块开始扫描');
INSERT INTO `ctq`.`tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) VALUES ('0', 'blockConfirmUsdt', '0', 'admin', now(), 'USDT需要多少个区块确认');
