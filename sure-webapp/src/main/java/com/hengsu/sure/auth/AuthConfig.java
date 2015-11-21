package com.hengsu.sure.auth;

import com.hengsu.sure.core.Context;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by haiquanli on 15/11/20.
 */
@Configuration
public class AuthConfig {

    @Bean
    @Qualifier("authContext")
    public Context authContext(){
        Context<String,Long> context = new Context<>();
        //TODO 需要去掉这行
        context.put("aaaaa",3L);
        context.put("bbbbb",4L);
        return context;
    }
}
