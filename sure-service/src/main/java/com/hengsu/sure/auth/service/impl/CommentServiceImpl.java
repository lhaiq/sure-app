package com.hengsu.sure.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.auth.entity.Comment;
import com.hengsu.sure.auth.repository.CommentRepository;
import com.hengsu.sure.auth.model.CommentModel;
import com.hengsu.sure.auth.service.CommentService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private CommentRepository commentRepo;

	@Transactional
	@Override
	public int create(CommentModel commentModel) {
		return commentRepo.insert(beanMapper.map(commentModel, Comment.class));
	}

	@Transactional
	@Override
	public int createSelective(CommentModel commentModel) {
		return commentRepo.insertSelective(beanMapper.map(commentModel, Comment.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return commentRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public CommentModel findByPrimaryKey(Long id) {
		Comment comment = commentRepo.selectByPrimaryKey(id);
		return beanMapper.map(comment, CommentModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(CommentModel commentModel) {
		return commentRepo.selectCount(beanMapper.map(commentModel, Comment.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(CommentModel commentModel) {
		return commentRepo.updateByPrimaryKey(beanMapper.map(commentModel, Comment.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(CommentModel commentModel) {
		return commentRepo.updateByPrimaryKeySelective(beanMapper.map(commentModel, Comment.class));
	}

}
