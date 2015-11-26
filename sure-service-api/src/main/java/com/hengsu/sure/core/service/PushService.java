package com.hengsu.sure.core.service;

import com.hengsu.sure.auth.model.UserModel;

import java.util.List;

/**
 * Created by haiquanli on 15/11/25.
 */
public interface PushService {

    public void pushMessage(String data,List<UserModel> users);

    public void pushMessage(String data,UserModel user);
}
