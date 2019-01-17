/*
-- Query: SELECT type,ckey,cvalue,'admin' updater,now() update_datetime,remark FROM xn_ctq.tsys_config
-- Date: 2019-01-16 13:38
*/
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0','curEthBlockNumber','6961776','admin','2019-01-16 05:38:06','ETH下次从哪个区块开始扫描');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0','curBtcBlockNumber','550175','admin','2019-01-16 05:38:06','BTC下次从哪个区块开始扫描');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0','curWanBlockNumber','0','admin','2019-01-16 05:38:06','WAN下次从哪个区块开始扫描');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0','curWTokenBlockNumber','0','admin','2019-01-16 05:38:06','WAN Token下次从哪个区块开始扫描');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0','blockConfirmEth','12','admin','2019-01-16 05:38:06','ETH需要多少个区块确认');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0','blockConfirmBtc','0','admin','2019-01-16 05:38:06','BTC需要多少个区块确认');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0','blockConfirmWan','0','admin','2019-01-16 05:38:06','WAN需要多少个区块确认');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0','blockConfirmWToken','0','admin','2019-01-16 05:38:06','WAN Token需要多少个区块确认');

INSERT INTO `tctq_token_contract` (`symbol`,`contract_address`,`create_datetime`) VALUES ('FMVP','0x68aaefbdd5cb18ff577966cb2679df42e2eba548',now());