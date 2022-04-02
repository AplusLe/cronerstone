package com.mate.starter.common.config;

import com.mate.starter.common.thread.TtlThreadPoolTaskExecutor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步任务配置
 *
 * Created by Kevin on 2022/3/24 18:15
 */
@Data
@Slf4j
@Configuration
public class DefaultAsyncTaskConfig {
    /**
     * 线程池维护线程的最小数量.
     */
    @Value("${async-task.corePoolSize:10}")
    private int corePoolSize;
    /**
     * 线程池维护线程的最大数量
     */
    @Value("${async-task.maxPoolSize:10}")
    private int maxPoolSize;
    /**
     * 队列最大长度
     */
    @Value("${async-task.queueCapacity:10000}")
    private int queueCapacity;
    /**
     * 线程池前缀
     */
    @Value("${async-task.threadNamePrefix:async-task-}")
    private String threadNamePrefix;


    @Bean("myTaskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new TtlThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        log.info("[初始化线程池完毕, corePoolSize[{}], maxPoolSize[{}], queueCapacity[{}]]",corePoolSize,maxPoolSize,queueCapacity);
        return executor;
    }
}
