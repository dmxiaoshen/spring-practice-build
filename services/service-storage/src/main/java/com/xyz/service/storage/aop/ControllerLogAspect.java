package com.xyz.service.storage.aop;

import com.xyz.common.base.aop.AbstractControllerLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class ControllerLogAspect extends AbstractControllerLogAspect {

    public static final String point = "execution(public * com.xyz.service.storage.controller..*.*(..))";

    @Pointcut(point)
    @Override
    public void log() {}
}
