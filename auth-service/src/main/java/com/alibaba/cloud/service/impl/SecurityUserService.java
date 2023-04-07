package com.alibaba.cloud.service.impl;

import com.alibaba.cloud.mapper.RoleMapper;
import com.alibaba.cloud.mapper.UserMapper;
import com.alibaba.cloud.pojo.Role;
import com.alibaba.cloud.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auth tom
 * @Date 2023-04-07 17:45:35
 */
@Component
public class SecurityUserService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    /***
     * 登录验证
     * @param name
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(name);
        if (user == null){
            return new User();
        }
        List<Role> rolesByUserId = roleMapper.findRolesByUserId(user.getId());
        ArrayList<GrantedAuthority> roles = new ArrayList<>();
        for (Role role : rolesByUserId) {
            roles.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return role.getRole();
                }
            });
        }
        user.setAuthorities(roles);
        return user;
    }
}
