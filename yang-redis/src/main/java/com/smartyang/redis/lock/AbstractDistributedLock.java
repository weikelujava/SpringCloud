package com.smartyang.redis.lock;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0.0
 * @title: AbstractDistributedLock
 * @description: 分布式锁抽象类
 * @author: hollysmart
 * @date: 2019/10/16 12:31
 * @remark:
 */
public abstract class AbstractDistributedLock implements DistributedLock {

    @Override
    public boolean lock(String key) {
        return lock(key, TIMEOUT_MILLIS, RETRY_TIMES, SLEEP_MILLIS);
    }

    @Override
    public boolean lock(String key, int retryTimes) {
        return lock(key, TIMEOUT_MILLIS, retryTimes, SLEEP_MILLIS);
    }

    @Override
    public boolean lock(String key, int retryTimes, long sleepMillis) {
        return lock(key, TIMEOUT_MILLIS, retryTimes, sleepMillis);
    }

    @Override
    public boolean lock(String key, long expire) {
        return lock(key, expire, RETRY_TIMES, SLEEP_MILLIS);
    }

    @Override
    public boolean lock(String key, long expire, int retryTimes) {
        return lock(key, expire, retryTimes, SLEEP_MILLIS);
    }
}
