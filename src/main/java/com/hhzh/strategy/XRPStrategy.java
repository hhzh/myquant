package com.hhzh.strategy;

import com.alibaba.fastjson.JSONArray;
import com.hhzh.service.IFutureTradeService;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class XRPStrategy {

    @Autowired
    private IFutureTradeService futureTradeService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void executeStrategy() throws IOException, HttpException {
        // 1、从缓存中查出上次交易的价格
        String cachePriceStr = stringRedisTemplate.opsForValue().get("xrp");
        BigDecimal cachePrice = new BigDecimal(cachePriceStr);
        // 2、从K线行情中查出最新价格
        String kLine = futureTradeService.futureKLine("xrp_usd", "1min", "quarter", 1, 0L);
        JSONArray kLineList = JSONArray.parseArray(kLine);
        JSONArray lines = JSONArray.parseArray(kLineList.getString(0));
        BigDecimal lastPrice = lines.getBigDecimal(4);
        // 3、如果缓存中的交易价格高于最新价格，就买入开多，并买入平空;如果缓存中的交易价格低于最新价格，就卖出开空，并卖出平多;相等就不处理；
        // 4、下单交易
        if (cachePrice.compareTo(lastPrice) > 0) {
            String openLongResult = futureTradeService.futureTrade("xrp_usd", "quarter", lastPrice.toString(), "1", "1", "0");
            String closeShortResult = futureTradeService.futureTrade("xrp_usd", "quarter", lastPrice.toString(), "1", "4", "0");
        } else if (cachePrice.compareTo(lastPrice) < 0) {
            String openShortResult = futureTradeService.futureTrade("xrp_usd", "quarter", lastPrice.toString(), "1", "2", "0");
            String closeLongResult = futureTradeService.futureTrade("xrp_usd", "quarter", lastPrice.toString(), "1", "3", "0");
        } else {
            return;
        }

        // 5、把最新价格写入缓存
        stringRedisTemplate.opsForValue().set("xrp", lastPrice.toString());
    }

}
