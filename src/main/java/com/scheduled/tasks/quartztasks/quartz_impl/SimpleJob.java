package com.scheduled.tasks.quartztasks.quartz_impl;

import com.scheduled.tasks.quartztasks.caching_emulator.CachingEmulator;
import com.scheduled.tasks.quartztasks.entities.Country;
import com.scheduled.tasks.quartztasks.entities.Universities;
import com.scheduled.tasks.quartztasks.service.ApiConsumeService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class SimpleJob implements Job {

    @Autowired
    ApiConsumeService service;

    @Autowired
    CachingEmulator cachingEmulator;

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public void execute(JobExecutionContext context) {
        Country randomCountry = service.getARandomCountry();
        Universities universities = service.getUniversityFromCounty(randomCountry.getCountry());
        cachingEmulator.put(LocalDateTime.now().format(format) , randomCountry);
        log.info("Executing the invoker method.");
    }

}