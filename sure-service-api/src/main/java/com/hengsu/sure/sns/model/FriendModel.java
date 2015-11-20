package com.hengsu.sure.sns.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.sns.entity.Friend")
public class FriendModel{
	
	private Long id;
	private Long aId;
	private Long bId;
	private Date time;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setAId(Long aId){
		this.aId = aId;
	}
	
	public Long getAId(){
		return this.aId;
	}
		
	public void setBId(Long bId){
		this.bId = bId;
	}
	
	public Long getBId(){
		return this.bId;
	}
		
	public void setTime(Date time){
		this.time = time;
	}
	
	public Date getTime(){
		return this.time;
	}
		
		
}