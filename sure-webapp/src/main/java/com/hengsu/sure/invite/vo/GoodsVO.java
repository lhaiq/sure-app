package com.hengsu.sure.invite.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;
import java.util.List;

@MapClass("com.hengsu.sure.invite.model.GoodsModel")
public class GoodsVO{
	
	private Long id;
	private String name;
	private List<String> imageIds;
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

	public void setImageIds(List<String> imageIds) {
		this.imageIds = imageIds;
	}

	public List<String> getImageIds() {
		return imageIds;
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