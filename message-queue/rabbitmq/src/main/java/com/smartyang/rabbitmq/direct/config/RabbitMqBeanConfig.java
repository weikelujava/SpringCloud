package com.smartyang.rabbitmq.direct.config;

import com.smartyang.rabbitmq.direct.callback.MessageConfirmCallback;
import com.smartyang.rabbitmq.direct.callback.MessageReturnCallback;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @version V1.0
 * @title: RabbitMqBeanConfig
 * @description:
 * @author: weike
 * @date: 2020/8/27 14:28
 * @remark: 修改内容
 */
@Configuration
public class RabbitMqBeanConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate createRabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new MessageConfirmCallback());
        rabbitTemplate.setReturnCallback(new MessageReturnCallback());
        return rabbitTemplate;
    }


    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setIgnoreDeclarationExceptions(true);
        return rabbitAdmin;
    }
}
