
package com.hengsu.sure.auth.service;

import com.hengsu.sure.auth.model.FriendModel;
import java.util.Date;

public interface FriendService{
	
	public int create(FriendModel friendModel);
	
	public int createSelective(FriendModel friendModel);
	
	public FriendModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(FriendModel friendModel);
	
	public int updateByPrimaryKeySelective(FriendModel friendModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(FriendModel friendModel);
	
}