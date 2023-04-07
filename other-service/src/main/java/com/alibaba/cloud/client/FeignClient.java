package com.alibaba.cloud.client;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auth tom
 * @Date 2023-04-07 13:48:49
 */
@org.springframework.cloud.openfeign.FeignClient(name = "produce-service"
        , fallbackFactory = EchoServiceFallBack.class
        , configuration = FeignConfiguration.class)
public interface FeignClient {

    /**
     * 测试服务调用
     * @return
     */
    @GetMapping("/product/port")
    String port();
}
