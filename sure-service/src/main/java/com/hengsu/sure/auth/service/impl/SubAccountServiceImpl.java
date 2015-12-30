package com.hengsu.sure.auth.service.impl;

import com.hengsu.sure.auth.entity.SubAccount;
import com.hengsu.sure.auth.model.SubAccountModel;
import com.hengsu.sure.auth.model.UserModel;
import com.hengsu.sure.auth.repository.SubAccountRepository;
import com.hengsu.sure.auth.service.SubAccountService;
import com.hengsu.sure.auth.service.UserService;
import com.hengsu.sure.core.service.YunTongXunService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubAccountServiceImpl implements SubAccountService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SubAccountRepository subAccountRepo;

    @Autowired
    private YunTongXunService yunTongXunService;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public int create(SubAccountModel subAccountModel) {
        return subAccountRepo.insert(beanMapper.map(subAccountModel, SubAccount.class));
    }

    @Transactional
    @Override
    public int createSelective(SubAccountModel subAccountModel) {
        return subAccountRepo.insertSelective(beanMapper.map(subAccountModel, SubAccount.class));
    }

    @Transactional(readOnly = true)
    @Override
    public SubAccountModel findByPrimaryKey(Long id) {
        SubAccount subAccount = subAccountRepo.selectByPrimaryKey(id);
        return beanMapper.map(subAccount, SubAccountModel.class);
    }

    @Transactional
    @Override
    public SubAccountModel findOrCreateSubAccount(Long userId) {
        SubAccount subAccount = subAccountRepo.selectByUserId(userId);
        SubAccountModel subAccountModel = beanMapper.map(subAccount, SubAccountModel.class);
        if (null == subAccountModel) {
            UserModel userModel = userService.findByPrimaryKeyNoPass(userId);
            subAccountModel = yunTongXunService.createSubAccount(userModel.getPhone());
//            createSelective(subAccountModel);
        }
        return subAccountModel;
    }
}
