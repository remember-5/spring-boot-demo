package com.remember.quartz.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangjiahao
 * @date 2022/5/19 13:25
 */
@Slf4j
@DisallowConcurrentExecution
public class DemoJob01 extends QuartzJobBean {

    private final AtomicInteger counts = new AtomicInteger();

    /**
     * @param context
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("[executeInternal][定时第 ({}) 次执行]", counts.incrementAndGet());
    }
}
