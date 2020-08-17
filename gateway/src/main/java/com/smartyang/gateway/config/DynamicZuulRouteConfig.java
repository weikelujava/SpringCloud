package com.smartyang.gateway.config;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.smartyang.gateway.route.nacos.NacosDynRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @version V1.0
 * @title: DynamicZuulRouteConfig
 * @description: 动态路由配置
 * @author: lukewei
 * @date: 2020/7/15 19:40
 * @remark: 修改内容
 *
 * # @ConditionalOnProperty注解使用说明：
 * 1.prefix  application.yml配置中的前缀
 * 2.name 属性是从application.yml配置中读取属性值
 * 3.havingValue 配置读取的属性值跟havingValue中的值做比较，如果一样就返回true，否则返回false;返回false则该配置不生效，true时生效
 * 4.matchIfMissing =true标识如果在application.yml中没有设置该属性，默认条件符合并返回true
 */


@Configuration
@ConditionalOnProperty(prefix = "smartyang.gateway.dynamicRoute", name = "enabled", havingValue = "true", matchIfMissing = true)
public class DynamicZuulRouteConfig {


    @Autowired
    private ZuulProperties zuulProperties;

    @Autowired
    private DispatcherServletPath dispatcherServletPath;

    /**
     * 动态路由-Nacos的实现
     */
    @Configuration
    @ConditionalOnProperty(prefix = "smartyang.gateway.dynamicRoute", name = "dataType", havingValue = "nacos", matchIfMissing = true)
    class NacosZuulRoute {

        @Autowired
        private NacosConfigProperties nacosConfigProperties;

        @Autowired
        private ApplicationEventPublisher applicationEventPublisher;


        @Bean
        public NacosDynRouteLocator nacosDynRouteLocator() {
            return new NacosDynRouteLocator(nacosConfigProperties, applicationEventPublisher, dispatcherServletPath.getPath(), zuulProperties);
        }

    }
}
