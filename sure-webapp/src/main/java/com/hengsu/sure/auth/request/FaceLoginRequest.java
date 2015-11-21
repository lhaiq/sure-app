package com.hengsu.sure.auth.request;

/**
 * Created by haiquanli on 15/11/21.
 */
public class FaceLoginRequest {

    private String registerFaceId;
    private String loginFaceId;

    public String getLoginFaceId() {
        return loginFaceId;
    }

    public void setLoginFaceId(String loginFaceId) {
        this.loginFaceId = loginFaceId;
    }

    public String getRegisterFaceId() {
        return registerFaceId;
    }

    public void setRegisterFaceId(String registerFaceId) {
        this.registerFaceId = registerFaceId;
    }
}
