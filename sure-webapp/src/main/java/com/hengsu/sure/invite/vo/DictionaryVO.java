package com.hengsu.sure.invite.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.sure.invite.model.DictionaryModel")
public class DictionaryVO{
	
	private Long id;
	private String content;
	private String description;
	private String type;
		
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
		
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
		
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
		
		
}