package com.hhzh.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CoinPairEnum {
    BTC_USD("btc_usd"),
    LTC_USD("ltc_usd"),
    ETH_USD("eth_usd"),
    ETH_USD("eth_usd"),
    BCH_USD("bch_usd"),
    BTG_USD("btg_usd"),
    XRP_USD("xrp_usd"),
    EOS_USD("eos_usd"),;

    @Getter
    private String code;

}
