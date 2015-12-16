package com.hengsu.sure.invite.service.impl;

import com.hengsu.sure.invite.RentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.invite.entity.Rent;
import com.hengsu.sure.invite.repository.RentRepository;
import com.hengsu.sure.invite.model.RentModel;
import com.hengsu.sure.invite.service.RentService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.Date;

@Service
public class RentServiceImpl implements RentService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private RentRepository rentRepo;

	@Transactional
	@Override
	public int create(RentModel rentModel) {
		return rentRepo.insert(beanMapper.map(rentModel, Rent.class));
	}

	@Transactional
	@Override
	public int createSelective(RentModel rentModel) {
		return rentRepo.insertSelective(beanMapper.map(rentModel, Rent.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return rentRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public RentModel findByPrimaryKey(Long id) {
		Rent rent = rentRepo.selectByPrimaryKey(id);
		return beanMapper.map(rent, RentModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(RentModel rentModel) {
		return rentRepo.selectCount(beanMapper.map(rentModel, Rent.class));
	}

	@Transactional
	@Override
	public void publishRent(RentModel rentModel) {

		//设置时间
		rentModel.setCreateTime(new Date());
		rentModel.setStatus(RentStatus.PUBLISH.getCode());
		createSelective(rentModel);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(RentModel rentModel) {
		return rentRepo.updateByPrimaryKey(beanMapper.map(rentModel, Rent.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(RentModel rentModel) {
		return rentRepo.updateByPrimaryKeySelective(beanMapper.map(rentModel, Rent.class));
	}

}
