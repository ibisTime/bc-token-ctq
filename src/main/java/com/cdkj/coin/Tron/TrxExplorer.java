package com.cdkj.coin.Tron;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.core.OkHttpUtils;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TrxExplorer {

    private static final String URL = PropertiesUtil.Config.TRX_URL;

    private static final String LAST_BLOCK = "/wallet/getnowblock";

    private static final String GET_TRANSACTION_LIST_BY_BLOCK = "/wallet/getblockbynum";

    private static final String GET_TRANSACTION = "/wallet/gettransactioninfobyid";

    public static Block getLastBlock() {
        String url = URL + LAST_BLOCK;
        JsonObject params = new JsonObject();
        String jsonStr = OkHttpUtils.doAccessHTTPPostJson(url,
                params.toString());
        return JSON.parseObject(jsonStr, Block.class);
    }

    public static Long getLastBlockHeight() {
        return (long)getLastBlock().getBlockHeader().getRawData().getNumber();
    }

    public static TrxTx getTransactionList(Integer number) {
        String url = URL + GET_TRANSACTION_LIST_BY_BLOCK;
        JsonObject params = new JsonObject();
        params.addProperty("num", number);
        String jsonStr = OkHttpUtils.doAccessHTTPPostJson(url,
                params.toString());
        TrxTx trxTransactions = JSON.parseObject(jsonStr, TrxTx.class);
        return trxTransactions;
    }

    public static BigDecimal getMineFee(String hash) {
        String url = URL + GET_TRANSACTION;
        JsonObject params = new JsonObject();
        params.addProperty("value", hash);
        String jsonStr = OkHttpUtils.doAccessHTTPPostJson(url,
                params.toString());
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        BigDecimal fee = jsonObject.getBigDecimal("fee");
        if (fee == null) {
            fee = BigDecimal.ZERO;
        }
        return fee;
    }

    public static void main(String[] args) {
//        Block block = getLastBlock();
//        System.out.println(block.getBlockHeader().getRawData().getNumber());
        getTransactionList(8129813);
    }
}
