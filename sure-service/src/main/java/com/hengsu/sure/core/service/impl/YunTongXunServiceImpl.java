package com.hengsu.sure.core.service.impl;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.hengsu.sure.auth.model.SubAccountModel;
import com.hengsu.sure.core.service.YunTongXunService;

import com.hkntv.pylon.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by haiquanli on 15/11/19.
 */
@Service
public class YunTongXunServiceImpl implements YunTongXunService {


    private static final String MSM_DEFAULT_RETURN_CODE = "000000";

    @Autowired
    private CCPRestSmsSDK smsClient;

    @Autowired
    private CCPRestSDK restClient;


    @Override
    public void sendAuthCode(List<String> phones, String templateId, String[] data) {
        String phoneNumbers = StringUtils.collectionToDelimitedString(phones, ",");
        HashMap<String, Object> result = smsClient.sendTemplateSMS(phoneNumbers, templateId, data);
        Object statusCode = result.get("statusCode");
        if (!MSM_DEFAULT_RETURN_CODE.equals(statusCode)) {
            throw new BusinessException(statusCode.toString(), result.get("statusMsg").toString());
        }

    }

    @Override
    public SubAccountModel createSubAccount(String phone) {

        Map<String, Object> result = restClient.createSubAccount("sure_" + phone + "_" +
                System.currentTimeMillis());
        Object statusCode = result.get("statusCode");

        if (!MSM_DEFAULT_RETURN_CODE.equals(statusCode)) {
            throw new BusinessException(result.get("statusMsg").toString(), statusCode.toString());
        } else {
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            Map<String, Object> subAccount = (Map) ((List) data.get("SubAccount")).get(0);
            SubAccountModel subAccountModel = new SubAccountModel();
            subAccountModel.setSubAccountSid(subAccount.get("subAccountSid").toString());
            subAccountModel.setDateCreated(subAccount.get("dateCreated").toString());
            subAccountModel.setSubToken(subAccount.get("subToken").toString());
            subAccountModel.setVoipAccount(subAccount.get("voipAccount").toString());
            subAccountModel.setVoipPwd(subAccount.get("voipPwd").toString());
            return subAccountModel;
        }

    }
}
