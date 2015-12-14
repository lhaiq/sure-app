package com.hengsu.sure.auth.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

import javax.validation.constraints.NotNull;
import java.util.Date;

@MapClass("com.hengsu.sure.auth.model.UserLBSModel")
public class UserLBSVO{

	@NotNull
	private Double longitude;
	@NotNull
	private Double latitude;
		
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