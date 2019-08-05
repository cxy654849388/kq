package com.chm.kd.kq.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * @author caihongming
 * @version v1.0
 * @title ThreadTaskConfig
 * @package com.chm.kd.kq.config
 * @since 2019-07-13
 * description
 **/
@Configuration
public class ThreadTaskConfig {

  @Bean(name = "groupListener")
  public ExecutorService executorService(@Value("${thread.group.listener.corePoolSize}") Integer corePoolSize,
                                         @Value("${thread.group.listener.maxPoolSize}") Integer maxPoolSize,
                                         @Value("${thread.group.listener.keepAliveTime}") Long keepAliveTime,
                                         @Value("${thread.group.listener.capacity}") Integer capacity) {
    return new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(capacity),
            new ThreadFactoryBuilder().setNameFormat("group-listener-service-%d").build(),
            new ThreadPoolExecutor.AbortPolicy());
  }

  @Bean(name = "scheduledExecutorService")
  public ScheduledExecutorService scheduledExecutorService(@Value("${scheduled.executor.corePoolSize}") Integer corePoolSize) {
    return new ScheduledThreadPoolExecutor(new java.util.concurrent.ScheduledThreadPoolExecutor(corePoolSize, new BasicThreadFactory.Builder().namingPattern("scheduled-executor-%d").build()));
  }

}
