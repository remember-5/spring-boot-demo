package com.remember.quartz;

import com.remember.quartz.jobs.DemoJob01;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
class DemoQuartzApplicationTests {

	@Resource
	private Scheduler scheduler;

	@Test
	void contextLoads() throws SchedulerException, InterruptedException {
		// 创建 JobDetail
		JobDetail jobDetail = JobBuilder.newJob(DemoJob01.class)
				.withIdentity("demoJob") // 名字为 demoJob01
				.storeDurably() // 没有 Trigger 关联的时候任务是否被保留。因为创建 JobDetail 时，还没 Trigger 指向它，所以需要设置为 true ，表示保留。
				.build();
		// 创建 Trigger
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(5) // 频率。
				.repeatForever(); // 次数。
		Trigger trigger = TriggerBuilder.newTrigger()
				.forJob(jobDetail) // 对应 Job 为 demoJob01
				.withIdentity("demoJobTrigger") // 名字为 demoJob01Trigger
				.withSchedule(scheduleBuilder) // 对应 Schedule 为 scheduleBuilder
				.build();
		// 添加调度任务
		scheduler.scheduleJob(jobDetail, trigger);
//        scheduler.scheduleJob(jobDetail, Sets.newSet(trigger), true);
		new CountDownLatch(1).await();


	}

}
