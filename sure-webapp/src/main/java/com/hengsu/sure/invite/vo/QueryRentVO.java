package com.hengsu.sure.invite.vo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by haiquanli on 15/12/27.
 */
public class QueryRentVO {

    @NotNull
    private Integer city;
    private Integer gender;
    private List<String> scenes;
    private String startDate;
    private String endDate;
    private String time;
    private Integer car;
    private Integer minAge;
    private Integer maxAge;

    public Integer getCar() {
        return car;
    }

    public void setCar(Integer car) {
        this.car = car;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getCity() {
        return city;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }


    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public List<String> getScenes() {
        return scenes;
    }

    public void setScenes(List<String> scenes) {
        this.scenes = scenes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }
}
