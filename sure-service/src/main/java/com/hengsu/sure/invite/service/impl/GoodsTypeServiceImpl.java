package com.hengsu.sure.invite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.invite.entity.GoodsType;
import com.hengsu.sure.invite.repository.GoodsTypeRepository;
import com.hengsu.sure.invite.model.GoodsTypeModel;
import com.hengsu.sure.invite.service.GoodsTypeService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private GoodsTypeRepository goodsTypeRepo;

	@Transactional
	@Override
	public int create(GoodsTypeModel goodsTypeModel) {
		return goodsTypeRepo.insert(beanMapper.map(goodsTypeModel, GoodsType.class));
	}

	@Transactional
	@Override
	public int createSelective(GoodsTypeModel goodsTypeModel) {
		return goodsTypeRepo.insertSelective(beanMapper.map(goodsTypeModel, GoodsType.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return goodsTypeRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public GoodsTypeModel findByPrimaryKey(Long id) {
		GoodsType goodsType = goodsTypeRepo.selectByPrimaryKey(id);
		return beanMapper.map(goodsType, GoodsTypeModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(GoodsTypeModel goodsTypeModel) {
		return goodsTypeRepo.selectCount(beanMapper.map(goodsTypeModel, GoodsType.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(GoodsTypeModel goodsTypeModel) {
		return goodsTypeRepo.updateByPrimaryKey(beanMapper.map(goodsTypeModel, GoodsType.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(GoodsTypeModel goodsTypeModel) {
		return goodsTypeRepo.updateByPrimaryKeySelective(beanMapper.map(goodsTypeModel, GoodsType.class));
	}

}
