
package com.hengsu.sure.invite.service;

import com.hengsu.sure.invite.model.IndentModel;
import java.util.Date;

public interface IndentService{
	
	public int create(IndentModel indentModel);
	
	public int createSelective(IndentModel indentModel);
	
	public IndentModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(IndentModel indentModel);
	
	public int updateByPrimaryKeySelective(IndentModel indentModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(IndentModel indentModel);
	
}