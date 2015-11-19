package com.hengsu.sure.core;

import com.hkntv.pylon.core.exception.BusinessException;

/**
 * Created by haiquanli on 15/11/19.
 */
public enum ErrorCode {

    //AUTH
    AUTH_CODE_TIME_OUT("1000", "验证码超时"),
    AUTH_CODE_ERROR("1002", "验证码不正确"),
    AUTH_TOKEN_INVALID("1003", "AUTH TOKEN非法"),

    //register
    REGISTER_PHONE_EXISTED("2001", "该手机已经注册过"),

    //login
    LOGIN_USERNAME_NOT_EXISTED("3001","用户名不存在"),
    LOGIN_PASSWORD_ERROR("3002","密码错误"),



    ;

    private String code;
    private String errorMsg;

    private ErrorCode(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public static void throwBusinessException(ErrorCode errorCode) {
        throw new BusinessException(errorCode.errorMsg, errorCode.code);
    }
}
