package com.smartyang.redis.config;

import com.smartyang.redis.utils.RedisObjectSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: RedisConfig
 * @description: Redis配置键值序列化
 * @author: lukewei
 * @date: 2020/7/24 16:01
 * @remark: 修改内容
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        //声明键存储类型
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
        //声明缓存值存储类型
        RedisSerializer<Object> objectRedisSerializer = new RedisObjectSerializer();
        //定义缓存键存储类型
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //定义缓存维度类型
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        //定义缓存值存储类型
        redisTemplate.setValueSerializer(objectRedisSerializer);
        //完成设置后进行初始化操作，初始化RedisTemplate
        redisTemplate.afterPropertiesSet();
        //返回 redisTemplate
        return redisTemplate;
    }
}
