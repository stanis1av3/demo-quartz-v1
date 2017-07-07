package com.example.demo.service.scheduler;

import com.example.demo.service.scheduler.jobs.BarJobRunner;
import com.example.demo.service.scheduler.jobs.FooJobRunner;
import org.quartz.*;
import org.quartz.core.jmx.JobDataMapSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;

/**
 * Created by Pavlovskii-pc on 7/6/2017.
 */
@Service
public class QuartzSchedulerService {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    //name of group that is used to gather all the required tasks into one group
    final String GROUP="group1";

    //immediate start task example
    public void applyFooTaskNow(String param){

        try {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("param-foo",param);

        JobDetail detail = newJob(FooJobRunner.class)
                .withIdentity("job1", GROUP).setJobData(jobDataMap)
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("trigger1", GROUP)
                .startNow()
                .build();


            schedulerFactoryBean.getScheduler().scheduleJob(detail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }

    //the same
    public void applyBarTaskNow(String param){

        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("param-foo",param);

            JobDetail detail = newJob(BarJobRunner.class)
                    .withIdentity("job2", GROUP).setJobData(jobDataMap)
                    .build();

            Trigger trigger = newTrigger()
                    .withIdentity("trigger2", GROUP)
                    .startNow()
                    .build();


            schedulerFactoryBean.getScheduler().scheduleJob(detail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    //pass cron parameter to start task on schedule
    public void applyTasksPerCron(String cron, Class task){
        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("param-foo","Passed param 222");

            JobDetail detail = newJob(task)
                    .withIdentity("job3", GROUP).setJobData(jobDataMap)
                    .build();

            Trigger trigger = newTrigger()
                    .withIdentity("trigger3", GROUP)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();


            schedulerFactoryBean.getScheduler().scheduleJob(detail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    //kills running task based on task trigger name

    public void destroyTask(String triggerName) {
        try {
            schedulerFactoryBean.getScheduler().unscheduleJob(triggerKey(triggerName, GROUP));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    @PostConstruct
    public void init(){
        this.applyFooTaskNow("SOME PARAM AS TEXT!!");
        this.applyTasksPerCron("0/10 * * * * ?", FooJobRunner.class);
        //this.destroyTask("trigger1");
    }

}
