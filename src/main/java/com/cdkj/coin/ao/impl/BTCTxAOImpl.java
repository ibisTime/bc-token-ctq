package com.cdkj.coin.ao.impl;

import java.math.BigDecimal;
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
import com.cdkj.coin.bitcoin.BlockchainBlock;
import com.cdkj.coin.bitcoin.BlockchainOutput;
import com.cdkj.coin.bitcoin.BlockchainTx;
import com.cdkj.coin.bitcoin.BtcUtxo;
import com.cdkj.coin.bo.IBTCAddressBO;
import com.cdkj.coin.bo.IBtcUtxoBO;
import com.cdkj.coin.bo.ISYSConfigBO;
import com.cdkj.coin.common.AmountUtil;
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

        boolean isDebug = true;
        if (isDebug) {
            logger.info("******BTC扫描区块开始******");
        }

        while (true) {

            Long blockNumber = sysConfigBO
                .getLongValue(SysConstants.CUR_BTC_BLOCK_NUMBER);

            // 如果区块高度为达到带扫描的区块
            Long lasterBlockNumber = blockDataService.getBlockCount();
            if (lasterBlockNumber < blockNumber) {
                break;
            }

            // 判断是否有足够的区块确认 推荐1
            BigInteger blockConfirm = sysConfigBO
                .getBigIntegerValue(SysConstants.BLOCK_CONFIRM_BTC);
            if (blockNumber == null
                    || (lasterBlockNumber - blockNumber) < blockConfirm
                        .longValue()) {

                if (isDebug) {
                    System.out.println("*********同步循环结束,区块号"
                            + (blockNumber - 1) + "为最后一个可信任区块*******");
                }

                break;
            }

            List<BtcUtxo> ourOutUTXOList = new ArrayList<>();

            if (isDebug) {
                System.out.println("*********开始扫描区块" + blockNumber + "*******");
            }

            // 获取区块详细信息，包含交易列表数据
            BlockchainBlock blockchainBlock = blockDataService
                .getBlockWithTx(BigInteger.valueOf(blockNumber));

            // 最小充值金额
            BigDecimal orangeMinChangeMoney = sysConfigBO
                .getBigDecimalValue(SysConstants.MIN_BTC_RECHARGE_MONEY);
            BigDecimal minChangeMoney = AmountUtil.toBtc(orangeMinChangeMoney);

            // 遍历交易
            for (BlockchainTx blockchainTx : blockchainBlock.getTx()) {

                // 遍历输出
                for (BlockchainOutput output : blockchainTx.getOut()) {
                    String outToAddress = output.getAddr();
                    if (outToAddress == null) {
                        continue;
                    }
                    if (btcUtxoBO.isBtcUtxoExist(blockchainTx.getHash(),
                        output.getN())) {
                        continue;
                    }

                    long count = this.btcAddressBO.addressCount(outToAddress);

                    if (count <= 0) {
                        continue;
                    }

                    BtcUtxo btcutxo = this.convertOut(output, blockchainTx,
                        blockNumber, EBTCUtxoStatus.OUT_UN_PUSH.getCode());

                    // 小于最小充值金额跳过
                    if (minChangeMoney.compareTo(btcutxo.getCount()) > 0) {
                        continue;
                    }

                    ourOutUTXOList.add(btcutxo);
                }

            }

            this.saveToDB(ourOutUTXOList, blockNumber);

        }

        if (isDebug) {
            logger.info("******BTC扫描区块结束******");
        }

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
                "请传入正确的json数组"
                        + EBizErrorCode.PUSH_STATUS_UPDATE_FAILURE
                            .getErrorCode());
        }

        for (BtcUtxo btcutxo : utxoList) {

            BtcUtxo ourBtcUtxo = this.btcUtxoBO.getBtcUtxo(btcutxo.getTxid(),
                btcutxo.getVout());

            EBTCUtxoStatus nextStatus = null;

            nextStatus = EBTCUtxoStatus.OUT_PUSHED;

            if (nextStatus == null) {

                logger.error("utxo 状态异常，无法对应，原因：" + "txid:"
                        + ourBtcUtxo.getTxid() + "  " + "vout:"
                        + ourBtcUtxo.getVout());

            } else {

                this.btcUtxoBO.refreshStatus(ourBtcUtxo, nextStatus);

            }

        }

    }

    private BtcUtxo convertOut(BlockchainOutput output, BlockchainTx tx,
            Long blockHeight, String status) {

        BtcUtxo btcutxo = new BtcUtxo();
        btcutxo.setAddress(output.getAddr());
        btcutxo.setCount(new BigDecimal(output.getValue().toString()));
        btcutxo.setScriptPubKey(output.getScript());
        btcutxo.setTxid(tx.getHash());
        btcutxo.setVout(output.getN());
        btcutxo.setSyncTime(new Date());
        btcutxo.setBlockHeight(blockHeight);
        btcutxo.setStatus(status);
        return btcutxo;

    }

}
