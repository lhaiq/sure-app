package com.hengsu.sure.sns.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.sns.entity.Relation")
public class RelationModel{
	
	private Long id;
	private Long fromUser;
	private Long toUser;
	private Date time;
	private Integer type;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setFromUser(Long fromUser){
		this.fromUser = fromUser;
	}
	
	public Long getFromUser(){
		return this.fromUser;
	}
		
	public void setToUser(Long toUser){
		this.toUser = toUser;
	}
	
	public Long getToUser(){
		return this.toUser;
	}
		
	public void setTime(Date time){
		this.time = time;
	}
	
	public Date getTime(){
		return this.time;
	}
		
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return this.type;
	}
		
		
}