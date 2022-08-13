package com.learn.test.demo.myThread.executors;

import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.lang.reflect.Method;
import java.util.concurrent.Executors;

/**
 * SpringBoot定时任务配置类
 */
@Configuration
public class ScheduledConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        Method[] methods = BatchProperties.Job.class.getMethods();
        int defaultPoolSize = 4; //默认的线程数为 4
        int corePoolSize = 0;
        // 扫描配置了@Scheduled 调度注解的方法
        //根据需要调度的方法数，配置线程池中的线程数
        if (methods.length > 0) {
            for (Method method : methods) {
                Scheduled annotation = method.getAnnotation(Scheduled.class);
                if (annotation != null) {
                    corePoolSize++;
                }
            }
            if (defaultPoolSize > corePoolSize) {
                corePoolSize = defaultPoolSize;
            }
        }
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(corePoolSize));
    }
}