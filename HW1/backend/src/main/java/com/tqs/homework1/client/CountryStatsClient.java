package com.tqs.homework1.client;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountryStatsClient {
    private HttpRequest request;

    public CountryStatsClient() {
    }

    public JSONObject findCountries() throws IOException, InterruptedException, ParseException {
        request = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-193.p.rapidapi.com/countries"))
                .header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
                .header("X-RapidAPI-Key", "1e5f4af0cemshec98a494e86ee93p156bbfjsnad21dd3eb244")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonObj = (JSONObject) new JSONParser().parse(response.body());
        return jsonObj;
    }

    public JSONObject findStatisticsByCountry(String country) throws IOException, InterruptedException, ParseException {
        request = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-193.p.rapidapi.com/statistics?country="+country))
                .header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
                .header("X-RapidAPI-Key", "1e5f4af0cemshec98a494e86ee93p156bbfjsnad21dd3eb244")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonObj = (JSONObject) new JSONParser().parse(response.body());
        return jsonObj;
    }

    public JSONObject findHistoryByCountry(String country, LocalDate date) throws IOException, InterruptedException, ParseException {
        request = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-193.p.rapidapi.com/history?country="+country+"&day="+date.toString()))
                .header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
                .header("X-RapidAPI-Key", "1e5f4af0cemshec98a494e86ee93p156bbfjsnad21dd3eb244")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonObj = (JSONObject) new JSONParser().parse(response.body());
        return jsonObj;
    }
}
