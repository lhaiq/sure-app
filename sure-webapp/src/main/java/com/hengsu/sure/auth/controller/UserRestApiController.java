package com.hengsu.sure.auth.controller;

import com.hengsu.sure.ReturnCode;
import com.hengsu.sure.auth.request.LoginRequest;
import com.hengsu.sure.auth.request.RegisterRequest;
import com.hengsu.sure.auth.request.ValidateAuthCodeRequest;
import com.hengsu.sure.auth.service.UserService;
import com.hengsu.sure.auth.vo.LoginSuccessVO;
import com.hengsu.sure.core.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.sure.auth.model.UserModel;
import com.hengsu.sure.auth.vo.UserVO;


@RestApiController
@RequestMapping("/sure")
public class UserRestApiController {

    private final Logger logger = LoggerFactory.getLogger(UserRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;


    @Autowired
    private UserService userService;

    /**
     * 注册获取验证码
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "/auth/registercode/{phone}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/auth/validatecode", method = RequestMethod.POST)
    public ResponseEnvelope<String> validateAuthCode(@RequestBody ValidateAuthCodeRequest authCodeRequest) {
        userService.checkRegisterAuthCode(authCodeRequest.getPhone(), authCodeRequest.getCode());
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return responseEnv;
    }


    /**
     * 提交注册流程，返回user详细信息
     * @param registerRequest
     * @return
     */
    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEnvelope<LoginSuccessVO> register(@RequestBody RegisterRequest registerRequest) {

        //将注册信息保存
        UserModel userModel = beanMapper.map(registerRequest, UserModel.class);
        userService.registerUser(userModel);

        //注册后 生成auth token
        String authToken = RandomUtil.generateAuthToken();

        //将User 信息返回
        UserModel returnUser = userService.findUserByPhone(registerRequest.getPhone());
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        loginSuccessVO.setAuthToken(authToken);
        loginSuccessVO.setUser(returnUser);

        ResponseEnvelope<LoginSuccessVO> responseEnv = new ResponseEnvelope<>(loginSuccessVO, true);
        return responseEnv;
    }

    /**
     * 修改密码：获取验证码
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "/auth/changepasscode/{phone}", method = RequestMethod.GET)
    public ResponseEnvelope<String> getChangePassAuthCode(@PathVariable String phone) {
        userService.generateRegisterAuthCode(phone);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return responseEnv;
    }

    /**
     * 修改密码
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "/auth/changepass/{phone}", method = RequestMethod.PUT)
    public ResponseEnvelope<String> changePass(@PathVariable String phone) {
        userService.generateRegisterAuthCode(phone);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return responseEnv;
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEnvelope<LoginSuccessVO> login(@RequestBody LoginRequest loginRequest) {
        UserModel userModel = userService.login(loginRequest.getPhone(), loginRequest.getPassword());

        //登录成功后生成auth token，并保存到内存
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        String authToken = RandomUtil.generateAuthToken();
        loginSuccessVO.setAuthToken(authToken);
        loginSuccessVO.setUser(userModel);

        ResponseEnvelope<LoginSuccessVO> responseEnv = new ResponseEnvelope<>(loginSuccessVO, true);
        return responseEnv;
    }


    @RequestMapping(value = "/auth/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Integer>> deleteUserByPrimaryKey(@PathVariable Long id) {
        Integer result = userService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    @RequestMapping(value = "/auth/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<Integer>> updateUserByPrimaryKeySelective(@PathVariable Long id, @RequestBody UserVO userVO) {
        UserModel userModel = beanMapper.map(userVO, UserModel.class);
        userModel.setId(id);
        Integer result = userService.updateByPrimaryKeySelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


}
