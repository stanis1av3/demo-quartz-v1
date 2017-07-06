package com.example.demo.service.scheduler.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * Created by Pavlovskii-pc on 7/6/2017.
 */
public class FooJobRunner implements Job {
//    @Autowired
//    SomeService someService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap data = jobExecutionContext.getMergedJobDataMap();
//        log.info("Start job execution, id={}", data.get("scheduleId"));
//        SecurityContextHolder.setContext(QuartzSchedulerService.SINGLE_STATIC_SECURITY_CONTEXT);

        System.out.println("Heil, i am a Foo Process, that is started with param: "+data.get("param-foo"));

    }
}
