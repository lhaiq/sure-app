package com.hengsu.sure.core.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

import java.io.InputStream;
import java.util.Date;

@MapClass("com.hengsu.sure.core.entity.Image")
public class ImageModel{
	
	private Long id;
	private String filename;
	private String path;
	private Date time;
	private String contentType;
	private Long length;
	private InputStream content;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setFilename(String filename){
		this.filename = filename;
	}
	
	public String getFilename(){
		return this.filename;
	}
		
	public void setPath(String path){
		this.path = path;
	}
	
	public String getPath(){
		return this.path;
	}
		
	public void setTime(Date time){
		this.time = time;
	}
	
	public Date getTime(){
		return this.time;
	}
		
	public void setContentType(String contentType){
		this.contentType = contentType;
	}
	
	public String getContentType(){
		return this.contentType;
	}
		
	public void setLength(Long length){
		this.length = length;
	}
	
	public Long getLength(){
		return this.length;
	}

	public void setContent(InputStream content) {
		this.content = content;
	}

	public InputStream getContent() {
		return content;
	}
}