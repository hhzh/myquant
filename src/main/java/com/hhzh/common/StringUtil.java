package com.hhzh.common;

public class StringUtil {

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        String tempStr = str.trim();
        if (tempStr.length() == 0 || "null".equals(tempStr)) {
            return true;
        }
        return false;
    }

}
