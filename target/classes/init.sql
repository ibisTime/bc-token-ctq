
CREATE TABLE IF NOT EXISTS `coin_eth_get_tx`.`tcoin_eth_address` (
  `code` VARCHAR(32) NOT NULL,
  `address` CHAR(42) NOT NULL,
  `type` VARCHAR(3) NOT NULL,
  `create_datetime` DATETIME NOT NULL,
  PRIMARY KEY (`code`),
  INDEX `address_index` (`address` ASC))
  ENGINE = InnoDB  DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `coin_eth_get_tx`.`tcoin_eth_transaction` (
  `hash` CHAR(66) NOT NULL COMMENT '交易哈希\'',
  `nonce` BIGINT(20) NOT NULL COMMENT '交易次数',
  `block_hash` VARCHAR(66) NOT NULL COMMENT '\'区块哈希\'',
  `transaction_index` BIGINT(20) NOT NULL,
  `from` CHAR(42) NOT NULL COMMENT '\'转出地址\'',
  `to` CHAR(42) NOT NULL COMMENT '\'转入地址\'',
  `value` VARCHAR(30) NOT NULL COMMENT '\'交易额\'',
  `gas_price` VARCHAR(20) NOT NULL COMMENT '\'gas价格\'',
  `block_create_datetime` DATETIME NOT NULL COMMENT '区块形成时间',
  `sync_datetime` DATETIME NOT NULL COMMENT '同步时间',
  `status` VARCHAR(4) NOT NULL COMMENT '\'状态 0-未推送 1-已推送\'',
  `gas` BIGINT(20) NOT NULL COMMENT 'gas使用量',
  `block_number` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`hash`),
  INDEX `from_index` (`from` ASC),
  INDEX `to_index` (`to` ASC, `status` ASC),
  INDEX `status_index` (`status` ASC))
  ENGINE = InnoDB;


CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `ckey` varchar(255) DEFAULT NULL COMMENT 'key',
  `cvalue` text COMMENT 'value',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;


CREATE TABLE `tsys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号（自增长）',
  `type` char(1) DEFAULT NULL COMMENT '类型（第一层/第二层）',
  `parent_key` varchar(32) DEFAULT NULL COMMENT '父key',
  `dkey` varchar(32) DEFAULT NULL COMMENT 'key',
  `dvalue` varchar(255) DEFAULT NULL COMMENT '值',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;



insert INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark, system_code,company_code)
VALUES
  ('0','curBlockNumber','4443761','code',now(),'下次从哪个区块开始扫描');