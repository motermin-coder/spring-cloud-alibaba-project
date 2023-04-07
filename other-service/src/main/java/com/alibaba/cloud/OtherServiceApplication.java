package com.alibaba.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auth tom
 * @Date 2023-04-07 13:46:19
 */
@SpringBootApplication
@EnableFeignClients
public class OtherServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtherServiceApplication.class,args);
    }
}
