package com.alibaba.cloud.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Auth tom
 * @Date 2023-04-07 18:43:45
 */
@Component
public class RedisService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;



    /**
     * 获取数据
     * @param key
     * @return
     */
    public Object getData(String key){
        if (key == null){
            throw new RuntimeException("键不能为空");
        }
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 保存数据无过期时间
     * @param key
     * @param value
     */
    public void saveDataNoExpire(String key,Object value){
        if (key == null){
            throw new RuntimeException("键不能为空");
        }
        redisTemplate.opsForValue().set(key,value);
    }

    /***
     * 保存数据
     * @param key
     * @param value
     * @param time
     */
    public void saveData(String key,Object value,Long time){
        if (key == null){
            throw new RuntimeException("键不能为空");
        }
        redisTemplate.opsForValue().set(key,value,time, TimeUnit.MINUTES);
    }
}
