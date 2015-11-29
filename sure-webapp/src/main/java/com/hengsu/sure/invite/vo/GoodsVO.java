package com.hengsu.sure.invite.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.invite.model.GoodsModel")
public class GoodsVO{
	
	private Long id;
	private String name;
	private String images;
	private Date startTime;
	private Date endTime;
	private String desc;
	private String address;
	private Integer invoice;
	private String invoiceDesc;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
		
	public void setImages(String images){
		this.images = images;
	}
	
	public String getImages(){
		return this.images;
	}
		
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	public Date getStartTime(){
		return this.startTime;
	}
		
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	public Date getEndTime(){
		return this.endTime;
	}
		
	public void setDesc(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return this.desc;
	}
		
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return this.address;
	}
		
	public void setInvoice(Integer invoice){
		this.invoice = invoice;
	}
	
	public Integer getInvoice(){
		return this.invoice;
	}
		
	public void setInvoiceDesc(String invoiceDesc){
		this.invoiceDesc = invoiceDesc;
	}
	
	public String getInvoiceDesc(){
		return this.invoiceDesc;
	}
		
		
}