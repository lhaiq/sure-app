package com.hengsu.sure.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.auth.entity.SubAccount;
import com.hengsu.sure.auth.repository.SubAccountRepository;
import com.hengsu.sure.auth.model.SubAccountModel;
import com.hengsu.sure.auth.service.SubAccountService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class SubAccountServiceImpl implements SubAccountService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private SubAccountRepository subAccountRepo;

	@Transactional
	@Override
	public int create(SubAccountModel subAccountModel) {
		return subAccountRepo.insert(beanMapper.map(subAccountModel, SubAccount.class));
	}

	@Transactional
	@Override
	public int createSelective(SubAccountModel subAccountModel) {
		return subAccountRepo.insertSelective(beanMapper.map(subAccountModel, SubAccount.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return subAccountRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public SubAccountModel findByPrimaryKey(Long id) {
		SubAccount subAccount = subAccountRepo.selectByPrimaryKey(id);
		return beanMapper.map(subAccount, SubAccountModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(SubAccountModel subAccountModel) {
		return subAccountRepo.selectCount(beanMapper.map(subAccountModel, SubAccount.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(SubAccountModel subAccountModel) {
		return subAccountRepo.updateByPrimaryKey(beanMapper.map(subAccountModel, SubAccount.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(SubAccountModel subAccountModel) {
		return subAccountRepo.updateByPrimaryKeySelective(beanMapper.map(subAccountModel, SubAccount.class));
	}

}
