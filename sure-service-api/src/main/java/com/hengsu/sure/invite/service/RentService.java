
package com.hengsu.sure.invite.service;

import com.hengsu.sure.invite.model.RentModel;
import java.util.Date;
import java.util.List;

public interface RentService{
	
	public int create(RentModel rentModel);
	
	public int createSelective(RentModel rentModel);
	
	public RentModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(RentModel rentModel);
	
	public int updateByPrimaryKeySelective(RentModel rentModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(RentModel rentModel);

	public void publishRent(RentModel rentModel);

//	public List<RentModel> queryRent()
	
}