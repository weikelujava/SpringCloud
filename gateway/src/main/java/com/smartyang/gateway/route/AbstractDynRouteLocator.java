package com.smartyang.gateway.route;

import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: AbstractDynRouteLocator
 * @description:
 * @author: lukewei
 * @date: 2020/7/15 20:04
 * @remark: 修改内容
 */
public abstract class AbstractDynRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator{

    private ZuulProperties zuulProperties;

    public AbstractDynRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.zuulProperties = properties;
    }

    /**
     * 刷新路由
     */
    @Override
    public void refresh(){
        doRefresh();
    }

    /**
     * 加载路由配置，由子类去实现
     * @return Map<String,ZuulProperties.ZuulRoute>
     */
    public abstract Map<String,ZuulProperties.ZuulRoute> loadDynamicRoute();

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes(){

        LinkedHashMap<String,ZuulProperties.ZuulRoute> routeLinkedHashMap = new LinkedHashMap<>(1);
        // 从application.properties中加载静态路由信息
        routeLinkedHashMap.putAll(super.locateRoutes());
        // 从数据源中加载动态路由信息
        routeLinkedHashMap.putAll(loadDynamicRoute());

        //优化配置
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>(1);
       if(routeLinkedHashMap.keySet().size() > 0){
           for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : routeLinkedHashMap.entrySet()) {
               String path = entry.getKey();
               // 如果不存在就在前面加 /
               if(!path.startsWith("/")){
                   path = "/" + path;
               }
               if(StringUtils.hasText(this.zuulProperties.getPrefix())){
                   path = this.zuulProperties.getPrefix() + path;
                   // 如果不存在就在前面加 /
                   if(!path.startsWith("/")){
                       path = "/" + path;
                   }
               }
               values.put(path,entry.getValue());
           }
       }

        return values;
    }



}
