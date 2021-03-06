package com.smartyang.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @version V1.0
 * @title: RedisApplication
 * @description:
 * @author: lukewei
 * @date: 2020/7/24 15:59
 * @remark: 修改内容
 */
@MapperScan("com.smartyang.redis.mapper.*")
@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
