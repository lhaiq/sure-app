package com.hengsu.sure.core.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

@MapClass("com.hengsu.sure.core.model.FeedbackModel")
public class FeedbackVO{

	@NotEmpty
	private String content;

	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return this.content;
	}
		
		
}