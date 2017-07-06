package com.example.demo.service.scheduler.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

/**
 * Created by Pavlovskii-pc on 7/6/2017.
 */
public class BarJobRunner implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap data = jobExecutionContext.getMergedJobDataMap();
        System.out.println("Heil, i am a Bar Process, that is started with param: "+data.get("param-bar"));
    }
}
