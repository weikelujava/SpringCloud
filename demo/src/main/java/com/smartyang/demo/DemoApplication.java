package com.smartyang.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @version V1.0
 * @title: DemoApplication
 * @description:
 * @author: weike
 * @date: 2020/8/17 15:18
 * @remark: 修改内容
 */
@MapperScan("com.smartyang.demo.mapper.*")
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
