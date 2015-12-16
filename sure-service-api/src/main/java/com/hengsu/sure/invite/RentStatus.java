package com.hengsu.sure.invite;

/**
 * Created by haiquanli on 15/11/26.
 */
public enum RentStatus {

    PUBLISH(0, "publish"),
    SELECTED(1, "selected"),
    FINISHED(2, "finished"),;

    private Integer code;
    private String desc;

    private RentStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }
}
