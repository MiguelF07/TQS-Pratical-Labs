package com.tqs.homework1.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.tqs.homework1.cache.Cache;
import com.tqs.homework1.model.Cases;
import com.tqs.homework1.model.CountryStats;
import com.tqs.homework1.model.Deaths;
import com.tqs.homework1.model.Tests;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;


import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class CountryStatsService {
    private List<String> countries;
    private Cache cache = new Cache();

    private HttpRequest request;
    public List<String> getCountriesList() throws IOException, InterruptedException, ParseException {
        List<String> cacheCountries = cache.getCountries();
        if(cacheCountries.size()==0) {
            this.countries = new ArrayList<>();
            request = HttpRequest.newBuilder()
                    .uri(URI.create("https://covid-193.p.rapidapi.com/countries"))
                    .header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "1e5f4af0cemshec98a494e86ee93p156bbfjsnad21dd3eb244")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            JSONObject jsonObj = (JSONObject) new JSONParser().parse(response.body());
            JSONArray results = (JSONArray) jsonObj.get("response");
            for(int i=0;i<results.size();i++) {
                String c = (String) results.get(i);
                this.countries.add(c);
            }
            cache.setCountries(this.countries);
            Map<String,CountryStats> cacheData = new HashMap<>();
            for(String c:this.countries) {
                cacheData.put(c,null);
            }
            cache.setCacheData(cacheData);
            return this.countries;
        }
        return cacheCountries;
    }

    public Optional<CountryStats> getStatisticsByCountry(String country) throws IOException, InterruptedException, ParseException {
        request = HttpRequest.newBuilder()
		.uri(URI.create("https://covid-193.p.rapidapi.com/statistics?country="+country))
		.header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
		.header("X-RapidAPI-Key", "1e5f4af0cemshec98a494e86ee93p156bbfjsnad21dd3eb244")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        JSONObject jsonObj = (JSONObject) new JSONParser().parse(response.body());  
        Long result = (Long) jsonObj.get("results");
        if(result==0) //There are no statistics for the asked country
        {
            return Optional.empty();
        }
        JSONObject responseJson = (JSONObject)((JSONArray) jsonObj.get("response")).get(0);
        JSONObject casesJson = (JSONObject) responseJson.get("cases");
        JSONObject deathsJson = (JSONObject) responseJson.get("deaths");
        JSONObject testsJson = (JSONObject) responseJson.get("tests");
        Cases cases = new Cases((String) casesJson.get("new"), (Long) casesJson.get("active"), (Long) casesJson.get("critical"), (Long) casesJson.get("recovered"), (String) casesJson.get("1M_pop"), (Long) casesJson.get("total"));
        Deaths deaths = new Deaths((String) deathsJson.get("new"), (String) deathsJson.get("1M_pop"), (Long) deathsJson.get("total"));
        Tests tests = new Tests((String) testsJson.get("1M_pop"),(Long) testsJson.get("total"));
        CountryStats stats = new CountryStats((String) responseJson.get("continent"), (String) responseJson.get("country"), (Long) responseJson.get("population"), cases, deaths, tests, (String) responseJson.get("day"), (String) responseJson.get("time"));

        return Optional.of(stats);
    }

    public List<Optional<CountryStats>> getHistoryByCountry(String country,LocalDate startDate,LocalDate endDate) throws ParseException, IOException, InterruptedException {
        List<Optional<CountryStats>> historyRes = new ArrayList<>();
        List<LocalDate> dates = new ArrayList<>();
        if(endDate==null || (startDate.isEqual(endDate))) {
            dates.add(startDate);
        }
        else {
            dates=startDate.datesUntil(endDate).collect(Collectors.toList());
            dates.add(endDate);
        }
        
        for(LocalDate date: dates) {
            request = HttpRequest.newBuilder()
            .uri(URI.create("https://covid-193.p.rapidapi.com/history?country="+country+"&day="+date.toString()))
            .header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
            .header("X-RapidAPI-Key", "1e5f4af0cemshec98a494e86ee93p156bbfjsnad21dd3eb244")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            JSONObject jsonObj = (JSONObject) new JSONParser().parse(response.body());  
            Long result = (Long) jsonObj.get("results");
            if(result==0) //There are no statistics for the asked country
            {
                historyRes.add(Optional.empty());
                //return historyRes;
            }
            JSONObject responseJson = (JSONObject)((JSONArray) jsonObj.get("response")).get(0);
            JSONObject casesJson = (JSONObject) responseJson.get("cases");
            JSONObject deathsJson = (JSONObject) responseJson.get("deaths");
            JSONObject testsJson = (JSONObject) responseJson.get("tests");
            Cases cases = new Cases((String) casesJson.get("new"), (Long) casesJson.get("active"), (Long) casesJson.get("critical"), (Long) casesJson.get("recovered"), (String) casesJson.get("1M_pop"), (Long) casesJson.get("total"));
            Deaths deaths = new Deaths((String) deathsJson.get("new"), (String) deathsJson.get("1M_pop"), (Long) deathsJson.get("total"));
            Tests tests = new Tests((String) testsJson.get("1M_pop"),(Long) testsJson.get("total"));
            CountryStats stats = new CountryStats((String) responseJson.get("continent"), (String) responseJson.get("country"), (Long) responseJson.get("population"), cases, deaths, tests, (String) responseJson.get("day"), (String) responseJson.get("time"));
            historyRes.add(Optional.of(stats));
        }
        return historyRes;
    }
}
