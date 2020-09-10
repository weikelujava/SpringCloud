package com.smartyang.rabbitmq.direct.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @version V1.0
 * @title: DirectMqParamConfig
 * @description:
 * @author: lukewei
 * @date: 2020-09-08 2020/9/8
 * @remark: 修改内容
 */
@Configuration
public class DirectMqParamConfig {

    @Value("${mq.direct.nginx.exchange}")
    private String directNginxExchange;

    @Value("${mq.direct.nginx.queue}")
    private String directNginxDelCacheQueue;

    @Value("${mq.direct.nginx.rounting}")
    private String directNginxRouting;

    @Value("${mq.direct.nginx.ip}")
    private String nginxIp;

    public DirectMqParamConfig() {
    }


    public String getDirectNginxExchange() {
        return directNginxExchange;
    }

    public void setDirectNginxExchange(String directNginxExchange) {
        this.directNginxExchange = directNginxExchange;
    }

    public String getDirectNginxDelCacheQueue() {
        return directNginxDelCacheQueue;
    }

    public void setDirectNginxDelCacheQueue(String directNginxDelCacheQueue) {
        this.directNginxDelCacheQueue = directNginxDelCacheQueue;
    }

    public String getDirectNginxRouting() {
        return directNginxRouting;
    }

    public void setDirectNginxRouting(String directNginxRouting) {
        this.directNginxRouting = directNginxRouting;
    }

    public String[] getNginxIp() {
        if(null != nginxIp){
            if(nginxIp.contains(",")){
                return nginxIp.split(",");
            }else {
                return new String[]{nginxIp};
            }
        }
        return new String[]{nginxIp};
    }

    public void setNginxIp(String nginxIp) {
        this.nginxIp = nginxIp;
    }
}
