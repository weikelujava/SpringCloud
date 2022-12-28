package com.smart.drools.mode;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: Order
 * @description:
 * @author: lukewei
 * @date: 2020-09-14 17:59
 * @remark: 修改内容
 */
@Slf4j
public class Order implements Serializable {

    private int buyCount;

    /**
     * 原价
     */
    public Double originalPrice;

    /**
     * 实际价格
     */
    public Double realPrice;

    public Order() {
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }
}
