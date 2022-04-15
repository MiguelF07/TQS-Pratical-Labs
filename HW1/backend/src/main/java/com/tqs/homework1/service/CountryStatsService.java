package com.tqs.homework1.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

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
}
