package com.hengsu.sure.invite.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@MapClass("com.hengsu.sure.invite.model.InvitationModel")
public class InvitationVO {

    private Long id;

    @NotNull
    private Integer city;
    private List<String> scenes;
    @NotNull
    private Integer gender;
    @NotNull
    private String age;
    @NotNull
    private String date;
    @NotNull
    private String time;
    private Integer car;
    @NotNull
    private Double money;

    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

    @NotNull
    private Double price;
    private String state;
    private Date createTime;
    private Integer status;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getCar() {
        return car;
    }

    public void setCar(Integer car) {
        this.car = car;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getScenes() {
        return scenes;
    }

    public void setScenes(List<String> scenes) {
        this.scenes = scenes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
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

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getMoney() {
        return money;
    }
}