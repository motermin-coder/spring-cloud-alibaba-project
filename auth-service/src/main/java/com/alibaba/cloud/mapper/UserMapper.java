package com.alibaba.cloud.mapper;

import com.alibaba.cloud.pojo.User;

/**
 * @Auth tom
 * @Date 2023-04-07 17:39:12
 */
public interface UserMapper {

    User loadUserByUsername(String username);
}
