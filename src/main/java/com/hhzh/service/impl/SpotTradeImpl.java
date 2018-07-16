package com.hhzh.service.impl;

import com.hhzh.service.ISpotTrade;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpException;

import java.io.IOException;

/**
 * @author huazhen.he
 * @apiNote
 * @date 2018/7/16
 */
public class SpotTradeImpl implements ISpotTrade {

    private String secret_key;

    private String api_key;

    private String url_prex;

    public SpotTradeImpl(String url_prex,String api_key,String secret_key){
        this.api_key = api_key;
        this.secret_key = secret_key;
        this.url_prex = url_prex;
    }

    public SpotTradeImpl(String url_prex){
        this.url_prex = url_prex;
    }

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
        return null;
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
        return null;
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
        return null;
    }

    /**
     * 获取用户信息
     *
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String userinfo() throws HttpException, IOException {
        return null;
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
        return null;
    }

    /**
     * 批量下单
     *
     * @param symbol      btc_usd: 比特币 ltc_usd: 莱特币
     * @param type        买卖类型： 限价单（buy/sell） 市价单（buy_market/sell_market）
     * @param orders_data JSON类型的字符串 例：[{price:3,amount:5},{price:3,amount:3}]
     *                    最大下单量为5，price和amount参数参考trade接口中的说明
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String batch_trade(String symbol, String type, String orders_data) throws HttpException, IOException {
        return null;
    }

    /**
     * 撤销订单
     *
     * @param symbol   btc_usd: 比特币 ltc_usd: 莱特币
     * @param order_id 订单ID(多个订单ID中间以","分隔,一次最多允许撤消3个订单)
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String cancel_order(String symbol, String order_id) throws HttpException, IOException {
        return null;
    }

    /**
     * 获取用户的订单信息
     *
     * @param symbol   btc_usd: 比特币 ltc_usd: 莱特币
     * @param order_id 订单ID(-1查询全部订单，否则查询相应单号的订单)
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String order_info(String symbol, String order_id) throws HttpException, IOException {
        return null;
    }

    /**
     * 批量获取用户订单
     *
     * @param type     查询类型 0:未成交，未成交 1:完全成交，已撤销
     * @param symbol   btc_usd: 比特币 ltc_usd: 莱特币
     * @param order_id 订单ID(多个订单ID中间以","分隔,一次最多允许查询50个订单)
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String orders_info(String type, String symbol, String order_id) throws HttpException, IOException {
        return null;
    }

    /**
     * 获取历史订单信息，只返回最近七天的信息
     *
     * @param symbol       btc_usd: 比特币 ltc_usd: 莱特币
     * @param status       委托状态: 0：未成交 1：已完成(最近七天的数据)
     * @param current_page 当前页数
     * @param page_length  每页数据条数，最多不超过200
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String order_history(String symbol, String status, String current_page, String page_length) throws HttpException, IOException {
        return null;
    }
}
