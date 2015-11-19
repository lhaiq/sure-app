
package com.hengsu.sure.auth.service;

import com.hengsu.sure.auth.model.CommentModel;
import java.util.Date;

public interface CommentService{
	
	public int create(CommentModel commentModel);
	
	public int createSelective(CommentModel commentModel);
	
	public CommentModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(CommentModel commentModel);
	
	public int updateByPrimaryKeySelective(CommentModel commentModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(CommentModel commentModel);
	
}