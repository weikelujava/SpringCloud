package com.smartyang.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smartyang.redis.model.Review;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: ReviewService
 * @description:
 * @author: lukewei
 * @date: 2020/7/24 19:19
 * @remark: 修改内容
 */
public interface ReviewService extends IService<Review> {

    /**
     * 保存评论
     * @param review 评论对象
     * @return 受影响的行数
     */
    Integer saveReview(Review review);
}
