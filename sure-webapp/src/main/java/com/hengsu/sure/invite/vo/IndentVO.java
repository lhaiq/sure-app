package com.hengsu.sure.invite.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.invite.model.IndentModel")
public class IndentVO{
	
	private Long id;
	private Long userId;
	private Integer cityId;
	private String scene;
	private Integer gender;
	private String age;
	private String date;
	private String time;
	private String car;
	private String price;
	private String state;
	private Date createTime;
	private Integer status;
	private Long relationId;
	private Integer type;
	private Double longitude;
	private Double latitude;
		
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
		
	public void setCityId(Integer cityId){
		this.cityId = cityId;
	}
	
	public Integer getCityId(){
		return this.cityId;
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
		
	public void setCar(String car){
		this.car = car;
	}
	
	public String getCar(){
		return this.car;
	}
		
	public void setPrice(String price){
		this.price = price;
	}
	
	public String getPrice(){
		return this.price;
	}
		
	public void setState(String state){
		this.state = state;
	}
	
	public String getState(){
		return this.state;
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
		
	public void setRelationId(Long relationId){
		this.relationId = relationId;
	}
	
	public Long getRelationId(){
		return this.relationId;
	}
		
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return this.type;
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
		
		
}