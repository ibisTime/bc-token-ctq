ALTER TABLE `tctq_sc_transaction` 
ADD COLUMN `outputid` VARCHAR(255) NOT NULL COMMENT 'output哈希' AFTER `transactionid`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`transactionid`, `outputid`);