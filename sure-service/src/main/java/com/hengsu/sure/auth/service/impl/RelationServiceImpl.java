package com.hengsu.sure.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.auth.entity.Relation;
import com.hengsu.sure.auth.repository.RelationRepository;
import com.hengsu.sure.auth.model.RelationModel;
import com.hengsu.sure.auth.service.RelationService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class RelationServiceImpl implements RelationService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private RelationRepository relationRepo;

	@Transactional
	@Override
	public int create(RelationModel relationModel) {
		return relationRepo.insert(beanMapper.map(relationModel, Relation.class));
	}

	@Transactional
	@Override
	public int createSelective(RelationModel relationModel) {
		return relationRepo.insertSelective(beanMapper.map(relationModel, Relation.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return relationRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public RelationModel findByPrimaryKey(Long id) {
		Relation relation = relationRepo.selectByPrimaryKey(id);
		return beanMapper.map(relation, RelationModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(RelationModel relationModel) {
		return relationRepo.selectCount(beanMapper.map(relationModel, Relation.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(RelationModel relationModel) {
		return relationRepo.updateByPrimaryKey(beanMapper.map(relationModel, Relation.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(RelationModel relationModel) {
		return relationRepo.updateByPrimaryKeySelective(beanMapper.map(relationModel, Relation.class));
	}

}
