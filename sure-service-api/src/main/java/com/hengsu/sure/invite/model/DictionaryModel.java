package com.hengsu.sure.invite.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.sure.invite.entity.Dictionary")
public class DictionaryModel{
	
	private Long id;
	private String content;
	private String description;
	private Integer type;
		
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

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}
}