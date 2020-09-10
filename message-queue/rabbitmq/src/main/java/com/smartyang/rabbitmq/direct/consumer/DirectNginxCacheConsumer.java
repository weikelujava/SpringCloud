package com.smartyang.rabbitmq.direct.consumer;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.smartyang.rabbitmq.direct.config.DirectMqParamConfig;
import com.smartyang.rabbitmq.model.NginxCacheParamEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @version V1.0
 * @title: DirectNginxCacheConsumer
 * @description: Nginx缓存消费者，多实例下Direct模式，轮询且只有一个消费者消费消息
 * @author: lukewei
 * @date: 2020-09-08
 * @remark: 修改内容
 */
@Slf4j
@Component
public class DirectNginxCacheConsumer {

    @Autowired
    private DirectMqParamConfig paramConfig;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${mq.direct.nginx.queue}", durable = "true"),
        exchange = @Exchange(name = "${mq.direct.nginx.exchange}", type = ExchangeTypes.DIRECT),
        key = "${mq.direct.nginx.rounting}"))
    public void process(Message message, Channel channel) {
        log.info("3.消费者消费消息");
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("4.消息参数："+msg);
        NginxCacheParamEntity param = JSON.parseObject(msg, NginxCacheParamEntity.class);
//        if(null == param.getType() || "".equals(param.getType())){
//            return;
//        }
        String[] nginxIps = paramConfig.getNginxIp();
        if (nginxIps != null &&nginxIps.length > 0){
            for (String nginxIp : nginxIps) {
                    RestTemplate restTemplate = new RestTemplate();

                    String baseUrl = nginxIp+"/delcache?target="+param.getTarget()+"&&type="+param.getType()+"&&target-id="+param.getTypeId();
//                    String url = (null == param.getTypeId()) ? baseUrl : (baseUrl+"&&target-id="+param.getTypeId());
//                    String url = (null == param.getTypeId()) ? (baseUrl+"") : (baseUrl+param.getTypeId());
                    log.info("5.请求的url: "+baseUrl);
                    restTemplate.getMessageConverters().add(0,new StringHttpMessageConverter(StandardCharsets.UTF_8));
                    String res = null;
                    try {
                        res = restTemplate.getForObject(new URI(baseUrl),String.class);
                    } catch (URISyntaxException e) {
                        log.error("7.访问Nginx路径[ "+baseUrl+" ],返回失败");
                        e.printStackTrace();
                    }
                   log.info("6.访问 [ "+baseUrl+" ],返回结果:"+res);
            }
        }



    }
}
