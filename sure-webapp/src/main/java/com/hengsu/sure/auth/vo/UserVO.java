package com.hengsu.sure.auth.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

import javax.validation.constraints.NotNull;
import java.util.Date;

@MapClass("com.hengsu.sure.auth.model.UserModel")
public class UserVO{

	private Long id;
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
	private Long fansCount;
	private Long attentionCount;
	private Long friendCount;
	private Double balance;
	private Double faceScore;
	private Double credit;
	private Double skills;

	private Integer status;
	private String faceId;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAlipay() {
		return alipay;
	}

	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}

	public Long getAttentionCount() {
		return attentionCount;
	}

	public void setAttentionCount(Long attentionCount) {
		this.attentionCount = attentionCount;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}

	public Double getFaceScore() {
		return faceScore;
	}

	public void setFaceScore(Double faceScore) {
		this.faceScore = faceScore;
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

	public Double getSkills() {
		return skills;
	}

	public void setSkills(Double skills) {
		this.skills = skills;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}