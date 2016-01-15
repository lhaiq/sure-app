package com.hengsu.sure.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.core.entity.Feedback;
import com.hengsu.sure.core.repository.FeedbackRepository;
import com.hengsu.sure.core.model.FeedbackModel;
import com.hengsu.sure.core.service.FeedbackService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private FeedbackRepository feedbackRepo;

	@Transactional
	@Override
	public int create(FeedbackModel feedbackModel) {
		return feedbackRepo.insert(beanMapper.map(feedbackModel, Feedback.class));
	}

	@Transactional
	@Override
	public int createSelective(FeedbackModel feedbackModel) {
		return feedbackRepo.insertSelective(beanMapper.map(feedbackModel, Feedback.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return feedbackRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public FeedbackModel findByPrimaryKey(Integer id) {
		Feedback feedback = feedbackRepo.selectByPrimaryKey(id);
		return beanMapper.map(feedback, FeedbackModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(FeedbackModel feedbackModel) {
		return feedbackRepo.selectCount(beanMapper.map(feedbackModel, Feedback.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(FeedbackModel feedbackModel) {
		return feedbackRepo.updateByPrimaryKey(beanMapper.map(feedbackModel, Feedback.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(FeedbackModel feedbackModel) {
		return feedbackRepo.updateByPrimaryKeySelective(beanMapper.map(feedbackModel, Feedback.class));
	}

}
