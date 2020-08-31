package com.smartyang.rabbitmq.fanout.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @version V1.0
 * @title: FanoutMqBeanConfig
 * @description:
 * @author: weike
 * @date: 2020/8/31 10:42
 * @remark: 修改内容
 */
@Configuration
public class FanoutMqBeanConfig {

    @Value("${mq.fanout.exchange.test}")
    private String fanoutExchangeNameTest;

    @Value("${mq.fanout.queue.test}")
    private String fanoutQueueTest;

    /**
     * Fanout 交换机 持久化
     * Test交换机
     * @return
     */
    public FanoutExchange fanoutExchangeTest(){
        return new FanoutExchange(fanoutExchangeNameTest,true,false);
    }

    /**
     * Fanout 队列 持久化
     * Test队列
     * @return
     */
    @Bean
    public Queue fanoutQueueTest(){
        return new Queue(fanoutQueueTest,true);
    }


    /**
     * 绑定Test队列到Test交换机
     * @return
     */
    @Bean
    public Binding bingTestFanoutQueueToFanoutExchange(){
        return BindingBuilder.bind(fanoutQueueTest()).to(fanoutExchangeTest());
    }



}
