package com.hengsu.sure.sns.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import com.hkntv.pylon.core.beans.mapping.annotation.MapField;

import java.util.Date;

@MapClass("com.hengsu.sure.sns.entity.Comment")
public class CommentModel{

	private Long id;
	@MapField("mId")
	private Long mId;
	private String content;
	private Date time;
	private Integer type;
	private Long userid;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}



	public void setmId(Long mId) {
		this.mId = mId;
	}

	public Long getmId() {
		return mId;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}

	public void setTime(Date time){
		this.time = time;
	}

	public Date getTime(){
		return this.time;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public Integer getType(){
		return this.type;
	}

	public void setUserid(Long userid){
		this.userid = userid;
	}

	public Long getUserid(){
		return this.userid;
	}


}