
--
-- Table structure for table `tctq_eth_address`
--

DROP TABLE IF EXISTS `tctq_eth_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tctq_eth_address` (
  `code` varchar(32) NOT NULL,
  `address` char(42) NOT NULL,
  `type` varchar(3) NOT NULL,
  `create_datetime` datetime NOT NULL,
  PRIMARY KEY (`code`),
  KEY `address_index` (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tctq_eth_transaction`
--

DROP TABLE IF EXISTS `tctq_eth_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  KEY `to_index` (`to`,`status`),
  KEY `status_index` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_config`
--

DROP TABLE IF EXISTS `tsys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsys_dict`
--

DROP TABLE IF EXISTS `tsys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;
