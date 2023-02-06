package com.scheduled.tasks.quartztasks.service;

import com.scheduled.tasks.quartztasks.caching_emulator.CachingEmulator;
import com.scheduled.tasks.quartztasks.entities.*;
import com.scheduled.tasks.quartztasks.util.RandomFromMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ApiConsumeService {

    @Autowired
    RestTemplate restTemplate;

    CountriesPayload countryPayload = new CountriesPayload();
    Universities universitiesPayload = new Universities();

    @Autowired
    CachingEmulator cachingEmulator;

    @Value("${api.fetch.threshold}")
    int fetchThreshold;
    int countryFetchCount = 0;
    int universityFetchCount = 0;

    public CountriesPayload getCountryList(){
        if( countryPayload.getData() == null || countryFetchCount > fetchThreshold) {
            log.info("Fetching fresh country data");
            countryPayload = restTemplate.getForObject("https://api.first.org/data/v1/countries", CountriesPayload.class);
            countryFetchCount = 1;
        } else {
            log.info("Fetching country data from memory");
            countryFetchCount ++;
        }
        return countryPayload;
    }
    public Country getARandomCountry(){
        Countries countries = this.getCountryList().getData();
        RandomFromMap<Countries> countriesRandomFromMap = new RandomFromMap<>();
        Country randomCountry = countries.get(countriesRandomFromMap.getSomeKey(countries));
        log.info("Invoked {} ", randomCountry);
        return randomCountry;
    }
    public Universities getUniversities(){
        if( universitiesPayload.size() == 0 || countryFetchCount > fetchThreshold) {
            log.info("Fetching fresh University data");
            universitiesPayload = restTemplate.getForObject("http://universities.hipolabs.com/search", Universities.class);
            universityFetchCount = 1;
        } else {
            log.info("Fetching University data from memory");
            universityFetchCount ++;
        }
        return universitiesPayload;
    }
    public String test(){

        String Data = restTemplate.getForObject("http://universities.hipolabs.com/search", String.class);
        System.out.println(Data);
        return "OK!";
    }

    public Universities getUniversityFromCounty(String country)
    {
        Universities universities = this.getUniversities();
        Universities filteredUniversities = universities.stream().filter( u ->
             u.getAlphaTwoCode().equalsIgnoreCase(country) ||
                    u.getCountry().equalsIgnoreCase(country)).collect(Collectors.toCollection(Universities::new));
        return filteredUniversities;
    }

    public Map getCacheStatus(){
        return cachingEmulator;
    }

}
