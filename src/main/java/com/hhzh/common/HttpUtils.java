package com.hhzh.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author chuping.cui
 */
public class HttpUtils {

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    /**
     * HTTP Post请求
     *
     * @param url
     * @param paramMap
     * @return
     * @throws IOException
     */
    public static Response post(String url, Map<String, ? extends Object> paramMap) throws IOException {

        FormBody.Builder formBuilder = new FormBody.Builder();

        for (Entry<String, ? extends Object> entry : paramMap.entrySet()) {
            formBuilder.add(entry.getKey(), String.valueOf(entry.getValue()));
        }

        /*ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Map<String, String> headMap = new HashMap<>();
        if (attributes != null) {
            HttpServletRequest requestTmp = attributes.getRequest();
            Enumeration<String> headerNames = requestTmp.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = requestTmp.getHeader(name);
                    headMap.put(name, values);
                }
            }
        }

        headMap.put("Referer", "http://www.okex.com");

        Headers headers = Headers.of(headMap);
        Request request = new Request.Builder().headers(headers).url(url).post(formBuilder.build()).build();*/

        Request request = new Request.Builder().header("Referer", "http://www.okex.com").url(url).post(formBuilder.build()).build();

        okhttp3.Response response = HTTP_CLIENT.newCall(request).execute();

        return new Response(response.code(), response.body().string());
    }

    /**
     * Http Get 请求
     *
     * @param url
     * @param paramMap
     * @return
     * @throws IOException
     */
    public static Response get(String url, Map<String, ? extends Object> paramMap) throws IOException {

        String queryString = buildQueryString(paramMap);

        Request request = new Request.Builder().url(url + "?" + queryString).get().build();

        okhttp3.Response response = HTTP_CLIENT.newCall(request).execute();

        return new Response(response.code(), response.body().string());
    }

    private static String buildQueryString(Map<String, ? extends Object> paramMap) {

        StringBuilder queryString = new StringBuilder();

        if (paramMap != null && !paramMap.isEmpty()) {

            for (Entry<String, ? extends Object> entry : paramMap.entrySet()) {
                queryString.append(entry).append("=").append(entry.getValue()).append("&");
            }
            queryString.setLength(queryString.length() - 1);
        }
        return queryString.toString();
    }

    @Data
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private int code;
        private String body;
    }
}
