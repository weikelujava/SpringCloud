package com.smartyang.rabbitmq.fanout.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 *
 * @version V1.0
 * @title: FanoutMqConsumer
 * @description:
 * @author: weike
 * @date: 2020/8/31 10:53
 * @remark: 修改内容
 */
@Slf4j
@Component
public class FanoutMqConsumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${mq.fanout.queue.test}",durable = "true"),
            exchange = @Exchange(name = "${mq.fanout.exchange.test}", type = ExchangeTypes.FANOUT)))
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("Fanout 消费信息：【" + msg + "】");
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
