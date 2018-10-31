DROP TABLE IF EXISTS `tctq_usdt_transaction`;
CREATE TABLE `tctq_usdt_transaction` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) VALUES ('0', 'curUsdtBlockNumber', '0', 'admin', now(), 'USDT下次从哪个区块开始扫描');
INSERT INTO `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) VALUES ('0', 'blockConfirmUsdt', '0', 'admin', now(), 'USDT需要多少个区块确认');
INSERT INTO `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) VALUES ('0', 'minEthRechargeMoney', '0', 'admin', now(), 'ETH最小充值金额');
INSERT INTO `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) VALUES ('0', 'minBtcRechargeMoney', '0', 'admin', now(), 'BTC最小充值金额');
INSERT INTO `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) VALUES ('0', 'minWanRRechargeMoney', '0', 'admin', now(), 'WAN最小充值金额');
INSERT INTO `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) VALUES ('0', 'minUsdtRechargeMoney', '0', 'admin', now(), 'USDT最小充值金额');

ALTER TABLE `tctq_eth_transaction` DROP PRIMARY KEY;
create unique index `hash_UNIQUE` on tctq_eth_transaction (hash);
ALTER TABLE `tctq_eth_transaction` 
	ADD COLUMN `id` bigint(32) PRIMARY KEY AUTO_INCREMENT FIRST,
	ADD COLUMN `gas_fee` bigint(20) NOT NULL COMMENT 'gas费率',
	ADD COLUMN `input` text COMMENT 'input输入',
	ADD COLUMN `public_key` text COMMENT 'publicKey',
	ADD COLUMN `raw` text COMMENT 'raw',
	ADD COLUMN `r` text COMMENT 'r',
	ADD COLUMN `s` text COMMENT 's',
	CHANGE `gas` `gas_limit` bigint(20) NOT NULL COMMENT 'gas最大使用限制';
	
	
DROP TABLE IF EXISTS `tcoin_eth_token_event`;
CREATE TABLE `tcoin_eth_token_event` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `hash` varchar(255) NOT NULL COMMENT '交易哈希',
  `token_from` varchar(255) DEFAULT NULL COMMENT 'token币发起地址',
  `token_to` varchar(255) DEFAULT NULL COMMENT 'token币接收地址',
  `token_value` decimal(64,0) DEFAULT NULL COMMENT 'token币数量',
  `token_log_index` bigint(32) DEFAULT NULL COMMENT 'event_log_index',
  `symbol` varchar(32) NOT NULL COMMENT '币种符号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ETHtoken交易event';