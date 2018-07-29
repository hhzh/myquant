package com.hhzh.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hhzh.common.HttpUtils;

/**
 * 合约下单
 *
 * @author Tony Tian
 * @version 1.0.0
 * @date 2018/6/6 23:19
 */
public class OrderApi extends BaseApi {

    public void test() {
        try {
            JSONObject params = getParams();
            params.put("symbol", "xrp_usd");
            params.put("contract_type", "quarter");
            params.put("type", 1);
            params.put("price", 0.494);
            params.put("amount", 7);
            params.put("match_price", 0);
            params.put("lever_rate", 20);
            params.put("sign", sign(params));
            HttpUtils.Response response = HttpUtils.post(getUrl("/api/v1/future_trade.do"), params);
            System.out.println("合约下单:" + JSON.toJSONString(response));
            // 合约下单:{"body":"{\"result\":true,\"order_id\":888845120785408}","code":200}
        } catch (Exception e) {
            System.out.println("合约下单异常");
            e.printStackTrace();
        }
    }
}
