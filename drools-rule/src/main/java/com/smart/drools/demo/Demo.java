package com.smart.drools.demo;

import com.smart.drools.mode.Order;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: Demo
 * @description:
 * @author: lukewei
 * @date: 2020-09-14 18:52
 * @remark: 修改内容
 */
@RestController
public class Demo {


    @GetMapping("/demo")
    public void test1(){
        System.out.println("aa");

        KieServices kieServices = KieServices.Factory.get();
        // 获取kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从kie容器中获取创建一个kieSession对象,目前配置文件default配置的为true，所以默认的会拿到session对象，可以使用newKieSession(..)指定文件
        KieSession session = kieContainer.newKieSession();
        // Fact对象，事实对象
        Order order = new Order();
        order.setOriginalPrice(200.01d);
        // 将对象插入到工作内存中去
        session.insert(order);
        // 激活规则，由框架Drools自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        // 关闭会话
        session.dispose();
    }
}
