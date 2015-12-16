package com.hengsu.sure.invite.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.invite.entity.Rent")
public class RentModel{
	
	private Long id;
	private Long userId;
	private Integer city;
	private String scene;
	private Integer gender;
	private String date;
	private String time;
	private Integer car;
	private Double price;
	private Double money;
	private String state;
	private Date createTime;
	private Integer status;
	private String images;
		
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
		
	public void setPrice(Double price){
		this.price = price;
	}
	
	public Double getPrice(){
		return this.price;
	}
		
	public void setMoney(Double money){
		this.money = money;
	}
	
	public Double getMoney(){
		return this.money;
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
		
	public void setImages(String images){
		this.images = images;
	}
	
	public String getImages(){
		return this.images;
	}
		
		
}