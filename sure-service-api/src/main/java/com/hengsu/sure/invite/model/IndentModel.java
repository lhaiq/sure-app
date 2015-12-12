package com.hengsu.sure.invite.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.invite.entity.Indent")
public class IndentModel{
	
	private Long id;
	private String indentNo;
	private Long customerId;
	private Long sellerId;
	private Long tradeId;
	private Double money;
	private Integer status;
	private Long referId;
	private Integer type;
	private Double longitude;
	private Double latitude;
	private Date createTime;
	private Date applyTime;
	private Date canceledTime;
	private String snapshot;
	private String cancelReason;
	private Long handleId;
	private Date startTime;
	private Date endTime;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setIndentNo(String indentNo){
		this.indentNo = indentNo;
	}
	
	public String getIndentNo(){
		return this.indentNo;
	}
		
	public void setCustomerId(Long customerId){
		this.customerId = customerId;
	}
	
	public Long getCustomerId(){
		return this.customerId;
	}
		
	public void setSellerId(Long sellerId){
		this.sellerId = sellerId;
	}
	
	public Long getSellerId(){
		return this.sellerId;
	}
		
	public void setTradeId(Long tradeId){
		this.tradeId = tradeId;
	}
	
	public Long getTradeId(){
		return this.tradeId;
	}
		
	public void setMoney(Double money){
		this.money = money;
	}
	
	public Double getMoney(){
		return this.money;
	}
		
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}
		
	public void setReferId(Long referId){
		this.referId = referId;
	}
	
	public Long getReferId(){
		return this.referId;
	}
		
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return this.type;
	}
		
	public void setLongitude(Double longitude){
		this.longitude = longitude;
	}
	
	public Double getLongitude(){
		return this.longitude;
	}
		
	public void setLatitude(Double latitude){
		this.latitude = latitude;
	}
	
	public Double getLatitude(){
		return this.latitude;
	}
		
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return this.createTime;
	}
		
	public void setApplyTime(Date applyTime){
		this.applyTime = applyTime;
	}
	
	public Date getApplyTime(){
		return this.applyTime;
	}
		
	public void setCanceledTime(Date canceledTime){
		this.canceledTime = canceledTime;
	}
	
	public Date getCanceledTime(){
		return this.canceledTime;
	}
		
	public void setSnapshot(String snapshot){
		this.snapshot = snapshot;
	}
	
	public String getSnapshot(){
		return this.snapshot;
	}
		
	public void setCancelReason(String cancelReason){
		this.cancelReason = cancelReason;
	}
	
	public String getCancelReason(){
		return this.cancelReason;
	}
		
	public void setHandleId(Long handleId){
		this.handleId = handleId;
	}
	
	public Long getHandleId(){
		return this.handleId;
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
		
		
}