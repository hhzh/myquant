package com.hhzh.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hhzh.common.HttpUtils;

/**
 * 用户持仓获取OKEX合约账户信息 （全仓）
 *
 * @author Tony Tian
 * @version 1.0.0
 * @date 2018/5/22 19:43
 */
public class FuturePositionApi extends BaseApi {

    public void test1() {
        try {
            JSONObject params = getParams();
            params.put("symbol", "btc_eos");
            params.put("contract_type", "quarter");
            params.put("sign", sign(params));
            HttpUtils.Response response = HttpUtils.post(getUrl("api/v1/future_position.do"), params);
            System.out.println("全仓持仓信息:" + JSON.toJSONString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
