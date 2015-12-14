
package com.hengsu.sure.auth.service;

import com.hengsu.sure.auth.model.UserLBSModel;

public interface UserLBSService{
	
	public int create(UserLBSModel userLBSModel);
	
	public int createSelective(UserLBSModel userLBSModel);
	
	public UserLBSModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(UserLBSModel userLBSModel);
	
	public int updateByPrimaryKeySelective(UserLBSModel userLBSModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(UserLBSModel userLBSModel);
	
}