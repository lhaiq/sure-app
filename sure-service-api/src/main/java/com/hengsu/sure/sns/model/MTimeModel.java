package com.hengsu.sure.sns.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;
import java.util.List;

@MapClass("com.hengsu.sure.sns.entity.MTime")
public class MTimeModel{
	
	private Long id;
	private Long userId;
	private Date time;
	private Integer cityId;
	private Double longitude;
	private Double latitude;
	private String content;
	private String images;
	private List<String> imageIds;
	private Long commentsCount;
	private Long statusesCount;
		
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
		
	public void setImages(String images){
		this.images = images;
	}
	
	public String getImages(){
		return this.images;
	}

	public void setImageIds(List<String> imageIds) {
		this.imageIds = imageIds;
	}

	public List<String> getImageIds() {
		return imageIds;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public Long getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(Long commentsCount) {
		this.commentsCount = commentsCount;
	}

	public Long getStatusesCount() {
		return statusesCount;
	}

	public void setStatusesCount(Long statusesCount) {
		this.statusesCount = statusesCount;
	}
}