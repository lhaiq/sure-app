package com.hengsu.sure.core.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.core.model.ImageModel")
public class ImageVO{
	
	private Long id;
	private String filename;
	private String path;
	private Date time;
	private String contentType;
		
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
		
		
}