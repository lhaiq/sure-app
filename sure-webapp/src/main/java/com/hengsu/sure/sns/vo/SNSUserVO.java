package com.hengsu.sure.sns.vo;

/**
 * Created by haiquanli on 15/11/21.
 */
public class SNSUserVO {

    private Long id;
    private String nickname;
    private Integer gender;
    private Integer age;
    private Integer city;
    private Long icon;
    private Long fansCount;
    private Long attentionCount;
    private Long friendCount;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getIcon() {
        return icon;
    }

    public void setIcon(Long icon) {
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(Long attentionCount) {
        this.attentionCount = attentionCount;
    }

    public Long getFansCount() {
        return fansCount;
    }

    public void setFansCount(Long fansCount) {
        this.fansCount = fansCount;
    }

    public Long getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(Long friendCount) {
        this.friendCount = friendCount;
    }
}
