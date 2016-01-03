package com.hengsu.sure.core;

import com.gexin.rp.sdk.http.IGtPush;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by haiquanli on 15/11/25.
 */
@Configuration
public class PushConfig {

    @Value("${push.app.key}")
    private String appKey;

    @Value("${push.master}")
    private String master;

    @Bean
    public IGtPush push() {
        return new IGtPush(appKey, master);
    }

}
