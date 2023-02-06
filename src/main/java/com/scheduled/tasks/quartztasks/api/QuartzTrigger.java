package com.scheduled.tasks.quartztasks.api;

import com.scheduled.tasks.quartztasks.service.ApiConsumeService;
import com.scheduled.tasks.quartztasks.util.CsvWriter;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;

@RestController
public class QuartzTrigger {

    @Autowired
    ApiConsumeService service;

    @Autowired
    CsvWriter csvWriter;


    @GetMapping("/countries")
    public ResponseEntity getCountryList(){
        return ResponseEntity.ok(service.getCountryList().getData());
    }

    @GetMapping("/random-country")
    public ResponseEntity getRandomCountry(){
        return ResponseEntity.ok(service.getARandomCountry());
    }

    @GetMapping("/universities")
    public ResponseEntity universities(){
        return ResponseEntity.ok(service.getUniversities());
    }

    @GetMapping("/university-by-country/{country}")
    public ResponseEntity universityByCountry(@PathVariable(name = "country",required = false) String country){
        return ResponseEntity.ok(service.getUniversityFromCounty(country));
    }

    @GetMapping("/university-by-random")
    public ResponseEntity universitiesFromRandomCountries(){
        String country = service.getARandomCountry().getCountry();
        return ResponseEntity.ok(service.getUniversityFromCounty(country));
    }

    @GetMapping("/test")
    public ResponseEntity test() throws SchedulerException, IOException {

        return ResponseEntity.ok(csvWriter.writeReportToCsv());
    }

}