package com.alibaba.cloud.filter;

import com.alibaba.cloud.component.UserLoginComponent;
import com.alibaba.cloud.pojo.User;
import com.alibaba.cloud.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auth tom
 * @Date 2023-04-07 20:52:10
 */
@Component
public class LoginFilter extends OncePerRequestFilter {

    @Autowired
    private UserLoginComponent userLoginComponent;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorization = httpServletRequest.getHeader("Authorization");
        if (StringUtil.ifNullOrEmpty(authorization)){
            authorization = authorization.replace("Bearer ","");
            User user = userLoginComponent.parseTokenInfo(authorization);
            String username = user.getUsername();
            String password = user.getPassword();
            if (StringUtil.ifNullOrEmpty(username)&&StringUtil.ifNullOrEmpty(password)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
