/**
 * @Title HttpUtil.java 
 * @Package com.cdkj.ylq.http 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年9月27日 下午4:01:30 
 * @version V1.0   
 */
package com.cdkj.coin.http;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/** 
 * @author: haiqingzheng 
 * @since: 2017年9月27日 下午4:01:30 
 * @history:
 */
public class HttpUtil {

    public static String requestGetGzip(String urlString, byte[] requestData,
            Properties requestProperties) throws Exception {
        String responseData = null;

        HttpURLConnection con = null;

        try {
            URL url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();

            if ((requestProperties != null) && (requestProperties.size() > 0)) {
                for (Map.Entry<Object, Object> entry : requestProperties
                    .entrySet()) {
                    String key = String.valueOf(entry.getKey());
                    String value = String.valueOf(entry.getValue());
                    con.setRequestProperty(key, value);
                }
            }
            con.setConnectTimeout(10000);
            con.setRequestMethod("GET"); // 置为GET方法
            con.setDoInput(true); // 开启输入流
            con.setDoOutput(true); // 开启输出流
            // con.setUseCaches(false); // 不使用缓存
            // logger.debug("打开连接:" + url);
            con.connect();
            // 如果请求数据不为空，输出该数据。
            if (requestData != null) {
                DataOutputStream out = new DataOutputStream(
                    con.getOutputStream());
                out.write(requestData);
                out.flush();
                out.close();
            }
            InputStream bStream = con.getInputStream();
            ByteArrayOutputStream bOutStream = new ByteArrayOutputStream();
            GZIPInputStream gis = new GZIPInputStream(bStream);
            byte[] buffer = new byte[1];
            int len;

            while ((len = gis.read(buffer)) != -1) {
                bOutStream.write(buffer, 0, len);
            }
            bOutStream.close();
            gis.close();
            responseData = new String(bOutStream.toByteArray());
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                con.disconnect();
                con = null;
            }
        }

        return responseData;
    }

    public static String requestGet(String urlString, byte[] requestData,
            Properties requestProperties) throws Exception {
        String responseData = null;

        HttpURLConnection con = null;

        try {
            URL url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();

            if ((requestProperties != null) && (requestProperties.size() > 0)) {
                for (Map.Entry<Object, Object> entry : requestProperties
                    .entrySet()) {
                    String key = String.valueOf(entry.getKey());
                    String value = String.valueOf(entry.getValue());
                    con.setRequestProperty(key, value);
                }
            }
            con.setConnectTimeout(10000);
            con.setRequestMethod("GET"); // 置为GET方法
            con.setDoInput(true); // 开启输入流
            con.setDoOutput(true); // 开启输出流
            // con.setUseCaches(false); // 不使用缓存
            // logger.debug("打开连接:" + url);
            con.connect();
            // 如果请求数据不为空，输出该数据。
            if (requestData != null) {
                DataOutputStream out = new DataOutputStream(
                    con.getOutputStream());
                out.write(requestData);
                out.flush();
                out.close();
            }
            StringBuffer resposne = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
            String responseMessage = "";
            while ((responseMessage = reader.readLine()) != null) {
                resposne.append(responseMessage);
            }
            responseData = resposne.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                con.disconnect();
                con = null;
            }
        }

        return responseData;
    }

    public static String doAccessHTTPPostJson(String sendUrl,
            RequestBody formBody) {

        String jsonStr = null;

        String requestStr = sendUrl;

        OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(1,
            TimeUnit.DAYS).build();

        Request request = new Request.Builder()
            .addHeader("User-Agent", "Sia-Agent").url(requestStr)
            .post(formBody).build();
        Call call = okHttpClient.newCall(request);
        try {

            Response response = call.execute();
            jsonStr = response.body().string();
            // System.out.println("请求URL：" + sendUrl);
            // System.out.println("返回结果：" + jsonStr);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jsonStr;
    }

    public static String doAccessHTTPGetJson(String sendUrl) {
        // System.out.println("请求URL：" + sendUrl);
        String jsonStr = null;

        String requestStr = sendUrl;

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().get()
            .addHeader("User-Agent", "Sia-Agent").url(requestStr).build();
        Call call = okHttpClient.newCall(request);
        try {

            Response response = call.execute();
            jsonStr = response.body().string();

            // System.out.println("返回结果：" + jsonStr);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jsonStr;
    }
}
