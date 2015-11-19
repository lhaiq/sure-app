package com.hengsu.sure.core.service.impl;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.hengsu.sure.core.service.AuthCodeService;

import com.hkntv.pylon.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by haiquanli on 15/11/19.
 */
@Service
public class AuthCodeServiceImpl implements AuthCodeService {

    private static final String PHONE_DELIMITER = ",";

    private static final String MSM_DEFAULT_RETURN_CODE = "000000";

    @Autowired
    private CCPRestSmsSDK smsClient;


    @Override
    public void sendAuthCode(List<String> phones, String templateId, String[] data) {
        String phoneNumbers = StringUtils.collectionToDelimitedString(phones, PHONE_DELIMITER);
        HashMap<String, Object> result = smsClient.sendTemplateSMS(phoneNumbers, templateId, data);
        Object statusCode = result.get("statusCode");
        if (!MSM_DEFAULT_RETURN_CODE.equals(statusCode)) {
            throw new BusinessException(statusCode.toString(), result.get("statusMsg").toString());
        }

    }
}
