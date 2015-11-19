package com.hengsu.sure.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.auth.entity.Friend;
import com.hengsu.sure.auth.repository.FriendRepository;
import com.hengsu.sure.auth.model.FriendModel;
import com.hengsu.sure.auth.service.FriendService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private FriendRepository friendRepo;

	@Transactional
	@Override
	public int create(FriendModel friendModel) {
		return friendRepo.insert(beanMapper.map(friendModel, Friend.class));
	}

	@Transactional
	@Override
	public int createSelective(FriendModel friendModel) {
		return friendRepo.insertSelective(beanMapper.map(friendModel, Friend.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return friendRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public FriendModel findByPrimaryKey(Long id) {
		Friend friend = friendRepo.selectByPrimaryKey(id);
		return beanMapper.map(friend, FriendModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(FriendModel friendModel) {
		return friendRepo.selectCount(beanMapper.map(friendModel, Friend.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(FriendModel friendModel) {
		return friendRepo.updateByPrimaryKey(beanMapper.map(friendModel, Friend.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(FriendModel friendModel) {
		return friendRepo.updateByPrimaryKeySelective(beanMapper.map(friendModel, Friend.class));
	}

}
