
package com.hengsu.sure.invite.service;

import com.hengsu.sure.invite.model.StatementModel;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface StatementService{
	
	public int create(StatementModel statementModel);
	
	public int createSelective(StatementModel statementModel);
	
	public StatementModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(StatementModel statementModel);
	
	public int updateByPrimaryKeySelective(StatementModel statementModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(StatementModel statementModel);

	public List<StatementModel> selectPage(StatementModel statementModel,Pageable pageable);
	
}