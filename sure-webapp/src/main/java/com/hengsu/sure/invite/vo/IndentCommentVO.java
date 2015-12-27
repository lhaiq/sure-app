package com.hengsu.sure.invite.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

import javax.validation.constraints.NotNull;
import java.util.Date;

@MapClass("com.hengsu.sure.invite.model.IndentCommentModel")
public class IndentCommentVO{
	
	private Long id;
	@NotNull
	private Double faceScore;
	@NotNull
	private Double credit;
	private String content;

		
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
		

		
		
}