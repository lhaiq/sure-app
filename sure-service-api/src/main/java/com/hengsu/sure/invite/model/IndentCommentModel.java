package com.hengsu.sure.invite.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.invite.entity.IndentComment")
public class IndentCommentModel{
	
	private Long id;
	private Double faceScore;
	private Double credit;
	private String content;
	private Long indentId;
	private Long userId;
	private Date createTime;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setFaceScore(Double faceScore){
		this.faceScore = faceScore;
	}
	
	public Double getFaceScore(){
		return this.faceScore;
	}
		
	public void setCredit(Double credit){
		this.credit = credit;
	}
	
	public Double getCredit(){
		return this.credit;
	}
		
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return this.content;
	}
		
	public void setIndentId(Long indentId){
		this.indentId = indentId;
	}
	
	public Long getIndentId(){
		return this.indentId;
	}
		
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return this.userId;
	}
		
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return this.createTime;
	}
		
		
}