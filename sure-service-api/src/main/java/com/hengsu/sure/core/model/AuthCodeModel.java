package com.hengsu.sure.core.model;

/**
 * Created by haiquanli on 15/11/19.
 */
public class AuthCodeModel {

    private String code;

    private Long generateTime;

    public AuthCodeModel(String code) {
        this.code = code;
        generateTime = System.currentTimeMillis() / 100;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getGenerateTime() {
        return generateTime;
    }
}
