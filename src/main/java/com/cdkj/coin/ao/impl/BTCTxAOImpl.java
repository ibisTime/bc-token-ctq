package com.cdkj.coin.ao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.coin.ao.IBTCTxAO;
import com.cdkj.coin.bitcoin.BTCOriginalTx;
import com.cdkj.coin.bitcoin.BTCScriptPubKey;
import com.cdkj.coin.bitcoin.BTCTXs;
import com.cdkj.coin.bitcoin.BTCVoutUTXO;
import com.cdkj.coin.bitcoin.BtcUtxo;
import com.cdkj.coin.bo.IBTCAddressBO;
import com.cdkj.coin.bo.IBtcUtxoBO;
import com.cdkj.coin.bo.ISYSConfigBO;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.common.SysConstants;
import com.cdkj.coin.domain.SYSConfig;
import com.cdkj.coin.enums.EBTCUtxoStatus;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.EBizErrorCode;
import com.cdkj.coin.http.PostSimulater;

@Service
public class BTCTxAOImpl implements IBTCTxAO {

    static final Logger logger = LoggerFactory.getLogger(BTCTxAOImpl.class);

    @Autowired
    BTCBlockDataService blockDataService;

    @Autowired
    IBTCAddressBO btcAddressBO;

    @Autowired
    IBtcUtxoBO btcUtxoBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    public void doBtcTransactionSync() {

        // logger.info("******BTC扫描区块开始******");

        Long blockNumber = sysConfigBO
            .getLongValue(SysConstants.CUR_BTC_BLOCK_NUMBER);

        // 如果区块高度为达到带扫描的区块
        Long lasterBlockNumber = blockDataService.getBlockCount();
        if (lasterBlockNumber < blockNumber) {
            return;
        }

        // 判断是否有足够的区块确认 推荐1
        BigInteger blockConfirm = sysConfigBO
            .getBigIntegerValue(SysConstants.BLOCK_CONFIRM_BTC);
        if (blockNumber == null
                || (lasterBlockNumber - blockNumber) < blockConfirm
                    .longValue()) {
            // System.out.println("*********同步循环结束,区块号" + (blockNumber - 1)
            // + "为最后一个可信任区块*******");
            return;
        }

        // 该区块有测试数据
        // Long blockNumber = Long.valueOf(1284522);
        List<BtcUtxo> ourOutUTXOList = new ArrayList<>();

        // 查询的分页
        Integer pageNum = 0;
        while (true) {
            // logger.info(
            // "******扫描区块：" + blockNumber + " 第" + pageNum + "页：" + "******");

            BTCTXs btctXs = null;
            try {
                btctXs = this.blockDataService.getBlockTxs(blockNumber,
                    pageNum);
            } catch (Exception e) {
                logger.error("******扫描区块：" + blockNumber + " 第" + pageNum + "页："
                        + "发送异常，原因：" + e.getMessage() + "，重新扫描******");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                continue;
            }

            if (btctXs == null) {
                logger.error("******扫描区块：" + blockNumber + " 第" + pageNum + "页："
                        + "发送异常，重新扫描******");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                continue;
            }

            // 说明该区块已经遍历完了
            if (btctXs.getTxs().size() <= 0) {
                // logger.info("******扫描区块完成：" + blockNumber + "******");
                break;
            }

            // 遍历交易
            for (BTCOriginalTx originalTx : btctXs.getTxs()) {
                // todo 暂不处理coinbase 挖矿充值可能会被忽略
                if (originalTx.getCoinBase() != null
                        && originalTx.getCoinBase()) {
                    continue;
                }

                // 遍历输出
                for (BTCVoutUTXO voutUTXO : originalTx.getVout()) {

                    List<String> addressList = voutUTXO.getScriptPubKey()
                        .getAddresses();
                    if (addressList == null || addressList.size() == 0) {
                        continue;
                    }
                    if (btcUtxoBO.isBtcUtxoExist(originalTx.getTxid(),
                        voutUTXO.getN())) {
                        continue;
                    }

                    String outToAddress = addressList.get(0);
                    long count = this.btcAddressBO.addressCount(outToAddress);

                    if (count <= 0) {
                        continue;
                    }

                    BtcUtxo btcutxo = this.convertOut(voutUTXO,
                        originalTx.getTxid(), originalTx.getBlockheight(),
                        EBTCUtxoStatus.OUT_UN_PUSH.getCode());
                    ourOutUTXOList.add(btcutxo);

                }

            }

            pageNum += 1;

        }

        this.saveToDB(ourOutUTXOList, blockNumber);

        // logger.info("******BTC扫描区块结束******");

    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void saveToDB(List<BtcUtxo> ourOutUTXOList, Long blockNumber) {

        // 存储 utxo
        for (BtcUtxo insertUTXO : ourOutUTXOList) {
            this.btcUtxoBO.saveBtcUtxo(insertUTXO);
        }

        // 修改_区块遍历信息
        SYSConfig config = sysConfigBO
            .getSYSConfig(SysConstants.CUR_BTC_BLOCK_NUMBER);
        //
        sysConfigBO.refreshSYSConfig(config.getId(),
            String.valueOf(blockNumber + 1), config.getUpdater(),
            config.getRemark());

    }

    // 时间调度任务,定期扫描——未推送的——交易
    public void pushTx() {

        // logger.info("******BTC推送UTXO开始******");

        List<BtcUtxo> btcutxoList = this.btcUtxoBO.selectUnPush();
        if (btcutxoList != null && btcutxoList.size() > 0) {
            // 推送出去
            try {

                String pushJsonStr = JsonUtil.Object2Json(btcutxoList);
                String url = PropertiesUtil.Config.BTC_PUSH_ADDRESS_URL;
                Properties formProperties = new Properties();
                formProperties.put("btcUtxolist", pushJsonStr);
                PostSimulater.requestPostForm(url, formProperties);

            } catch (Exception e) {
                logger.error("回调业务biz异常，原因：" + e.getMessage());
            }
        }

    }

    // 确认推送
    @Override
    public synchronized void confirmPush(List<BtcUtxo> utxoList) {

        if (utxoList == null || utxoList.size() <= 0) {
            throw new BizException(
                EBizErrorCode.PUSH_STATUS_UPDATE_FAILURE.getErrorCode(),
                "请传入正确的json数组" + EBizErrorCode.PUSH_STATUS_UPDATE_FAILURE
                    .getErrorCode());
        }

        for (BtcUtxo btcutxo : utxoList) {

            BtcUtxo ourBtcUtxo = this.btcUtxoBO.getBtcUtxo(btcutxo.getTxid(),
                btcutxo.getVout());

            EBTCUtxoStatus nextStatus = null;

            nextStatus = EBTCUtxoStatus.OUT_PUSHED;

            if (nextStatus == null) {

                logger
                    .error("utxo 状态异常，无法对应，原因：" + "txid:" + ourBtcUtxo.getTxid()
                            + "  " + "vout:" + ourBtcUtxo.getVout());

            } else {

                this.btcUtxoBO.refreshStatus(ourBtcUtxo, nextStatus);

            }

        }

    }

    private BtcUtxo convertOut(BTCVoutUTXO btcOut, String txid,
            Long blockHeight, String status) {

        BTCScriptPubKey scriptPubKey = btcOut.getScriptPubKey();

        BtcUtxo btcutxo = new BtcUtxo();
        btcutxo.setAddress(scriptPubKey.getAddresses().get(0));
        btcutxo.setCount(btcOut.getValue());
        btcutxo.setScriptPubKey(scriptPubKey.getHex());
        btcutxo.setTxid(txid);
        btcutxo.setVout(btcOut.getN());
        btcutxo.setSyncTime(new Date());
        btcutxo.setBlockHeight(blockHeight);
        btcutxo.setStatus(status);
        return btcutxo;

    }

}
