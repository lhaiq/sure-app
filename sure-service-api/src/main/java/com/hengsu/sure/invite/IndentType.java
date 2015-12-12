package com.hengsu.sure.invite;

/**
 * Created by haiquanli on 15/11/26.
 */
public enum IndentType {

    INVITATION(0, "invitation"),
    RENT(1, "rent"),
    GOODS(2, "goods"),;

    private Integer code;
    private String desc;

    private IndentType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }
}
