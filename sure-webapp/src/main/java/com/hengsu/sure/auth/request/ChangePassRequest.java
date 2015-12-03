package com.hengsu.sure.auth.request;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by haiquanli on 15/11/21.
 */
public class ChangePassRequest {

    @NotEmpty
    @Size(min = 11,max = 11)
    private String phone;
    @NotEmpty
    private String code;
    @NotEmpty
    private String password;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
