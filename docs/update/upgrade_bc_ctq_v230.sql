
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
  `hash` char(66) NOT NULL COMMENT '交易哈希',
  `nonce` bigint(20) NOT NULL COMMENT '交易次数',
  `block_hash` varchar(66) NOT NULL COMMENT '区块哈希',
  `transaction_index` bigint(20) NOT NULL,
  `from` char(42) NOT NULL COMMENT '转出地址',
  `to` char(42) NOT NULL COMMENT '转入地址',
  `value` varchar(30) NOT NULL COMMENT '交易额',
  `input` text COMMENT 'input 输入',
  `token_from` char(42) DEFAULT NULL COMMENT 'token币发起地址',
  `token_to` char(42) DEFAULT NULL COMMENT 'token币接收地址',
  `token_value` varchar(30) DEFAULT NULL COMMENT 'token币数量',
  `token_log_index` int(11) DEFAULT NULL COMMENT 'event_log_index',
  `block_create_datetime` datetime NOT NULL COMMENT '区块形成时间',
  `sync_datetime` datetime NOT NULL COMMENT '同步时间',
  `block_number` varchar(20) NOT NULL,
  `gas_price` decimal(64,0) NOT NULL COMMENT 'gas价格',
  `gas_limit` bigint(20) NOT NULL COMMENT 'gas最大使用限制',
  `gas_used` bigint(20) NOT NULL COMMENT 'gas实际使用量',
  `status` varchar(4) NOT NULL COMMENT '状态 0-未推送 1-已推送',
  PRIMARY KEY (`id`),
  KEY `to_index` (`hash`,`token_log_index`),
  KEY `status_index` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;