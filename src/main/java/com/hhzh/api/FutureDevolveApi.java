package com.hhzh.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hhzh.common.HttpUtils;

/**
 * 币币、合约，资金划转
 *
 * @author Tony Tian
 * @version 1.0.0
 * @date 2018/5/22 10:52
 */
public class FutureDevolveApi extends BaseApi {

    /**
     * type : 1：币币转合约 2：合约转币币
     */
    public void test1() {
        try {
            JSONObject params = getParams();
            params.put("symbol", "xrp_usd");
            params.put("type", 1);
            params.put("amount", 1.0);
            params.put("sign", sign(params));
            HttpUtils.Response response = HttpUtils.post(getUrl("api/v1/future_devolve.do"), params);
            System.out.println("币币转合约:" + JSON.toJSONString(response));
        } catch (Exception e) {
            System.out.println("币币转合约异常");
            e.printStackTrace();
        }
    }

    public void test2() {
        try {
            JSONObject params = getParams();
            params.put("symbol", "xrp_usd");
            params.put("type", 2);
            params.put("amount", 1.0);
            params.put("sign", sign(params));
            HttpUtils.Response response = HttpUtils.post(getUrl("api/v1/future_devolve.do"), params);
            System.out.println("合约转币币结果:" + JSON.toJSONString(response));
        } catch (Exception e) {
            System.out.println("合约转币币异常");
            e.printStackTrace();
        }
    }
}
