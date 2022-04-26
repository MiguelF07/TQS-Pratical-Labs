package com.tqs.homework1;

import com.tqs.homework1.cache.Cache;
import com.tqs.homework1.model.Cases;
import com.tqs.homework1.model.CountryStats;
import com.tqs.homework1.model.Deaths;
import com.tqs.homework1.model.Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class CacheTest {
    private Cache cache;

    @BeforeEach
    public void setUp() {
        this.cache = new Cache();
    }

    @Test
    public void onCacheCreation_numRequestsIsZero() {
        assertEquals(0,this.cache.getNum_requests(),"Expected size 0");
    }

    @Test
    public void onCacheCreation_numHitsIsZero() {
        assertEquals(0,this.cache.getNum_hits(),"Expected size 0");
    }

    @Test
    public void onCacheCreation_numMissesIsZero() {
        assertEquals(0,this.cache.getNum_misses(),"Expected size 0");
    }

    @Test
    public void onCacheCreation_numCallsGetCountriesIsZero() {
        assertEquals(0,this.cache.getNum_calls_getCountries(),"Expected size 0");
    }

    @Test
    public void onCacheCreation_numCallsGetCacheDataIsZero() {
        assertEquals(0,this.cache.getNum_calls_getCacheData(),"Expected size 0");
    }

    @Test
    public void onCacheCreation_numCallsGetCacheHistoryIsZero() {
        assertEquals(0,this.cache.getNum_calls_getCacheHistory(),"Expected size 0");
    }

    @Test
    public void onCacheCreation_countriesIsEmpty() {
        assertTrue(this.cache.getCountries().isEmpty());
    }

    @Test
    public void onCacheCreation_cacheDataIsEmpty() {
        assertTrue(this.cache.getCacheData().isEmpty());
    }

    @Test
    public void onCacheCreation_cacheHistoryIsEmpty() {
        assertTrue(this.cache.getCacheHistory().isEmpty());
    }

    @Test
    public void settingCountries_gettingCountries() {
        ArrayList<String> countries = new ArrayList<>(Arrays.asList("Portugal","Spain"));
        this.cache.setCountries(countries);
        assertEquals(countries,this.cache.getCountries(),"Obtained countries different from the setted ones");
    }

    @Test
    public void settingCacheData_gettingCacheData() {
        HashMap<String, CountryStats> cacheData = new HashMap<>();
        Cases c = new Cases("+230",23000L,2L,400L,"12938",127365L);
        Deaths d = new Deaths("+2","28374",182738L);
        Tests t = new Tests("384724",2384789L);
        CountryStats cs = new CountryStats("Europe","Portugal",12L,c,d,t,"2022-03-25","12:00");
        cacheData.put("Portugal",cs);
        this.cache.setCacheData(cacheData);
        assertEquals(cacheData,this.cache.getCacheData(),"Obtained cache data different from the setted");
    }

    @Test
    public void settingCacheHistory_gettingCacheHistory() {
        HashMap<String,HashMap<LocalDate,CountryStats>> cacheHistory = new HashMap<>();
        HashMap<LocalDate,CountryStats> dateData= new HashMap<>();
        LocalDate date = LocalDate.parse("2022-03-25");
        Cases c = new Cases("+230",23000L,2L,400L,"12938",127365L);
        Deaths d = new Deaths("+2","28374",182738L);
        Tests t = new Tests("384724",2384789L);
        CountryStats cs = new CountryStats("Europe","Portugal",12L,c,d,t,"2022-03-25","12:00");
        dateData.put(date,cs);
        cacheHistory.put("Portugal",dateData);
        this.cache.setCacheHistory(cacheHistory);
        assertEquals(cacheHistory,this.cache.getCacheHistory(),"Obtained cache history different from the setted");
    }

    @Test
    public void arrayStatus_whenClearingCache() {
        int num_requests = this.cache.getNum_requests();
        int num_hits = this.cache.getNum_hits();
        int num_misses = this.cache.getNum_misses();
        int num_calls_getCountries = this.cache.getNum_calls_getCountries();
        int num_calls_getCacheData = this.cache.getNum_calls_getCacheData();
        int num_calls_getCacheHistory = this.cache.getNum_calls_getCacheHistory();

        this.cache.clearCache();

        assertAll("Cache statistics must remain the same",
                () -> assertEquals(num_requests,this.cache.getNum_requests()),
                () -> assertEquals(num_hits,this.cache.getNum_hits()),
                () -> assertEquals(num_misses,this.cache.getNum_misses()),
                () -> assertEquals(num_calls_getCountries,this.cache.getNum_calls_getCountries()),
                () -> assertEquals(num_calls_getCacheData,this.cache.getNum_calls_getCacheData()),
                () -> assertEquals(num_calls_getCacheHistory,this.cache.getNum_calls_getCacheHistory())
        );

        assertAll("Cache data structures must be empty",
                () -> assertTrue(this.cache.getCountries().isEmpty()),
                () -> assertTrue(this.cache.getCacheData().isEmpty()),
                () -> assertTrue(this.cache.getCacheHistory().isEmpty())
        );
    }
}
