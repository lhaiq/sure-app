package com.hengsu.sure.auth.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

import javax.validation.constraints.NotNull;
import java.util.Date;

@MapClass("com.hengsu.sure.auth.model.UserModel")
public class UserVO{

	private String nickname;
	private Integer gender;
	private Double longitude;
	private Double latitude;
	private String role;
	private Integer age;
	private Integer car;
	private Integer city;
	private Long icon;
	private String alipay;
	private String clientId;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}

	public String getAlipay() {
		return alipay;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientId() {
		return clientId;
	}
}