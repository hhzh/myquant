package com.hhzh.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hhzh.common.HttpUtils;

/**
 * 合约批量下单
 *
 * @author Tony Tian
 * @version 1.0.0
 * @date 2018/7/8 12:13
 */
public class BatchOrderApi extends BaseApi{

    public void test() {
        try {
            JSONObject params = getParams();
            params.put("symbol", "xrp_usd");
            params.put("contract_type", "quarter");

            JSONArray orders_data = new JSONArray();
            JSONObject order1 = new JSONObject();
            order1.put("type", 1);
            order1.put("price", 0.494);
            order1.put("amount", 3);
            order1.put("match_price", 0);
            JSONObject order2 = new JSONObject();
            order2.put("type", 2);
            order2.put("price", 0.493);
            order2.put("amount", 5);
            order2.put("match_price", 0);
            JSONObject order3 = new JSONObject();
            order3.put("type", 1);
            order3.put("price", 0.493);
            order3.put("amount", 4);
            order3.put("match_price", 1);
            orders_data.add(order1);
            orders_data.add(order2);
            orders_data.add(order3);

            params.put("orders_data", orders_data.toJSONString());
            params.put("lever_rate", 20);
            params.put("sign", sign(params));
            HttpUtils.Response response = HttpUtils.post(getUrl("/api/v1/future_batch_trade.do"), params);
            System.out.println("合约批量下单:" + JSON.toJSONString(response));
            // 合约批量下单:{"body":"{\"result\":true,\"order_info\":[{\"order_id\":1067429493500928},{\"order_id\":1067429497957376},{\"order_id\":1067429503462400}]}","code":200}
        } catch (Exception e) {
            System.out.println("合约批量下单异常");
            e.printStackTrace();
        }
    }
}
