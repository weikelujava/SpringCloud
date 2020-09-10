package com.smartyang.rabbitmq.direct.produce;

import com.alibaba.fastjson.JSON;
import cn.hutool.core.util.IdUtil;
import com.smartyang.rabbitmq.direct.config.DirectMqParamConfig;
import com.smartyang.rabbitmq.model.NginxCacheParamEntity;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component
public class DirectMqSender {

    @Autowired
    private DirectMqParamConfig paramConfig;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message){

        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(IdUtil.createSnowflake(1,1).nextIdStr());
        // 发送消息到队列
        rabbitTemplate.convertAndSend("test-direct-exchange","TestDirectRouting",message,correlationData);
    }

    public void sendTestMessage(NginxCacheParamEntity paramEntity){
        // 分布式唯一ID
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(IdUtil.createSnowflake(1,1).nextIdStr());
        // 发送消息到队列
        rabbitTemplate.convertAndSend(paramConfig.getDirectNginxExchange(),paramConfig.getDirectNginxRouting(), JSON.toJSONString(paramEntity),correlationData);
        log.info("2.已经发送消息到队列");
    }
}
