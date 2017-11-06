/**
 * @Title ParityClient.java 
 * @Package ethereum 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月18日 下午7:42:57 
 * @version V1.0   
 */
package com.cdkj.coin.eth;

import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;

import com.cdkj.coin.common.PropertiesUtil;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月18日 下午7:42:57 
 * @history:
 */
public class ParityClient {

    private static String ETH_URL = PropertiesUtil.Config.ETH_URL;

    private ParityClient() {
    }

    private static class ClientHolder {
        private static final Parity parity = Parity.build(new HttpService(
            ETH_URL));
    }

    public static final Parity getParity() {
        return ClientHolder.parity;
    }
}
