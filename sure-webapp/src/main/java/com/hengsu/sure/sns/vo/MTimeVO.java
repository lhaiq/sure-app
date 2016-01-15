package com.hengsu.sure.sns.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@MapClass("com.hengsu.sure.sns.model.MTimeModel")
public class MTimeVO {

    private Date time;
    private Double longitude;
    private Double latitude;
    @NotEmpty
    private String content;
    @NotNull
    private Integer cityId;
    private List<String> imageIds;


    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return this.time;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }


    public void setImageIds(List<String> imageIds) {
        this.imageIds = imageIds;
    }

    public List<String> getImageIds() {
        return imageIds;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCityId() {
        return cityId;
    }
}