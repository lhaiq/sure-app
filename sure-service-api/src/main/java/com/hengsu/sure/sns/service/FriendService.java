
package com.hengsu.sure.sns.service;

import com.hengsu.sure.sns.model.FriendModel;

public interface FriendService{
	
	public int create(FriendModel friendModel);
	
	public int createSelective(FriendModel friendModel);
	
	public FriendModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(FriendModel friendModel);
	
	public int updateByPrimaryKeySelective(FriendModel friendModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(FriendModel friendModel);
	
}