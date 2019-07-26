package com.xyz.common.base.scheduled;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Scheculed默认的线程池是单线程的，任务放在delayQueue队列，排队执行的，
 *
 * 所以才会出现等待情况，解决这个问题的办法就是把默认线程改成多线程的
 */
@Configuration
public class ScheduledConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(executor());
    }

    @Bean
    public Executor executor(){
        return Executors.newScheduledThreadPool(10);
    }
}
