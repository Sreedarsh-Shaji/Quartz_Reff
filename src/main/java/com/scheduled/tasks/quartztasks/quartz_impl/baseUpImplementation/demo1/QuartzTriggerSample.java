package com.scheduled.tasks.quartztasks.quartz_impl.baseUpImplementation.demo1;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Slf4j
public class QuartzTriggerSample {

    @Bean
    public Trigger sampleMethod() throws SchedulerException {

        SchedulerFactory schedFact = new StdSchedulerFactory();
        Scheduler sched = schedFact.getScheduler();
        sched.start();

        JobDetail job = JobBuilder.newJob(DocumentationJob.class)
                .withIdentity("myJob", "group1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3600 / 4)
                        .repeatForever())
                .build();

        log.info(" Creating Report ");

        return trigger;
    }

}
