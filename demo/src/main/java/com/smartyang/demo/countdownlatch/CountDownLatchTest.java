package com.smartyang.demo.countdownlatch;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 *
 * @version V1.0
 * @title: CountDownLatchTest
 * @description:
 * @author: weike
 * @date: 2020/8/10 17:05
 * @remark: 修改内容
 *
 * countDownLatch是在java1.5被引入,存于java.util.current包下
 * countDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行
 * 是通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，计数器的值就-1，当计数器的值为0时，表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了
 */
@Slf4j
public class CountDownLatchTest {


    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(2);
        System.out.println("主线程开始执行... ...");


    }
}
