package com.smartyang.rabbitmq.direct.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @version V1.0
 * @title: DirectRabbitConfig
 * @description:
 * @author: weike
 * @date: 2020/8/27 13:37
 * @remark: 修改内容
 */
@Configuration
public class DirectRabbitConfig {

    /**
     * 创建队列
     * 队列名称： direct-test
     * durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
     * exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
     * autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
     * @return direct-test队列
     */
    @Bean
    public Queue testDirectQueue(){
        return new Queue("direct-test",true,false,false);
    }


    /**
     * 创建交换机
     * 交换机名称： test-direct-exchange
     * durable:是否持久化,默认是false,持久化交换机：会被存储在磁盘上，当消息代理重启时仍然存在，暂存交换机：当前连接有效
     * autoDelete:是否自动删除
     * @return test-direct-exchange交换机
     */
    @Bean
    public DirectExchange testDirectExchange(){
        return new DirectExchange("test-direct-exchange",true,false);
    }

    /**
     * 队列和交换机通过路由键进行绑定
     * 路由键名称：TestDirectRouting
     * @return
     */
    @Bean
    public Binding bindingDirect(){
        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with("TestDirectRouting");
    }


}
