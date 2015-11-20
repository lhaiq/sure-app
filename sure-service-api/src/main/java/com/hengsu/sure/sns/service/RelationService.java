
package com.hengsu.sure.sns.service;

import com.hengsu.sure.sns.model.RelationModel;

public interface RelationService{
	
	public int create(RelationModel relationModel);
	
	public int createSelective(RelationModel relationModel);
	
	public RelationModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(RelationModel relationModel);
	
	public int updateByPrimaryKeySelective(RelationModel relationModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(RelationModel relationModel);
	
}