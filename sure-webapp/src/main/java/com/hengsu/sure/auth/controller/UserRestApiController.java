package com.hengsu.sure.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.hengsu.sure.ReturnCode;
import com.hengsu.sure.auth.annotation.IgnoreAuth;
import com.hengsu.sure.auth.entity.SubAccount;
import com.hengsu.sure.auth.model.SubAccountModel;
import com.hengsu.sure.auth.model.UserLBSModel;
import com.hengsu.sure.auth.request.*;
import com.hengsu.sure.auth.service.SubAccountService;
import com.hengsu.sure.auth.service.UserService;
import com.hengsu.sure.auth.vo.*;
import com.hengsu.sure.core.Context;
import com.hengsu.sure.core.service.PushService;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.sure.auth.model.UserModel;

import javax.validation.Valid;
import java.util.List;


@RestApiController
@RequestMapping("/sure")
public class UserRestApiController {

    private final Logger logger = LoggerFactory.getLogger(UserRestApiController.class);

    private static final String OTHER_LOGIN="other_login";

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("authContext")
    private Context<String, Long> authContext;

    @Autowired
    @Qualifier("loginContext")
    private Context<Long, String> loginContext;

    @Autowired
    private RelationService relationService;

    @Autowired
    private SubAccountService subAccountService;

    @Autowired
    private PushService pushService;

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
    public ResponseEnvelope<String> validateAuthCode(@Valid @RequestBody ValidateAuthCodeRequest authCodeRequest) {
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
    public ResponseEnvelope<LoginSuccessVO> register(@Valid @RequestBody RegisterRequest registerRequest) {

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
        UserVO userVO = beanMapper.map(userModel, UserVO.class);
        userVO.setAttentionCount(0L);
        userVO.setFriendCount(0L);
        userVO.setFansCount(0L);
        loginSuccessVO.setUser(userVO);

        //auth token保存到缓存
        updateAuthToken(authToken,userModel);

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
    public ResponseEnvelope<LoginSuccessVO> accountLogin(@Valid @RequestBody LoginRequest loginRequest) {
        UserModel userModel = userService.accountLogin(loginRequest.getPhone(),
                loginRequest.getPassword(),loginRequest.getClientId());

        //登录成功后生成auth token，并保存到内存
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        String authToken = RandomUtil.generateAuthToken();
        loginSuccessVO.setAuthToken(authToken);
        UserVO userVO = beanMapper.map(userModel, UserVO.class);
        setSNSInfo(userVO);
        loginSuccessVO.setUser(userVO);

        //auth token保存到缓存
        updateAuthToken(authToken, userModel);

        ResponseEnvelope<LoginSuccessVO> responseEnv = new ResponseEnvelope<>(loginSuccessVO, true);
        return responseEnv;
    }


    /**
     * 人脸登录
     *
     * @param faceLoginRequest
     * @return
     */
//    @IgnoreAuth
//    @RequestMapping(value = "/auth/facelogin", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEnvelope<LoginSuccessVO> faceLogin(@Valid @RequestBody FaceLoginRequest faceLoginRequest) {
//        UserModel userModel = userService.faceLogin(faceLoginRequest.getPhone(), faceLoginRequest.getLoginFaceId());
//
//        //登录成功后生成auth token，并保存到内存
//        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
//        String authToken = RandomUtil.generateAuthToken();
//        loginSuccessVO.setAuthToken(authToken);
//        UserVO userVO = beanMapper.map(userModel, UserVO.class);
//        setSNSInfo(userVO);
//        loginSuccessVO.setUser(userVO);
//        updateAuthToken(authToken,userModel.getId());
//
//        ResponseEnvelope<LoginSuccessVO> responseEnv = new ResponseEnvelope<>(loginSuccessVO, true);
//        return responseEnv;
//    }

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


    /**
     * 忘记密码 修改
     *
     * @param changePassRequest
     * @return
     */
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
     * 更新密码 登陆后
     *
     * @param modifyPassRequest
     * @param userId
     * @return
     */
    @RequestMapping(value = "/auth/modifypass", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEnvelope<String> modifyPass(
            @RequestBody ModifyPassRequest modifyPassRequest,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        userService.modifyPass(userId, modifyPassRequest.getOldPass(), modifyPassRequest.getNewPass());
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return responseEnv;
    }

    /**
     * 修改user 信息
     *
     * @param modifyUserVO
     * @return
     */
    @RequestMapping(value = "/auth/user", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<ResponseEnvelope<Integer>> updateUserByPrimaryKeySelective(
            @RequestBody ModifyUserVO modifyUserVO,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        UserModel userModel = beanMapper.map(modifyUserVO, UserModel.class);
        userModel.setId(userId);
        Integer result = userService.updateByPrimaryKeySelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 获取云通讯账户
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/auth/cloudopen", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseEnvelope<SubAccountModel>> getCloudOpenAccount(
            @Value("#{request.getAttribute('userId')}") Long userId) {
        SubAccountModel result = subAccountService.findOrCreateSubAccount(userId);
        ResponseEnvelope<SubAccountModel> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 更新位置信息
     *
     * @param userVO
     * @param userId
     * @return
     */
    @RequestMapping(value = "/auth/userlbs", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseEnvelope<String>> updateUserLBS(
            @Valid @RequestBody UserLBSVO userVO,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        UserLBSModel userLBSModel = beanMapper.map(userVO, UserLBSModel.class);
        userLBSModel.setUserId(userId);
        userService.updateLBS(userLBSModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    public void setSNSInfo(UserVO userVO) {
        Long userId = userVO.getId();

        //粉丝数
        RelationModel fansParam = new RelationModel();
        fansParam.setToUser(userId);
        fansParam.setType(RelationType.RELATION.getCode());
        userVO.setFansCount(Long.valueOf(relationService.selectCount(fansParam)));

        //关注
        RelationModel attentionParam = new RelationModel();
        attentionParam.setFromUser(userId);
        attentionParam.setType(RelationType.RELATION.getCode());
        userVO.setAttentionCount(Long.valueOf(relationService.selectCount(attentionParam)));

        //朋友
        RelationModel friendParam = new RelationModel();
        friendParam.setFromUser(userId);
        friendParam.setType(RelationType.FRIEND.getCode());
        userVO.setFriendCount(Long.valueOf(relationService.selectCount(friendParam)));
    }

    /**
     * 查找用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/auth/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEnvelope<UserModel> getUserById(@PathVariable Long id) {
        UserModel userModel = userService.findByPrimaryKeyNoPass(id);
        userModel.setAlipay(null);
        ResponseEnvelope<UserModel> responseEnv = new ResponseEnvelope<>(userModel, true);
        return responseEnv;
    }

    /**
     * 附近的人
     *
     * @param queryNearUserVO
     * @param userId
     * @return
     */
    @RequestMapping(value = "/auth/nearuser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEnvelope<List<UserModel>> queryNearUser(
            @Valid @RequestBody QueryNearUserVO queryNearUserVO,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        List<UserModel> userModels = userService.queryUserByTimeAndLocation(2 * 60 * 60L,
                30.0 * 1000, queryNearUserVO.getLongitude(), queryNearUserVO.getLatitude(),
                userId);
        ResponseEnvelope<List<UserModel>> responseEnv = new ResponseEnvelope<>(userModels, true);
        return responseEnv;
    }

    private void updateAuthToken(String authToken,UserModel userModel){

        //该用户是否已经登陆过，如果是，以前登陆的失效
        long id = userModel.getId();
        String auth = loginContext.get(id);
        if(!StringUtils.isEmpty(auth)&&!StringUtils.isEmpty(userModel.getClientId())){
            JSONObject message = new JSONObject();
            message.put("pushId", OTHER_LOGIN);
            pushService.pushMessage(message.toJSONString(),userModel);
            authContext.remove(auth);
        }

        //保存该clientId
        authContext.put(authToken,id);
        loginContext.put(id,authToken);

    }
}
