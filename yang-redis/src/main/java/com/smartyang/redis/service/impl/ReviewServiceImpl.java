package com.smartyang.redis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smartyang.redis.mapper.ReviewMapper;
import com.smartyang.redis.model.Review;
import com.smartyang.redis.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
@Transactional(readOnly = true)
public class ReviewServiceImpl extends SuperServiceImpl<ReviewMapper, Review> implements ReviewService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional(readOnly = false)
    public Integer saveReview(Review review) {
        return baseMapper.insert(review);
    }

    @Override
    public List<Review> findReviewListByCategoryName(String categoryName) {
        String hkey = "redis-review-category-name";
        String cacheKey = "review:category:" + categoryName;
        List<Review> reviews = new ArrayList<>(1);
        Object redisObj = redisTemplate.opsForHash().get(cacheKey, hkey);
        if (null != redisObj) {
            reviews = (List<Review>) redisObj;
            return reviews;
        }
        List<Review> reviewList = baseMapper.selectList(new QueryWrapper<Review>().eq("category_name", categoryName));
        redisTemplate.opsForHash().put(cacheKey, hkey, reviewList);
        return reviewList;
    }

    @Override
    public List<Review> findReviewListByCustomerId(Integer customerId) {
        String hkey = "redis-review-customer-id";
        String cacheKey = "review:category:" + customerId;
        List<Review> reviews = new ArrayList<>(1);
        Object redisObj = redisTemplate.opsForHash().get(cacheKey, hkey);
        if (null != redisObj) {
            reviews = (List<Review>) redisObj;
            return reviews;
        }
        List<Review> reviewList = baseMapper.selectList(new QueryWrapper<Review>().eq("customer_id", customerId));
        redisTemplate.opsForHash().put(cacheKey, hkey, reviewList);
        return reviewList;
    }

    @Override
    public void delReviewCacheWithCategoryName() {
        String cacheKey = "review:category:北海公园";
//        redisTemplate.opsForHash().delete(hkey,)
        redisTemplate.delete(cacheKey);
    }

    @Override
    public void delReviewCacheWithCustomerId() {
        String hkey = "redis-review-customer-id";
    }
}
