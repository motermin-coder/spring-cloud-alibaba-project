package com.alibaba.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auth tom
 * @Date 2023-04-07 13:28:54
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 负载均衡测试
     * @return
     */
    @GetMapping("/other")
    public String test(){
        return restTemplate.getForObject("http://other-service/info",String.class, (Object) null);
    }


    /**
     * 资源限流测试
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/port")
    @SentinelResource("port")
    public String portInfo(HttpServletRequest httpServletRequest){

        return "当前端口为：" + port + "请求端口为：" + httpServletRequest.getRemotePort();
    }
}
