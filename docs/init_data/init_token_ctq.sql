INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('1','contractAddress','0xf4b2Ffd295366211484706cA4AcdBE729988c43b','admin',now(),'智能合约地址');

INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','curEthBlockNumber','0','code',now(),'ETH下次从哪个区块开始扫描');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','blockConfirmEth','0','admin',now(),'ETH需要多少个区块确认');
