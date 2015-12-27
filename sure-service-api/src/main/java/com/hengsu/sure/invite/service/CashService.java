
package com.hengsu.sure.invite.service;

import com.hengsu.sure.invite.model.CashModel;
import java.util.Date;

public interface CashService{
	
	public int create(CashModel cashModel);
	
	public int createSelective(CashModel cashModel);
	
	public CashModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(CashModel cashModel);
	
	public int updateByPrimaryKeySelective(CashModel cashModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(CashModel cashModel);

	public void applyCash(CashModel cashModel);
	
}