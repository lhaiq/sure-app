package com.hengsu.sure.sns.vo;

import java.util.Date;

/**
 * Created by haiquanli on 15/11/21.
 */
public class ListCommentsVO {

    private Long id;
    private Long mId;
    private String content;
    private Date time;
    private Integer type;

    private SNSUserVO user;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public Long getmId() {
        return mId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public SNSUserVO getUser() {
        return user;
    }

    public void setUser(SNSUserVO user) {
        this.user = user;
    }
}
