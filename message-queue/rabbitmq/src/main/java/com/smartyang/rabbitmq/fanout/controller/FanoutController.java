package com.smartyang.rabbitmq.fanout.controller;

import com.smartyang.rabbitmq.fanout.produce.FanoutMqSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @version V1.0
 * @title: FanoutController
 * @description:
 * @author: weike
 * @date: 2020/8/31 10:45
 * @remark: 修改内容
 */
@Slf4j
@RestController
public class FanoutController {


    @Autowired
    private FanoutMqSender fanoutMqSender;

    @GetMapping("/fanout/send")
    public String sendDirectMessage(){
        String message = "头发乱了-"+System.currentTimeMillis();
        fanoutMqSender.sendMessage(message);
        log.info("发送消息到mq(Fanout),message:"+message);
        return "ok";
    }
}
