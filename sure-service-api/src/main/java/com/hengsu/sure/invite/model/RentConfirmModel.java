package com.hengsu.sure.invite.model;

/**
 * Created by haiquanli on 15/12/25.
 */
public class RentConfirmModel {

    private Long id;
    private Long userId;
    private String indentNo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndentNo() {
        return indentNo;
    }

    public void setIndentNo(String indentNo) {
        this.indentNo = indentNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
