package com.cdkj.coin.omni;

import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdkj.coin.common.PropertiesUtil;
import com.cdkj.coin.common.StringUtil;
import com.cdkj.coin.exception.BizException;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class UsdtClient {

    private static String OMNI_URL = PropertiesUtil.Config.OMNI_URL;

    private static String OMNI_USERNAME = PropertiesUtil.Config.OMNI_USERNAME;

    private static String OMNI_PASSWORD = PropertiesUtil.Config.OMNI_PASSWORD;

    private final static String OMNI_LIST_BLOCK_TRANSACTONIS = "omni_listblocktransactions";

    private final static String METHOD_GET_TRANSACTION = "omni_gettransaction";

    private final static String METHOD_GET_BALANCE = "omni_getbalance";

    private final static String METHOD_GET_INFO = "omni_getinfo";

    private final static int USDt_PROPERTYID = 31;

    // private final static boolean ENV = false;

    /*
     * public OmniClient getClient() { NetworkParameters params = null; if (ENV)
     * { params = MainNetParams.get(); } else { params = TestNet3Params.get(); }
     * try { URI server = new URI(OMNI_URL); return new OmniClient(params,
     * server, OMNI_NAME, OMNI_PWD); } catch (Exception e) { throw new
     * BizException("获取rpcClient异常，原因：" + e.getMessage()); } }
     */
    /*
     * public List<String> getOmniHashListByBlock(int blockNum) {
     * List<Sha256Hash> shaHashList = new ArrayList<Sha256Hash>(); try {
     * shaHashList = getClient().omniListBlockTransactions(blockNum); } catch
     * (Exception e) { throw new BizException("获取区块hash列表异常，原因：" +
     * e.getMessage()); } List<String> hashList = new ArrayList<String>(); for
     * (Sha256Hash sha256Hash : shaHashList) {
     * hashList.add(sha256Hash.toString()); } return hashList; }
     */

    /*
     * public BigDecimal getUstdBlanceByAddress(String address) {
     * getClient().omniGetBalance(); }
     */

    public static List<String> getOmniHashListByBlock(int blockNum) {
        Object[] objParam = new Object[] { blockNum };
        String result = doSend(OMNI_LIST_BLOCK_TRANSACTONIS, objParam);
        List<String> strsToList = StringUtil.arrayStringToList(result);
        return strsToList;
    }

    public static OmniTransaction getOmniTransInfoByTxid(String txid) {
        Object[] objParam = new Object[] { txid };
        String result = doSend(METHOD_GET_TRANSACTION, objParam);
        OmniTransaction omniTransaction = JSON.parseObject(result,
            OmniTransaction.class);
        return omniTransaction;
    }

    public static BigDecimal getUstdBlanceByAddress(String address) {
        Object[] objParam = new Object[] { address, USDt_PROPERTYID };
        String result = doSend(METHOD_GET_BALANCE, objParam);
        BigDecimal balance = JSONObject.parseObject(result).getBigDecimal(
            "balance");
        return balance;
    }

    public static Long getBlockHeight() {
        Object[] objParam = new Object[] {};
        String result = doSend(METHOD_GET_INFO, objParam);
        Long blockHeight = JSONObject.parseObject(result).getLongValue("block");
        return blockHeight;
    }

    public static String doSend(String method, Object[] objParam) {
        String result = null; // 身份认证
        Base64 base64 = new Base64();
        String auth = OMNI_USERNAME + ":" + OMNI_PASSWORD;
        byte[] textByte = auth.getBytes();
        String cred = base64.encodeToString(textByte);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "Basic " + cred);
        try {
            JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(OMNI_URL),
                headers);
            result = JSON.toJSONString(client.invoke(method, objParam,
                Object.class));

        } catch (Throwable e) {
            throw new BizException("调用usdt rpc接口异常,原因:" + e.getMessage());
        }
        return result;
    }

    // public static void main(String[] args) {
    // System.out.print(getBlockHeight());
    // }
}
