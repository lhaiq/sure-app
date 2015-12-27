package com.hengsu.sure.invite;

/**
 * Created by haiquanli on 15/11/26.
 */
public enum StatementType {

    CASH(0, "cash"),
    INCOME(1, "income"),;

    private Integer code;
    private String desc;

    private StatementType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }
}
