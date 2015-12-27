package com.hengsu.sure.invite.entity;

/**
 * Created by haiquanli on 15/12/27.
 */
public class IndentCommentScore {

    private Long userId;
    private Double faceScore;
    private Double credit;

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getFaceScore() {
        return faceScore;
    }

    public void setFaceScore(Double faceScore) {
        this.faceScore = faceScore;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
