package com.smartyang.rabbitmq.fanout.produce;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @version V1.0
 * @title: FanoutMqSender
 * @description:
 * @author: weike
 * @date: 2020/8/31 10:46
 * @remark: 修改内容
 */
@Slf4j
@Component
public class FanoutMqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${mq.fanout.exchange.test}")
    private String fanoutExchangeName;

    public void sendMessage(String message){

        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(IdUtil.createSnowflake(1,1).nextIdStr());
        // 发送消息到Fanout交换机，所有绑定该交换机的不同队列都能收到消息
        rabbitTemplate.convertAndSend(fanoutExchangeName,"",message,correlationData);
    }
}
