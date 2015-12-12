package com.hengsu.sure.auth;

/**
 * Created by haiquanli on 15/11/26.
 */
public enum UserRole {

    CUSTOMER(0, "customer"),
    SELLER(1, "seller"),
    ;

    private Integer code;
    private String desc;

    private UserRole(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }
}
