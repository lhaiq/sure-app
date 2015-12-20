package com.hengsu.sure.core.service.impl;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.hengsu.sure.core.model.SubAccountModel;
import com.hengsu.sure.core.service.YunTongXunService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haiquanli on 15/11/19.
 */
@Service
public class YunTongXunServiceImpl implements YunTongXunService {


    private static final String MSM_DEFAULT_RETURN_CODE = "000000";

    private String yunTongXunUrl;

    private String createAccountPath=yunTongXunUrl+"/{SoftVersion}/Accounts/{accountSid}/SubAccounts";

    @Autowired
    private CCPRestSmsSDK smsClient;

//    private


    @Override
    public void sendAuthCode(List<String> phones, String templateId, String[] data) {
        //TODO 开发阶段暂时不发
//        String phoneNumbers = StringUtils.collectionToDelimitedString(phones, PHONE_DELIMITER);
//        HashMap<String, Object> result = smsClient.sendTemplateSMS(phoneNumbers, templateId, data);
//        Object statusCode = result.get("statusCode");
//        if (!MSM_DEFAULT_RETURN_CODE.equals(statusCode)) {
//            throw new BusinessException(statusCode.toString(), result.get("statusMsg").toString());
//        }

    }

    @Override
    public SubAccountModel createSubAccount(String name, String accountSid) {
        //TODO
        return null;
    }
}
