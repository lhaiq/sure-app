package com.hengsu.sure.auth.entity;

public class SubAccount {
    private Long id;

    private String subAccountSid;

    private String subToken;

    private String dateCreated;

    private String voipAccount;

    private String voipPwd;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubAccountSid() {
        return subAccountSid;
    }

    public void setSubAccountSid(String subAccountSid) {
        this.subAccountSid = subAccountSid;
    }

    public String getSubToken() {
        return subToken;
    }

    public void setSubToken(String subToken) {
        this.subToken = subToken;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getVoipAccount() {
        return voipAccount;
    }

    public void setVoipAccount(String voipAccount) {
        this.voipAccount = voipAccount;
    }

    public String getVoipPwd() {
        return voipPwd;
    }

    public void setVoipPwd(String voipPwd) {
        this.voipPwd = voipPwd;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}