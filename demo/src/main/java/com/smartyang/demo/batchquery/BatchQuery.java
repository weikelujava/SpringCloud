package com.smartyang.demo.batchquery;

import com.smartyang.demo.common.model.User;
import com.smartyang.demo.common.threadpool.ThreadPoolProvider;
import com.smartyang.demo.common.util.RandomUser;
import com.smartyang.demo.servcie.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @version V1.0
 * @title: BatchQuery
 * @description:
 * @author: weike
 * @date: 2020/8/17 9:55
 * @remark: 修改内容
 */
@RestController
public class BatchQuery {

    @Autowired
    private UserService userService;

    @GetMapping("/demo/batch-query/{total}")
    public void batchQuery(@PathVariable("total") Integer total){

        //每次查询的数据
        Integer batchCount = total;
        //查询总数
        Long count = userService.countUser();
        if(count == 0){
            return;
        }
        //计算查询的次数
        Long times = (count%batchCount == 0 ? count/batchCount : count/batchCount+1);

        CountDownLatch latch = new CountDownLatch(times.intValue());
        for (Long i = 0L; i < times; i++) {
//            Long begin = System.currentTimeMillis();
            Integer startRow = Math.toIntExact(i * batchCount);
            Integer pageSize = batchCount;
            Long currentTime = i+1;
            ThreadPoolProvider.newFixedThreadPool().execute(()->{
                try {
                    Long everyTime = System.currentTimeMillis();
                    List<User> userList = userService.getUserListByBatch(startRow,pageSize);
                    System.out.println("第"+(currentTime)+"次查询,查询的数量为："+userList.size()+",起始行数："+startRow+",页容量:"+pageSize+"执行时间："+(System.currentTimeMillis()-everyTime));
                    //批量写入ES
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
//            latch.countDown();
//            System.out.println("第"+latch.getCount()+"线程，执行时间："+(System.currentTimeMillis()-begin));
        }
    }

    @PostMapping("/demo/user/{num}")
    public void saveUser(@PathVariable("num") Integer num){
        Long begin = System.currentTimeMillis();
//        List<String> userIds = RandomUser.getUserIds(null, 100);
        List<String> userIds = RandomUser.getUserIds(null, num);
//        List<String> passwords = RandomUser.getPasswords(100, 6);
        List<String> passwords = RandomUser.getPasswords(num, 6);
        List<User> users = new ArrayList<>();
        for (int i = 0; i < userIds.size(); i++) {
            User user = new User();
            user.setUserName(userIds.get(i));
            user.setPassword(passwords.get(i));
            users.add(user);
        }

        userService.saveUsers(users);

        System.out.println("插入"+num+"条数据，使用时间："+(System.currentTimeMillis()-begin));
    }

}
