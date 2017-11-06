/*
 * 操作步骤
 * 1、替换CD-COIN000017 为新的system_code，后面出现的XX为新系统简称
 * 2、替换UCOIN201700000000000001为UXX201700000000000001
 * 3、替换COINSM201为XXSM201
 * 4、替换COINSR2017为XXSR2017
*/
INSERT INTO `tstd_user` (`user_id`,`login_name`,`login_pwd`,`login_pwd_strength`,`kind`,`level`,`role_code`,`status`,`create_datetime`,`remark`,`company_code`,`system_code`) VALUES ('UCOIN201700000000000001','admin','21218cca77804d2ba1922c33e0151105','1','P','0','COINSR201700000000000000','0',now(),'管理端系统方','CD-COIN000017','CD-COIN000017');

/*
-- Query: SELECT code,name,type,url,order_no,'admin' updater, now() as update_datetime,remark,parent_code,system_code FROM tsys_menu
-- Date: 2017-08-24 11:28
*/
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201612071021105964','财务管理','1','#','3','admin',now(),'','COINSM201700000000000000','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120710215035834','账户查询','1','#','1','admin',now(),'','COINSM201612071021105964','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201612071022206883','外部账对账','1','#','5','admin',now(),'','COINSM201612071021105964','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120710225078473','内部账对账','1','#','6','admin',now(),'','COINSM201612071021105964','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120710233232137','平台账户','1','#','2','admin',now(),'','COINSM201612071021105964','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120710242555432','线下取现','1','#','3','admin',now(),'','COINSM201612071021105964','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201612071029309117','账户查询','1','/finance/account.htm','1','admin',now(),'','COINSM2016120710215035834','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120710341064879','流水查询','1','/finance/ledger.htm','2','admin',now(),'','COINSM2016120710215035834','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120710421392861','自动对账监控','1','/finance/autoReconControl.htm','1','admin',now(),'','COINSM201612071022206883','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120710443551388','不平账处理','1','/finance/roughHand.htm','2','admin',now(),'','COINSM201612071022206883','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120710470696888','二次轧账','1','/finance/twoRoll.htm','1','admin',now(),'','COINSM2016120710225078473','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120710484209132','不平账处理','1','/finance/inRoughHand.htm','2','admin',now(),'','COINSM2016120710225078473','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120710583381112','盈亏余额','1','/finance/breakBalance.htm','1','admin',now(),'','COINSM2016120710233232137','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120711021944714','线下充值','1','/finance/lineRecharge.htm','1','admin',now(),'','COINSM201707251143314118180','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120711034365344','线下取现','1','/finance/lineUnder.htm','2','admin',now(),'','COINSM2016120710242555432','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120714062949665','流水','2','/flow','1','admin',now(),'','COINSM201612071029309117','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120714343212111','详情','2','/detail','1','admin',now(),'','COINSM2016120710341064879','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120816125733948','人工对账','2','edit','1','admin',now(),'','COINSM2016120710421392861','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120816133940523','详情','2','/detail','2','admin',now(),'','COINSM2016120710421392861','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120911033945882','审核','2','/examine','1','admin',now(),'','COINSM2016120710443551388','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120911042060088','详情','2','/detail','2','admin',now(),'','COINSM2016120710443551388','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201612091114505291','二次轧账','2','edit','1','admin',now(),'','COINSM2016120710470696888','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120911160661084','详情','2','/detail','2','admin',now(),'','COINSM2016120710470696888','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120911293120195','审核','2','/examine','1','admin',now(),'','COINSM2016120710484209132','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016120911301231346','详情','2','/detail','2','admin',now(),'','COINSM2016120710484209132','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016122219561180130','导出','2','/export','9','admin',now(),'','COINSM201612071029309117','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016122219573703627','导出','2','/export','9','admin',now(),'','COINSM2016120710341064879','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016122219590218515','导出','2','/export','9','admin',now(),'','COINSM2016120710470696888','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016122220000572417','导出','2','/export','9','admin',now(),'','COINSM2016120710484209132','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016122317321369746','代申请','2','add','1','admin',now(),'','COINSM2016120711021944714','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016122317325464966','代申请','2','add','2','admin',now(),'','COINSM2016120711034365344','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016122317590316953','流水','2','/flow','1','admin',now(),'','COINSM2016120710583381112','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016122412144574736','导出','2','export','9','admin',now(),'','COINSM2016120710583381112','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016122413510161245','审核','2','/examine','2','admin',now(),'','COINSM2016120711021944714','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2016122413512505485','审核','2','/examine','2','admin',now(),'','COINSM2016120711034365344','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201700000000000000','根目录','1','#','1','admin',now(),'','','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201700001000000001','系统管理','1','#','1','admin',now(),'','COINSM201700000000000000','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201700001000000002','运维管理','1','#','2','admin',now(),NULL,'COINSM201700001000000001','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201700001000000003','菜单管理','1','/system/menu.htm','1','admin',now(),NULL,'COINSM201700001000000002','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201700001000000004','新增','2','/add','1','admin',now(),'','COINSM201700001000000003','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017022412460281492','资金流水','1','/finance/platform_ledger.htm','2','admin',now(),'','COINSM2016120710233232137','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017022412472301488','详情','2','/detail','1','admin',now(),'','COINSM2017022412460281492','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017022412482059354','导出','2','/export','2','admin',now(),'','COINSM2017022412460281492','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017022413014074915','导出','2','/export','3','admin',now(),'','COINSM2016120710443551388','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017022413024043573','导出','2','/export','3','admin',now(),'','COINSM2016120710421392861','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017032911200961325','修改','2','/edit','2','admin',now(),'','COINSM201700001000000003','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017033020005366333','banner管理','1','/public/banner.htm','1','admin',now(),'','COINSM201707251006045006005','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017033020015631166','新增','2','/add','1','admin',now(),'','COINSM2017033020005366333','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017033020021094115','修改','2','/edit','2','admin',now(),'','COINSM2017033020005366333','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017033020022649991','删除','2','/delete','3','admin',now(),'','COINSM2017033020005366333','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017033020024827112','详情','2','/detail','4','admin',now(),'','COINSM2017033020005366333','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017040616442519963','详情','2','/detail1','3','admin',now(),'','COINSM2016120711021944714','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017040616443747740','详情','2','/detail1','3','admin',now(),'','COINSM2016120711034365344','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017050810454898238','导出','2','/export','4','admin',now(),'','COINSM2016120711034365344','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017051511323094138','回录','2','/huilu','21','admin',now(),'','COINSM2016120711034365344','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201705151132544318','导出','2','/export','5','admin',now(),'','COINSM2016120711021944714','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017052014005252645','取现规则','1','/rules/enchashmentRule.htm','1','admin',now(),'','COINSM2016120710242555432','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017052014013690196','修改','2','/edit','1','admin',now(),'','COINSM2017052014005252645','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201707251006045006005','广告位管理','1','#','5','admin',now(),'','COINSM201700001000000001','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201707251143314118180','线下充值','1','#','4','admin',now(),'','COINSM201612071021105964','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201707251147233303780','取现查询','1','/finance/lineUnder.htm','3','admin',now(),'','COINSM2016120710242555432','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201707251148117352030','充值查询','1','/finance/lineRecharge.htm','2','admin',now(),'','COINSM201707251143314118180','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201707251320494186231','导出','2','/export','5','admin',now(),'','COINSM201707251147233303780','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201707251331497821971','导出','2','/export','5','admin',now(),'','COINSM201707251148117352030','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017101716241339082','运营管理','1','#','1','admin',now(),'','COINSM201700001000000001','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017101716253866426','角色管理','1','/system/role.htm','1','admin',now(),'','COINSM2017101716241339082','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017101716261754674','用户管理','1','/system/user.htm','2','admin',now(),'','COINSM2017101716241339082','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017101716450533995','分配菜单','2','/change','4','admin',now(),'','COINSM2017101716253866426','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017101717551955993','新增','2','/add','1','admin',now(),'','COINSM2017101716253866426','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017101717560118734','修改','2','/edit','2','admin',now(),'','COINSM2017101716253866426','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017101717563661357','删除','2','/delete','3','admin',now(),'','COINSM2017101716253866426','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017101719082391126','新增','2','/add','1','admin',now(),'','COINSM2017101716261754674','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017101719094151894','重置密码','2','/reset','2','admin',now(),'','COINSM2017101716261754674','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017101719100760088','注销','2','/rock','4','admin',now(),'','COINSM2017101716261754674','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017101719110981215','设置角色','2','/assign','5','admin',now(),'','COINSM2017101716261754674','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017112911152991684','消息推送','1','#','3','admin',now(),'','COINSM201700001000000001','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017112911280249973','公告管理','1','/public/notice.htm','1','admin',now(),'','COINSM2017112911152991684','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017112914292031228','新增','2','/add','1','admin',now(),'','COINSM2017112911280249973','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017112914295002950','修改','2','/edit2','2','admin',now(),'','COINSM2017112911280249973','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017112914322481897','发布/撤下','2','/push','3','admin',now(),'','COINSM2017112911280249973','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017112914325471772','详情','2','/detail','5','admin',now(),'','COINSM2017112911280249973','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017120610552303416','激活','2','/active','3','admin',now(),'','COINSM2017101716261754674','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017121215543215610','文章管理','1','#','4','admin',now(),'','COINSM201700001000000001','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM2017121216045274832','关于我们','1','/public/aboutus_addedit.htm','1','admin',now(),'','COINSM2017121215543215610','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201708241024194086655','删除','2','/delete','3','admin',now(),'','COINSM201700001000000003','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201708241027280514724','服务热线','1','/public/hotline_addedit.htm','2','admin',now(),'','COINSM2017121215543215610','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201708241027559375318','服务时间','1','/public/serviceTime_addedit.htm','25','admin',now(),'','COINSM2017121215543215610','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201708241036442974134','业务管理','1','#','2','admin',now(),'','COINSM201700000000000000','CD-COIN000017');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('COINSM201708241037322072730','统计分析','1','#','4','admin',now(),'','COINSM201700000000000000','CD-COIN000017');

/*
-- Query: SELECT `code`,`name`,`level`,'admin' as `updater`,now() as 'update_datetime',`remark`,`system_code` FROM tsys_role where system_code = 'CD-CZH000001'
LIMIT 0, 10000

-- Date: 2017-03-31 10:33
*/
INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','超级管理员','1','admin',now(),'','CD-COIN000017');

/*
-- Query: SELECT 'COINSR201700000000000000' `role_code`,code `menu_code`,'admin' `updater`,now() as `update_datetime`,'' `remark`,'CD-COIN000017' `system_code` FROM tsys_menu
-- Date: 2017-08-24 11:29
*/
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201612071021105964','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120710215035834','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201612071022206883','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120710225078473','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120710233232137','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120710242555432','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201612071029309117','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120710341064879','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120710421392861','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120710443551388','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120710470696888','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120710484209132','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120710583381112','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120711021944714','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120711034365344','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120714062949665','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120714343212111','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120816125733948','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120816133940523','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120911033945882','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120911042060088','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201612091114505291','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120911160661084','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120911293120195','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016120911301231346','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016122219561180130','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016122219573703627','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016122219590218515','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016122220000572417','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016122317321369746','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016122317325464966','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016122317590316953','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016122412144574736','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016122413510161245','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2016122413512505485','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201700000000000000','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201700001000000001','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201700001000000002','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201700001000000003','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201700001000000004','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017022412460281492','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017022412472301488','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017022412482059354','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017022413014074915','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017022413024043573','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017032911200961325','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017033020005366333','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017033020015631166','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017033020021094115','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017033020022649991','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017033020024827112','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017040616442519963','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017040616443747740','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017050810454898238','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017051511323094138','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201705151132544318','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017052014005252645','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017052014013690196','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201707251006045006005','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201707251143314118180','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201707251147233303780','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201707251148117352030','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201707251320494186231','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201707251331497821971','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101716241339082','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101716253866426','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101716261754674','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101716450533995','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101717551955993','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101717560118734','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101717563661357','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101719082391126','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101719094151894','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101719100760088','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101719110981215','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017112911152991684','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017112911280249973','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017112914292031228','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017112914295002950','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017112914322481897','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017112914325471772','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017120610552303416','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017121215543215610','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017121216045274832','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201708241024194086655','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201708241027280514724','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201708241027559375318','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201708241036442974134','admin',now(),'','CD-COIN000017');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201708241037322072730','admin',now(),'','CD-COIN000017');

/*
-- Query: select `type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code` from tsys_config
-- Date: 2017-08-23 10:21
*/
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('qiniu','qiniu_access_key','cU832V2Gh5LoUWyNBLQ2kS9_0I-KnEF7jERyRDdh','admin',now(),'七牛云key1','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('qiniu','qiniu_secret_key','ed5q5pSqKZZDN80gjGSkX93TmdDMTeZPVNd5bxgA','admin',now(),'七牛云key1','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('qiniu','qiniu_bucket','hyds','admin',now(),'存储空间','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('qiniu','qiniu_domain','ov30dsi08.bkt.clouddn.com','admin',now(),'访问域名','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('sys_txt','about_us','关于我们','admin',now(),'关于我们','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('sys_txt','reg_protocol','注册协议','admin',now(),'注册协议','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('sys_txt','help_center','帮助中心','admin',now(),'帮助中心','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('sys_txt','time','09:00:00 - 17:30:00','admin',now(),'服务时间','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('sys_txt','telephone','0571-89000000','admin',now(),'服务电话','CD-COIN000017','CD-COIN000017');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('sys_txt','reg_url','http://hy.hichengdai.com','admin',now(),'注册链接','CD-COIN000017','CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'user_status','用户状态','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','0','正常','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','1','程序锁定','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','2','人工锁定','admin',now(),NULL,'CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'id_kind','证件类型','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','id_kind','1','身份证','admin',now(),NULL,'CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'role_level','角色等级','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','1','运维','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','2','运营','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','3','客户','admin',now(),NULL,'CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'res_type','资源类型','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','res_type','1','菜单','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','res_type','2','按钮','admin',now(),NULL,'CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'lock_direction','锁定方向','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','lock_direction','1','锁定','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','lock_direction','2','解锁','admin',now(),NULL,'CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'user_kind','针对人群','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_kind','C','C端用户','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_kind','B','B端用户','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_kind','P','平台用户','admin',now(),NULL,'CD-COIN000017');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'notice_status','公告状态','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','notice_status','0','未发布','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','notice_status','1','已发布','admin',now(),NULL,'CD-COIN000017');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','notice_status','2','已下架','admin',now(),NULL,'CD-COIN000017');