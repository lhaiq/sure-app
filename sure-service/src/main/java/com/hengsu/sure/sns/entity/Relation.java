package com.hengsu.sure.sns.entity;

import java.util.Date;

public class Relation {
    private Long id;

    private Long fromUser;

    private Long toUser;

    private Date time;

    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromUser() {
        return fromUser;
    }

    public void setFromUser(Long fromUser) {
        this.fromUser = fromUser;
    }

    public Long getToUser() {
        return toUser;
    }

    public void setToUser(Long toUser) {
        this.toUser = toUser;
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
}