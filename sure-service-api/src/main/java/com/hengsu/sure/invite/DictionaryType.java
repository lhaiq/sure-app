package com.hengsu.sure.invite;

/**
 * Created by haiquanli on 15/12/5.
 */
public enum DictionaryType {

    SCENE(0, "scene"),
    TIME_SLOT(1, "time_slot"),
    VOID(2, "void"),;

    private Integer code;
    private String desc;

    private DictionaryType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }
}
