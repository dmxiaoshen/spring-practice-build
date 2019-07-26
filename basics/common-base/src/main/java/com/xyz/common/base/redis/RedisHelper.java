package com.xyz.common.base.redis;

import com.xyz.common.base.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisHelper {

    private static RedisTemplate<String,String> redisTemplate = (RedisTemplate)SpringContextUtil.getBean("redisTemplate");

    public static void set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    public static String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public static void set(String key,String value,long seconds){
        redisTemplate.opsForValue().set(key,value,seconds, TimeUnit.SECONDS);
    }

    public static String lock(String lockKey,long expttl){
        String value = String.valueOf(System.currentTimeMillis()+expttl*1000);
        if(redisTemplate.opsForValue().setIfAbsent(lockKey,value)){
            //对应setnx,表示请求锁成功
            return value;
        }

        //不能set成功，则表示lockKey这个key已经存在，这时候要验证下看是否过期
        String existValue = redisTemplate.opsForValue().get(lockKey);
        if(!isBlank(existValue) && Long.parseLong(existValue)<System.currentTimeMillis()){
            //过期的情况下,getset返回旧值
            String oldValue = redisTemplate.opsForValue().getAndSet(lockKey,value);
            if(!isBlank(oldValue) && oldValue.equals(existValue)){
                return value;
            }
        }

        return null;

    }

    public static void unlock(String lockKey,String lockValue){
        try {
            String currentValue = redisTemplate.opsForValue().get(lockKey);
            if (!isBlank(currentValue) && currentValue.equals(lockValue)) {
                redisTemplate.opsForValue().getOperations().delete(lockKey);
            }
        }catch (Exception e){
            log.error("redis锁解锁异常,key={},value={}",lockKey,lockValue);
        }
    }

    private static boolean isBlank(String value){
        return value==null || "".equals(value);
    }
}
