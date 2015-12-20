package com.hengsu.sure.invite;

/**
 * Created by haiquanli on 15/11/26.
 */
public enum InvitationStatus {

    PUBLISHED(0, "published"),
    FINISHED(1, "finished"),
    VOID(2, "void"),;

    private Integer code;
    private String desc;

    private InvitationStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }
}
