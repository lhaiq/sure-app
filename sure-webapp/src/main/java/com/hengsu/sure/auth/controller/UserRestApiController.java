package com.hengsu.sure.auth.controller;

import com.hengsu.sure.ReturnCode;
import com.hengsu.sure.auth.annotation.IgnoreAuth;
import com.hengsu.sure.auth.request.*;
import com.hengsu.sure.auth.service.UserService;
import com.hengsu.sure.auth.vo.LoginSuccessVO;
import com.hengsu.sure.core.Context;
import com.hengsu.sure.core.util.RandomUtil;
import com.hengsu.sure.sns.RelationType;
import com.hengsu.sure.sns.model.RelationModel;
import com.hengsu.sure.sns.service.RelationService;
import com.hengsu.sure.sns.vo.SNSUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.sure.auth.model.UserModel;
import com.hengsu.sure.auth.vo.UserVO;

import javax.validation.Valid;


@RestApiController
@RequestMapping("/sure")
public class UserRestApiController {

    private final Logger logger = LoggerFactory.getLogger(UserRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;


    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("authContext")
    private Context<String, Long> authContext;

    @Autowired
    private RelationService relationService;

    /**
     * 注册获取验证码
     *
     * @param phone
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/auth/registercode/{phone}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEnvelope<String> getRegisterAuthCode(@PathVariable String phone) {
        userService.generateRegisterAuthCode(phone);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return responseEnv;
    }

    /**
     * 注册流程 验证码校验
     *
     * @param authCodeRequest
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/auth/validatecode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEnvelope<String> validateAuthCode(@Valid@RequestBody ValidateAuthCodeRequest authCodeRequest) {
        userService.checkRegisterAuthCode(authCodeRequest.getPhone(), authCodeRequest.getCode());
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return responseEnv;
    }


    /**
     * 提交注册流程，返回user详细信息
     *
     * @param registerRequest
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEnvelope<LoginSuccessVO> register(@Valid@RequestBody RegisterRequest registerRequest) {

        //将注册信息保存
        UserModel userModel = beanMapper.map(registerRequest, UserModel.class);
        userService.registerUser(userModel);

        //注册后 生成auth token
        String authToken = RandomUtil.generateAuthToken();

        //将User 信息返回,密码不返回
        UserModel returnUser = userService.findUserByPhone(registerRequest.getPhone());
        returnUser.setPassword(null);
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        loginSuccessVO.setAuthToken(authToken);
        SNSUserVO snsUserVO = beanMapper.map(userModel, SNSUserVO.class);
        snsUserVO.setAttentionCount(0L);
        snsUserVO.setFriendCount(0L);
        snsUserVO.setFansCount(0L);
        loginSuccessVO.setUser(snsUserVO);

        //auth token保存到缓存
        authContext.put(authToken, userModel.getId());

        ResponseEnvelope<LoginSuccessVO> responseEnv = new ResponseEnvelope<>(loginSuccessVO, true);
        return responseEnv;
    }

    /**
     * 手机密码登录
     *
     * @param loginRequest
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/auth/accountlogin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEnvelope<LoginSuccessVO> accountLogin(@Valid@RequestBody LoginRequest loginRequest) {
        UserModel userModel = userService.accountLogin(loginRequest.getPhone(), loginRequest.getPassword());

        //登录成功后生成auth token，并保存到内存
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        String authToken = RandomUtil.generateAuthToken();
        loginSuccessVO.setAuthToken(authToken);
        SNSUserVO snsUserVO = beanMapper.map(userModel, SNSUserVO.class);
        setSNSInfo(snsUserVO);
        loginSuccessVO.setUser(snsUserVO);
        authContext.put(authToken, userModel.getId());

        ResponseEnvelope<LoginSuccessVO> responseEnv = new ResponseEnvelope<>(loginSuccessVO, true);
        return responseEnv;
    }


    /**
     * 人脸登录
     *
     * @param faceLoginRequest
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/auth/facelogin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEnvelope<LoginSuccessVO> faceLogin(@Valid@RequestBody FaceLoginRequest faceLoginRequest) {
        UserModel userModel = userService.faceLogin(faceLoginRequest.getRegisterFaceId(), faceLoginRequest.getLoginFaceId());

        //登录成功后生成auth token，并保存到内存
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        String authToken = RandomUtil.generateAuthToken();
        loginSuccessVO.setAuthToken(authToken);
        SNSUserVO snsUserVO = beanMapper.map(userModel, SNSUserVO.class);
        setSNSInfo(snsUserVO);
        loginSuccessVO.setUser(snsUserVO);

        authContext.put(authToken, userModel.getId());

        ResponseEnvelope<LoginSuccessVO> responseEnv = new ResponseEnvelope<>(loginSuccessVO, true);
        return responseEnv;
    }

    /**
     * 修改密码：获取验证码
     *
     * @param phone
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/auth/changepasscode/{phone}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEnvelope<String> getChangePassAuthCode(@PathVariable String phone) {
        userService.generateChangePassAuthCode(phone);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return responseEnv;
    }


    @IgnoreAuth
    @RequestMapping(value = "/auth/changepass", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEnvelope<String> changePass(@RequestBody ChangePassRequest changePassRequest) {
        userService.changePass(changePassRequest.getPhone(), changePassRequest.getPassword(),
                changePassRequest.getCode());
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return responseEnv;
    }


    /**
     * 修改user 信息
     *
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/auth/user", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<ResponseEnvelope<Integer>> updateUserByPrimaryKeySelective(
            @RequestBody UserVO userVO,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        UserModel userModel = beanMapper.map(userVO, UserModel.class);
        userModel.setId(userId);
        Integer result = userService.updateByPrimaryKeySelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    public void setSNSInfo(SNSUserVO snsUserVO) {
        Long userId = snsUserVO.getId();

        //粉丝数
        RelationModel fansParam = new RelationModel();
        fansParam.setToUser(userId);
        fansParam.setType(RelationType.RELATION.getCode());
        snsUserVO.setFansCount(Long.valueOf(relationService.selectCount(fansParam)));

        //关注
        RelationModel attentionParam = new RelationModel();
        attentionParam.setFromUser(userId);
        attentionParam.setType(RelationType.RELATION.getCode());
        snsUserVO.setAttentionCount(Long.valueOf(relationService.selectCount(attentionParam)));

        //朋友
        RelationModel friendParam = new RelationModel();
        friendParam.setFromUser(userId);
        friendParam.setType(RelationType.FRIEND.getCode());
        snsUserVO.setFriendCount(Long.valueOf(relationService.selectCount(friendParam)));
    }

}
