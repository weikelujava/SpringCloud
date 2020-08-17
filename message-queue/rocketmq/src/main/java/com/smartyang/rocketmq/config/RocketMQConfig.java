package com.smartyang.rocketmq.config;

import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 *
 * @version V1.0
 * @title: RocketMQConfig
 * @description:
 * @author: lukewei
 * @date: 2020/7/13 19:15
 * @remark: 修改内容
 */
@Configuration
public class RocketMQConfig {

    public Properties getProperties() {

        Properties properties = new Properties();
        /**
         * 键的首字母必须大写
         */
        properties.setProperty("AccessKey", "**");
        //
        properties.setProperty("SecretKey", "**");

        //
//        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "3000");
        // 顺序消息消费失败进行重试前的等待时间，单位(毫秒)
//        properties.put(PropertyKeyConst.SuspendTimeMillis, "100");
        // 消息消费失败时的最大重试次数
//        properties.put(PropertyKeyConst.MaxReconsumeTimes, "20");
        //
//        properties.put(PropertyKeyConst.NAMESRV_ADDR, "http://MQ_INST_1944503281593155_BaOTPbFU.mq-internet-access.mq-internet.aliyuncs.com:80");
        return properties;
    }
}
