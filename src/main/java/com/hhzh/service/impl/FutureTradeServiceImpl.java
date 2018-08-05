package com.hhzh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hhzh.common.HttpUtilManager;
import com.hhzh.common.HttpUtils;
import com.hhzh.common.MD5Util;
import com.hhzh.common.StringUtil;
import com.hhzh.service.CommonTradeService;
import com.hhzh.service.IFutureTradeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service
@Slf4j
public class FutureTradeServiceImpl extends CommonTradeService implements IFutureTradeService {

    /**
     * 期货行情URL
     */
    private final String FUTURE_TICKER_URL = "/api/v1/future_ticker.do";
    /**
     * 期货指数查询URL
     */
    private final String FUTURE_INDEX_URL = "/api/v1/future_index.do";

    /**
     * 期货交易记录查询URL
     */
    private final String FUTURE_TRADES_URL = "/api/v1/future_trades.do";

    /**
     * 期货市场深度查询URL
     */
    private final String FUTURE_DEPTH_URL = "/api/v1/future_depth.do";
    /**
     * 美元-人民币汇率查询URL
     */
    private final String FUTURE_EXCHANGE_RATE_URL = "/api/v1/exchange_rate.do";

    /**
     * 期货取消订单URL
     */
    private final String FUTURE_CANCEL_URL = "/api/v1/future_cancel.do";

    /**
     * 期货下单URL
     */
    private final String FUTURE_TRADE_URL = "/api/v1/future_trade.do";

    /**
     * 期货账户信息URL
     */
    private final String FUTURE_USERINFO_URL = "/api/v1/future_userinfo.do";

    /**
     * 逐仓期货账户信息URL
     */
    private final String FUTURE_USERINFO_4FIX_URL = "/api/v1/future_userinfo_4fix.do";

    /**
     * 期货持仓查询URL
     */
    private final String FUTURE_POSITION_URL = "/api/v1/future_position.do";

    /**
     * 期货逐仓持仓查询URL
     */
    private final String FUTURE_POSITION_4FIX_URL = "/api/v1/future_position_4fix.do";

    /**
     * 用户期货订单信息查询URL
     */
    private final String FUTURE_ORDER_INFO_URL = "/api/v1/future_order_info.do";

    /**
     * 获取K线信息
     */
    private final String FUTURE_KLINE = "/api/v1/future_kline.do";

    /**
     * 获取当前可用合约总持仓量
     */
    private final String FUTURE_HOLD_AMOUNT = "/api/v1/future_hold_amount.do";

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
        return this.requestGet(symbol, contractType, FUTURE_TICKER_URL);
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
        return this.requestGet(symbol, FUTURE_INDEX_URL);
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
        return this.requestGet(symbol, contractType, FUTURE_TRADES_URL);
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
        return this.requestGet(symbol, contractType, FUTURE_DEPTH_URL);
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
        return httpUtil.requestHttpGet(this.urlPrex, FUTURE_EXCHANGE_RATE_URL, null);
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
    public String cancelOrder(String symbol, String contractType, String orderId) {
        try {
            JSONObject params = getParams();
            params.put("symbol", symbol);
            params.put("contract_type", contractType);
            params.put("order_id", orderId);
            params.put("sign", sign(params));
            HttpUtils.Response response = HttpUtils.post(getUrl(FUTURE_CANCEL_URL), params);
            return JSONObject.toJSONString(response);
        } catch (Exception e) {
            log.error("Cancel order failure,symbol:{},contractType:{},orderId:{}", symbol, contractType, orderId);
        }
        return null;
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
        if (!StringUtil.isEmpty(api_key)) {
            params.put("api_key", api_key);
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
    public String futureUserInfo() throws HttpException, IOException {
        return this.requestPost(FUTURE_USERINFO_URL);
    }

    /**
     * 期货逐仓账户信息
     *
     * @return
     * @throws HttpException
     * @throws IOException
     */
    @Override
    public String futureUserInfo4Fix() throws HttpException, IOException {
        return this.requestPost(FUTURE_USERINFO_4FIX_URL);
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
        return this.requestPost(symbol, contractType, FUTURE_POSITION_URL);
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
    public String futurePosition4Fix(String symbol, String contractType) throws HttpException, IOException {
        return this.requestPost(symbol, contractType, this.FUTURE_POSITION_4FIX_URL);
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
     */
    @Override
    public String futureOrderInfo(String symbol, String contractType, String orderId, String status, String currentPage, String pageLength) {
        try {
            JSONObject params = getParams();
            params.put("symbol", symbol);
            params.put("contract_type", contractType);
            params.put("status", status);
            params.put("order_id", orderId);
            params.put("current_page", currentPage);
            params.put("page_length", pageLength);
            params.put("sign", sign(params));
            HttpUtils.Response response = HttpUtils.post(getUrl(FUTURE_ORDER_INFO_URL), params);
            return JSONObject.toJSONString(response);
        } catch (Exception e) {
            log.info("Get future order info failure ,symbol:{},contractType:{},orderId:{},status:{},currentPage:{},pageLength:{}",
                    symbol, contractType, orderId, status, currentPage, pageLength);
        }
        return null;
    }

    /**
     * 获取K线信息
     *
     * @param symbol       btc_usd:比特币    ltc_usd :莱特币
     * @param type         1min/3min/5min/15min/30min/1day/3day/1week/1hour/2hour/4hour/6hour/12hour
     * @param contractType 合约类型: this_week:当周   next_week:下周   month:当月   quarter:季度
     * @param size         指定获取数据的条数
     * @param since        时间戳（eg：1417536000000）。 返回该时间戳以后的数据
     */
    @Override
    public String futureKLine(String symbol, String type, String contractType, Integer size, Long since) throws IOException, HttpException {
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String param = "symbol=" + symbol;
        param += "&type=" + type;
        param += "&contract_type=" + contractType;
        if (size != null && size > 0) {
            param += "&size=" + size;
        }
        if (since != null && since > 0) {
            param += "&since=" + since;
        }

        return httpUtil.requestHttpGet(urlPrex, FUTURE_KLINE, param);
    }

    /**
     * 获取当前可用合约总持仓量
     *
     * @param symbol       btc_usd:比特币    ltc_usd :莱特币
     * @param contractType 合约类型: this_week:当周   next_week:下周   month:当月   quarter:季度
     */
    @Override
    public String futureHoldAmount(String symbol, String contractType) throws HttpException, IOException {
        return this.requestGet(symbol, contractType, FUTURE_HOLD_AMOUNT);
    }


}

