
#行情
CREATE TABLE `tcoin_market` (

  `code` VARCHAR(32) NOT NULL ,
  `coin` VARCHAR(10) NOT NULL COMMENT '数字货币',
  `refer_currency` VARCHAR(10) NOT NULL COMMENT '参照法币币种',
  `origin` VARCHAR(32) NOT NULL COMMENT '行情来源',
  `last_price` VARCHAR(32) NOT NULL COMMENT '最新价格',
  `low` VARCHAR(32) NOT NULL COMMENT '最低价格',
  `high` VARCHAR(32) NOT NULL COMMENT '最高价格',
  `volume` VARCHAR(32) DEFAULT NULL COMMENT '交易量',
  `update_datetime` DATETIME NOT NULL  COMMENT '更新时间',
  PRIMARY KEY (`code`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#汇率
CREATE TABLE `tcoin_currency_rate` (

  `currency` VARCHAR(10) NOT NULL COMMENT '币种',
  `refer_currency` VARCHAR(10) NOT NULL COMMENT '参照币种 为CNY',
  `origin` VARCHAR(32) NOT NULL COMMENT '汇率来源',
  `rate` DECIMAL(4,4) NOT NULL COMMENT '汇率',
  `update_datetime` DATETIME NOT NULL  COMMENT '更新时间',
   PRIMARY KEY (`currency`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

