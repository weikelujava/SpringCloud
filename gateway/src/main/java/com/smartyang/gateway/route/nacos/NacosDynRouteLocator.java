package com.smartyang.gateway.route.nacos;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.smartyang.gateway.entity.ZuulRouteEntity;
import com.smartyang.gateway.route.AbstractDynRouteLocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 *
 * @version V1.0
 * @title: NacosDynRouteLocator
 * @description:
 * @author: lukewei
 * @date: 2020/7/15 20:03
 * @remark: 修改内容
 */
@Slf4j
public class NacosDynRouteLocator extends AbstractDynRouteLocator {

    private static final String ZUUL_DATA_ID = "zuul-routes";

    private static final String ZUUL_GROUP_ID = "ZUUL_GATEWAY";


    private NacosConfigProperties nacosConfigProperties;

    private ApplicationEventPublisher applicationEventPublisher;

    private NacosDynRouteLocator nacosDynRouteLocator;

    private List<ZuulRouteEntity> zuulRouteEntityList = new ArrayList<>(1);

    public NacosDynRouteLocator(NacosConfigProperties nacosConfigProperties, ApplicationEventPublisher applicationEventPublisher, String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.nacosConfigProperties = nacosConfigProperties;
        this.applicationEventPublisher = applicationEventPublisher;
        this.nacosDynRouteLocator = this;
        // 添加Nacos监听
        addListener();
    }

    /**
     * 增加Nacos监听
     */
    private void addListener() {
        try {
            nacosConfigProperties.configServiceInstance().addListener(ZUUL_DATA_ID, ZUUL_GROUP_ID, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    // 赋值路由信息
                    if (!StringUtils.isEmpty(configInfo)) {
                        zuulRouteEntityList = JSONObject.parseArray(configInfo, ZuulRouteEntity.class);
                    }
                    nacosDynRouteLocator.setZuulRouteEntityList(zuulRouteEntityList);
                    RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(nacosDynRouteLocator);
                    applicationEventPublisher.publishEvent(routesRefreshedEvent);
                }
            });
        } catch (NacosException e) {
            log.error("nacos-addListener-error", e);
        }
    }


    @Override
    public Map<String, ZuulProperties.ZuulRoute> loadDynamicRoute() {
        Map<String, ZuulProperties.ZuulRoute> routeMap = new LinkedHashMap<>(1);
        if (null == zuulRouteEntityList) {
            zuulRouteEntityList = getNacosConfig();
        }
        for (ZuulRouteEntity zuulRouteEntity : zuulRouteEntityList) {
            if (!StringUtils.hasText(zuulRouteEntity.getPath()) || !zuulRouteEntity.isEnabled()) {
                continue;
            }
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            BeanUtils.copyProperties(zuulRouteEntity, zuulRoute);
            routeMap.put(zuulRoute.getPath(), zuulRoute);
        }
        return routeMap;
    }

    /**
     * 获取zuul的路由配置列表
     *
     * @return List<ZuulRouteEntity>
     */
    private List<ZuulRouteEntity> getNacosConfig() {
        try {
            String content = nacosConfigProperties.configServiceInstance().getConfig(ZUUL_DATA_ID, ZUUL_GROUP_ID, 5000);
            if (!StringUtils.isEmpty(content)) {
                return JSONObject.parseArray(content, ZuulRouteEntity.class);
            }
            return new ArrayList<>(1);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(1);
    }

    public void setZuulRouteEntityList(List<ZuulRouteEntity> zuulRouteEntityList) {
        this.zuulRouteEntityList = zuulRouteEntityList;
    }
}
