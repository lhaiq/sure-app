package com.hengsu.sure.auth.request;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by haiquanli on 15/11/19.
 */
public class ValidateAuthCodeRequest {

    @NotEmpty
    private String phone;
    @NotEmpty
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
