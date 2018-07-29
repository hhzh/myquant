package com.hhzh.common;

/**
 * 合约周期类型
 */
public enum ContractTypeEnum {
    THIS_WEEK("this_week"),
    NEXT_WEEK("next_week"),
    MONTH("month"),
    QUARTER("quarter");

    private String code;

    ContractTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
