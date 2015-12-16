package com.hengsu.sure.invite.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.invite.entity.Goods")
public class GoodsModel{
	
	private Long id;
	private String name;
	private String images;
	private Date startTime;
	private Date endTime;
	private String description;
	private Integer cityId;
	private Integer quantity;
	private Double price;
	private Double money;
	private Long goodsType;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
		
	public void setImages(String images){
		this.images = images;
	}
	
	public String getImages(){
		return this.images;
	}
		
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	public Date getStartTime(){
		return this.startTime;
	}
		
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	public Date getEndTime(){
		return this.endTime;
	}
		
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
		
	public void setCityId(Integer cityId){
		this.cityId = cityId;
	}
	
	public Integer getCityId(){
		return this.cityId;
	}
		
	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}
	
	public Integer getQuantity(){
		return this.quantity;
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
		
	public void setGoodsType(Long goodsType){
		this.goodsType = goodsType;
	}
	
	public Long getGoodsType(){
		return this.goodsType;
	}
		
		
}