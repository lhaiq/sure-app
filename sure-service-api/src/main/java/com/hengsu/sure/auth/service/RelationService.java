
package com.hengsu.sure.auth.service;

import com.hengsu.sure.auth.model.RelationModel;
import java.util.Date;

public interface RelationService{
	
	public int create(RelationModel relationModel);
	
	public int createSelective(RelationModel relationModel);
	
	public RelationModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(RelationModel relationModel);
	
	public int updateByPrimaryKeySelective(RelationModel relationModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(RelationModel relationModel);
	
}