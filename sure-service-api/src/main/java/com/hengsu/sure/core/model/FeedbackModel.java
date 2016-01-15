package com.hengsu.sure.core.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.core.entity.Feedback")
public class FeedbackModel{
	
	private Integer id;
	private Long userId;
	private Date feedbackDate;
	private String content;
		
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
		
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return this.userId;
	}
		
	public void setFeedbackDate(Date feedbackDate){
		this.feedbackDate = feedbackDate;
	}
	
	public Date getFeedbackDate(){
		return this.feedbackDate;
	}
		
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return this.content;
	}
		
		
}