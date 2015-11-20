package com.hengsu.sure.auth.service.impl;

import com.google.common.collect.ImmutableList;
import com.hengsu.sure.auth.entity.User;
import com.hengsu.sure.auth.service.UserService;
import com.hengsu.sure.core.ErrorCode;
import com.hengsu.sure.core.model.AuthCodeModel;
import com.hengsu.sure.core.service.AuthCodeService;
import com.hengsu.sure.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.auth.repository.UserRepository;
import com.hengsu.sure.auth.model.UserModel;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private UserRepository userRepo;

    @Value("$register.authcode.template.id")
    private String registerAuthCodeTemplateId;

    @Value("${register.auth.code.valid.time}")
    private Long registerAuthCodeValidTime;

    @Value("${changepass.auth.code.valid.time}")
    private Long changePassAuthCodeValidTime;

    /**
     * 注册验证码缓存
     */
    private Map<String, AuthCodeModel> registerAuthCodes = new HashMap<>();

    /**
     * 修改密码缓存
     */
    private Map<String, AuthCodeModel> changePassAuthCodes = new HashMap<>();


    @Autowired
    private AuthCodeService authCodeService;

    @Transactional
    @Override
    public int createSelective(UserModel userModel) {
        User user = beanMapper.map(userModel, User.class);
        int retVal = userRepo.insertSelective(user);
        userModel.setId(user.getId());
        return retVal;
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return userRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserModel findByPrimaryKey(Long id) {
        User user = userRepo.selectByPrimaryKey(id);
        return beanMapper.map(user, UserModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(UserModel userModel) {
        return userRepo.selectCount(beanMapper.map(userModel, User.class));
    }

    @Override
    public void generateRegisterAuthCode(String phone) {
        String authCode = RandomUtil.createRandom(true, 4);
        registerAuthCodes.put(authCode, new AuthCodeModel(authCode));
        authCodeService.sendAuthCode(ImmutableList.of(phone), registerAuthCodeTemplateId,
                new String[]{authCode});
    }

    @Override
    public void checkRegisterAuthCode(String phone, String authCode) {
        AuthCodeModel authCodeModel = registerAuthCodes.get(phone);

        //判断是否超时
        if ((System.currentTimeMillis() / 1000 - authCodeModel.getGenerateTime())
                > registerAuthCodeValidTime) {
            ErrorCode.throwBusinessException(ErrorCode.AUTH_CODE_TIME_OUT);
        }
        //检查验证码是否正确
        if (!authCodeModel.getCode().equals(authCode)) {
            ErrorCode.throwBusinessException(ErrorCode.AUTH_CODE_ERROR);
        }

    }

    @Override
    public void generateChangePassAuthCode(String phone) {

        //检查手机号是否注册
        UserModel userModel = findUserByPhone(phone);
        if (null == userModel) {
            ErrorCode.throwBusinessException(ErrorCode.PHONE_NOT_REGISTER);
        }

        //生成验证码
        String authCode = RandomUtil.createRandom(true, 4);
        AuthCodeModel authCodeModel = new AuthCodeModel(authCode);
        changePassAuthCodes.put(phone, authCodeModel);

    }

    @Override
    public void changePass(String phone, String newPass, String authCode) {

        //验证码校验
        AuthCodeModel authCodeModel = changePassAuthCodes.get(phone);
        if ((System.currentTimeMillis() / 1000 - authCodeModel.getGenerateTime())
                > changePassAuthCodeValidTime) {
            ErrorCode.throwBusinessException(ErrorCode.AUTH_CODE_TIME_OUT);
        }

        //更新密码
        UserModel userModel = findUserByPhone(phone);
        String password = DigestUtils.md5DigestAsHex(newPass.getBytes());
        userModel.setPassword(password);
        updateByPrimaryKey(userModel);

    }

    @Transactional
    @Override
    public void registerUser(UserModel userModel) {

        //判断User是否存在
        UserModel existedUser = findUserByPhone(userModel.getPhone());
        if (null != existedUser) {
            ErrorCode.throwBusinessException(ErrorCode.REGISTER_PHONE_EXISTED);
        }

        //MD5加密
        String password = DigestUtils.md5DigestAsHex(userModel.getPassword().getBytes());
        userModel.setPassword(password);
        createSelective(userModel);
    }

    @Override
    public UserModel findUserByPhone(String phone) {
        User user = userRepo.findUserByPhone(phone);
        if (null != user) {
            user.setPassword(null);
        }
        return beanMapper.map(user, UserModel.class);
    }

    @Override
    public UserModel login(String phone, String password) {
        UserModel userModel = findUserByPhone(phone);
        password = DigestUtils.md5DigestAsHex(userModel.getPassword().getBytes());
        if (!userModel.getPassword().equals(password)) {
            ErrorCode.throwBusinessException(ErrorCode.LOGIN_PASSWORD_ERROR);
        }

        //密码不返回
        userModel.setPassword(null);
        return userModel;
    }


    @Transactional
    @Override
    public int updateByPrimaryKey(UserModel userModel) {
        return userRepo.updateByPrimaryKey(beanMapper.map(userModel, User.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(UserModel userModel) {
        return userRepo.updateByPrimaryKeySelective(beanMapper.map(userModel, User.class));
    }

}
