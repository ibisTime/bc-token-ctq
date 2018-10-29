
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','curEthBlockNumber','0','admin',now(),'ETH下次从哪个区块开始扫描');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','curBtcBlockNumber','1','admin',now(),'BTC下次从哪个区块开始扫描');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','curUsdtBlockNumber','0','admin', now(),'USDT下次从哪个区块开始扫描');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','curWanBlockNumber','0','admin',now(),'WAN下次从哪个区块开始扫描');

INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','blockConfirmEth','0','admin',now(),'ETH需要多少个区块确认');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','blockConfirmBtc','0','admin',now(),'BTC需要多少个区块确认');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','blockConfirmUsdt','0','admin',now(),'USDT需要多少个区块确认');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','blockConfirmWan','0','admin',now(),'WAN需要多少个区块确认');

INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','minEthRechargeMoney','0','admin', now(),'ETH最小充值金额');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','minBtcRechargeMoney','0','admin', now(),'BTC最小充值金额');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','minWanRechargeMoney','0','admin', now(),'WAN最小充值金额');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','minUsdtRechargeMoney','0','admin', now(),'USDT最小充值金额');
