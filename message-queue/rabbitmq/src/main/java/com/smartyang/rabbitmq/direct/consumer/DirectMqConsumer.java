package com.smartyang.rabbitmq.direct.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @version V1.0
 * @title: DirectMqConsumer
 * @description:
 * @author: weike
 * @date: 2020/8/27 14:07
 * @remark: 修改内容
 */
@Slf4j
@Component
public class DirectMqConsumer  {

//    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "direct-test", durable = "true"),
//            exchange = @Exchange(name = "test-direct-exchange", type = ExchangeTypes.DIRECT),
//            key = "TestDirectRouting"))
    public void process(Message message, Channel channel) throws IOException {

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("Direct Consumer: "+msg);
    }
}
