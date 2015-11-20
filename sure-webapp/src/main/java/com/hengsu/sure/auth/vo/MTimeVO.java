package com.hengsu.sure.auth.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.auth.model.MTimeModel")
public class MTimeVO{
	
	private Long userId;
	private Date time;
	private Double longitude;
	private Double latitude;
	private String content;


	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return this.userId;
	}
		
	public void setTime(Date time){
		this.time = time;
	}
	
	public Date getTime(){
		return this.time;
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
		
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return this.content;
	}
		

}