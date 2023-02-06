package com.scheduled.tasks.quartztasks.quartz_impl.baseUpImplementation.demo1;


import com.scheduled.tasks.quartztasks.util.CsvWriter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class DocumentationJob implements Job {

    @Autowired
    CsvWriter csvWriter;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            csvWriter.writeReportToCsv();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}