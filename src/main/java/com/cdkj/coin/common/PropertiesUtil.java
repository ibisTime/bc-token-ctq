package com.cdkj.coin.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties props;
    static {
        props = new Properties();
        try {
            props.load(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("config.properties"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("找不到config.properties文件", e);
        } catch (IOException e) {
            throw new RuntimeException("读取config.properties文件出错", e);
        }
    }

    public static String getProperty(String key) {
        if (props == null) {
            // throw new Exception("配置文件初始化失败");
        }
        return props.getProperty(key);
    }

    public static final class Config {

        /* 以太坊 */
        public static String ETH_PUSH_ADDRESS_URL = props
            .getProperty("ETH_PUSH_ADDRESS_URL");

        public static String ETH_URL = props.getProperty("ETH_URL");

        /* 比特币 */
        public static String BTC_PUSH_ADDRESS_URL = props
            .getProperty("BTC_PUSH_ADDRESS_URL");

        /* USDT */
        public static String USDT_PUSH_ADDRESS_URL = props
            .getProperty("USDT_PUSH_ADDRESS_URL");

        public static String BTC_URL = props.getProperty("BTC_URL");

        /* 万维币 */
        public static String WAN_PUSH_ADDRESS_URL = props
            .getProperty("WAN_PUSH_ADDRESS_URL");

        public static String WAN_URL = props.getProperty("WAN_URL");

        /* USDT */

        public static String OMNI_URL = props.getProperty("OMNI_URL");

        public static String OMNI_USERNAME = props.getProperty("OMNI_USERNAME");

        public static String OMNI_PASSWORD = props.getProperty("OMNI_PASSWORD");

        public static String USDT_ENV = props.getProperty("USDT_ENV");

    }
}
