package com.hengsu.sure.invite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.invite.entity.InvitationReceive;
import com.hengsu.sure.invite.repository.InvitationReceiveRepository;
import com.hengsu.sure.invite.model.InvitationReceiveModel;
import com.hengsu.sure.invite.service.InvitationReceiveService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class InvitationReceiveServiceImpl implements InvitationReceiveService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private InvitationReceiveRepository invitationReceiveRepo;

	@Transactional
	@Override
	public int create(InvitationReceiveModel invitationReceiveModel) {
		return invitationReceiveRepo.insert(beanMapper.map(invitationReceiveModel, InvitationReceive.class));
	}

	@Transactional
	@Override
	public int createSelective(InvitationReceiveModel invitationReceiveModel) {
		return invitationReceiveRepo.insertSelective(beanMapper.map(invitationReceiveModel, InvitationReceive.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return invitationReceiveRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public InvitationReceiveModel findByPrimaryKey(Long id) {
		InvitationReceive invitationReceive = invitationReceiveRepo.selectByPrimaryKey(id);
		return beanMapper.map(invitationReceive, InvitationReceiveModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(InvitationReceiveModel invitationReceiveModel) {
		return invitationReceiveRepo.selectCount(beanMapper.map(invitationReceiveModel, InvitationReceive.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(InvitationReceiveModel invitationReceiveModel) {
		return invitationReceiveRepo.updateByPrimaryKey(beanMapper.map(invitationReceiveModel, InvitationReceive.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(InvitationReceiveModel invitationReceiveModel) {
		return invitationReceiveRepo.updateByPrimaryKeySelective(beanMapper.map(invitationReceiveModel, InvitationReceive.class));
	}

}
