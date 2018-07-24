package com.hhzh.service.impl;

import com.hhzh.common.HttpUtilManager;
import com.hhzh.common.MD5Util;
import com.hhzh.common.StringUtil;
import com.hhzh.service.CommonTradeService;
import com.hhzh.service.ISpotTrade;
import org.apache.http.HttpException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@Service
public class SpotTradeImpl extends CommonTradeService implements ISpotTrade {

    /**
     * 现货行情URL
     */
    private final String TICKER_URL = "/api/v1/ticker.do?";

    /**
     * 现货市场深度URL
     */
    private final String DEPTH_URL = "/api/v1/depth.do?";

    /**
     * 现货历史交易信息URL
     */
    private final String TRADES_URL = "/api/v1/trades.do?";

    /**
     * 现货获取用户信息URL
     */
    private final String USERINFO_URL = "/api/v1/userinfo.do?";

    /**
     * 现货 下单交易URL
     */
    private final String TRADE_URL = "/api/v1/trade.do?";

    /**
     * 现货 批量下单URL
     */
    private final String BATCH_TRADE_URL = "/api/v1/batch_trade.do";

    /**
     * 现货 撤销订单URL
     */
    private final String CANCEL_ORDER_URL = "/api/v1/cancel_order.do";

    /**
     * 现货 获取用户订单URL
     */
    private final String ORDER_INFO_URL = "/api/v1/order_info.do";

    /**
     * 现货 批量获取用户订单URL
     */
    private final String ORDERS_INFO_URL = "/api/v1/orders_info.do";

    /**
     * 现货 获取历史订单信息，只返回最近七天的信息URL
     */
    private final String ORDER_HISTORY_URL = "/api/v1/order_history.do";

    /**
     * 行情
     *
     * @param symbol btc_usd:比特币    ltc_usd :莱特币
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String ticker(String symbol) throws HttpException, IOException {
        return this.requestGet(symbol, TICKER_URL);
    }

    /**
     * 市场深度
     *
     * @param symbol btc_usd:比特币    ltc_usd :莱特币
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String depth(String symbol) throws HttpException, IOException {
        return this.requestGet(symbol, DEPTH_URL);
    }

    /**
     * 现货历史交易信息
     *
     * @param symbol btc_usd:比特币    ltc_usd :莱特币
     * @param since  不加since参数时，返回最近的60笔交易
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String trades(String symbol, String since) throws HttpException, IOException {
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String param = "";
        if (!StringUtil.isEmpty(symbol)) {
            param += "symbol=" + symbol;
        }
        if (!StringUtil.isEmpty(since)) {
            if (!"".equals(param)) {
                param += "&";
            }
            param += "since=" + since;
        }
        return httpUtil.requestHttpGet(urlPrex, this.TRADES_URL, param);
    }

    /**
     * 获取用户信息
     */
    @Override
    public String userInfo() throws HttpException, IOException {
        return this.requestPost(USERINFO_URL);
    }

    /**
     * 下单交易
     *
     * @param symbol btc_usd: 比特币 ltc_usd: 莱特币
     * @param type   买卖类型： 限价单（buy/sell） 市价单（buy_market/sell_market）
     * @param price  下单价格 [限价买单(必填)： 大于等于0，小于等于1000000 |
     *               市价买单(必填)： BTC :最少买入0.01个BTC 的金额(金额>0.01*卖一价) / LTC :最少买入0.1个LTC 的金额(金额>0.1*卖一价)]
     * @param amount 交易数量 [限价卖单（必填）：BTC 数量大于等于0.01 / LTC 数量大于等于0.1 |
     *               市价卖单（必填）： BTC :最少卖出数量大于等于0.01 / LTC :最少卖出数量大于等于0.1]
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String trade(String symbol, String type, String price, String amount) throws HttpException, IOException {
        // 构造参数签名
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key", api_key);
        if (!StringUtil.isEmpty(symbol)) {
            params.put("symbol", symbol);
        }
        if (!StringUtil.isEmpty(type)) {
            params.put("type", type);
        }
        if (!StringUtil.isEmpty(price)) {
            params.put("price", price);
        }
        if (!StringUtil.isEmpty(amount)) {
            params.put("amount", amount);
        }
        String sign = MD5Util.buildMysignV1(params, this.secretKey);
        params.put("sign", sign);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpPost(urlPrex, this.TRADE_URL,
                params);

        return result;
    }

    /**
     * 批量下单
     *
     * @param symbol     btc_usd: 比特币 ltc_usd: 莱特币
     * @param type       买卖类型： 限价单（buy/sell） 市价单（buy_market/sell_market）
     * @param ordersData JSON类型的字符串 例：[{price:3,amount:5},{price:3,amount:3}]
     *                   最大下单量为5，price和amount参数参考trade接口中的说明
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String batchTrade(String symbol, String type, String ordersData) throws HttpException, IOException {
        // 构造参数签名
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key", api_key);
        if (!StringUtil.isEmpty(symbol)) {
            params.put("symbol", symbol);
        }
        if (!StringUtil.isEmpty(type)) {
            params.put("type", type);
        }
        if (!StringUtil.isEmpty(ordersData)) {
            params.put("orders_data", ordersData);
        }
        String sign = MD5Util.buildMysignV1(params, this.secretKey);
        params.put("sign", sign);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpPost(urlPrex, this.BATCH_TRADE_URL,
                params);

        return result;
    }

    /**
     * 撤销订单
     *
     * @param symbol  btc_usd: 比特币 ltc_usd: 莱特币
     * @param orderId 订单ID(多个订单ID中间以","分隔,一次最多允许撤消3个订单)
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String cancelOrder(String symbol, String orderId) throws HttpException, IOException {
        // 构造参数签名
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key", api_key);
        if (!StringUtil.isEmpty(symbol)) {
            params.put("symbol", symbol);
        }
        if (!StringUtil.isEmpty(orderId)) {
            params.put("order_id", orderId);
        }

        String sign = MD5Util.buildMysignV1(params, this.secretKey);
        params.put("sign", sign);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpPost(urlPrex, this.CANCEL_ORDER_URL,
                params);

        return result;
    }

    /**
     * 获取用户的订单信息
     *
     * @param symbol  btc_usd: 比特币 ltc_usd: 莱特币
     * @param orderId 订单ID(-1查询全部订单，否则查询相应单号的订单)
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String orderInfo(String symbol, String orderId) throws HttpException, IOException {
        // 构造参数签名
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key", api_key);
        if (!StringUtil.isEmpty(symbol)) {
            params.put("symbol", symbol);
        }
        if (!StringUtil.isEmpty(orderId)) {
            params.put("order_id", orderId);
        }

        String sign = MD5Util.buildMysignV1(params, this.secretKey);
        params.put("sign", sign);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpPost(urlPrex, this.ORDER_INFO_URL,
                params);

        return result;
    }

    /**
     * 批量获取用户订单
     *
     * @param type    查询类型 0:未成交，未成交 1:完全成交，已撤销
     * @param symbol  btc_usd: 比特币 ltc_usd: 莱特币
     * @param orderId 订单ID(多个订单ID中间以","分隔,一次最多允许查询50个订单)
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String ordersInfo(String type, String symbol, String orderId) throws HttpException, IOException {
        // 构造参数签名
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key", api_key);
        if (!StringUtil.isEmpty(type)) {
            params.put("type", type);
        }
        if (!StringUtil.isEmpty(symbol)) {
            params.put("symbol", symbol);
        }
        if (!StringUtil.isEmpty(orderId)) {
            params.put("order_id", orderId);
        }

        String sign = MD5Util.buildMysignV1(params, this.secretKey);
        params.put("sign", sign);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpPost(urlPrex, this.ORDERS_INFO_URL,
                params);

        return result;
    }

    /**
     * 获取历史订单信息，只返回最近七天的信息
     *
     * @param symbol      btc_usd: 比特币 ltc_usd: 莱特币
     * @param status      委托状态: 0：未成交 1：已完成(最近七天的数据)
     * @param currentPage 当前页数
     * @param pageLength  每页数据条数，最多不超过200
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String orderHistory(String symbol, String status,
                               String currentPage, String pageLength) throws HttpException, IOException {
        // 构造参数签名
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key", api_key);
        if (!StringUtil.isEmpty(symbol)) {
            params.put("symbol", symbol);
        }
        if (!StringUtil.isEmpty(status)) {
            params.put("status", status);
        }
        if (!StringUtil.isEmpty(currentPage)) {
            params.put("current_page", currentPage);
        }
        if (!StringUtil.isEmpty(pageLength)) {
            params.put("page_length", pageLength);
        }

        String sign = MD5Util.buildMysignV1(params, this.secretKey);
        params.put("sign", sign);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpPost(urlPrex, this.ORDER_HISTORY_URL,
                params);

        return result;
    }

}
