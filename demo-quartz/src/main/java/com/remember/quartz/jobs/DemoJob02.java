package com.remember.quartz.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author wangjiahao
 * @date 2022/5/19 13:55
 */
@Slf4j
@DisallowConcurrentExecution
public class DemoJob02 extends QuartzJobBean {

    /**
     * @param context
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("[executeInternal][我开始的执行了]");
    }
}
