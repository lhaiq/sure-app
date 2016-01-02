package com.hengsu.sure.auth;

import com.facepp.http.HttpRequests;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by haiquanli on 15/12/30.
 */
@Configuration
public class FaceConfig {

    @Value("${face.api.key}")
    private String apiKey;

    @Value("$(face.api.secret)")
    private String apiSecret;

    @Bean
    public HttpRequests httpRequests() {
        return new HttpRequests(apiKey, apiSecret, true, true);
    }
}
