package com.hengsu.sure.auth.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.invite.entity.UserLBS")
public class UserLBSModel{
	
	private Long id;
	private Long userId;
	private Double longitude;
	private Double latitude;
	private Date time;
		
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
		
	public void setTime(Date time){
		this.time = time;
	}
	
	public Date getTime(){
		return this.time;
	}
		
		
}