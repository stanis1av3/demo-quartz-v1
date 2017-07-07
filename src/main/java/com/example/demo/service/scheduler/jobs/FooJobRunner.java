package com.example.demo.service.scheduler.jobs;

import com.example.demo.repository.DataRepository;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * Created by Pavlovskii-pc on 7/6/2017.
 */
@DisallowConcurrentExecution
public class FooJobRunner implements Job {

// the way beans can be injected into the job
//    @Autowired
//    SomeService someService;

    @Autowired
    DataRepository dataRepository;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap data = jobExecutionContext.getMergedJobDataMap();
        System.out.println("Heil, i am a Foo Process, that is started with param: "+data.get("param-foo"));

        dataRepository.save("FOO:" +LocalDateTime.now().toString());
    }
}
