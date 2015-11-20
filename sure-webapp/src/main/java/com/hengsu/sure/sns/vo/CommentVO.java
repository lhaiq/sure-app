package com.hengsu.sure.sns.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.sns.model.CommentModel")
public class CommentVO{
	
	private Long id;
	private Long mId;
	private String content;
	private Date time;
	private Integer type;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setMId(Long mId){
		this.mId = mId;
	}
	
	public Long getMId(){
		return this.mId;
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
		
		
}