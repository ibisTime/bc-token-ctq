/*迁移地址表*/
INSERT INTO `tctq_token_address` 
(`code`,`address`,`symbol`,`create_datetime`) 
SELECT 
 `code`,`address`,'OGC',`create_datetime`
FROM online_token_bc_ctq.tctq_eth_address WHERE type = 'X';