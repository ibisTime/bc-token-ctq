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

        public static String ETH_PUSH_ADDRESS_URL = props
            .getProperty("ETH_PUSH_ADDRESS_URL");

        public static String ETH_URL = props.getProperty("ETH_URL");

        public static String SC_PUSH_ADDRESS_URL = props
            .getProperty("SC_PUSH_ADDRESS_URL");

        public static String SC_URL = props.getProperty("SC_URL");

        public static String BTC_PUSH_ADDRESS_URL = props
            .getProperty("BTC_PUSH_ADDRESS_URL");

        public static String BTC_URL = props.getProperty("BTC_URL");

        public static String TOKEN_URL = props.getProperty("TOKEN_URL");

        public static String TOKEN_PUSH_ADDRESS_URL = props
            .getProperty("TOKEN_PUSH_ADDRESS_URL");

        public static String CONTRACT_HOLDER_PWD = props
            .getProperty("CONTRACT_HOLDER_PWD");

        public static String CONTRACT_HOLDER_KEYSTORE = props
            .getProperty("CONTRACT_HOLDER_KEYSTORE");

        public static String KEY_STORE_PATH = props
            .getProperty("KEY_STORE_PATH");

    }
}
