package com.smartyang.rabbitmq.direct.controller;

import com.smartyang.rabbitmq.direct.produce.DirectMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @version V1.0
 * @title: SendMessageController
 * @description:
 * @author: weike
 * @date: 2020/8/27 13:47
 * @remark: 修改内容
 */
@RestController
public class SendMessageController {


    @Autowired
    private DirectMqSender directMqSender;

    @GetMapping("/direct/send")
    public String sendDirectMessage(){
        String message = "吻别-"+System.currentTimeMillis();
        directMqSender.sendMessage(message);
        return "ok";
    }
}
