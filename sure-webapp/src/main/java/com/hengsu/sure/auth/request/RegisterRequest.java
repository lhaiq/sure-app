package com.hengsu.sure.auth.request;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by haiquanli on 15/11/19.
 */
public class RegisterRequest {

    @NotEmpty
    private String phone;
    @NotEmpty
    private String password;
    @NotEmpty
    private String faceId;

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
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
