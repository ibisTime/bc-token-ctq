

# 初始化数据
insert INTO `tsys_config`(type, ckey, cvalue, updater, update_datetime, remark)
VALUES
  ('0','curBlockNumber','4432876','code',now(),'下次从哪个区块开始扫描');