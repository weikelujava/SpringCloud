package com.smartyang.rabbitmq.model;

import java.io.Serializable;

/**
 *
 * @version V1.0
 * @title: NginxCacheParamEntity
 * @description: 该类用于向Nginx直连交换机发送消息的参数实体类
 * @author: lukewei
 * @date: 2020-09-08 2020/9/8
 * @remark: 修改内容
 */
public class NginxCacheParamEntity implements Serializable {

    private static final long serialVersionUID = 8471728380908197079L;

    /**
     * 缓存对象
     */
    private String target;

    /**
     * 缓存类型，type=pc or type=mobile
     */
    private String type;

    /**
     * 类型ID，允许传入为null,categoryId or productId
     */
    private String typeId;

    public NginxCacheParamEntity() {
    }

    public String getTarget() {
        return target == null ? "" : target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeId() {

        return typeId == null ? "": String.valueOf(typeId);
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "NginxCacheParamEntity{" +
            "target='" + target + '\'' +
            ", type='" + type + '\'' +
            ", typeId=" + typeId +
            '}';
    }
}
