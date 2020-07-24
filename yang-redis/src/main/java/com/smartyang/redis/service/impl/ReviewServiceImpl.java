package com.smartyang.redis.service.impl;

import com.smartyang.redis.mapper.ReviewMapper;
import com.smartyang.redis.model.Review;
import com.smartyang.redis.service.ReviewService;
import org.springframework.stereotype.Service;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: ReviewServcieImpl
 * @description:
 * @author: lukewei
 * @date: 2020/7/24 19:21
 * @remark: 修改内容
 */
@Service
public class ReviewServiceImpl extends SuperServiceImpl<ReviewMapper, Review> implements ReviewService {


    @Override
    public Integer saveReview(Review review) {
        return baseMapper.insert(review);
    }
}
