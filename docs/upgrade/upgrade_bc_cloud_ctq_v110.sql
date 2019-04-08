/*添加trx币种*/
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('8154454','curTrxBlockNumber','0','admin',now(),'TRX下次从哪个区块开始扫描');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','blockConfirmTrx','25','admin',now(),'TRX需要多少个区块确认');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','minTrxRechargeMoney','0','admin', now(),'TRX最小充值金额');

DROP TABLE IF EXISTS `tctq_trx_address`;
CREATE TABLE `tctq_trx_address` (
  `code` varchar(32) NOT NULL,
  `address` char(36) NOT NULL COMMENT '地址',
  `create_datetime` datetime NOT NULL,
  PRIMARY KEY (`code`),
  KEY `address_index` (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tctq_trx_transaction`;
CREATE TABLE `tctq_trx_transaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hash` varchar(255) DEFAULT NULL COMMENT '哈希',
  `from` varchar (64) DEFAULT NULL COMMENT 'from地址',
  `to` varchar (64) DEFAULT NULL COMMENT 'to地址',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '数量',
  `fee` decimal(64,0) DEFAULT NULL COMMENT '矿工费',
  `type_url` varchar(255) DEFAULT NULL COMMENT '合约地址',
  `type` varchar(255) DEFAULT NULL COMMENT '合约类型',
  `block_height` int(10) DEFAULT NULL COMMENT '区块高度',
  `status` char(1) DEFAULT NULL COMMENT '推送状态 0-未推送 1-已推送',
  `create_datetime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;