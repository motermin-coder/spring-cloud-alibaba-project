package com.alibaba.cloud.component;

import com.alibaba.cloud.pojo.User;
import com.alibaba.cloud.util.StringUtil;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * @Auth tom
 * @Date 2023-04-07 18:19:44
 */
@Component
public class UserLoginComponent {


    @Autowired
    private DaoAuthenticationProvider authenticationProvider;

    @Autowired
    private JWTConfig jwtConfig;

    @Autowired
    private RedisService redisService;


    /**
     * 用户登录
     * @param user
     * @return
     */
    public String login(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        if (!StringUtil.ifNullOrEmpty(username)&&!StringUtil.ifNullOrEmpty(password)){
            return "";
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
        emptyContext.setAuthentication(usernamePasswordAuthenticationToken);
        SecurityContextHolder.setContext(emptyContext);
        Authentication authenticate = authenticationProvider.authenticate(usernamePasswordAuthenticationToken);
        User details = (User) authenticate.getPrincipal();
        SecurityContextHolder.clearContext();
        return createToken(details);
    }

    /**
     * 创建token
     * @param user
     * @return
     */
    public String createToken(User user){
        String uuid = UUID.randomUUID().toString();
        user.setToken(uuid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",uuid);
        String token = jwtConfig.generateToken(map);
        redisService.saveData(token,user,120L);
        return token;
    }

    public User parseTokenInfo(String token){
        Claims claims = jwtConfig.parseToken(token);
        Date expiration = claims.getExpiration();
        long time = expiration.getTime();
        long time1 = new Date().getTime();
        if (time - time1 < 10 * 60){
            HashMap<String, Object> map = new HashMap<>();
        }
        Object data = redisService.getData(token);
        User user = new User();
        if (data != null){
            user = JSON.parseObject(data.toString(), User.class);
        }
        return user;
    }
}
