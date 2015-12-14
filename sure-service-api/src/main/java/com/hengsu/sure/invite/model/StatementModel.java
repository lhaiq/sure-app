package com.hengsu.sure.invite.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.invite.entity.Statement")
public class StatementModel{
	
	private Long id;
	private String name;
	private Integer type;
	private Double money;
	private Date time;
	private Long userId;
		
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
		
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return this.type;
	}
		
	public void setMoney(Double money){
		this.money = money;
	}
	
	public Double getMoney(){
		return this.money;
	}
		
	public void setTime(Date time){
		this.time = time;
	}
	
	public Date getTime(){
		return this.time;
	}
		
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return this.userId;
	}
		
		
}