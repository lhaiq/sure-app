package com.hengsu.sure.invite.vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by haiquanli on 15/12/20.
 */
public class   InvitationConfirmVO {

    @NotNull
    private Long receivedUserId;
    @NotEmpty
    private String indentNo;

    public String getIndentNo() {
        return indentNo;
    }

    public void setIndentNo(String indentNo) {
        this.indentNo = indentNo;
    }

    public Long getReceivedUserId() {
        return receivedUserId;
    }

    public void setReceivedUserId(Long receivedUserId) {
        this.receivedUserId = receivedUserId;
    }


}
