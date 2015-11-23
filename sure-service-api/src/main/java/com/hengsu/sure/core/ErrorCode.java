package com.hengsu.sure.core;

import com.hkntv.pylon.core.exception.BusinessException;

/**
 * Created by haiquanli on 15/11/19.
 */
public enum ErrorCode {

    //AUTH
    SYSTEM_INTERNAL_ERROR("1000", "内部错误"),
    AUTH_CODE_TIME_OUT("1001", "验证码超时"),
    AUTH_CODE_ERROR("1002", "验证码不正确"),
    AUTH_TOKEN_INVALID("1003", "AUTH TOKEN非法"),
    AUTH_TOKEN_MUST("1004", "AUTH TOKEN 必须填"),

    REGISTER_PHONE_EXISTED("1005", "该手机已经注册过"),

    LOGIN_USER_NOT_EXISTED("1006","用户不存在"),
    LOGIN_PASSWORD_ERROR("1007","密码错误"),
    LOGIN_FACE_ERROR("1007","与注册人脸不匹配"),
    PHONE_NOT_REGISTER("1007","该手机号尚未注册"),

    JSON_FORMATTED("1008", "JSON格式错误"),
    FIELD_MUST("1009", "字段必须填"),
    IMAGE_EXISTED("1010", "图片不存在"),
    AUTH_CODE_NOT_EXISTED("1011", "该手机号尚未获取验证码"),

    //时光5
    MTIME_DELETE_ERROR("5000", "只能删除自己的时光"),
    COMMENT_DELETE_ERROR("5001", "只能删除自己的评论"),

    RELATION_EXISTED("5002", "关系已存在"),

    RELATION_ERROR("5003", "不能关注自己或加自己为好友"),



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

    public String getCode() {
        return code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public static void main(String[] args) {
        for(ErrorCode errorCode:ErrorCode.values()){
            System.out.println(errorCode.getCode()+"\t"+errorCode.getErrorMsg());
        }
    }
}
