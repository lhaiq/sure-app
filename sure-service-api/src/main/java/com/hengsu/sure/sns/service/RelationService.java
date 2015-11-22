
package com.hengsu.sure.sns.service;

import com.hengsu.sure.sns.model.RelationModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RelationService{
	
	public int create(RelationModel relationModel);
	
	public int createSelective(RelationModel relationModel);
	
	public RelationModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(RelationModel relationModel);
	
	public int updateByPrimaryKeySelective(RelationModel relationModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(RelationModel relationModel);

	public void addRelation(RelationModel relationModel);

	public void deleteRelation(Long fromUser,Long toUser);

	public List<RelationModel> listRelations(RelationModel relationModel,Pageable pageable);
	
}