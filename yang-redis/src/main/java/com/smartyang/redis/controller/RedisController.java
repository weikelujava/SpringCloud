package com.smartyang.redis.controller;

import com.smartyang.redis.model.Review;
import com.smartyang.redis.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

/**
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

    private static final List<String> CATEGORY_NAME = new ArrayList<String>(Arrays.asList("北海公园", "中山公园", "颐和园", "中国博物馆", "北京动物园"));


    @Autowired
    private ReviewService reviewService;


    @RequestMapping("/add-review")
    public String addRedisData() {
        for (int i = 1; i < 100; i++) {
            Review review = new Review();
            review.setId(i);
            review.setCustomerId(randomCustomerId());
            review.setReviewContent("第" + i + "条评论,评论者:" + review.getCustomerId());
            review.setCategoryName(getCategoryName());
            reviewService.saveReview(review);
        }
        return "增加成功";
    }

    /**
     * 随机生成10位用户
     *
     * @return 用户ID
     */
    private Integer randomCustomerId() {
        return new Random().nextInt(10) + 1;
    }

    /**
     * 随机获取公园名称
     *
     * @return 公园名称
     */
    private String getCategoryName() {
        return CATEGORY_NAME.get(new Random().nextInt(5));
    }


    @GetMapping("/get-review-category-name/{categoryName}")
    public List<Review> findReviewListByCategoryName(@PathVariable("categoryName") String categoryName) {
        return reviewService.findReviewListByCategoryName(categoryName);
    }

    @GetMapping("/get-review-customer-id/{customerId}")
    public List<Review> findReviewListByCustomerId(@PathVariable("customerId") Integer customerId) {
        return reviewService.findReviewListByCustomerId(customerId);
    }

    @GetMapping("/del-review-cache/category-name")
    public String delReviewCacheWithCategoryName() {
        reviewService.delReviewCacheWithCategoryName();
        return "删除公园名称的评论缓存成功";
    }

    @GetMapping("/del-review-cache/customer-id")
    public String delReviewCacheWithCustomerId() {
        reviewService.delReviewCacheWithCustomerId();
        return "删除用户的评论缓存成功";
    }
}
