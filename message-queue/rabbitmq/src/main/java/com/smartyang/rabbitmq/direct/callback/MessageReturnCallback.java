package com.smartyang.rabbitmq.direct.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 *
 * @version V1.0
 * @title: MessageReturnCallback
 * @description:
 * @author: weike
 * @date: 2020/8/27 14:27
 * @remark: 修改内容
 */
@Slf4j
public class MessageReturnCallback implements RabbitTemplate.ReturnCallback{
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("rabbitmq return callback-->消息路由失败。路由信息:" + "exchange:" + exchange + " routingKey:" + routingKey);
        log.error("rabbitmq return callback-->消息路由失败。消息内容:" + message);
    }
}
