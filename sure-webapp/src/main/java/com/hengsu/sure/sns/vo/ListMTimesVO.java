package com.hengsu.sure.sns.vo;

import java.util.Date;
import java.util.List;

/**
 * Created by haiquanli on 15/11/21.
 */
public class ListMTimesVO {

    private Long id;
    private Date time;
    private Double longitude;
    private Double latitude;
    private String content;
    private Integer cityId;
    private Long commentsCount;
    private Long statusesCount;
    private List<Long> imageIds;
    private SNSUserVO user;
    private boolean isStatus;
    private boolean isAttention;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImageIds(List<Long> imageIds) {
        this.imageIds = imageIds;
    }

    public List<Long> getImageIds() {
        return imageIds;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public SNSUserVO getUser() {
        return user;
    }

    public void setUser(SNSUserVO user) {
        this.user = user;
    }

    public Long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Long commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Long getStatusesCount() {
        return statusesCount;
    }

    public void setStatusesCount(Long statusesCount) {
        this.statusesCount = statusesCount;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public boolean isAttention() {
        return isAttention;
    }

    public void setIsAttention(boolean isAttention) {
        this.isAttention = isAttention;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setIsStatus(boolean isStatus) {
        this.isStatus = isStatus;
    }
}
