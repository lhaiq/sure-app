package com.hengsu.sure.invite.service.impl;

import com.alibaba.fastjson.JSON;
import com.hengsu.sure.auth.UserRole;
import com.hengsu.sure.auth.model.UserModel;
import com.hengsu.sure.auth.service.UserService;
import com.hengsu.sure.core.ErrorCode;
import com.hengsu.sure.invite.IndentType;
import com.hengsu.sure.invite.RentStatus;
import com.hengsu.sure.invite.model.*;
import com.hengsu.sure.invite.service.IndentService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.invite.entity.Rent;
import com.hengsu.sure.invite.repository.RentRepository;
import com.hengsu.sure.invite.service.RentService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RentServiceImpl implements RentService {

    private static final SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private RentRepository rentRepo;

    @Autowired
    private IndentService indentService;

    @Autowired
    private UserService userService;

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

        //角色是否为卖家
        Long userId = rentModel.getUserId();
        UserModel userModel = userService.findByPrimaryKeyNoPass(userId);
        if (UserRole.SELLER.getCode() != userModel.getRole()) {
            ErrorCode.throwBusinessException(ErrorCode.RENT_ROLE_ERROR);
        }

        //设置时间
        rentModel.setCreateTime(new Date());
        rentModel.setStatus(RentStatus.PUBLISH.getCode());

        createSelective(rentModel);
    }

    @Transactional
    @Override
    public void confirmRent(RentConfirmModel rentConfirmModel) {

        RentModel rentModel = findByPrimaryKey(rentConfirmModel.getId());
        if (null == rentModel) {
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

        List<String> dates = JSON.parseArray(rentModel.getDate(),String.class);
        setStartAndEndTime(indentModel,dates,rentModel.getTime());
        indentService.createSelective(indentModel);

    }

    @Override
    public List<QueryRentModel> queryRent(QueryRentParamModel queryRentParamModel,
                                          Pageable pageable) {

        Map<String, Object> param = new HashMap<>();
        param.put("cityId", queryRentParamModel.getCityId());
        param.put("gender", queryRentParamModel.getGender());
        param.put("car", queryRentParamModel.getCar());
        param.put("maxAge", queryRentParamModel.getMaxAge());
        param.put("minAge", queryRentParamModel.getMinAge());
        param.put("time", queryRentParamModel.getTime());
        param.put("scenes", queryRentParamModel.getScenes());
        param.put("dates", queryRentParamModel.getDates());
        List<QueryRentModel> rents = rentRepo.queryRent(param, pageable);
        return rents;
    }

    @Override
    public int queryRentCount(QueryRentParamModel queryRentParamModel) {
        Map<String, Object> param = new HashMap<>();
        param.put("cityId", queryRentParamModel.getCityId());
        param.put("gender", queryRentParamModel.getGender());
        param.put("car", queryRentParamModel.getCar());
        param.put("maxAge", queryRentParamModel.getMaxAge());
        param.put("minAge", queryRentParamModel.getMinAge());
        param.put("time", queryRentParamModel.getTime());
        param.put("scenes", queryRentParamModel.getScenes());
        param.put("dates", queryRentParamModel.getDates());
        return rentRepo.queryRentCount(param);
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

    private void setStartAndEndTime(IndentModel indentModel,List<String> dates,String time){
        Collections.sort(dates);
        String [] timeSlots=time.split("-");
        try {
            Date startDate=simpleFormat.parse(dates.get(0));
            Date endDate=simpleFormat.parse(dates.get(dates.size()-1));
            Date startTime = DateUtils.addDays(startDate, Integer.parseInt(timeSlots[0]));
            Date endTime = DateUtils.addDays(endDate,Integer.parseInt(timeSlots[1]));
            indentModel.setStartTime(startTime);
            indentModel.setEndTime(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
