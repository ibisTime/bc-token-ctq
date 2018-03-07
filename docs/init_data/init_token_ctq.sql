INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('1','contractAddress','0xEb463487e165cF630E108d4740521D3A56910b7c','admin',now(),'智能合约地址');

INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','curEthBlockNumber','0','code',now(),'ETH下次从哪个区块开始扫描');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','blockConfirmEth','0','admin',now(),'ETH需要多少个区块确认');
