package com.hengsu.sure.sns.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.sns.model.RelationModel")
public class RelationVO{
	
	private Long id;
	private Long fromUser;
	private Long toUser;
	private Date time;
		
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
		
		
}