
DROP TABLE IF EXISTS `tctq_btc_address`;
CREATE TABLE `tctq_btc_address` (
  `code` varchar(32) NOT NULL,
  `address` char(36) NOT NULL COMMENT '地址',
  `create_datetime` datetime NOT NULL,
  PRIMARY KEY (`code`),
  KEY `address_index` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tctq_btc_utxo`;
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


