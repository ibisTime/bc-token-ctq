#广告
CREATE TABLE `tcoin_advertise` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(32) NOT NULL COMMENT '类型 0 = 买币、1=卖币',
  `user_id` varchar(32) NOT NULL COMMENT '发布用户编号',
  `trade_currency` varchar(8) NOT  NULL COMMENT '交易币种',
  `only_trust` VARCHAR(2) NOT NULL  COMMENT '是否只有信任的人可以交易',
  `premium_rate` decimal(4,2) NOT NULL COMMENT '溢价率',
  `market_price` bigint(32) NOT NULL COMMENT '行情价格',
  `protect_price` bigint(32) DEFAULT NULL COMMENT '保护单价',
  `min_trade` bigint(32) DEFAULT NULL COMMENT '最小交易额',
  `max_trade` bigint(32) DEFAULT NULL COMMENT '最大交易额',
  `pay_type` varchar(32) DEFAULT NULL COMMENT '付款方式',
  `pay_limit` int(11) DEFAULT NULL COMMENT '付款超时时间（分钟）',
  `status` varchar(4) NOT NULL COMMENT '状态 0=草稿、1=上架中、2=下架',
  `leave_message` text COMMENT '广告留言',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `update_datetime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#广告可见时间
CREATE TABLE `tcoin_advertise_display_time` (
  `id` INTEGER NOT NULL COMMENT '主键',
  `adsCode` varchar(32) NOT NULL COMMENT '编号',
  `week` CHAR(1) NOT NULL COMMENT '周几',
  `start_time` TIME NOT NULL COMMENT '开始时间',
  `end_time` TIME NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`adsCode`,`week`)  USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#订单
CREATE TABLE `tcoin_eth_order` (
  `code` varchar(32) NOT NULL COMMENT '订单编号',
  `type` varchar(32) DEFAULT NULL COMMENT '订单类型',
  `advertise_code` varchar(32) DEFAULT NULL COMMENT '广告编号',
  `buy_user` varchar(32) NOT  NULL COMMENT '买方',
  `sell_user` varchar(32) NOT NULL COMMENT '卖方',
  `leave_message` text COMMENT '广告留言',
  `trade_currency` varchar(32) NOT NULL COMMENT '交易币种',
  `trade_amount` bigint(32) NOT NULL COMMENT '交易总额',
  `fee` decimal(64,0) DEFAULT NULL COMMENT '手续费',
  `count` decimal(64,0) NOT NULL COMMENT '交易数字货币数量',
  `pay_type` varchar(32) NOT NULL,
  `invalid_datetime` datetime DEFAULT NULL COMMENT '支付超时时间',
  `status` varchar(4) NOT NULL COMMENT '订单状态',
  `sb_comment` varchar(32) DEFAULT NULL COMMENT '卖方对买方的评价',
  `bs_comment` varchar(32) DEFAULT NULL COMMENT '买方对卖方的评价',
  `arbitrate_status` varchar(32) DEFAULT NULL COMMENT '仲裁状态',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `update_datatime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8