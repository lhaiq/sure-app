
package com.hengsu.sure.invite.service;

import com.hengsu.sure.invite.model.TradeModel;
import java.util.Date;

public interface TradeService{
	
	public int create(TradeModel tradeModel);
	
	public int createSelective(TradeModel tradeModel);
	
	public TradeModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(TradeModel tradeModel);
	
	public int updateByPrimaryKeySelective(TradeModel tradeModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(TradeModel tradeModel);
	
}