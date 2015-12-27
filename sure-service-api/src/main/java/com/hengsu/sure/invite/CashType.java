package com.hengsu.sure.invite;

/**
 * Created by haiquanli on 15/11/26.
 */
public enum CashType {

    CASH(0, "cash"),
    REFUND(1, "refund"),;

    private Integer code;
    private String desc;

    private CashType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }
}
