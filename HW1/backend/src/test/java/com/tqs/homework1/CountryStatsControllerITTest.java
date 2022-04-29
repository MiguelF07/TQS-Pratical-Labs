package com.tqs.homework1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.oneOf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CountryStatsControllerITTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void whenGetCountries_thenStatus200() throws Exception {
        mvc.perform(get("/api/countries").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(equalTo(233))))
                .andExpect(jsonPath("$[0]", is("Afghanistan")))
                .andExpect(jsonPath("$[1]", is("Albania")))
                .andExpect(jsonPath("$[-1]",is("Zimbabwe")));
    }

    @Test
    public void whenGetCache_thenStatus200() throws Exception {
        mvc.perform(get("/api/cache").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", equalTo(9)))
                .andExpect(jsonPath("$.keys()", everyItem(is(oneOf("num_requests","num_hits","num_misses","num_calls_getCountries","num_calls_getCacheData","num_calls_getCacheHistory","countries","cacheData","cacheHistory")))));
    }

    @Test
    public void whenGetStatisticsByCountry_thenStatus200() throws Exception {
        mvc.perform(get("/api/statistics/portugal").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", equalTo(8)))
                .andExpect(jsonPath("$.continent", is("Europe")))
                .andExpect(jsonPath("$.cases.length()", equalTo(6)))
                .andExpect(jsonPath("$.deaths.length()", equalTo(3)))
                .andExpect(jsonPath("$.tests.length()", equalTo(2)));
    }

    @Test
    public void whenGetHistoryByCountry_thenStatus200() throws Exception {
        mvc.perform(get("/api/history/portugal/2022-02-07?date2=2022-02-08").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", equalTo(2)))
                .andExpect(jsonPath("$.[0].country", is("Portugal")))
                .andExpect(jsonPath("$.[1].country", is("Portugal")))
                .andExpect(jsonPath("$.[0].day", is("2022-02-07")))
                .andExpect(jsonPath("$.[1].day", is("2022-02-08")))
                .andExpect(jsonPath("$.[0].cases.length()", equalTo(6)))
                .andExpect(jsonPath("$.[0].deaths.length()", equalTo(3)))
                .andExpect(jsonPath("$.[0].tests.length()", equalTo(2)))
                .andExpect(jsonPath("$.[1].cases.length()", equalTo(6)))
                .andExpect(jsonPath("$.[1].deaths.length()", equalTo(3)))
                .andExpect(jsonPath("$.[1].tests.length()", equalTo(2)));
    }
}

