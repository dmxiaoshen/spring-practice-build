package com.xyz.common.base.aop;

import com.xyz.common.base.annotation.RequestLock;
import com.xyz.common.base.exception.LockException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class AbstractLockAspect {

    public abstract void around();

    @Around("around()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        String targetName = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();

        RequestLock requestLock = method.getAnnotation(RequestLock.class);
        Class keyClass = requestLock.key();

        //所有参数
        Parameter[] parameters = method.getParameters();
        //所有参数类型
        Class[] types = method.getParameterTypes();
        //所有真实的参数(取值要从这里取)
        Object[] args = point.getArgs();

        String skey = null;//注解标识的锁的key
        Integer count = null;
        //首先检查参数层有没有RequestLockKey注解,如果有则直接取该值
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(keyClass)) {
                count = i;
                break;
            }
        }

        if (count != null) {
            skey = args[count].toString();
        } else {
            //如果没有直接在参数层做RequestLockKey注解，那么一定是在参数对象内部注解了
            //这里遍历所有参数对象,取第一个获取到内部有RequestLockKey注解的属性的值
            for (int i = 0; i < types.length; i++) {
                if (types[i] instanceof Object) {
                    Field[] temp = FieldUtils.getFieldsWithAnnotation(types[i], keyClass);
                    if (temp.length > 0) {
                        skey = FieldUtils.readField(temp[0], args[i], true).toString();
                    }
                }
            }
        }

        if (skey == null) {
            throw new RuntimeException("标识为@RequestLock的方法必须声明key");
        }
        String key = buildKey(targetName, methodName, skey);
        long exp = requestLock.expirationTime();
        if (requestLock.timeUnit() == TimeUnit.MILLISECONDS) {
            exp = exp / 1000l;
        }
        String value = getLock(key, exp);
        if (value != null) {
            try {
                return point.proceed();
            } finally {
                releaseLock(key, value);
            }
        } else {
            throw new LockException("获取锁失败");
        }

    }

    private String buildKey(String targetName, String methodName, String key) {
        StringBuilder buffer = new StringBuilder("lock:")
                .append(targetName).append(":")
                .append(methodName).append(":")
                .append(key);
        return buffer.toString();
    }

    protected abstract String getLock(String key, long exp);

    protected abstract void releaseLock(String key, String value);
}
