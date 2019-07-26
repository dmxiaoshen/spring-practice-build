package com.xyz.common.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (ctx == null) {
            ctx = applicationContext;
        }
    }

    public static Object getBean(String beanName) {
        if (ctx != null) {
            return ctx.getBean(beanName);
        }

        return null;
    }

    public static <T> T getBean(Class<T> requiredType) {
        if (ctx != null) {
            return ctx.getBean(requiredType);
        }

        return null;
    }

    public static ApplicationContext getCtx() {
        return ctx;
    }

}