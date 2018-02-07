package com.cdkj.coin.ao.impl;

import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.domain.BTC.BTCOriginalTx;
import com.cdkj.coin.domain.BTC.BTCTXs;
import com.cdkj.coin.exception.BizException;

@Service
public class BTCBlockDataService {

    private static OkHttpClient okHttpClient = new OkHttpClient();

    public BTCOriginalTx queryTxHash(String txid) {

        try {

            String txURL = this.baseApiURL() + "/tx/" + txid;
            String jsonStr = this.get(txURL);
            if (jsonStr == null) {
                return null;
            }
            return com.alibaba.fastjson.JSON.parseObject(jsonStr,
                BTCOriginalTx.class);

        } catch (Exception e) {

            throw new BizException("xn00", "拉取数据失败");

        }

    }

    /**
     * 返回
     *
     * @param blockHeight
     * @param pageNum
     * @return
     */
    public BTCTXs getBlockTxs(Long blockHeight, Integer pageNum) {

        String jsonStr = this.get(this.blockTxsUrl(blockHeight, pageNum));
        if (jsonStr == null) {
            return null;
        }
        BTCTXs btctXs = JSON.parseObject(jsonStr, BTCTXs.class);
        return btctXs;

    }

    private String blockTxsUrl(Long blockHeight, Integer pageNum) {

        String blockHassh = this.blockHash(blockHeight);
        return this.baseApiURL() + "/txs/?block=" + blockHassh + "&pageNum="
                + pageNum;

    }

    private String blockHash(Long blockHeight) {

        String requestUrl = this.baseApiURL() + "/block-index/" + blockHeight;
        String jsonStr = this.get(requestUrl);
        Map map = JSONObject.parseObject(jsonStr, Map.class);
        return (String) map.get("blockHash");

    }

    private String get(String url) throws BizException {

        // 200 ok
        // 404 如果没有
        Request req = new Request.Builder().get().url(url).build();
        try {

            Call call = okHttpClient.newCall(req);
            Response response = call.execute();

            if (response.code() == 200) {

                return response.body().string();

            } else if (response.code() == 404) {

                return null;

            } else {

                throw new BizException("xn000", "拉取数据失败");

            }

        } catch (Exception e) {

            throw new BizException("xn000", "拉取数据失败");

        }

    }

    private String baseApiURL() {

        return PropertiesUtil.Config.BTC_URL;

    }

}
