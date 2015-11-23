package com.hengsu.sure.sns;

/**
 * Created by haiquanli on 15/11/22.
 */
public enum RelationType {

    RELATION(0, "relation"),
    FRIEND(1, "friend"),;

    private Integer code;
    private String desc;

    private RelationType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static RelationType valueOf(Integer code) {
        switch (code) {
            case 0:
                return RELATION;
            case 1:
                return FRIEND;
            default:
                return null;
        }
    }

}
