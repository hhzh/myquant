package com.hhzh.common;

import java.io.IOException;

import com.hhzh.service.ISpotTrade;
import com.hhzh.service.impl.SpotTradeImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpException;


import com.alibaba.fastjson.JSONObject;

/**
 * 现货 REST API 客户端请求
 */
@Slf4j
public class SpotClient {

    public static void main(String[] args) throws HttpException, IOException{

        String apiKey = "";
        String secretKey = "";
        String urlPrex = "https://www.okb.com";

        /**
         * get请求无需发送身份认证,通常用于获取行情，市场深度等公共信息
         *
         */
        ISpotTrade spotGet = new SpotTradeImpl(urlPrex);

        /**
         * post请求需发送身份认证，获取用户个人相关信息时，需要指定api_key,与secret_key并与参数进行签名，
         * 此处对构造方法传入api_key与secret_key,在请求用户相关方法时则无需再传入，
         * 发送post请求之前，程序会做自动加密，生成签名。
         *
         */
        ISpotTrade spotPost = new SpotTradeImpl(urlPrex, apiKey, secretKey);

        //现货行情
        String btcUsd = spotGet.ticker("btc_usd");
        log.info(btcUsd);

        //现货市场深度
        //String depth = spotGet.depth("btc_usd");
        //
        ////现货OKCoin历史交易信息
        //String trades = spotGet.trades("btc_usd", "20");
        //
        ////现货用户信息
        //String userinfo = spotPost.userInfo();
        //
        ////现货下单交易
        //String tradeResult = spotPost.trade("btc_usd", "buy", "50", "0.02");
        //System.out.println(tradeResult);
        //JSONObject tradeJSV1 = JSONObject.parseObject(tradeResult);
        //String tradeOrderV1 = tradeJSV1.getString("order_id");
        //
        ////现货获取用户订单信息
        //spotPost.orderInfo("btc_usd", tradeOrderV1);
        //
        ////现货撤销订单
        //spotPost.cancelOrder("btc_usd", tradeOrderV1);
        //
        ////现货批量下单
        //spotPost.batchTrade("btc_usd", "buy", "[{price:50, amount:0.02},{price:50, amount:0.03}]");
        //
        ////批量获取用户订单
        //spotPost.ordersInfo("0", "btc_usd", "125420341, 125420342");
        //
        ////获取用户历史订单信息，只返回最近七天的信息
        //spotPost.orderHistory("btc_usd", "0", "1", "20");




    }
}
