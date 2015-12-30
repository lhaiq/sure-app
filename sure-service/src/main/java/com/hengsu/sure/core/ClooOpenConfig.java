package com.hengsu.sure.core;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by haiquanli on 15/11/19.
 */
@Configuration
public class ClooOpenConfig {

    @Value("${sms.endpoint}")
    private String smsEndPoint;

    @Value("${sms.port}")
    private String smsPort;

    @Value("${sms.account.sid}")
    private String accountSid;

    @Value("${sms.auth.token}")
    private String authToken;

    @Value("${sms.app.id}")
    private String appId;

    @Bean
    public CCPRestSmsSDK smsClient() {
        CCPRestSmsSDK smsClient = new CCPRestSmsSDK();
        smsClient.init(smsEndPoint, smsPort);
        smsClient.setAccount(accountSid, authToken);
        smsClient.setAppId(appId);
        return smsClient;
    }

    @Bean
    public CCPRestSDK restClient(){
        CCPRestSDK restClient = new CCPRestSDK();
        restClient.init(smsEndPoint,smsPort);
        restClient.setAccount(accountSid,authToken);
        restClient.setAppId(appId);
        return restClient;
    }
}
