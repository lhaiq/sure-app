package com.hengsu.sure.invite.service.impl;

import com.alibaba.fastjson.JSON;
import com.hengsu.sure.core.ErrorCode;
import com.hengsu.sure.invite.IndentType;
import com.hengsu.sure.invite.RentStatus;
import com.hengsu.sure.invite.model.*;
import com.hengsu.sure.invite.service.IndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.invite.entity.Rent;
import com.hengsu.sure.invite.repository.RentRepository;
import com.hengsu.sure.invite.service.RentService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.Date;
import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private RentRepository rentRepo;

    @Autowired
    private IndentService indentService;

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
    public void confirmRent(RentConfirmModel rentConfirmModel) {

        RentModel rentModel = findByPrimaryKey(rentConfirmModel.getId());
        if(null==rentModel){
            ErrorCode.throwBusinessException(ErrorCode.RENT_NOT_EXISTED);
        }

        //更新状态
        RentModel newRentModel = new RentModel();
        newRentModel.setId(rentConfirmModel.getId());
        newRentModel.setStatus(RentStatus.FINISHED.getCode());

        //创建订单
        IndentModel indentModel = new IndentModel();
        indentModel.setCustomerId(rentConfirmModel.getBuyerId());
        indentModel.setSellerId(rentConfirmModel.getUserId());
        indentModel.setIndentNo(rentConfirmModel.getIndentNo());
        indentModel.setQuantity(rentConfirmModel.getQuantity());
        indentModel.setPrice(rentConfirmModel.getPrice());
        indentModel.setMoney(rentConfirmModel.getMoney());
        indentModel.setCreateTime(new Date());
        indentModel.setType(IndentType.RENT.getCode());
        indentModel.setReferId(rentConfirmModel.getId());
        indentModel.setSnapshot(JSON.toJSONString(rentModel));

        //TODO 开始时间 结束时间
        indentService.createSelective(indentModel);

    }

    @Override
    public List<QueryRentModel> queryRent(QueryRentParamModel queryRentParamModel,
                                          Pageable pageable) {
        return null;
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
