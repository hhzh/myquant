package com.hhzh.service.impl;

import com.hhzh.common.HttpUtilManager;
import com.hhzh.common.MD5Util;
import com.hhzh.common.StringUtil;
import com.hhzh.service.IFutureTrade;
import org.apache.http.HttpException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FutureTradeImpl implements IFutureTrade {

    private String secretKey;

    private String apiKey;

    private String urlPrex;

    public FutureTradeImpl(String urlPrex, String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.urlPrex = urlPrex;
    }

    public FutureTradeImpl(String urlPrex) {
        this.urlPrex = urlPrex;

    }

    /**
     * 期货行情URL
     */
    private final String FUTURE_TICKER_URL = "/api/v1/futureTicker.do";
    /**
     * 期货指数查询URL
     */
    private final String FUTURE_INDEX_URL = "/api/v1/futureIndex.do";

    /**
     * 期货交易记录查询URL
     */
    private final String FUTURE_TRADES_URL = "/api/v1/futureTrades.do";

    /**
     * 期货市场深度查询URL
     */
    private final String FUTURE_DEPTH_URL = "/api/v1/futureDepth.do";
    /**
     * 美元-人民币汇率查询URL
     */
    private final String FUTURE_EXCHANGE_RATE_URL = "/api/v1/exchangeRate.do";

    /**
     * 期货取消订单URL
     */
    private final String FUTURE_CANCEL_URL = "/api/v1/futureCancel.do";

    /**
     * 期货下单URL
     */
    private final String FUTURE_TRADE_URL = "/api/v1/futureTrade.do";

    /**
     * 期货账户信息URL
     */
    private final String FUTURE_USERINFO_URL = "/api/v1/futureUserinfo.do";

    /**
     * 逐仓期货账户信息URL
     */
    private final String FUTURE_USERINFO_4FIX_URL = "/api/v1/futureUserinfo4fix.do";

    /**
     * 期货持仓查询URL
     */
    private final String FUTURE_POSITION_URL = "/api/v1/futurePosition.do";

    /**
     * 期货逐仓持仓查询URL
     */
    private final String FUTURE_POSITION_4FIX_URL = "/api/v1/futurePosition4fix.do";

    /**
     * 用户期货订单信息查询URL
     */
    private final String FUTURE_ORDER_INFO_URL = "/api/v1/futureOrderInfo.do";


    /**
     * 期货行情
     *
     * @param symbol       btc_usd:比特币    ltc_usd :莱特币
     * @param contractType 合约类型: this_week:当周   next_week:下周   month:当月   quarter:季度
     * @return
     * @throws HttpException
     * @throws IOException
     */
    @Override
    public String futureTicker(String symbol, String contractType) throws HttpException, IOException {
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String param = "";
        if (!StringUtil.isEmpty(symbol)) {
            if (!param.equals("")) {
                param += "&";
            }
            param += "symbol=" + symbol;
        }
        if (!StringUtil.isEmpty(contractType)) {
            if (!param.equals("")) {
                param += "&";
            }
            param += "contract_type=" + contractType;

        }
        String result = httpUtil.requestHttpGet(urlPrex, FUTURE_TICKER_URL, param);
        // System.out.println(result);
        return result;
    }

    /**
     * 期货指数
     *
     * @param symbol btc_usd:比特币    ltc_usd :莱特币
     * @return
     * @throws HttpException
     * @throws IOException
     */
    @Override
    public String futureIndex(String symbol) throws HttpException, IOException {
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String param = "";
        if (!StringUtil.isEmpty(symbol)) {
            if (!param.equals("")) {
                param += "&";
            }
            param += "symbol=" + symbol;
        }
        String result = httpUtil.requestHttpGet(urlPrex, FUTURE_INDEX_URL, param);
        // System.out.println(result);
        return result;
    }

    /**
     * 期货交易记录
     *
     * @param symbol       btc_usd:比特币    ltc_usd :莱特币
     * @param contractType 合约类型: this_week:当周   next_week:下周   month:当月   quarter:季度
     * @return
     * @throws HttpException
     * @throws IOException
     */
    @Override
    public String futureTrades(String symbol, String contractType) throws HttpException, IOException {
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String param = "";
        if (!StringUtil.isEmpty(symbol)) {
            if (!param.equals("")) {
                param += "&";
            }
            param += "symbol=" + symbol;
        }
        if (!StringUtil.isEmpty(contractType)) {
            if (!param.equals("")) {
                param += "&";
            }
            param += "contract_type=" + contractType;

        }
        String result = httpUtil.requestHttpGet(urlPrex, FUTURE_TRADES_URL, param);
        // System.out.println(result);
        return result;
    }

    /**
     * 期货深度
     *
     * @param symbol       btc_usd:比特币    ltc_usd :莱特币
     * @param contractType 合约类型: this_week:当周   next_week:下周   month:当月   quarter:季度
     * @return
     * @throws HttpException
     * @throws IOException
     */
    @Override
    public String futureDepth(String symbol, String contractType) throws HttpException, IOException {
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String param = "";
        if (!StringUtil.isEmpty(symbol)) {
            if (!param.equals("")) {
                param += "&";
            }
            param += "symbol=" + symbol;
        }
        if (!StringUtil.isEmpty(contractType)) {
            if (!param.equals("")) {
                param += "&";
            }
            param += "contract_type=" + contractType;

        }
        String result = httpUtil.requestHttpGet(urlPrex, FUTURE_DEPTH_URL, param);
        // System.out.println(result);
        return result;
    }

    /**
     * 汇率查询
     *
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String exchangeRate() throws HttpException, IOException {
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpGet(urlPrex, FUTURE_EXCHANGE_RATE_URL, null);
        // System.out.println(result);
        return result;
    }

    /**
     * 取消订单
     *
     * @param symbol       btc_usd:比特币    ltc_usd :莱特币
     * @param contractType 合约类型: this_week:当周   next_week:下周   month:当月   quarter:季度
     * @param orderId      订单ID
     * @return
     * @throws HttpException
     * @throws IOException
     */
    @Override
    public String futureCancel(String symbol, String contractType, String orderId) throws HttpException, IOException {
        // 构造参数签名
        Map<String, String> params = new HashMap<String, String>();
        if (!StringUtil.isEmpty(contractType)) {
            params.put("contract_type", contractType);
        }
        if (!StringUtil.isEmpty(orderId)) {
            params.put("order_id", orderId);
        }
        if (!StringUtil.isEmpty(apiKey)) {
            params.put("apiKey", apiKey);
        }
        if (!StringUtil.isEmpty(symbol)) {
            params.put("symbol", symbol);
        }
        String sign = MD5Util.buildMysignV1(params, secretKey);

        params.put("sign", sign);
        // 发送post请求

        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        return httpUtil.requestHttpPost(urlPrex, FUTURE_CANCEL_URL, params);

    }

    /**
     * 期货下单
     *
     * @param symbol       btc_usd:比特币    ltc_usd :莱特币
     * @param contractType 合约类型: this_week:当周   next_week:下周   month:当月   quarter:季度
     * @param price        价格
     * @param amount       委托数量
     * @param type         1:开多   2:开空   3:平多   4:平空
     * @param matchPrice   是否为对手价 0:不是    1:是   ,当取值为1时,price无效
     * @return
     * @throws HttpException
     * @throws IOException
     */
    @Override
    public String futureTrade(String symbol, String contractType, String price, String amount, String type, String matchPrice) throws HttpException, IOException {
        // 构造参数签名
        Map<String, String> params = new HashMap<String, String>();
        if (!StringUtil.isEmpty(symbol)) {
            params.put("symbol", symbol);
        }
        if (!StringUtil.isEmpty(contractType)) {
            params.put("contract_type", contractType);
        }
        if (!StringUtil.isEmpty(apiKey)) {
            params.put("apiKey", apiKey);
        }
        if (!StringUtil.isEmpty(price)) {
            params.put("price", price);
        }
        if (!StringUtil.isEmpty(amount)) {
            params.put("amount", amount);
        }
        if (!StringUtil.isEmpty(type)) {
            params.put("type", type);
        }
        if (!StringUtil.isEmpty(matchPrice)) {
            params.put("match_price", matchPrice);
        }
        String sign = MD5Util.buildMysignV1(params, secretKey);
        params.put("sign", sign);
        // 发送post请求

        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        return httpUtil.requestHttpPost(urlPrex, this.FUTURE_TRADE_URL, params);
    }

    /**
     * 期货账户信息
     *
     * @return
     * @throws IOException
     * @throws HttpException
     */
    @Override
    public String futureUserinfo() throws HttpException, IOException {
        // 构造参数签名
        Map<String, String> params = new HashMap<String, String>();

        params.put("apiKey", apiKey);

        String sign = MD5Util.buildMysignV1(params, this.secretKey);
        params.put("sign", sign);


        // 发送post请求

        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        return httpUtil.requestHttpPost(urlPrex, this.FUTURE_USERINFO_URL, params);
    }

    /**
     * 期货逐仓账户信息
     *
     * @return
     * @throws HttpException
     * @throws IOException
     */
    @Override
    public String futureUserinfo4fix() throws HttpException, IOException {
        // 构造参数签名
        Map<String, String> params = new HashMap<String, String>();
        params.put("apiKey", apiKey);

        String sign = MD5Util.buildMysignV1(params, this.secretKey);
        params.put("sign", sign);


        // 发送post请求

        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        return httpUtil.requestHttpPost(urlPrex, this.FUTURE_USERINFO_4FIX_URL, params);
    }

    /**
     * 用户持仓查询
     *
     * @param symbol       btc_usd:比特币    ltc_usd :莱特币
     * @param contractType 合约类型: this_week:当周   next_week:下周   month:当月   quarter:季度
     * @return
     * @throws HttpException
     * @throws IOException
     */
    @Override
    public String futurePosition(String symbol, String contractType) throws HttpException, IOException {
        // 构造参数签名
        Map<String, String> params = new HashMap<String, String>();
        if (!StringUtil.isEmpty(symbol)) {
            params.put("symbol", symbol);
        }
        if (!StringUtil.isEmpty(contractType)) {
            params.put("contract_type", contractType);
        }
        params.put("apiKey", apiKey);
        String sign = MD5Util.buildMysignV1(params, secretKey);
        params.put("sign", sign);
        // 发送post请求

        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        return httpUtil.requestHttpPost(urlPrex, this.FUTURE_POSITION_URL, params);
    }

    /**
     * 用户逐仓持仓查询
     *
     * @param symbol       btc_usd:比特币    ltc_usd :莱特币
     * @param contractType 合约类型: this_week:当周   next_week:下周   month:当月   quarter:季度
     * @return
     * @throws HttpException
     * @throws IOException
     */
    @Override
    public String futurePosition4fix(String symbol, String contractType) throws HttpException, IOException {
        // 构造参数签名
        Map<String, String> params = new HashMap<String, String>();
        if (!StringUtil.isEmpty(symbol)) {
            params.put("symbol", symbol);
        }
        if (!StringUtil.isEmpty(contractType)) {
            params.put("contract_type", contractType);
        }
        params.put("apiKey", apiKey);
        String sign = MD5Util.buildMysignV1(params, secretKey);
        params.put("sign", sign);
        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        return httpUtil.requestHttpPost(urlPrex, this.FUTURE_POSITION_4FIX_URL, params);
    }

    /**
     * 获取用户订单信息
     *
     * @param symbol       btc_usd:比特币    ltc_usd :莱特币
     * @param contractType 合约类型: this_week:当周   next_week:下周   month:当月   quarter:季度
     * @param orderId      订单ID(-1查询全部未成交订单，否则查询相应单号的订单)
     * @param status       查询状态：1:未完成(最近七天的数据)  2:已完成(最近七天的数据)
     * @param currentPage  当前页数
     * @param pageLength   每页获取条数，最多不超过50
     * @return
     * @throws HttpException
     * @throws IOException
     */
    @Override
    public String futureOrderInfo(String symbol, String contractType, String orderId, String status, String currentPage, String pageLength) throws HttpException, IOException {
        // 构造参数签名
        Map<String, String> params = new HashMap<>();
        if (!StringUtil.isEmpty(contractType)) {
            params.put("contract_type", contractType);
        }
        if (!StringUtil.isEmpty(currentPage)) {
            params.put("current_page", currentPage);
        }
        if (!StringUtil.isEmpty(orderId)) {
            params.put("order_id", orderId);
        }
        if (!StringUtil.isEmpty(apiKey)) {
            params.put("apiKey", apiKey);
        }
        if (!StringUtil.isEmpty(pageLength)) {
            params.put("page_length", pageLength);
        }
        if (!StringUtil.isEmpty(symbol)) {
            params.put("symbol", symbol);
        }
        if (!StringUtil.isEmpty(status)) {
            params.put("status", status);
        }
        String sign = MD5Util.buildMysignV1(params, secretKey);
        params.put("sign", sign);
        // 发送post请求

        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        return httpUtil.requestHttpPost(urlPrex, this.FUTURE_ORDER_INFO_URL, params);
    }
}
