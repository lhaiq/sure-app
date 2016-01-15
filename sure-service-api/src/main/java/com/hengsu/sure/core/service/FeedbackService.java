
package com.hengsu.sure.core.service;

import com.hengsu.sure.core.model.FeedbackModel;
import java.util.Date;

public interface FeedbackService{
	
	public int create(FeedbackModel feedbackModel);
	
	public int createSelective(FeedbackModel feedbackModel);
	
	public FeedbackModel findByPrimaryKey(Integer id);
	
	public int updateByPrimaryKey(FeedbackModel feedbackModel);
	
	public int updateByPrimaryKeySelective(FeedbackModel feedbackModel);
	
	public int deleteByPrimaryKey(Integer id);
	
	public int selectCount(FeedbackModel feedbackModel);
	
}