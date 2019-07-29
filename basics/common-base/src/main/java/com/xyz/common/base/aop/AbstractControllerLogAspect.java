package com.xyz.common.base.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Slf4j
public abstract class AbstractControllerLogAspect {

    public abstract void log();

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info("====ControllerLogAspect start");
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "rtv", pointcut = "log()")
    public void doAfterReturning(JoinPoint joinPoint, Object rtv) throws Throwable {
        // 处理完请求，返回内容
        log.info("Method Signature: " + joinPoint.getSignature());
        log.info("Returning:" + rtv);
        log.info("====ControllerLogAspect end");
    }

    /**
     * 这里@Around需要注意一定要把返回值返回，不然void的话所有被切入的方法都丢失返回值，
     * 这不是我们的初衷，我们应该把值传递出来
     *
     * @param pip
     * @return
     * @throws Throwable
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint pip) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("开始时间:" + start);
        Object obj = pip.proceed();
        long end = System.currentTimeMillis();
        log.info("结束时间:" + end);
        log.info("耗时:" + (end - start));
        return obj;
    }
}
