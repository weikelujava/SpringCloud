package com.smartyang.rabbitmq.direct.callback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 *
 * @version V1.0
 * @title: MessageConfirmCallback
 * @description:
 * @author: weike
 * @date: 2020/8/27 14:26
 * @remark: 修改内容
 */

public class MessageConfirmCallback implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

    }
}
