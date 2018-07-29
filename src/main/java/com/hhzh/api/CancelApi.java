package com.hhzh.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hhzh.common.HttpUtils;

/**
 * 合约撤单
 *
 * @author Tony Tian
 * @version 1.0.0
 * @date 2018/7/8 12:11
 */
public class CancelApi extends BaseApi {
    public void test() {
        try {
            JSONObject params = getParams();
            params.put("symbol", "xrp_usd");
            params.put("contract_type", "quarter");
            params.put("order_id", 888845120785408L);
            params.put("sign", sign(params));
            HttpUtils.Response response = HttpUtils.post(getUrl("/api/v1/future_cancel.do"), params);
            System.out.println("合约撤单:" + JSON.toJSONString(response));
            // 合约撤单:{"body":"{\"result\":true,\"order_id\":\"888845120785408\"}","code":200}
        } catch (Exception e) {
            System.out.println("合约撤单异常");
            e.printStackTrace();
        }
    }
}
