package com.hengsu.sure.auth.request;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by haiquanli on 15/11/19.
 */
public class LoginRequest {

    @NotEmpty
    private String phone;
    @NotEmpty
    private String password;

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
