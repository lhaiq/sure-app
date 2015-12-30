package com.hengsu.sure.core;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OSSConfig {

    @Value("${oss.access.endpoint}")
    private String endpoint;

    @Value("${oss.access.key.id}")
    private String accessKeyId;

    @Value("${oss.access.key.secret}")
    private String accessKeySecret;

    @Bean
    public OSSClient ossClient() {
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        return client;
    }

}
