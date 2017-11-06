/**
 * @Title ECurrency.java 
 * @Package com.ibis.account.enums 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午4:41:06 
 * @version V1.0   
 */
package com.cdkj.coin.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.coin.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年12月24日 下午1:51:38 
 * @history:
 */
public enum ECurrency {
    BTC("BTC", "比特币"), ETH("ETH", "以太币");

    public static Map<String, ECurrency> getResultMap() {
        Map<String, ECurrency> map = new HashMap<String, ECurrency>();
        for (ECurrency currency : ECurrency.values()) {
            map.put(currency.getCode(), currency);
        }
        return map;
    }

    public static ECurrency getCurrency(String code) {
        Map<String, ECurrency> map = getResultMap();
        ECurrency result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的currency不存在");
        }
        return result;
    }

    ECurrency(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
