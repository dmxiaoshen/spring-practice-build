package com.xyz.common.base.aop;

import com.xiaouchina.common.utils.JacksonUtils;
import com.xyz.common.base.swagger.SwaggerProperties;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Slf4j
public abstract class AbstractServiceLogAspect {

    public abstract void log();

    @Around("log()")
    public Object doAround(ProceedingJoinPoint pip) throws Throwable{
        Class clazz = pip.getClass();
        Object[] args = pip.getArgs();
        log.info("=====ServiceLogAspect start");
        log.info("class: "+clazz);
        log.info("args: "+Arrays.toString(args));
        log.info("toLongString: "+pip.toLongString());
        log.info("toShortString: "+pip.toShortString());
        log.info("toString: "+pip.toString());
        log.info("getName: "+pip.getSignature().getName());
        log.info("getDeclaringType: "+pip.getSignature().getDeclaringType());
        log.info("getDeclaringTypeName: "+pip.getSignature().getDeclaringTypeName());
        log.info("getClass: "+pip.getSignature().getClass());
        Object obj = pip.proceed();
        log.info("result: "+ JacksonUtils.toJson(obj));
        log.info("=====ServiceLogAspect end");
        return obj;
    }
}
