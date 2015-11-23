package com.hengsu.sure.auth.vo;

import com.hengsu.sure.auth.model.UserModel;
import com.hengsu.sure.sns.vo.SNSUserVO;

/**
 * Created by haiquanli on 15/11/19.
 */
public class LoginSuccessVO {

    private SNSUserVO user;

    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setUser(SNSUserVO user) {
        this.user = user;
    }

    public SNSUserVO getUser() {
        return user;
    }
}
