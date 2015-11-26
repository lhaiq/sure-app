package com.hengsu.sure.invite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.invite.entity.Indent;
import com.hengsu.sure.invite.repository.IndentRepository;
import com.hengsu.sure.invite.model.IndentModel;
import com.hengsu.sure.invite.service.IndentService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class IndentServiceImpl implements IndentService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private IndentRepository indentRepo;

	@Transactional
	@Override
	public int create(IndentModel indentModel) {
		return indentRepo.insert(beanMapper.map(indentModel, Indent.class));
	}

	@Transactional
	@Override
	public int createSelective(IndentModel indentModel) {
		return indentRepo.insertSelective(beanMapper.map(indentModel, Indent.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return indentRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public IndentModel findByPrimaryKey(Long id) {
		Indent indent = indentRepo.selectByPrimaryKey(id);
		return beanMapper.map(indent, IndentModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(IndentModel indentModel) {
		return indentRepo.selectCount(beanMapper.map(indentModel, Indent.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(IndentModel indentModel) {
		return indentRepo.updateByPrimaryKey(beanMapper.map(indentModel, Indent.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(IndentModel indentModel) {
		return indentRepo.updateByPrimaryKeySelective(beanMapper.map(indentModel, Indent.class));
	}

}
