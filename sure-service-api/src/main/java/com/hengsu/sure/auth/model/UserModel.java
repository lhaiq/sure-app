package com.hengsu.sure.auth.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.auth.entity.User")
public class UserModel{
	
	private Long id;
	private String phone;
	private String nickname;
	private Integer gender;
	private String password;
	private Double longitude;
	private Double latitude;
	private Date locationModifyTime;
	private Integer role;
	private Double balance;
	private Integer age;
	private Long icon;
	private Double faceScore;
	private Double credit;
	private Double skills;
	private Integer car;
	private Integer city;
	private Integer status;
	private String faceId;
	private String alipay;
	private String clientId;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public String getPhone(){
		return this.phone;
	}
		
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	
	public String getNickname(){
		return this.nickname;
	}
		
	public void setGender(Integer gender){
		this.gender = gender;
	}
	
	public Integer getGender(){
		return this.gender;
	}
		
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return this.password;
	}
		
	public void setLongitude(Double longitude){
		this.longitude = longitude;
	}
	
	public Double getLongitude(){
		return this.longitude;
	}
		
	public void setLatitude(Double latitude){
		this.latitude = latitude;
	}
	
	public Double getLatitude(){
		return this.latitude;
	}
		
	public void setLocationModifyTime(Date locationModifyTime){
		this.locationModifyTime = locationModifyTime;
	}
	
	public Date getLocationModifyTime(){
		return this.locationModifyTime;
	}
		
	public void setRole(Integer role){
		this.role = role;
	}
	
	public Integer getRole(){
		return this.role;
	}
		
	public void setBalance(Double balance){
		this.balance = balance;
	}
	
	public Double getBalance(){
		return this.balance;
	}
		
	public void setAge(Integer age){
		this.age = age;
	}
	
	public Integer getAge(){
		return this.age;
	}
		
	public void setIcon(Long icon){
		this.icon = icon;
	}
	
	public Long getIcon(){
		return this.icon;
	}
		
	public void setFaceScore(Double faceScore){
		this.faceScore = faceScore;
	}
	
	public Double getFaceScore(){
		return this.faceScore;
	}
		
	public void setCredit(Double credit){
		this.credit = credit;
	}
	
	public Double getCredit(){
		return this.credit;
	}
		
	public void setSkills(Double skills){
		this.skills = skills;
	}
	
	public Double getSkills(){
		return this.skills;
	}
		
	public void setCar(Integer car){
		this.car = car;
	}
	
	public Integer getCar(){
		return this.car;
	}
		
	public void setCity(Integer city){
		this.city = city;
	}
	
	public Integer getCity(){
		return this.city;
	}
		
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}
		
	public void setFaceId(String faceId){
		this.faceId = faceId;
	}
	
	public String getFaceId(){
		return this.faceId;
	}
		
	public void setAlipay(String alipay){
		this.alipay = alipay;
	}
	
	public String getAlipay(){
		return this.alipay;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientId() {
		return clientId;
	}
}