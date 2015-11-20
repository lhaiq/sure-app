
package com.hengsu.sure.sns.service;

import com.hengsu.sure.sns.model.CommentModel;

public interface CommentService{
	
	public int create(CommentModel commentModel);
	
	public int createSelective(CommentModel commentModel);
	
	public CommentModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(CommentModel commentModel);
	
	public int updateByPrimaryKeySelective(CommentModel commentModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(CommentModel commentModel);
	
}