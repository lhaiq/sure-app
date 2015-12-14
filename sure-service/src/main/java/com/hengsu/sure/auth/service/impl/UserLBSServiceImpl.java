package com.hengsu.sure.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.auth.entity.UserLBS;
import com.hengsu.sure.auth.repository.UserLBSRepository;
import com.hengsu.sure.auth.model.UserLBSModel;
import com.hengsu.sure.auth.service.UserLBSService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class UserLBSServiceImpl implements UserLBSService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private UserLBSRepository userLBSRepo;

	@Transactional
	@Override
	public int create(UserLBSModel userLBSModel) {
		return userLBSRepo.insert(beanMapper.map(userLBSModel, UserLBS.class));
	}

	@Transactional
	@Override
	public int createSelective(UserLBSModel userLBSModel) {
		return userLBSRepo.insertSelective(beanMapper.map(userLBSModel, UserLBS.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return userLBSRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public UserLBSModel findByPrimaryKey(Long id) {
		UserLBS userLBS = userLBSRepo.selectByPrimaryKey(id);
		return beanMapper.map(userLBS, UserLBSModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(UserLBSModel userLBSModel) {
		return userLBSRepo.selectCount(beanMapper.map(userLBSModel, UserLBS.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(UserLBSModel userLBSModel) {
		return userLBSRepo.updateByPrimaryKey(beanMapper.map(userLBSModel, UserLBS.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(UserLBSModel userLBSModel) {
		return userLBSRepo.updateByPrimaryKeySelective(beanMapper.map(userLBSModel, UserLBS.class));
	}

}
