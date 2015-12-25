package com.hengsu.sure.auth.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.sure.auth.entity.SubAccount")
public class SubAccountModel{
	
	private Long id;
	private String subAccountSid;
	private String subToken;
	private String dateCreated;
	private String voipAccount;
	private String voipPwd;
	private Long userId;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setSubAccountSid(String subAccountSid){
		this.subAccountSid = subAccountSid;
	}
	
	public String getSubAccountSid(){
		return this.subAccountSid;
	}
		
	public void setSubToken(String subToken){
		this.subToken = subToken;
	}
	
	public String getSubToken(){
		return this.subToken;
	}
		
	public void setDateCreated(String dateCreated){
		this.dateCreated = dateCreated;
	}
	
	public String getDateCreated(){
		return this.dateCreated;
	}
		
	public void setVoipAccount(String voipAccount){
		this.voipAccount = voipAccount;
	}
	
	public String getVoipAccount(){
		return this.voipAccount;
	}
		
	public void setVoipPwd(String voipPwd){
		this.voipPwd = voipPwd;
	}
	
	public String getVoipPwd(){
		return this.voipPwd;
	}
		
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return this.userId;
	}
		
		
}