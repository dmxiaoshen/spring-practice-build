package com.xyz.app.business.aop;

import com.xyz.common.base.aop.AbstractServiceLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ServiceLogAspect extends AbstractServiceLogAspect {

    public static final String point = "execution(public * com.xyz.app.business.service..*.*(..))";

    @Pointcut(point)
    @Override
    public void log() {}
}
