package com.hengsu.sure.invite.model;

/**
 * Created by haiquanli on 15/12/5.
 */
public class InvitationResultModel {

    private Integer residueCount;
    private Integer sendCount;

    public void setResidueCount(Integer residueCount) {
        this.residueCount = residueCount;
    }

    public Integer getResidueCount() {
        return residueCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    public Integer getSendCount() {
        return sendCount;
    }
}
