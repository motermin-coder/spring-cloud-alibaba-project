package com.alibaba.cloud.util;

/**
 * @Auth tom
 * @Date 2023-04-07 18:22:06
 */
public class StringUtil {

    /**
     * 判断是否不为null及不为空
     * @param str
     * @return
     */
    public static boolean ifNullOrEmpty(String str){
        return str != null && !"".equals(str);
    }
}
