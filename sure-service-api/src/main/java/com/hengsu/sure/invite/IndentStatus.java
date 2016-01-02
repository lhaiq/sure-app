package com.hengsu.sure.invite;

/**
 * Created by haiquanli on 15/11/26.
 */
public enum IndentStatus {

    CONFIRMED(0, "confirmed"),
    PAYED(1, "payed"),
    FINISHED(2, "finished"),
    CANCELING(3, "canceling"),
    CANCELED(4, "canceled"),
    ;

    private Integer code;
    private String desc;

    private IndentStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }
}
