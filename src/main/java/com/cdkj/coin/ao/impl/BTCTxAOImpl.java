package com.cdkj.coin.ao.impl;

import com.cdkj.coin.ao.IBTCTxAO;
import com.cdkj.coin.bo.IBTCAddressBO;
import com.cdkj.coin.bo.IBTCUtxoBO;
import com.cdkj.coin.bo.ISYSConfigBO;
import com.cdkj.coin.common.JsonUtil;
import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.common.SysConstants;
import com.cdkj.coin.domain.BTC.*;
import com.cdkj.coin.domain.SYSConfig;
import com.cdkj.coin.enums.EBTCUtxoStatus;
import com.cdkj.coin.exception.BizErrorCode;
import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.http.PostSimulater;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class BTCTxAOImpl implements IBTCTxAO {

    static final Logger logger = LoggerFactory
            .getLogger(BTCTxAOImpl.class);

    @Autowired
    BTCBlockDataService blockDataService;

    @Autowired
    IBTCAddressBO btcAddressBO;

    @Autowired
    IBTCUtxoBO btcUtxoBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;


    public void doBtcTransactionSync() {

        //
//        Long blockNumber = sysConfigBO
//                .getLongValue(SysConstants.CUR_BTC_BLOCK_NUMBER);

        //该区块有测试数据
        Long blockNumber = Long.valueOf(1260212);
        List<BTCUTXO> ourInUTXOList = new ArrayList<>();
        List<BTCUTXO> ourOutUTXOList = new ArrayList<>();

        OkHttpClient okHttpClient = new OkHttpClient();
        // 查询的分页
        Integer pageNum = 0;
        while (true) {

            BTCTXs btctXs = this.blockDataService.getBlockTxs(blockNumber, pageNum);

            if (btctXs == null) {
                throw new BizException("xn000", "拉取数据失败");
            }

            // 说明该区块已经遍历完了
            if (btctXs.getTxs().size() <= 0) {
                break;
            }

            //遍历交易
            for (BTCOriginalTx originalTx : btctXs.getTxs()) {

                //todo 暂不处理coinbase
                if (originalTx.getCoinBase() != null && originalTx.getCoinBase()) {
                    continue;
                }


                //遍历输入
                for (BTCVinUTXO vinUTXO : originalTx.getVin()) {

                    String outAddress = vinUTXO.getAddr();
                    if (outAddress == null || outAddress.length() == 0) {
                        continue;
                    }

                    long count = this.btcAddressBO.addressCount(outAddress);
                    if (count <= 0) {
                        continue;
                    }

                    //添加需要更新
                    BTCUTXO btcutxo = new BTCUTXO();
                    btcutxo.setTxid(vinUTXO.getTxid());
                    btcutxo.setVout(vinUTXO.getVout());
                    btcutxo.setStatus(EBTCUtxoStatus.IN_UN_PUSH.getCode());
                    ourInUTXOList.add(btcutxo);

                }

                //遍历输出
                for (BTCVoutUTXO voutUTXO : originalTx.getVout()) {

                    List<String> addressList = voutUTXO.getScriptPubKey().getAddresses();
                    if (addressList == null || addressList.size() == 0) {
                        continue;
                    }

                    String outToAddress = addressList.get(0);
                    long count = this.btcAddressBO.addressCount(outToAddress);

//                    if (outToAddress.equals("n22qbkmhfip9Ks5ehxZqCT8CHR23FDw4ka") || outToAddress.equals("mktt7K5TH6aieW2xUV6fBjJyEbxPs6QjgG") || outToAddress.equals("myc1x6qKivfuxqcGovfMXhbmZWbBrx5TKz")) {
//
//                    }

                    if (count <= 0) {
                        continue;
                    }

                    BTCUTXO btcutxo = this.convertOut(
                            voutUTXO,
                            originalTx.getTxid(),
                            originalTx.getBlockheight(),
                            EBTCUtxoStatus.OUT_UN_PUSH.getCode());
                    ourOutUTXOList.add(btcutxo);


                }

            }

            pageNum += 1;

        }

        this.saveToDB(ourInUTXOList, ourOutUTXOList, blockNumber);

    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void saveToDB(List<BTCUTXO> ourInUTXOList, List<BTCUTXO> ourOutUTXOList, Long blockNumber) {

        //变更 输入状态,为已归集
        for (BTCUTXO updateUTXO : ourInUTXOList) {
            //变更状态为，已使用未推送
            this.btcUtxoBO.update(updateUTXO.getTxid(), updateUTXO.getVout(), EBTCUtxoStatus.IN_UN_PUSH.getCode());

        }

        //存储 utxo
        if (ourOutUTXOList.size() > 0) {
            this.btcUtxoBO.insertUTXOList(ourOutUTXOList);
        }

        // 修改_区块遍历信息
        SYSConfig config = sysConfigBO
                .getSYSConfig(SysConstants.CUR_BTC_BLOCK_NUMBER);
        //
        sysConfigBO.refreshSYSConfig(config.getId(),
                String.valueOf(blockNumber + 1), "code", "下次从哪个区块开始扫描");

    }

    // 时间调度任务,定期扫描——未推送的——交易
    public void pushTx() {


        List<BTCUTXO> btcutxoList = this.btcUtxoBO.selectUnPush();
        if (btcutxoList != null && btcutxoList.size() > 0) {
            // 推送出去
            try {

                String pushJsonStr = JsonUtil.Object2Json(btcutxoList);
                String url = PropertiesUtil.Config.ETH_PUSH_ADDRESS_URL;
                Properties formProperties = new Properties();
                formProperties.put("btcUtxolist", pushJsonStr);
                PostSimulater.requestPostForm(url, formProperties);

            } catch (Exception e) {
                logger.error("回调业务biz异常，原因：" + e.getMessage());
            }
        }


    }

    //确认推送
    @Override
    public synchronized void confirmPush(List<BTCUTXO> utxoList) {

        if (utxoList == null || utxoList.size() <= 0) {
            throw new BizException(
                    BizErrorCode.PUSH_STATUS_UPDATE_FAILURE.getErrorCode(),
                    "请传入正确的json数组"
                            + BizErrorCode.PUSH_STATUS_UPDATE_FAILURE
                            .getErrorCode());
        }

        for (BTCUTXO btcutxo : utxoList) {

            BTCUTXO ourBtcUtxo = this.btcUtxoBO.select(btcutxo.getTxid(), btcutxo.getVout());

            String nextStatus = null;
            if (ourBtcUtxo.getStatus().equals(EBTCUtxoStatus.OUT_UN_PUSH)) {

                nextStatus = EBTCUtxoStatus.OUT_PUSHED.getCode();

            } else if (ourBtcUtxo.getStatus().equals(EBTCUtxoStatus.IN_UN_PUSH)) {

                nextStatus = EBTCUtxoStatus.IN_PUSHED.getCode();

            }
            if (nextStatus == null) {

                logger.error("utxo 状态异常，无法对应，原因：" + "txid:" + ourBtcUtxo.getTxid() + "  " + "vout:" + ourBtcUtxo.getVout());

            } else {

                this.btcUtxoBO.update(ourBtcUtxo.getTxid(), ourBtcUtxo.getVout(), nextStatus);

            }

        }


    }

    private BTCUTXO convertOut(BTCVoutUTXO btcOut, String txid, Long blockHeight, String
            status) {

        BTCScriptPubKey scriptPubKey = btcOut.getScriptPubKey();

        BTCUTXO btcutxo = new BTCUTXO();
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
