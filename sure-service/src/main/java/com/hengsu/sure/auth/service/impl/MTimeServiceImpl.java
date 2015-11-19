package com.hengsu.sure.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.auth.entity.MTime;
import com.hengsu.sure.auth.repository.MTimeRepository;
import com.hengsu.sure.auth.model.MTimeModel;
import com.hengsu.sure.auth.service.MTimeService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class MTimeServiceImpl implements MTimeService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private MTimeRepository mTimeRepo;

	@Transactional
	@Override
	public int create(MTimeModel mTimeModel) {
		return mTimeRepo.insert(beanMapper.map(mTimeModel, MTime.class));
	}

	@Transactional
	@Override
	public int createSelective(MTimeModel mTimeModel) {
		return mTimeRepo.insertSelective(beanMapper.map(mTimeModel, MTime.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return mTimeRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public MTimeModel findByPrimaryKey(Long id) {
		MTime mTime = mTimeRepo.selectByPrimaryKey(id);
		return beanMapper.map(mTime, MTimeModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(MTimeModel mTimeModel) {
		return mTimeRepo.selectCount(beanMapper.map(mTimeModel, MTime.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(MTimeModel mTimeModel) {
		return mTimeRepo.updateByPrimaryKey(beanMapper.map(mTimeModel, MTime.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(MTimeModel mTimeModel) {
		return mTimeRepo.updateByPrimaryKeySelective(beanMapper.map(mTimeModel, MTime.class));
	}

}
