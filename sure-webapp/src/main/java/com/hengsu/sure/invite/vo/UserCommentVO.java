package com.hengsu.sure.invite.vo;

import com.hengsu.sure.auth.vo.UserVO;

import java.util.Date;

/**
 * Created by haiquanli on 16/1/9.
 */
public class UserCommentVO {

    private Long id;
    private Double faceScore;
    private Double credit;
    private String content;
    private Long indentId;
    private UserVO user;
    private Date createTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIndentId() {
        return indentId;
    }

    public void setIndentId(Long indentId) {
        this.indentId = indentId;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }
}
