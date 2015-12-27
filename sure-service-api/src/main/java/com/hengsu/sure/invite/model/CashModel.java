package com.hengsu.sure.invite.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.invite.entity.Cash")
public class CashModel{
	
	private Long id;
	private Long userId;
	private Long handlerId;
	private Date createTime;
	private Double money;
	private String desc;
	private Date finishTime;
	private Integer status;
	private Integer type;
	private Long indentId;
	private Integer expireHour;
	private Double poundage;
	private Double rate;
		
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
		
	public void setHandlerId(Long handlerId){
		this.handlerId = handlerId;
	}
	
	public Long getHandlerId(){
		return this.handlerId;
	}
		
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return this.createTime;
	}
		
	public void setMoney(Double money){
		this.money = money;
	}
	
	public Double getMoney(){
		return this.money;
	}
		
	public void setDesc(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return this.desc;
	}
		
	public void setFinishTime(Date finishTime){
		this.finishTime = finishTime;
	}
	
	public Date getFinishTime(){
		return this.finishTime;
	}
		
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}
		
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return this.type;
	}
		
	public void setIndentId(Long indentId){
		this.indentId = indentId;
	}
	
	public Long getIndentId(){
		return this.indentId;
	}
		
	public void setExpireHour(Integer expireHour){
		this.expireHour = expireHour;
	}
	
	public Integer getExpireHour(){
		return this.expireHour;
	}
		
	public void setPoundage(Double poundage){
		this.poundage = poundage;
	}
	
	public Double getPoundage(){
		return this.poundage;
	}
		
	public void setRate(Double rate){
		this.rate = rate;
	}
	
	public Double getRate(){
		return this.rate;
	}
		
		
}