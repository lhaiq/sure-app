package com.hengsu.sure.invite.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.invite.entity.InvitationReceive")
public class InvitationReceiveModel{
	
	private Long id;
	private Long invitationId;
	private Long userId;
	private Date time;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setInvitationId(Long invitationId){
		this.invitationId = invitationId;
	}
	
	public Long getInvitationId(){
		return this.invitationId;
	}
		
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return this.userId;
	}
		
	public void setTime(Date time){
		this.time = time;
	}
	
	public Date getTime(){
		return this.time;
	}
		
		
}