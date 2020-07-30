package com.smartyang.redis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: Review
 * @description: 评论类
 * @author: lukewei
 * @date: 2020/7/24 16:28
 * @remark: 修改内容
 */
@TableName("review")
public class Review implements Serializable {

    private static final long serialVersionUID = -8872019980449384830L;

    /**
     * 评论ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer customerId;

    /**
     * 评论内容
     */
    private String reviewContent;

    /**
     * 公园名称
     */
    private String categoryName;

    public Review() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", categoryName=" + categoryName +
                ", reviewContent='" + reviewContent + '\'' +
                '}';
    }
}
