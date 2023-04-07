package com.alibaba.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auth tom
 * @Date 2023-04-07 21:30:01
 */
@RestController
public class UserController {


    @GetMapping("/list")
    public String list(){
        return "list info!";
    }
}
