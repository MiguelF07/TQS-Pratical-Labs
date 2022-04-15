package com.tqs.homework1.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tqs.homework1.service.CountryStatsService;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api")
public class CountryStatsController {
    @Autowired
    private CountryStatsService service;

    @GetMapping("/countries")
    public ResponseEntity<List<String>> getCountries() throws IOException, InterruptedException, ParseException {
        List<String> data = new ArrayList<>();
        for( String country : service.getCountriesList()) {
            data.add(country);
        }
        return ResponseEntity.ok().body(data);
    }
}
