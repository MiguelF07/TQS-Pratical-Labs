package com.tqs.homework1.controller;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tqs.homework1.cache.Cache;
import com.tqs.homework1.exceptions.ResourceNotFoundException;
import com.tqs.homework1.model.CountryStats;
import com.tqs.homework1.service.CountryStatsService;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api")
public class CountryStatsController {
    @Autowired
    private CountryStatsService service;

    @GetMapping("/cache")
    public ResponseEntity<Cache> getCache() {
        return ResponseEntity.ok().body(service.getCache());
    }

    @GetMapping("/countries")
    public ResponseEntity<List<String>> getCountries() throws IOException, InterruptedException, ParseException {
        List<String> data = new ArrayList<>(service.getCountriesList());
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/statistics/{country}")
    public ResponseEntity<CountryStats> getStatByCountry(@PathVariable(value="country") String country) throws IOException, InterruptedException, ParseException, ResourceNotFoundException {
        Optional<CountryStats> data = service.getStatisticsByCountry(country);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Updated History of country: "+data);

        if(data.isEmpty()) {
            throw new ResourceNotFoundException("Data not found for this country :: " + country);
        }
        return ResponseEntity.ok().body(data.get());
    }

    @GetMapping("/history/{country}/{date1}")
    public ResponseEntity<List<CountryStats>> getHistByCountry(@PathVariable(value="country") String country, @PathVariable(value="date1") String date1, @RequestParam(required = false) String date2) throws IOException, InterruptedException, ParseException, ResourceNotFoundException {
        LocalDate begin = LocalDate.parse(date1);
        LocalDate end = null;
        if(date2!=null) {
            end = LocalDate.parse(date2);
        }

        List<Optional<CountryStats>> data = service.getHistoryByCountry(country,begin,end);
        List<CountryStats> finalResults = new ArrayList<>();
        for(Optional<CountryStats> piece : data) {
            if(piece.isPresent())
            {
                finalResults.add(piece.get());
            }
        }
        return ResponseEntity.ok().body(finalResults);
        //http://localhost:8080/api/history/portugal/2022-02-07?date2=2022-02-09
    }


}
