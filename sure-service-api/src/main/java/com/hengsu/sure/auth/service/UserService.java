
package com.hengsu.sure.auth.service;

import com.hengsu.sure.auth.UserRole;
import com.hengsu.sure.auth.model.UserModel;

import java.util.Date;
import java.util.List;

public interface UserService {


    public int createSelective(UserModel userModel);

    public UserModel findByPrimaryKey(Long id);

    public UserModel findByPrimaryKeyNoPass(Long id);

    public int updateByPrimaryKey(UserModel userModel);

    public int updateByPrimaryKeySelective(UserModel userModel);

    public int deleteByPrimaryKey(Long id);

    public int selectCount(UserModel userModel);

    public void generateRegisterAuthCode(String phone);

    public void checkRegisterAuthCode(String phone, String authCode);

    public void generateChangePassAuthCode(String phone);

    public void changePass(String phone, String password, String authCode);

    public void registerUser(UserModel userModel);

    public UserModel findUserByPhone(String phone);

    public UserModel findUserByFaceId(String faceId);

    public UserModel accountLogin(String phone, String password);

    public UserModel faceLogin(String registerFaceId, String loginFaceId);

    public List<UserModel> queryUserByTimeAndLocation(Long sec,
                                                      Double distance,
                                                      Double longitude,
                                                      Double latitude,
                                                      Long userId,
                                                      UserRole userRole,
                                                      Integer cityId);

}