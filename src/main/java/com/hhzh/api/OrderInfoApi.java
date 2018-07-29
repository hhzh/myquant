package com.hhzh.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hhzh.common.HttpUtils;

/**
 * 获取合约订单信息
 *
 * @author Tony Tian
 * @version 1.0.0
 * @date 2018/7/3 22:36
 */
public class OrderInfoApi extends BaseApi {

    public void testOrderInfo(){
        try {
            JSONObject params = getParams();
            params.put("symbol", "xrp_usd");
            params.put("contract_type", "quarter");
            params.put("status", 2);
            params.put("order_id", 1004839752512512L);
            params.put("current_page", 1);
            params.put("page_length", 50);
            params.put("sign", sign(params));
            HttpUtils.Response response = HttpUtils.post(getUrl("/api/v1/future_order_info.do"), params);
            System.out.println("合约订单信息:" + JSON.toJSONString(response));
            // 合约订单信息:{"body":"{\"result\":true,\"orders\":[{\"symbol\":\"xrp_usd\",\"lever_rate\":10,\"amount\":1,\"fee\":-0.01098901,\"contract_name\":\"XRP0629\",\"unit_amount\":10,\"type\":3,\"price_avg\":0.455,\"deal_amount\":1,\"price\":0.443,\"create_date\":1530068637000,\"order_id\":1004839752512512,\"status\":2}]}","code":200}
        } catch (Exception e) {
            System.out.println("合约订单信息异常");
            e.printStackTrace();
        }
    }
}
