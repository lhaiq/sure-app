package com.hengsu.sure.auth.vo;

import com.hengsu.sure.auth.model.UserModel;

/**
 * Created by haiquanli on 15/11/19.
 */
public class LoginSuccessVO {

    private UserModel user;

    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
