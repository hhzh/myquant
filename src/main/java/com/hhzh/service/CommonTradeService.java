package com.hhzh.service;

import com.alibaba.fastjson.JSONObject;
import com.hhzh.api.BaseApi;
import com.hhzh.common.HttpUtilManager;
import com.hhzh.common.HttpUtils;
import com.hhzh.common.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpException;

import java.io.IOException;

@Slf4j
public class CommonTradeService extends BaseApi {

    protected String secretKey = "";
    protected String api_key = "";
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

    protected String requestPost(String url) {
        try {
            JSONObject params = getParams();
            params.put("sign", sign(params));
            HttpUtils.Response response = HttpUtils.post(getUrl(url), params);
            return JSONObject.toJSONString(response);
        } catch (Exception e) {
            log.error("No params post error,url:{}", url);
        }
        return null;
    }

    protected String requestPost(String symbol, String contractType, String url) {
        try {
            JSONObject params = getParams();
            params.put("symbol", contractType);
            params.put("contract_type", contractType);
            params.put("sign", sign(params));
            HttpUtils.Response response = HttpUtils.post(getUrl(url), params);
            return JSONObject.toJSONString(response);
        } catch (Exception e) {
            log.error("Have params post error,symbol:{},contractType:{},url:{}",symbol,contractType,url);
        }
        return null;
    }

}
