package com.alibaba.cloud.client;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Auth tom
 * @Date 2023-04-07 14:21:08
 */
@Component
public class FeignConfiguration {

    @Bean
    public EchoServiceFallBack echoServiceFallBack(){
        return new EchoServiceFallBack();
    }
}
