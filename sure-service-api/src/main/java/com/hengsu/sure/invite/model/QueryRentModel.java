package com.hengsu.sure.invite.model;

/**
 * Created by haiquanli on 15/12/27.
 */
public class QueryRentModel {
    private Long userId;
    private String nickname;
    private Integer gender;
    private Integer height;
    private Integer age;
    private String  icon;
    private Double  faceScore;
    private Double  credit;
    private Long  rentId;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public void setFaceScore(Double faceScore) {
        this.faceScore = faceScore;
    }

    public Double getFaceScore() {
        return faceScore;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
