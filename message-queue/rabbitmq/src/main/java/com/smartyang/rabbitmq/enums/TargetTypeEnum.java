package com.smartyang.rabbitmq.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @version V1.0
 * @title: TargetTypeEnum
 * @description:
 * @author: lukewei
 * @date: 2020-09-10 16:56
 * @remark: 修改内容
 */
public class TargetTypeEnum {

    private static final Map<String, TargetTypeEnum> TYPES = new HashMap<String, TargetTypeEnum>();

    /**
     * 公园首页
     */
    public static final TargetTypeEnum INDEX = new TargetTypeEnum("index");

    /**
     * 公园提示页
     */
    public static final TargetTypeEnum CATEGORY_WARNING = new TargetTypeEnum("category_warning");

    /**
     * 公园详情页
     */
    public static final TargetTypeEnum CATEGORY_DETAIL = new TargetTypeEnum("category_detail");

    /**
     * 公园商品详情页
     */
    public static final TargetTypeEnum PRODUCT_DETAIL = new TargetTypeEnum("product_detail");


    public static TargetTypeEnum getInstance(final String type) {
        return TYPES.get(type);
    }

    private String type;

    public TargetTypeEnum() {
    }

    public TargetTypeEnum(final String type) {
        setType(type);
    }

    public String getType() {
        return type;
    }

    private void setType(final String type) {
        this.type = type;
        if (!TYPES.containsKey(type)) {
            TYPES.put(type, this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }
        TargetTypeEnum other = (TargetTypeEnum) obj;
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }
}
