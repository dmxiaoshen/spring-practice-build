package com.xyz.common.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestLock {

    Class key() default RequestLockKey.class;

    long expirationTime() default 3000;//默认3000毫秒

    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
