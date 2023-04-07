package com.alibaba.cloud.config;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.StandardCharsets;

/**
 * @Auth tom
 * @Date 2023-04-07 18:33:02
 */
public class ObjectSerializer implements RedisSerializer<Object> {
    @Override
    public byte[] serialize(Object obj) throws SerializationException {
        return obj == null ? new byte[0] : JSON.toJSONString(obj).getBytes(StandardCharsets.UTF_8) ;
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return bytes != null && bytes.length != 0 ? JSON.parseObject(new String(bytes)) : new String(new byte[0]);
    }
}
