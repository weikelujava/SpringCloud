package com.smartyang.demo.common.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @version V1.0
 * @title: ThreadPoolProvider
 * @description:
 * @author: weike
 * @date: 2020/8/17 9:59
 * @remark: 修改内容
 */
@Slf4j
public class ThreadPoolProvider {

    private ThreadPoolProvider() {
    }

    /**
     * 线程池<br/>
     * 备注:不建议随处创建,可统一一处调用
     * new ThreadPoolExecutor.AbortPolicy() 添加不上抛异常
     * 目前使用自定义拒绝策略，在队列满了后放入新线程继续执行
     */
    private static final ExecutorService FIXED_THREAD_POOL = new ThreadPoolExecutor(cpuCoreSize(), cpuCoreSize()*2, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10000),
            new DefaultThreadFactory(),
            new CustomRejectedExecutionHandler());

    /**
     * @return ExecutorService
     * @Title newFixedThreadPool
     * @Description: 获取定长的线程池
     * @author weike
     * @date 2020-3-24 18:13:24
     */
    public static ExecutorService newFixedThreadPool() {
        return FIXED_THREAD_POOL;
    }

    /**
     * 智适应获取cpu大小
     * @return cpuCoreSize
     */
    private static Integer cpuCoreSize(){
        return Runtime.getRuntime().availableProcessors() != 0 ? Runtime.getRuntime().availableProcessors():8;
    }


    /**
     * @ClassName: DefaultThreadFactory
     * @Description: 线程命名工厂
     * @Author: weike
     * @Date: 2020-3-24 18:13:32
     */
    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            this("myThread-pool-t-");
        }

        DefaultThreadFactory(String prefix) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = prefix + POOL_NUMBER.getAndIncrement();
        }

        @Override
        public Thread newThread(Runnable runnable) {
            Thread t = new Thread(group, runnable,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    /**
     * 自定义线程池策略
     * 线程被拒绝后，重新开启一个线程执行被拒绝线程
     */
    private static class CustomRejectedExecutionHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            log.info("New Thread begin run which thread is Rejected");
            ThreadPoolProvider.newFixedThreadPool().execute(r);
        }
    }
}
