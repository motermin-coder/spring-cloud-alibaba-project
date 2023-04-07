package com.alibaba.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auth tom
 * @Date 2023-04-07 17:21:42
 */
@SpringBootApplication
@MapperScan(basePackages = { "com.alibaba.cloud.mapper" })
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class,args);
    }
}
