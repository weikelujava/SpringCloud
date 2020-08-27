package com.smartyang.rabbitmq.direct.produce;

import cn.hutool.core.util.IdUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @version V1.0
 * @title: DirectMqSender
 * @description:
 * @author: weike
 * @date: 2020/8/27 13:49
 * @remark: 修改内容
 */

@Component
public class DirectMqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message){

        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(IdUtil.createSnowflake(1,1).nextIdStr());
        // 发送消息到队列
        rabbitTemplate.convertAndSend("test-direct-exchange","TestDirectRouting",message,correlationData);
    }
}
