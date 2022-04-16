package com.tqs.homework1.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    private HttpRequest request;
    public List<String> getCountriesList() throws IOException, InterruptedException, ParseException {
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
        return this.countries;
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
}
