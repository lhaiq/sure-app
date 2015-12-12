package com.hengsu.sure.invite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.invite.entity.Trade;
import com.hengsu.sure.invite.repository.TradeRepository;
import com.hengsu.sure.invite.model.TradeModel;
import com.hengsu.sure.invite.service.TradeService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private TradeRepository tradeRepo;

	@Transactional
	@Override
	public int create(TradeModel tradeModel) {
		return tradeRepo.insert(beanMapper.map(tradeModel, Trade.class));
	}

	@Transactional
	@Override
	public int createSelective(TradeModel tradeModel) {
		Trade trade = beanMapper.map(tradeModel, Trade.class);
		int retVal = tradeRepo.insertSelective(trade);
		tradeModel.setId(trade.getId());
		return retVal;
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return tradeRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public TradeModel findByPrimaryKey(Long id) {
		Trade trade = tradeRepo.selectByPrimaryKey(id);
		return beanMapper.map(trade, TradeModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(TradeModel tradeModel) {
		return tradeRepo.selectCount(beanMapper.map(tradeModel, Trade.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(TradeModel tradeModel) {
		return tradeRepo.updateByPrimaryKey(beanMapper.map(tradeModel, Trade.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(TradeModel tradeModel) {
		return tradeRepo.updateByPrimaryKeySelective(beanMapper.map(tradeModel, Trade.class));
	}

}
