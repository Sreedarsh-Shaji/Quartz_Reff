package com.scheduled.tasks.quartztasks.util;

import com.scheduled.tasks.quartztasks.caching_emulator.CachingEmulator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CsvWriter {

    @Autowired
    CachingEmulator cachingEmulator;

    public String writeReportToCsv() throws IOException {

        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("hh:mm:ss dd-MM-yyyy");
        BufferedWriter writer = new BufferedWriter(new FileWriter("Report"+ LocalDateTime.now().format(formatter) +".txt"));

        Map data = cachingEmulator;

        data.forEach( (k,v) -> {
            try {
                writer.write(k.toString());
                writer.write(" , ");
                writer.write(v.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } );

        writer.close();
        log.info("writeReportToCsv() : Generated CSV");
        return ("writeReportToCsv() : Generated CSV");
    }

}
