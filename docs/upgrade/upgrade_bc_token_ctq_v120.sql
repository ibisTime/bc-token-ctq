
/*初始化config表，并把原先表的token扫描更新过来*/
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','curEthBlockNumber','0','code',now(),'ETH下次从哪个区块开始扫描');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','curBtcBlockNumber','1','admin',now(),'BTC下次从哪个区块开始扫描');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','curTokenBlockNumber','0','admin',now(),'Token下次从哪个区块开始扫描');

INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','blockConfirmEth','0','admin',now(),'ETH需要多少个区块确认');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','blockConfirmBtc','0','admin',now(),'BTC需要多少个区块确认');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','blockConfirmToken','0','admin',now(),'Token需要多少个区块确认');

UPDATE `tsys_config` SET `cvalue`= (SELECT `cvalue` FROM dev_token_bc_ctq.tsys_config where `ckey`='curEthBlockNumber') WHERE `ckey`='curTokenBlockNumber';

/*迁移地址表*/
INSERT INTO `tctq_token_address` 
(`code`,`address`,`symbol`,`create_datetime`) 
SELECT 
 `code`,`address`,'OGC',`create_datetime`
FROM dev_token_bc_ctq.tctq_eth_address;

/*迁移交易表*/
INSERT INTO `tctq_token_transaction` 
(`hash`,`nonce`,`block_hash`,`transaction_index`,`from`,`to`,`value`,`input`,`token_from`,`token_to`,`token_value`,`token_log_index`,`block_create_datetime`,`sync_datetime`,`block_number`,`gas_price`,`gas_limit`,`gas_used`,`status`,`symbol`) 
SELECT 
 `hash`,`nonce`,`block_hash`,`transaction_index`,`from`,`to`,`value`,`input`,`token_from`,`token_to`,`token_value`,0                ,`block_create_datetime`,`sync_datetime`,`block_number`,`gas_price`,`gas`      ,`gas_used`,`status`,'OGC' 
FROM dev_token_bc_ctq.tctq_eth_transaction;


