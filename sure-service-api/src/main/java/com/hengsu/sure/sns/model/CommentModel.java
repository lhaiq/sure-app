package com.hengsu.sure.sns.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.sns.entity.Comment")
public class CommentModel{
	
	private Long id;
	private Long mid;
	private String content;
	private Date time;
	private Integer type;
	private Long userId;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		

	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return this.content;
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

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public Long getMid() {
		return mid;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Long getUserId() {
		return userId;
	}

}