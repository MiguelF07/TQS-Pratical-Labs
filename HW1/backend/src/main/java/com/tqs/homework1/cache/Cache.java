package com.tqs.homework1.cache;

import com.tqs.homework1.model.CountryStats;
import com.tqs.homework1.service.CountryStatsService;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {
    private List<String> countries;
    private Map<String, CountryStats> cacheData;

    public Cache() {
        this.countries = new ArrayList<>();
        this.cacheData = new HashMap<>();
    }

    public List<String> getCountries() {
        System.out.println("PRINTING CACHE COUNTRIES");
        System.out.println(countries);
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public Map<String, CountryStats> getCacheData() {
        return cacheData;
    }

    public void setCacheData(Map<String, CountryStats> cacheData) {
        this.cacheData = cacheData;
    }
}
