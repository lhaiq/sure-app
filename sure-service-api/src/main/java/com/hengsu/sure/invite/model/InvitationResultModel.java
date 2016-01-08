package com.hengsu.sure.invite.model;

/**
 * Created by haiquanli on 15/12/5.
 */
public class InvitationResultModel {

    private Long invitationId;
    private Integer residueCount;
    private Integer sendCount;


    public void setInvitationId(Long invitationId) {
        this.invitationId = invitationId;
    }

    public Long getInvitationId() {
        return invitationId;
    }

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
