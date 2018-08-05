package com.hhzh.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hhzh.common.HttpUtils;

public class UserInfoApi extends BaseApi {

    public void testCross() {
        try {
            JSONObject params = getParams();
            params.put("sign", sign(params));
            HttpUtils.Response response = HttpUtils.post(getUrl("/api/v1/future_userinfo.do"), params);
            System.out.println("合约账户信息(全仓):" + JSON.toJSONString(response));
            // 合约下单:{"body":"{\"result\":true,\"order_id\":888845120785408}","code":200}
        } catch (Exception e) {
            System.out.println("合约账户信息(全仓)异常");
            e.printStackTrace();
        }
    }

    public void testFixed() {
        try {
            JSONObject params = getParams();
            params.put("sign", sign(params));
            HttpUtils.Response response = HttpUtils.post(getUrl("api/v1/future_userinfo_4fix.do"), params);
            System.out.println("合约账户信息(逐仓):" + JSON.toJSONString(response));
            // 合约账户信息:{"body":"{\"result\":true,\"info\":{\"btc\":{\"balance\":0.01171998,\"rights\":0.01171998,\"contracts\":[{\"contract_type\":\"quarter\",\"freeze\":0,\"balance\":1.702E-5,\"contract_id\":201809280000012,\"available\":0.01171998,\"profit\":-1.702E-5,\"bond\":0,\"unprofit\":0}]},\"btg\":{\"balance\":0.0413723,\"rights\":0.0413723,\"contracts\":[{\"contract_type\":\"quarter\",\"freeze\":0,\"balance\":0.00209711,\"contract_id\":201809280100061,\"available\":0.0413723,\"profit\":-0.00209711,\"bond\":0,\"unprofit\":0}]},\"etc\":{\"balance\":0.40987684,\"rights\":0.40987684,\"contracts\":[{\"contract_type\":\"this_week\",\"freeze\":0,\"balance\":0.00233346,\"contract_id\":201807060040046,\"available\":0.40987684,\"profit\":-0.00233346,\"bond\":0,\"unprofit\":0},{\"contract_type\":\"quarter\",\"freeze\":0,\"balance\":0.0016184,\"contract_id\":201809280040045,\"available\":0.40987684,\"profit\":-0.0016184,\"bond\":0,\"unprofit\":0}]},\"bch\":{\"balance\":4.0E-7,\"rights\":4.0E-7,\"contracts\":[]},\"xrp\":{\"balance\":21.79617117,\"rights\":24.2955799,\"contracts\":[{\"contract_type\":\"this_week\",\"freeze\":0,\"balance\":0,\"contract_id\":201807060150048,\"available\":24.2955,\"profit\":2.49940873,\"bond\":0,\"unprofit\":0},{\"contract_type\":\"quarter\",\"freeze\":0,\"balance\":1.09141254,\"contract_id\":201809280150047,\"available\":21.79617117,\"profit\":-1.09141254,\"bond\":0,\"unprofit\":0}]},\"eth\":{\"balance\":2.9E-7,\"rights\":2.9E-7,\"contracts\":[]},\"eos\":{\"balance\":4.4781E-4,\"rights\":4.4781E-4,\"contracts\":[{\"contract_type\":\"quarter\",\"freeze\":0,\"balance\":0,\"contract_id\":201809280200053,\"available\":4.4781E-4,\"profit\":0,\"bond\":0,\"unprofit\":0}]},\"ltc\":{\"balance\":0.19406173,\"rights\":0.19406173,\"contracts\":[{\"contract_type\":\"this_week\",\"freeze\":0,\"balance\":5.5061E-4,\"contract_id\":201807060010016,\"available\":0.19406173,\"profit\":-5.5061E-4,\"bond\":0,\"unprofit\":0},{\"contract_type\":\"quarter\",\"freeze\":0,\"balance\":0.00538766,\"contract_id\":201809280010015,\"available\":0.19406173,\"profit\":-0.00538766,\"bond\":0,\"unprofit\":0}]}}}","code":200}
        } catch (Exception e) {
            System.out.println("合约账户(逐仓)信息异常");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserInfoApi userInfoApi = new UserInfoApi();
        userInfoApi.testFixed();
    }

}
