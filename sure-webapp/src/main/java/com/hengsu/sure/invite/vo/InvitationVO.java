package com.hengsu.sure.invite.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

import javax.validation.constraints.NotNull;
import java.util.Date;

@MapClass("com.hengsu.sure.invite.model.InvitationModel")
public class InvitationVO{
	
	private Long id;
	@NotNull
	private Long userId;
	private Integer city;
	@NotNull
	private String scene;
	@NotNull
	private Integer gender;
	@NotNull
	private String age;
	@NotNull
	private String date;
	@NotNull
	private String time;
	@NotNull
	private Integer car;
	@NotNull
	private String address;
	@NotNull
	private Double latitude;
	@NotNull
	private Double longitude;
	@NotNull
	private Double price;
	private String state;
	private Long receivedUser;
	private Date createTime;
	private Integer status;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return this.userId;
	}
		
	public void setCity(Integer city){
		this.city = city;
	}
	
	public Integer getCity(){
		return this.city;
	}
		
	public void setScene(String scene){
		this.scene = scene;
	}
	
	public String getScene(){
		return this.scene;
	}
		
	public void setGender(Integer gender){
		this.gender = gender;
	}
	
	public Integer getGender(){
		return this.gender;
	}
		
	public void setAge(String age){
		this.age = age;
	}
	
	public String getAge(){
		return this.age;
	}
		
	public void setDate(String date){
		this.date = date;
	}
	
	public String getDate(){
		return this.date;
	}
		
	public void setTime(String time){
		this.time = time;
	}
	
	public String getTime(){
		return this.time;
	}
		
	public void setCar(Integer car){
		this.car = car;
	}
	
	public Integer getCar(){
		return this.car;
	}
		
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return this.address;
	}
		
	public void setLatitude(Double latitude){
		this.latitude = latitude;
	}
	
	public Double getLatitude(){
		return this.latitude;
	}
		
	public void setLongitude(Double longitude){
		this.longitude = longitude;
	}
	
	public Double getLongitude(){
		return this.longitude;
	}
		
	public void setPrice(Double price){
		this.price = price;
	}
	
	public Double getPrice(){
		return this.price;
	}
		
	public void setState(String state){
		this.state = state;
	}
	
	public String getState(){
		return this.state;
	}
		
	public void setReceivedUser(Long receivedUser){
		this.receivedUser = receivedUser;
	}
	
	public Long getReceivedUser(){
		return this.receivedUser;
	}
		
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return this.createTime;
	}
		
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}
		
		
}