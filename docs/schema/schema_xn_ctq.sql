
CREATE TABLE `tctq_eth_address` (
  `code` varchar(32) NOT NULL,
  `address` char(42) NOT NULL,
  `type` varchar(3) NOT NULL,
  `create_datetime` datetime NOT NULL,
  PRIMARY KEY (`code`),
  KEY `address_index` (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tctq_eth_transaction` (
  `hash` char(66) NOT NULL COMMENT '交易哈希',
  `nonce` bigint(20) NOT NULL COMMENT '交易次数',
  `block_hash` varchar(66) NOT NULL COMMENT '区块哈希',
  `transaction_index` bigint(20) NOT NULL,
  `from` char(42) NOT NULL COMMENT '转出地址',
  `to` char(42) NOT NULL COMMENT '转入地址',
  `value` varchar(30) NOT NULL COMMENT '交易额',
  `gas_price` varchar(20) NOT NULL COMMENT 'gas价格',
  `block_create_datetime` datetime NOT NULL COMMENT '区块形成时间',
  `sync_datetime` datetime NOT NULL COMMENT '同步时间',
  `status` varchar(4) NOT NULL COMMENT '状态 0-未推送 1-已推送',
  `gas` bigint(20) NOT NULL COMMENT 'gas使用量',
  `block_number` varchar(20) NOT NULL,
  `gas_used` bigint(10) NOT NULL COMMENT 'gas消耗',
  PRIMARY KEY (`hash`),
  KEY `from_index` (`from`),
  KEY `to_index` (`to`),
  KEY `status_index` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tctq_sc_address` (
  `code` varchar(32) NOT NULL,
  `address` varchar(255) NOT NULL,
  `type` varchar(3) NOT NULL,
  `create_datetime` datetime NOT NULL,
  PRIMARY KEY (`code`),
  KEY `address_index` (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tctq_sc_transaction` (
  `transactionid` varchar(255) NOT NULL COMMENT '交易哈希',
  `outputid` varchar(255) NOT NULL COMMENT 'output哈希',
  `confirmationheight` varchar(66) NOT NULL COMMENT '确认区块高度',
  `confirmationtimestamp` bigint(20) NOT NULL COMMENT '确认时间',
  `from` varchar(255) NOT NULL COMMENT '转出地址',
  `to` varchar(255) NOT NULL COMMENT '转入地址',
  `value` varchar(30) NOT NULL COMMENT '交易数量',
  `minerfee` varchar(30) NOT NULL COMMENT '矿工费',
  `sync_datetime` datetime NOT NULL COMMENT '同步时间',
  `status` varchar(4) NOT NULL COMMENT '状态 0-未推送 1-已推送',
  PRIMARY KEY (`transactionid`, `outputid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tctq_btc_address` (
  `code` varchar(32) NOT NULL,
  `address` char(36) NOT NULL COMMENT '地址',
  `create_datetime` datetime NOT NULL,
  PRIMARY KEY (`code`),
  KEY `address_index` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `tctq_btc_utxo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `txid` char(64) NOT NULL,
  `vout` int(11) NOT NULL,
  `count` decimal(64,0) NOT NULL,
  `script_pub_key` text NOT NULL,
  `address` varchar(40) NOT NULL,
  `sync_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `block_height` int(11) NOT NULL,
  `status` varchar(4) NOT NULL COMMENT '0-out未推送，1-out已推送，2-in未推送，3-in已推送',
  PRIMARY KEY (`id`),
  UNIQUE KEY `txid_vout_unique_key` (`txid`,`vout`),
  KEY `address` (`address`),
  KEY `status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_config`;
CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `ckey` varchar(255) DEFAULT NULL COMMENT 'key',
  `cvalue` text COMMENT 'value',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_dict`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tctq_token_contract`;
CREATE TABLE `tctq_token_contract` (
  `symbol` varchar(32) NOT NULL COMMENT '币种符号',
  `contract_address` varchar(255) NOT NULL COMMENT '合约地址',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`symbol`),
  KEY `address_index` (`contract_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tctq_token_address`;
CREATE TABLE `tctq_token_address` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `address` varchar(64) NOT NULL COMMENT '地址',
  `symbol` varchar(64) NOT NULL COMMENT '所属币种',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`code`),
  KEY `address_index` (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tctq_token_transaction`;
CREATE TABLE `tctq_token_transaction` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `hash` varchar(255) NOT NULL COMMENT '交易哈希',
  `nonce` bigint(32) NOT NULL COMMENT '交易次数',
  `block_hash` varchar(255) NOT NULL COMMENT '区块哈希',
  `transaction_index` bigint(32) NOT NULL COMMENT '交易index',
  `from` varchar(255) NOT NULL COMMENT '转出地址',
  `to` varchar(255)  NOT NULL COMMENT '转入地址',
  `value` decimal(64,0) NOT NULL COMMENT '交易额',
  `input` text COMMENT 'input 输入',
  `token_from` varchar(255) DEFAULT NULL COMMENT 'token币发起地址',
  `token_to` varchar(255) DEFAULT NULL COMMENT 'token币接收地址',
  `token_value` decimal(64,0) DEFAULT NULL COMMENT 'token币数量',
  `token_log_index` bigint(32) DEFAULT NULL COMMENT 'event_log_index',
  `block_create_datetime` datetime NOT NULL COMMENT '区块形成时间',
  `sync_datetime` datetime NOT NULL COMMENT '同步时间',
  `block_number` bigint(32) NOT NULL COMMENT '区块号',
  `gas_price` decimal(64,0) NOT NULL COMMENT 'gas价格',
  `gas_limit` bigint(20) NOT NULL COMMENT 'gas最大使用限制',
  `gas_used` bigint(20) NOT NULL COMMENT 'gas实际使用量',
  `status` varchar(4) NOT NULL COMMENT '状态 0-未推送 1-已推送',
  `symbol` varchar(32) NOT NULL COMMENT '币种符号',
  PRIMARY KEY (`id`),
  KEY `to_index` (`hash`,`token_log_index`),
  KEY `status_index` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
