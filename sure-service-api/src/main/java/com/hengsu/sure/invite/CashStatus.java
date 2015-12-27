package com.hengsu.sure.invite;

/**
 * Created by haiquanli on 15/11/26.
 */
public enum CashStatus {

    APPLYING(0, "APPLYING"),
    FINISHED(1, "finished"),
    FAILURE(2, "failure"),;

    private Integer code;
    private String desc;

    private CashStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }
}
