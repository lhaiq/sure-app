
package com.hengsu.sure.auth.service;

import com.hengsu.sure.auth.model.SubAccountModel;

public interface SubAccountService{
	
	public int create(SubAccountModel subAccountModel);
	
	public int createSelective(SubAccountModel subAccountModel);
	
	public SubAccountModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(SubAccountModel subAccountModel);
	
	public int updateByPrimaryKeySelective(SubAccountModel subAccountModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(SubAccountModel subAccountModel);
	
}