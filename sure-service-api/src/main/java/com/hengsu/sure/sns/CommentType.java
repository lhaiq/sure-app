package com.hengsu.sure.sns;

/**
 * Created by haiquanli on 15/11/22.
 */
public enum CommentType {

    COMMENT(0, "comment"),
    STATUSES(1, "statuses"),;

    private Integer code;
    private String desc;

    private CommentType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static CommentType valueOf(Integer code) {
        switch (code) {
            case 0:
                return COMMENT;
            case 1:
                return STATUSES;
            default:
                return null;
        }
    }

}
