
package com.hengsu.sure.invite.service;

import com.hengsu.sure.invite.model.IndentCommentModel;

public interface IndentCommentService{
	
	public int create(IndentCommentModel indentCommentModel);
	
	public int createSelective(IndentCommentModel indentCommentModel);
	
	public IndentCommentModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(IndentCommentModel indentCommentModel);
	
	public int updateByPrimaryKeySelective(IndentCommentModel indentCommentModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(IndentCommentModel indentCommentModel);
	
}