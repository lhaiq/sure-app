package com.hengsu.sure.invite;

/**
 * Created by haiquanli on 15/11/26.
 */
public enum IndentStatus {

    SUCCESS(0, "success"),
    FINISHED(1, "finished"),
    CANCELING(2, "canceling"),
    CANCELED(2, "canceled"),
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
