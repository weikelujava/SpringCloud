package com.smartyang.redis.controller;

import com.smartyang.redis.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: RedisController
 * @description:
 * @author: lukewei
 * @date: 2020/7/24 16:32
 * @remark: 修改内容
 */

@RestController
@RequestMapping("/smart-yang/redis")
public class RedisController {

    private static final List<String> CATEGORY_NAME = new ArrayList<String>(Arrays.asList("北海公园","中山公园","颐和园","中国博物馆","北京动物园"));

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;





    @RequestMapping("/add")
    public String addRedisData(){

        for (int i = 1; i < 1000; i++) {
            Review review = new Review();
            review.setId(i);
            review.setCustomerId(randomCustomerId());
            review.setReviewContent("第"+i+"条评论,评论者:"+review.getCustomerId());
            review.setCategoryName(getCategoryName());
            String cacheKey = "review:"+review.getCustomerId();

        }
        return "增加成功";
    }

    /**
     * 随机生成100位用户
     * @return 用户ID
     */
    private Integer randomCustomerId(){
         return new Random().nextInt(100000)+1;
    }

    /**
     * 随机获取公园名称
     * @return 公园名称
     */
    private String getCategoryName(){
       return CATEGORY_NAME.get(new Random().nextInt(5));
    }
}
