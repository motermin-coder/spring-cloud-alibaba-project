package com.alibaba.cloud.controller;

import com.alibaba.cloud.client.FeignClient;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auth tom
 * @Date 2023-04-07 13:52:33
 */
@RestController
public class OtherController {


    @Autowired
    private FeignClient feignClient;

    @Value("${server.port}")
    private String port;


    @GetMapping("/info")
    public String info(){
        return "This is a test message! Current port is :" + port;
    }

    /**
     * 测试sentinel限流，资源名可以包含斜杠
     * @return
     */
    @GetMapping("/other/port")
    @SentinelResource("/other/port")
    public String getPort(){
        return feignClient.port();
    }
}
