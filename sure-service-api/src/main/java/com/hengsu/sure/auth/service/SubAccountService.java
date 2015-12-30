
package com.hengsu.sure.auth.service;

import com.hengsu.sure.auth.model.SubAccountModel;

public interface SubAccountService{
	
	public int create(SubAccountModel subAccountModel);
	
	public int createSelective(SubAccountModel subAccountModel);
	
	public SubAccountModel findByPrimaryKey(Long id);

	public SubAccountModel findOrCreateSubAccount(Long userId);
	
}