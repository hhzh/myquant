package com.hhzh.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public enum CoinEnum {
    BTC("btc"),
    LTC("ltc"),
    ETH("eth"),
    ETC("etc"),
    BCH("bch"),
    XRP("xrp"),
    EOS("eos"),
    BTG("btg");

    @Getter
    private String code;
}
