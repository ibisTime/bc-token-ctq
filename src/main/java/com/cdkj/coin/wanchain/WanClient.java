/**
 * @Title Web3JClient.java
 * @Package ethereum
 * @Description
 * @author leo(haiqing)
 * @date 2017年10月18日 下午7:37:41
 * @version V1.0
 */
package com.cdkj.coin.wanchain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.cdkj.coin.common.PropertiesUtil;

/**
 * @author: haiqingzheng
 * @since: 2017年10月18日 下午7:37:41
 * @history:
 */
public class WanClient {

    static final Logger logger = LoggerFactory.getLogger(WanClient.class);

    private static String WAN_URL = PropertiesUtil.Config.WAN_URL;

    private WanClient() {
    }

    private volatile static Web3j web3j;

    public static Web3j getClient() {
        synchronized (WanClient.class) {
            if (web3j == null) {
                web3j = Web3j.build(new HttpService(WAN_URL));
            }
        }
        return web3j;
    }

    public static void main(String[] args) {
        System.out.println(
            "0xBe43E00690b7eB85E540Ae376AFd9aab4bDEe5cF".toLowerCase());
    }

}
