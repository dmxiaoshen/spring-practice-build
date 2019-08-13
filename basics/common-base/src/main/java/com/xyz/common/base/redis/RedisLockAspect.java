package com.xyz.common.base.redis;

import com.xyz.common.base.aop.AbstractLockAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class RedisLockAspect extends AbstractLockAspect {

    @Pointcut("@annotation(com.xyz.common.base.annotation.RequestLock)")
    @Override
    public void around() {
    }

    @Override
    protected String getLock(String key, long exp) {
        return RedisHelper.lock(key, exp);
    }

    @Override
    protected void releaseLock(String key, String value) {
        RedisHelper.unlock(key, value);
    }
}
