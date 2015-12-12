package com.hengsu.sure.invite.service.impl;

import com.hengsu.sure.core.ErrorCode;
import com.hengsu.sure.invite.IndentStatus;
import com.hengsu.sure.invite.model.TradeModel;
import com.hengsu.sure.invite.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.invite.entity.Indent;
import com.hengsu.sure.invite.repository.IndentRepository;
import com.hengsu.sure.invite.model.IndentModel;
import com.hengsu.sure.invite.service.IndentService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class IndentServiceImpl implements IndentService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private IndentRepository indentRepo;

	@Autowired
	private TradeService tradeService;

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
	public void receiveTrade(TradeModel tradeModel) {

		//保存记录
		tradeService.createSelective(tradeModel);
		Long tradeId = tradeModel.getId();

		//TODO 检查交易


	}

	@Transactional
	@Override
	public void cancelIndent(Long id, Long userId) {

		//判断是否为自己的订单
		IndentModel indentModel = findByPrimaryKey(id);
		if(userId!=indentModel.getCustomerId()){
			ErrorCode.throwBusinessException(ErrorCode.CANNOT_CANCEL_OTHERS_INDENT);
		}

		//判断订单是否可以取消
		if(IndentStatus.SUCCESS.getCode()!=indentModel.getStatus()){
			ErrorCode.throwBusinessException(ErrorCode.CANNOT_CANCEL_STATUS_ERROR);
		}

		//更新订单状态
		IndentModel param = new IndentModel();
		param.setId(indentModel.getId());
		param.setStatus(IndentStatus.CANCELING.getCode());
		updateByPrimaryKeySelective(param);

	}

	@Override
	public List<IndentModel> selectPage(IndentModel indentModel, Pageable pageable) {
		List<Indent> indents = indentRepo.selectPage(beanMapper.map(indentModel,Indent.class),pageable);
		return beanMapper.mapAsList(indents,IndentModel.class);
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
