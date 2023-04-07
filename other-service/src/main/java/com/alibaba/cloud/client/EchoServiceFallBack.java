package com.alibaba.cloud.client;

import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * @Auth tom
 * @Date 2023-04-07 14:20:15
 */
public class EchoServiceFallBack implements FallbackFactory<FeignClient> {


    @Override
    public FeignClient create(Throwable cause) {
        String message = cause.getMessage();
        System.out.println("message = " + message);
        return new FeignClient() {
            @Override
            public String port() {
                return "fallback!";
            }
        };
    }
}
