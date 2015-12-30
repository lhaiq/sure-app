package com.hengsu.sure.core.service;

import com.hengsu.sure.auth.model.SubAccountModel;

import java.util.List;

/**
 * Created by haiquanli on 15/11/19.
 */
public interface YunTongXunService {

    public void sendAuthCode(List<String> phones, String templateId, String[] data);

    public SubAccountModel createSubAccount(String phone);


}
