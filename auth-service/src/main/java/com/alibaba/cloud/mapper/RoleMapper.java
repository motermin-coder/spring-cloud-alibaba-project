package com.alibaba.cloud.mapper;

import com.alibaba.cloud.pojo.Role;

import java.util.List;

/**
 * @Auth tom
 * @Date 2023-04-07 17:47:16
 */
public interface RoleMapper {

    List<Role> findRolesByUserId(Integer id);
}
