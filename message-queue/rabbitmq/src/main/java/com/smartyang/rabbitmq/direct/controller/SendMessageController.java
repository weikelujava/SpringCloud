package com.smartyang.rabbitmq.direct.controller;

import com.smartyang.rabbitmq.direct.produce.DirectMqSender;
import com.smartyang.rabbitmq.enums.TargetTypeEnum;
import com.smartyang.rabbitmq.model.NginxCacheParamEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @version V1.0
 * @title: SendMessageController
 * @description:
 * @author: weike
 * @date: 2020/8/27 13:47
 * @remark: 修改内容
 */
@Slf4j
@RestController
public class SendMessageController {


    @Autowired
    private DirectMqSender directMqSender;

    @GetMapping("/direct/send")
    public String sendDirectMessage(){
        String message = "吻别-"+System.currentTimeMillis();
        directMqSender.sendMessage(message);
        log.info("发送消息到mq(Direct),message:"+message);
        return "ok";
    }

    @GetMapping("/index/send")
    public String sendDirectIndexMessage(){
        String message = "category";
        directMqSender.sendMessage(message);
        log.info("发送消息到mq(Direct),message:"+message);
        return "ok";
    }

    @GetMapping("/category/direct/test/{target}/{type}/{id}")
    public String test(@PathVariable("target")String target, @PathVariable("type") String type, @PathVariable("id") String id) {
        NginxCacheParamEntity paramEntity = new NginxCacheParamEntity();
        paramEntity.setTarget(target);
        if("0".equals(type)){
            paramEntity.setType(null);
        }else {
            paramEntity.setType(type);
        }
        if("0".equals(id)){
            paramEntity.setTypeId(null);
        }else {
            paramEntity.setTypeId(id);
        }
        log.info("1.controller开始执行发送消息");
        directMqSender.sendTestMessage(paramEntity);
        return "发送成功";
    }

    /**
     * 测试枚举类属性
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(TargetTypeEnum.CATEGORY_WARNING.getType());

        List<String> list= new ArrayList<>();
        list.add("1001");
        list.add("1002");
        String type = "";
        for (String s : list) {
            type+=s;
            type+="-";
        }
        System.out.println(type.substring(0,type.lastIndexOf("-")));
        System.out.println(type.endsWith("-"));

    }
}
