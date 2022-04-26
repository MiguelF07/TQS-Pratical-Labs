package com.tqs.homework1.cache;

import com.tqs.homework1.model.CountryStats;
import com.tqs.homework1.service.CountryStatsService;
import org.apache.tomcat.jni.Local;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Cache {
    private List<String> countries;
    private Map<String, CountryStats> cacheData;
    private Map<String,HashMap<LocalDate,CountryStats>> cacheHistory;
    //Statistics
    private int num_requests;
    private int num_hits;
    private int num_misses;
    private int num_calls_getCountries;
    private int num_calls_getCacheData;
    private int num_calls_getCacheHistory;

    public Cache() {
        this.countries = new ArrayList<>();
        this.cacheData = new HashMap<>();
        this.cacheHistory = new HashMap<>();
        this.num_requests = 0;
        this.num_hits = 0;
        this.num_misses = 0;
        this.num_calls_getCountries = 0;
        this.num_calls_getCacheData = 0;
        this.num_calls_getCacheHistory = 0;

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        clearCache();
                    }
                },
                60000
        );
    }

    public List<String> getCountries() {
        this.num_calls_getCountries++;
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public Map<String, CountryStats> getCacheData() {
        this.num_calls_getCacheData++;
        return cacheData;
    }

    public void setCacheData(Map<String, CountryStats> cacheData) {
        this.cacheData = cacheData;
    }

    public Map<String, HashMap<LocalDate, CountryStats>> getCacheHistory() {
        this.num_calls_getCacheHistory++;
        return cacheHistory;
    }

    public void setCacheHistory(Map<String, HashMap<LocalDate, CountryStats>> cacheHistory) {
        this.cacheHistory = cacheHistory;
    }

    public int getNum_requests() {
        return num_requests;
    }

    public void setNum_requests(int num_requests) {
        this.num_requests = num_requests;
    }

    public int getNum_hits() {
        return num_hits;
    }

    public void setNum_hits(int num_hits) {
        this.num_hits = num_hits;
    }

    public int getNum_misses() {
        return num_misses;
    }

    public void setNum_misses(int num_misses) {
        this.num_misses = num_misses;
    }

    public int getNum_calls_getCountries() {
        return num_calls_getCountries;
    }

    public void setNum_calls_getCountries(int num_calls_getCountries) {
        this.num_calls_getCountries = num_calls_getCountries;
    }

    public int getNum_calls_getCacheData() {
        return num_calls_getCacheData;
    }

    public void setNum_calls_getCacheData(int num_calls_getCacheData) {
        this.num_calls_getCacheData = num_calls_getCacheData;
    }

    public int getNum_calls_getCacheHistory() {
        return num_calls_getCacheHistory;
    }

    public void setNum_calls_getCacheHistory(int num_calls_getCacheHistory) {
        this.num_calls_getCacheHistory = num_calls_getCacheHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cache cache = (Cache) o;
        return num_requests == cache.num_requests && num_hits == cache.num_hits && num_misses == cache.num_misses && num_calls_getCountries == cache.num_calls_getCountries && num_calls_getCacheData == cache.num_calls_getCacheData && num_calls_getCacheHistory == cache.num_calls_getCacheHistory && Objects.equals(countries, cache.countries) && Objects.equals(cacheData, cache.cacheData) && Objects.equals(cacheHistory, cache.cacheHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countries, cacheData, cacheHistory, num_requests, num_hits, num_misses, num_calls_getCountries, num_calls_getCacheData, num_calls_getCacheHistory);
    }

    @Override
    public String toString() {
        return "Cache{" +
                "countries=" + countries +
                ", cacheData=" + cacheData +
                ", cacheHistory=" + cacheHistory +
                ", num_requests=" + num_requests +
                ", num_hits=" + num_hits +
                ", num_misses=" + num_misses +
                ", num_calls_getCountries=" + num_calls_getCountries +
                ", num_calls_getCacheData=" + num_calls_getCacheData +
                ", num_calls_getCacheHistory=" + num_calls_getCacheHistory +
                '}';
    }

    public void clearCache() {
        this.countries = new ArrayList<>();
        this.cacheData = new HashMap<>();
        this.cacheHistory = new HashMap<>();
    }


}
