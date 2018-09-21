/**
 * @Title StringUtil.java 
 * @Package com.ibis.account.common 
 * @Description 
 * @author miyb  
 * @date 2015-4-7 下午2:12:14 
 * @version V1.0   
 */
package com.cdkj.coin.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/** 
 * @author: miyb 
 * @since: 2015-4-7 下午2:12:14 
 * @history:
 */
public class StringUtil {
    public static String compressString(String... args) {
        StringBuffer str = new StringBuffer();
        for (String arg : args) {
            str.append(arg);
        }
        return str.toString();
    }

    public static List<String> arrayStringToList(String str) {
        List<String> list = new ArrayList<String>();
        if (StringUtils.isNotBlank(str) && str.length() > 2) {// []忽略
            str = str.replaceAll("\"", "");
            String[] pathArr = (str.substring(1, str.length() - 1)).split(",");
            List<String> list1 = Arrays.asList(pathArr);
            list.addAll(list1);
        }
        return list;
    }
}
