package com.hengsu.sure.auth.request;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by haiquanli on 15/11/21.
 */
public class FaceLoginRequest {

    @NotEmpty
    private String phone;
    @NotEmpty
    private String loginFaceId;

    public String getLoginFaceId() {
        return loginFaceId;
    }

    public void setLoginFaceId(String loginFaceId) {
        this.loginFaceId = loginFaceId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
