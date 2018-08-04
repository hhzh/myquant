package com.hhzh.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

public class BaseApi {

    protected static final String apiKey = "";
    protected static final String secretKey = "";

    protected static final String url = "https://www.okb.com/";

    private static final String MD5 = "MD5";
    private static final String UTF_8 = "UTF-8";

    public static String getUrl(String requestPath) {
        return url + requestPath;
    }

    public JSONObject getParams() {
        JSONObject params = new JSONObject();
        params.put("api_key", apiKey);
        return params;
    }

    public String sign(JSONObject params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        TreeMap<String, String> map = new TreeMap<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            map.put(entry.getKey(), entry.getValue().toString());
        }
        StringBuilder su = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            su.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        su.append("secret_key").append("=").append(secretKey);
        String sign = md5For32(su.toString());
        return sign == null ? null : sign.toUpperCase();
    }

    public String md5For32(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        MessageDigest md5 = MessageDigest.getInstance(MD5);
        md5.update((str).getBytes(UTF_8));
        byte b[] = md5.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }
}
