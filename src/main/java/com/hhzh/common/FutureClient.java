package com.hhzh.common;

import java.io.IOException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hhzh.service.IFutureTradeService;
import com.hhzh.service.impl.FutureTradeServiceImpl;
import org.apache.http.HttpException;

/**
 * 期货 REST API 客户端请求
 */
public class FutureClient {

    public static void main(String[] args) throws HttpException, IOException {
        /**
         *  get请求无需发送身份认证,通常用于获取行情，市场深度等公共信息
         */
        IFutureTradeService futureGetV1 = new FutureTradeServiceImpl();

        /**
         * post请求需发送身份认证，获取用户个人相关信息时，需要指定api_key,与secret_key并与参数进行签名，
         * 此处对构造方法传入api_key与secret_key,在请求用户相关方法时则无需再传入，
         * 发送post请求之前，程序会做自动加密，生成签名。
         *
         */
        IFutureTradeService futurePostV1 = new FutureTradeServiceImpl();

        //期货行情信息
        String ticker = futureGetV1.futureTicker("xrp_usd", "this_week");
        System.out.println(ticker); // {"date":"1532844000","ticker":{"high":0.459,"vol":105768,"day_high":0,"last":0.456,"low":0.45,"contract_id":201808030150048,"buy":0.455,"sell":0.456,"coin_vol":0,"day_low":0,"unit_amount":10}}
        //
        ////期货指数信息
        //String futureIndex = futureGetV1.futureIndex("xrp_usd");
        //System.out.println(futureIndex); // {"future_index":0.453}

        //期货交易信息
        //String futureTrades = futureGetV1.futureTrades("xrp_usd", "this_week");
        //System.out.println(futureTrades); // [{"date":1532826147,"date_ms":1532826147561,"amount":30,"price":0.456,"type":"buy","tid":1185555906987011},{"date":1532826148,"date_ms":1532826148310,"amount":16,"price":0.456,"type":"buy","tid":1185555928220674},{"date":1532826313,"date_ms":1532826313352,"amount":94,"price":0.456,"type":"buy","tid":1185566772069378},{"date":1532826313,"date_ms":1532826313352,"amount":200,"price":0.456,"type":"buy","tid":1185566772069380},{"date":1532826378,"date_ms":1532826378182,"amount":550,"price":0.455,"type":"sell","tid":1185571023979522},{"date":1532826924,"date_ms":1532826924706,"amount":90,"price":0.455,"type":"sell","tid":1185606841107456},{"date":1532828605,"date_ms":1532828605885,"amount":6,"price":0.456,"type":"sell","tid":1185717018461184},{"date":1532829203,"date_ms":1532829203428,"amount":54,"price":0.456,"type":"sell","tid":1185756179497985},{"date":1532829218,"date_ms":1532829218915,"amount":54,"price":0.456,"type":"sell","tid":1185757194257410},{"date":1532829424,"date_ms":1532829424863,"amount":200,"price":0.457,"type":"buy","tid":1185770691462145},{"date":1532829425,"date_ms":1532829425085,"amount":200,"price":0.457,"type":"buy","tid":1185770705159170},{"date":1532829442,"date_ms":1532829442041,"amount":200,"price":0.457,"type":"buy","tid":1185771816977411},{"date":1532829442,"date_ms":1532829442249,"amount":200,"price":0.457,"type":"buy","tid":1185771830871041},{"date":1532830152,"date_ms":1532830152505,"amount":16,"price":0.456,"type":"sell","tid":1185818378208266},{"date":1532830610,"date_ms":1532830610223,"amount":140,"price":0.457,"type":"buy","tid":1185848372921345},{"date":1532831108,"date_ms":1532831108232,"amount":2,"price":0.456,"type":"sell","tid":1185881012667393},{"date":1532831108,"date_ms":1532831108232,"amount":198,"price":0.456,"type":"sell","tid":1185881012667395},{"date":1532831108,"date_ms":1532831108448,"amount":90,"price":0.456,"type":"sell","tid":1185881026298881},{"date":1532831243,"date_ms":1532831243744,"amount":110,"price":0.455,"type":"sell","tid":1185889893385217},{"date":1532831245,"date_ms":1532831245661,"amount":200,"price":0.455,"type":"sell","tid":1185890019148801},{"date":1532831245,"date_ms":1532831245869,"amount":200,"price":0.455,"type":"sell","tid":1185890032911360},{"date":1532833459,"date_ms":1532833459687,"amount":1092,"price":0.455,"type":"sell","tid":1186035117491202},{"date":1532833655,"date_ms":1532833655438,"amount":104,"price":0.457,"type":"buy","tid":1186047946359809},{"date":1532834173,"date_ms":1532834173806,"amount":10,"price":0.457,"type":"buy","tid":1186081918059521},{"date":1532835675,"date_ms":1532835675549,"amount":68,"price":0.455,"type":"sell","tid":1186180336354305},{"date":1532835675,"date_ms":1532835675549,"amount":182,"price":0.455,"type":"sell","tid":1186180336354307},{"date":1532835675,"date_ms":1532835675549,"amount":44,"price":0.455,"type":"sell","tid":1186180336354309},{"date":1532835958,"date_ms":1532835958217,"amount":84,"price":0.456,"type":"buy","tid":1186198860694529},{"date":1532836323,"date_ms":1532836323789,"amount":10,"price":0.455,"type":"sell","tid":1186222819214337},{"date":1532836462,"date_ms":1532836462198,"amount":28,"price":0.457,"type":"buy","tid":1186231889658881},{"date":1532837810,"date_ms":1532837810974,"amount":18,"price":0.456,"type":"sell","tid":1186320283567105},{"date":1532838221,"date_ms":1532838221358,"amount":10,"price":0.455,"type":"sell","tid":1186347178427393},{"date":1532838382,"date_ms":1532838382486,"amount":28,"price":0.456,"type":"buy","tid":1186357737522177},{"date":1532838564,"date_ms":1532838564102,"amount":170,"price":0.455,"type":"sell","tid":1186369637680129},{"date":1532838564,"date_ms":1532838564102,"amount":2,"price":0.455,"type":"sell","tid":1186369637680131},{"date":1532838578,"date_ms":1532838578344,"amount":90,"price":0.454,"type":"sell","tid":1186370559902721},{"date":1532838578,"date_ms":1532838578344,"amount":44,"price":0.454,"type":"sell","tid":1186370559902723},{"date":1532838578,"date_ms":1532838578344,"amount":36,"price":0.454,"type":"sell","tid":1186370559902725},{"date":1532838578,"date_ms":1532838578344,"amount":252,"price":0.454,"type":"sell","tid":1186370559902727},{"date":1532838578,"date_ms":1532838578344,"amount":578,"price":0.454,"type":"sell","tid":1186370559902729},{"date":1532838605,"date_ms":1532838605362,"amount":912,"price":0.454,"type":"sell","tid":1186372338484225},{"date":1532838610,"date_ms":1532838610852,"amount":934,"price":0.454,"type":"sell","tid":1186372663149572},{"date":1532838791,"date_ms":1532838791942,"amount":28,"price":0.456,"type":"buy","tid":1186384572285953},{"date":1532839544,"date_ms":1532839544988,"amount":8,"price":0.455,"type":"buy","tid":1186433923646465},{"date":1532839832,"date_ms":1532839832098,"amount":200,"price":0.454,"type":"sell","tid":1186452739752983},{"date":1532839832,"date_ms":1532839832098,"amount":200,"price":0.454,"type":"sell","tid":1186452739752985},{"date":1532839832,"date_ms":1532839832098,"amount":6,"price":0.454,"type":"sell","tid":1186452739752987},{"date":1532839832,"date_ms":1532839832098,"amount":194,"price":0.454,"type":"sell","tid":1186452739752989},{"date":1532839834,"date_ms":1532839834139,"amount":6,"price":0.456,"type":"buy","tid":1186452873643009},{"date":1532839834,"date_ms":1532839834139,"amount":22,"price":0.456,"type":"buy","tid":1186452873643011}]

        //期货市场深度
        //String futureDepth = futureGetV1.futureDepth("xrp_usd", "this_week");
        //System.out.println(futureDepth); // {"asks":[[0.46,10897],[0.459,30208],[0.458,11],[0.457,11714],[0.456,1236]],"bids":[[0.455,2],[0.454,403],[0.453,30495],[0.452,3066],[0.451,1078]]}

        //美元-人民币汇率
        //String exchangeRate = futureGetV1.exchangeRate();
        //System.out.println(exchangeRate);
        //
        ////期货下单
        //String tradeResultV1 = futurePostV1.futureTrade("btc_usd", "this_week", "10.134", "1", "1", "0");
        //JSONObject tradeJSV1 = JSONObject.parseObject(tradeResultV1);
        //String tradeOrderV1 = tradeJSV1.getString("order_id");
        //System.out.println(tradeResultV1);
        //
        //期货用户订单查询
        //futurePostV1.futureOrderInfo("btc_usd", "this_week", tradeOrderV1, "1", "1", "2");
        //
        ////取消订单
        //futurePostV1.cancelOrder("btc_usd", "this_week", tradeOrderV1);

        //期货账户信息
        //String futureUserInfo = futurePostV1.futureUserInfo();
        //System.out.println(futureUserInfo);
        //
        ////逐仓期货账户信息
        //String userInfo4Fix = futurePostV1.futureUserInfo4Fix();
        //System.out.println(userInfo4Fix);

        ////期货用户持仓查询
        //futurePostV1.futurePosition("btc_usd", "this_week");
        //
        ////期货用户逐仓持仓查询
        //futurePostV1.futurePosition4Fix("btc_usd", null);

        //获取K线数据
        String kLine = futureGetV1.futureKLine("xrp_usd", "1min", "quarter", 1, 0L);
        System.out.println(kLine);
        JSONArray klineList = JSONArray.parseArray(kLine);
        System.out.println(klineList.get(0));
        JSONArray objects = JSONArray.parseArray(klineList.getString(0));
        System.out.println(objects.get(4));
        System.out.println(objects.get(5));

    }
}
