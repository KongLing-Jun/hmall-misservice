package com.hmall.cart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : RemoteCallConfig
 * @description : [远程调用服务]
 * @createTime : [2025/3/31 9:04]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/3/31 9:04]
 * @updateRemark : [v1.0]
 */
@Configuration
public class RemoteCallConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
