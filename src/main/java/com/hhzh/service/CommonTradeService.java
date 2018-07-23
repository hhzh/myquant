package com.hhzh.service;

import com.hhzh.common.HttpUtilManager;
import com.hhzh.common.MD5Util;
import com.hhzh.common.StringUtil;
import org.apache.http.HttpException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommonTradeService {

    protected String secretKey = "";
    protected String apiKey = "";
    protected String urlPrex = "https://www.okb.com";


    public CommonTradeService() {
    }

    protected String requestGet(String symbol, String url) throws HttpException, IOException {
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String param = "";
        if (!StringUtil.isEmpty(symbol)) {
            param += "symbol=" + symbol;
        }
        return httpUtil.requestHttpGet(urlPrex, url, param);
    }

    protected String requestGet(String symbol, String contractType, String url) throws HttpException, IOException {
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String param = "";
        if (!StringUtil.isEmpty(symbol)) {
            param += "symbol=" + symbol;
        }
        if (!StringUtil.isEmpty(contractType)) {
            if (!"".equals(param)) {
                param += "&";
            }
            param += "contract_type=" + contractType;

        }
        return httpUtil.requestHttpGet(urlPrex, url, param);
    }

    protected String requestPost(String url) throws HttpException, IOException {
        // 构造参数签名
        Map<String, String> params = new HashMap<>();
        params.put("apiKey", apiKey);

        String sign = MD5Util.buildMysignV1(params, this.secretKey);
        params.put("sign", sign);

        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        return httpUtil.requestHttpPost(urlPrex, url, params);
    }

    protected String requestPost(String symbol, String contractType, String url) throws HttpException, IOException {
        Map<String, String> params = new HashMap<>();
        if (!StringUtil.isEmpty(symbol)) {
            params.put("symbol", symbol);
        }
        if (!StringUtil.isEmpty(contractType)) {
            params.put("contract_type", contractType);
        }
        params.put("apiKey", apiKey);
        String sign = MD5Util.buildMysignV1(params, secretKey);
        params.put("sign", sign);
        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        return httpUtil.requestHttpPost(urlPrex, url, params);
    }

}
