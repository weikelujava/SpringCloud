package com.smartyang.gateway.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: CorsConfig
 * @description: 跨域配置
 * @author: lukewei
 * @date: 2020/7/15 18:44
 * @remark: 修改内容
 */
@Configuration
public class CorsConfig {

    /**
     * 跨域过滤配置
     * @return CorsFilter
     */
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许向该服务提交请求的方法，*表示全部允许
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        // 允许向该服务提交请求的Url，*代表全部允许
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        // 允许向该服务提交请求头信息，*代表全部允许
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        // 允许向该服务进行cookies跨域请求，true代表是
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(source);
    }

    /**
     * 配置过滤器
     * 此处需要引用javax.servlet-api依赖，否则过滤器报错，无法找到相对应的Filter
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> someFilterRegistration(){
        FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(corsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("corsFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }
}
