package com.tqs.homework1.model;

import java.util.Objects;

public class CountryStats {
    private String continent;
    private String country;
    private int population;
    private Cases cases;
    private Deaths deaths;
    private Tests tests;
    private String day;
    private String time;
    

    public CountryStats(String continent, String country, int population, Cases cases, Deaths deaths, Tests tests, String day, String time) {
        this.continent = continent;
        this.country = country;
        this.population = population;
        this.cases = cases;
        this.deaths = deaths;
        this.tests = tests;
        this.day = day;
        this.time = time;
    }


    public String getContinent() {
        return this.continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopulation() {
        return this.population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Cases getCases() {
        return this.cases;
    }

    public void setCases(Cases cases) {
        this.cases = cases;
    }

    public Deaths getDeaths() {
        return this.deaths;
    }

    public void setDeaths(Deaths deaths) {
        this.deaths = deaths;
    }

    public Tests getTests() {
        return this.tests;
    }

    public void setTests(Tests tests) {
        this.tests = tests;
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CountryStats)) {
            return false;
        }
        CountryStats covidStats = (CountryStats) o;
        return Objects.equals(continent, covidStats.continent) && Objects.equals(country, covidStats.country) && population == covidStats.population && Objects.equals(cases, covidStats.cases) && Objects.equals(deaths, covidStats.deaths) && Objects.equals(tests, covidStats.tests) && Objects.equals(day, covidStats.day) && Objects.equals(time, covidStats.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(continent, country, population, cases, deaths, tests, day, time);
    }


    @Override
    public String toString() {
        return "{" +
            " continent='" + getContinent() + "'" +
            ", country='" + getCountry() + "'" +
            ", population='" + getPopulation() + "'" +
            ", cases='" + getCases() + "'" +
            ", deaths='" + getDeaths() + "'" +
            ", tests='" + getTests() + "'" +
            ", day='" + getDay() + "'" +
            ", time='" + getTime() + "'" +
            "}";
    }
    
}
