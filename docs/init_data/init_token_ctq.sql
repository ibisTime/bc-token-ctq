INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('1','contractAddress','0x82435d705dF05779c5Ec3E128bF811dCd60316ae','admin',now(),'智能合约地址');

INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','curEthBlockNumber','0','code',now(),'ETH下次从哪个区块开始扫描');
INSERT INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark) VALUES ('0','blockConfirmEth','0','admin',now(),'ETH需要多少个区块确认');

INSERT INTO `tctq_eth_address` (`code`,`address`,`type`,`create_datetime`) VALUES ('AD201800000000000000','0x8E979a7621314e0BA506c54A2482F7F49a99Ef44','H',now());
