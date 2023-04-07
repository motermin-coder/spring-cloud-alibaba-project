package com.alibaba.cloud.controller;

import com.alibaba.cloud.component.UserLoginComponent;
import com.alibaba.cloud.pojo.ResultMap;
import com.alibaba.cloud.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auth tom
 * @Date 2023-04-07 18:06:27
 */
@RestController
public class SecurityUserController {

    @Autowired
    private UserLoginComponent userLoginComponent;


    @PostMapping("/login")
    public ResultMap<Object> login(@RequestBody User user){
        String login = userLoginComponent.login(user);
        return new ResultMap<>().success(login);
    }

}
