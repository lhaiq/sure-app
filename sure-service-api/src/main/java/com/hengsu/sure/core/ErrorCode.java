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
    LOGIN_USER_NOT_EXISTED("1006", "用户不存在"),
    LOGIN_PASSWORD_ERROR("1007", "密码错误"),
    LOGIN_FACE_ERROR("1008", "与注册人脸不匹配"),




    PHONE_NOT_REGISTER("1009", "该手机号尚未注册"),

    JSON_FORMATTED("1010", "JSON格式错误"),
    FIELD_MUST("1011", "字段必须填"),
    PARAMETER_MUST("1012", "字段必须填"),
    IMAGE_EXISTED("1013", "图片不存在"),

    DELETE_ONLY_SELF("1014", "只能删除自己"),
    AUTH_CODE_NOT_EXISTED("1015", "该手机号尚未获取验证码"),
    JSON_FORMAT_ERROR("1016", "json格式错误"),
    OLD_PASSWORD_ERROR("1017", "旧密码错误"),


    //时光5
    MTIME_DELETE_ERROR("5000", "只能删除自己的时光"),
    COMMENT_DELETE_ERROR("5001", "只能删除自己的评论"),

    RELATION_EXISTED("5002", "关系已存在"),

    RELATION_ERROR("5003", "不能关注自己或加自己为好友"),

    //600邀约
    HAVE_EXCEED_INVITATION("6000", "超出邀约次数"),
    INVITATION_FINISHED("6001", "该邀约已经完成"),
    INVITATION_PUBLISHER_NOT_MATCH("6002", "只能更新自己的邀约"),
    INVITATION_ROLE_ERROR("6003", "只有买家才能发布即时邀约"),
    CANNOT_RECEIVE_SLEF_INVITATION("6004", "不能接受自己的即时邀约"),
    CANNOT_CANCEL_OTHERS_INDENT("6005", "只能取消自己的订单"),
    CANNOT_CANCEL_STATUS_ERROR("6006", "只能取消未完成的订单"),

    //700订单
    AMOUNT_ERROR("7000", "金额不够"),
    TRADE_NOT_SUCCESS("7001", "支付尚未成功"),
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
        for (ErrorCode errorCode : ErrorCode.values()) {
            System.out.println(errorCode.getCode() + "\t" + errorCode.getErrorMsg());
        }
    }
}
