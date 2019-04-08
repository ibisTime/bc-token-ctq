/**
 * @Title HttpUtils.java 
 * @Package com.cdkj.info.util 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年4月11日 下午1:19:05 
 * @version V1.0   
 */
package com.cdkj.coin.core;

import com.cdkj.coin.exception.BizException;
import com.cdkj.coin.exception.EBizErrorCode;
import okhttp3.*;
import org.apache.log4j.Logger;

/** 
 * @author: haiqingzheng 
 * @since: 2018年4月11日 下午1:19:05 
 * @history:
 */
public class OkHttpUtils {

    private static Logger logger = Logger.getLogger(OkHttpUtils.class);

    public static final MediaType JSON = MediaType
        .parse("application/json; charset=utf-8");

    public static final MediaType WWW = MediaType
        .parse("application/x-www-form-urlencoded; charset=utf-8");

    public static int proxyPort = 1080;

    public static String proxyHost = "127.0.0.1";

    public static String doAccessHTTPPostJson(String sendUrl, String sendParam) {
        // OkHttpClient okHttpClient = new OkHttpClient.Builder()
        // .proxy(new Proxy(Proxy.Type.HTTP,
        // new InetSocketAddress(proxyHost, proxyPort)))
        // .build();

        OkHttpClient okHttpClient = new OkHttpClient();

        String requestStr = sendUrl;

        RequestBody requestBody = RequestBody.create(JSON, sendParam);

        Request request = new Request.Builder().post(requestBody)
            .url(requestStr).build();
        Call call = okHttpClient.newCall(request);
        try {

            Response response = call.execute();
            String jsonStr = response.body().string();
            return jsonStr;
        } catch (Exception e) {
            logger.error("********错误请求链接[" + sendUrl + "]********\n");
            logger.error("********错误请求参数[" + sendParam + "]********");
            throw new BizException("xn0000", "网络请求异常，原因" + e.getMessage());
        }
    }

    public static String doAccessHTTPPostWWW(String sendUrl, String sendParam) {

        // OkHttpClient okHttpClient = new OkHttpClient.Builder()
        // .proxy(new Proxy(Proxy.Type.HTTP,
        // new InetSocketAddress(proxyHost, proxyPort)))
        // .build();

        OkHttpClient okHttpClient = new OkHttpClient();

        String requestStr = sendUrl;

        RequestBody requestBody = RequestBody.create(WWW, sendParam);

        Request request = new Request.Builder().post(requestBody)
            .url(requestStr)
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build();
        Call call = okHttpClient.newCall(request);
        try {

            Response response = call.execute();
            String jsonStr = response.body().string();
            return jsonStr;
        } catch (Exception e) {
            throw new BizException("xn0000", "网络请求异常，原因" + e.getMessage());
        }
    }

    public static String doAccessHTTPGetJson(String sendUrl) {

        String requestStr = sendUrl;

        // OkHttpClient okHttpClient = new OkHttpClient.Builder()
        // .proxy(new Proxy(Proxy.Type.SOCKS,
        // new InetSocketAddress(proxyHost, proxyPort)))
        // .build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Request request = new Request.Builder().get().url(requestStr)
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .addHeader("Language", "zh-cn").build();
        Call call = okHttpClient.newCall(request);
        try {

            Response response = call.execute();
            String jsonStr = response.body().string();
            return jsonStr;
        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getErrorCode(), "网络请求异常，原因"
                    + e.getMessage());
        }
    }

    public static String doMultipartPost(String sendUrl, RequestBody requestBody) {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().post(requestBody).url(sendUrl)
            .build();
        Call call = okHttpClient.newCall(request);
        try {

            Response response = call.execute();
            String jsonStr = response.body().string();

            logger.info("请求URL=" + sendUrl);
            logger.info("返回结果=" + jsonStr);

            return jsonStr;
        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getErrorCode(), "网络请求异常，原因"
                    + e.getMessage());
        }

    }

}
