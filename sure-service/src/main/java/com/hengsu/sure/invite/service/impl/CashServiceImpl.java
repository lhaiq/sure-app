package com.hengsu.sure.invite.service.impl;

import com.hengsu.sure.auth.service.UserService;
import com.hengsu.sure.invite.CashStatus;
import com.hengsu.sure.invite.CashType;
import com.hengsu.sure.invite.StatementType;
import com.hengsu.sure.invite.entity.Cash;
import com.hengsu.sure.invite.model.CashModel;
import com.hengsu.sure.invite.model.StatementModel;
import com.hengsu.sure.invite.repository.CashRepository;
import com.hengsu.sure.invite.service.CashService;
import com.hengsu.sure.invite.service.StatementService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CashServiceImpl implements CashService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private CashRepository cashRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private StatementService statementService;

	@Transactional
	@Override
	public int create(CashModel cashModel) {
		return cashRepo.insert(beanMapper.map(cashModel, Cash.class));
	}

	@Transactional
	@Override
	public int createSelective(CashModel cashModel) {
		return cashRepo.insertSelective(beanMapper.map(cashModel, Cash.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return cashRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public CashModel findByPrimaryKey(Long id) {
		Cash cash = cashRepo.selectByPrimaryKey(id);
		return beanMapper.map(cash, CashModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(CashModel cashModel) {
		return cashRepo.selectCount(beanMapper.map(cashModel, Cash.class));
	}

	@Transactional
	@Override
	public void applyCash(CashModel cashModel) {

		//减少余额
		userService.reduceBalance(cashModel.getUserId(),cashModel.getMoney());

		//创建
		CashModel createCrash = new CashModel();
		createCrash.setType(CashType.CASH.getCode());
		createCrash.setStatus(CashStatus.APPLYING.getCode());
		createCrash.setCreateTime(new Date());
		createCrash.setMoney(cashModel.getMoney());
		createCrash.setUserId(cashModel.getUserId());
		createSelective(createCrash);

		//创建流水
		StatementModel statementModel = new StatementModel();
		statementModel.setType(StatementType.CASH.getCode());
		statementModel.setMoney(cashModel.getMoney());
		statementModel.setUserId(cashModel.getUserId());
		statementModel.setName("提现");
		statementModel.setTime(new Date());
		statementService.createSelective(statementModel);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(CashModel cashModel) {
		return cashRepo.updateByPrimaryKey(beanMapper.map(cashModel, Cash.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKeySelective(CashModel cashModel) {
		return cashRepo.updateByPrimaryKeySelective(beanMapper.map(cashModel, Cash.class));
	}

}
