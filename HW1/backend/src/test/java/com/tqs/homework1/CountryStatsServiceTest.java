package com.tqs.homework1;

import com.tqs.homework1.client.CountryStatsClient;
import com.tqs.homework1.model.Cases;
import com.tqs.homework1.model.CountryStats;
import com.tqs.homework1.model.Deaths;
import com.tqs.homework1.model.Tests;
import com.tqs.homework1.service.CountryStatsService;
import org.apache.tomcat.jni.Local;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CountryStatsServiceTest {
    @Mock(lenient = true)
    private CountryStatsClient client;

    @InjectMocks
    private CountryStatsService countryService;

    @BeforeEach
    public void setUp() throws ParseException, IOException, InterruptedException {
        String countries = "{\"response\":[\"Portugal\",\"Spain\"]}";
        String statistics = "{\"response\":[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"cases\":{\"new\":null,\"recovered\":null,\"total\":3791744,\"critical\":61,\"active\":null,\"1M_pop\":\"373836\"},\"tests\":{\"total\":41113089,\"1M_pop\":\"4053425\"},\"time\":\"2022-04-27T23:00:03+00:00\",\"day\":\"2022-04-27\",\"deaths\":{\"new\":null,\"total\":22162,\"1M_pop\":\"2185\"},\"population\":10142802}],\"get\":\"statistics\",\"parameters\":{\"country\":\"portugal\"},\"results\":1,\"errors\":[]}";
        String history7 = "{\"response\":[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"cases\":{\"new\":\"+31431\",\"recovered\":2266939,\"total\":2915971,\"critical\":180,\"active\":628810,\"1M_pop\":\"287311\"},\"tests\":{\"total\":33409187,\"1M_pop\":\"3291808\"},\"time\":\"2022-02-07T15:15:02+00:00\",\"day\":\"2022-02-07\",\"deaths\":{\"new\":\"+51\",\"total\":20222,\"1M_pop\":\"1992\"},\"population\":10149191},{\"continent\":\"Europe\",\"country\":\"Portugal\",\"cases\":{\"new\":\"+31431\",\"recovered\":2266939,\"total\":2915971,\"critical\":180,\"active\":628810,\"1M_pop\":\"287308\"},\"tests\":{\"total\":33409187,\"1M_pop\":\"3291782\"},\"time\":\"2022-02-07T00:00:02+00:00\",\"day\":\"2022-02-07\",\"deaths\":{\"new\":\"+51\",\"total\":20222,\"1M_pop\":\"1992\"},\"population\":10149272}],\"get\":\"history\",\"parameters\":{\"country\":\"portugal\",\"day\":\"2022-02-07\"},\"results\":2,\"errors\":[]}";
        String history8 = "{\"response\":[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"cases\":{\"new\":\"+17019\",\"recovered\":2304585,\"total\":2932990,\"critical\":178,\"active\":608147,\"1M_pop\":\"288990\"},\"tests\":{\"total\":33409187,\"1M_pop\":\"3291834\"},\"time\":\"2022-02-08T15:00:03+00:00\",\"day\":\"2022-02-08\",\"deaths\":{\"new\":\"+36\",\"total\":20258,\"1M_pop\":\"1996\"},\"population\":10149110},{\"continent\":\"Europe\",\"country\":\"Portugal\",\"cases\":{\"new\":\"+17019\",\"recovered\":2304585,\"total\":2932990,\"critical\":178,\"active\":608147,\"1M_pop\":\"288988\"},\"tests\":{\"total\":33409187,\"1M_pop\":\"3291808\"},\"time\":\"2022-02-08T00:00:03+00:00\",\"day\":\"2022-02-08\",\"deaths\":{\"new\":\"+36\",\"total\":20258,\"1M_pop\":\"1996\"},\"population\":10149191}],\"get\":\"history\",\"parameters\":{\"country\":\"portugal\",\"day\":\"2022-02-08\"},\"results\":2,\"errors\":[]}";
        String wrongCountry = "{\"response\":[],\"get\":\"statistics\",\"parameters\":{\"country\":\"fakecountry\"},\"results\":0,\"errors\":[]}";
        JSONObject jsonCountries = (JSONObject)new JSONParser().parse(countries);
        JSONObject jsonStatistics = (JSONObject) new JSONParser().parse(statistics);
        JSONObject jsonHistory7 = (JSONObject) new JSONParser().parse(history7);
        JSONObject jsonHistory8 = (JSONObject) new JSONParser().parse(history8);
        JSONObject jsonStatisticsFake = (JSONObject) new JSONParser().parse(wrongCountry);
        Mockito.when(client.findCountries()).thenReturn(jsonCountries);
        Mockito.when(client.findStatisticsByCountry("portugal")).thenReturn(jsonStatistics);
        Mockito.when(client.findStatisticsByCountry("fakecountry")).thenReturn(jsonStatisticsFake);
        Mockito.when(client.findHistoryByCountry("portugal", LocalDate.parse("2022-02-07"))).thenReturn(jsonHistory7);
        Mockito.when(client.findHistoryByCountry("portugal", LocalDate.parse("2022-02-08"))).thenReturn(jsonHistory8);
    }

    @Test
    public void getCountries_returnsListOfCountries() throws IOException, ParseException, InterruptedException {
        List<String> countriesL = new ArrayList<>();
        countriesL.add("Portugal");
        countriesL.add("Spain");
        assertThat(countryService.getCountriesList()).isEqualTo(countriesL);
    }

    @Test
    public void getStatisticsByCountry_returnsStatisticsOfCountry() throws IOException, ParseException, InterruptedException {
        Cases cas = new Cases(null,null,61L,null,"373836",3791744L);
        Deaths dea = new Deaths(null,"2185",22162L);
        Tests tes = new Tests("4053425",41113089L);
        CountryStats cs = new CountryStats("Europe","Portugal",10142802L,cas,dea,tes,"2022-04-27","2022-04-27T23:00:03+00:00");
        assertThat(countryService.getStatisticsByCountry("portugal")).isEqualTo(Optional.of(cs));
    }

    @Test
    public void getStatisticsByWrongCountry_returnsEmptyOptional() throws IOException, ParseException, InterruptedException {
        assertThat(countryService.getStatisticsByCountry("fakecountry")).isEqualTo(Optional.empty());
    }

    @Test
    public void getHistoryByCountry_returnsHistoryOfCountry() throws ParseException, IOException, InterruptedException {
        List<Optional<CountryStats>> history = new ArrayList<>();

        Cases cas7 = new Cases("+31431",628810L,180L,2266939L,"287311",2915971L);
        Deaths dea7 = new Deaths("+51","1992",20222L);
        Tests tes7 = new Tests("3291808",33409187L);
        CountryStats cs7 = new CountryStats("Europe","Portugal",10149191L,cas7,dea7,tes7,"2022-02-07","2022-02-07T15:15:02+00:00");

        Cases cas8 = new Cases("+17019",608147L,178L,2304585L,"288990",2932990L);
        Deaths dea8 = new Deaths("+36","1996",20258L);
        Tests tes8 = new Tests("3291834",33409187L);
        CountryStats cs8 = new CountryStats("Europe","Portugal",10149110L,cas8,dea8,tes8,"2022-02-08","2022-02-08T15:00:03+00:00");

        history.add(Optional.of(cs7));
        history.add(Optional.of(cs8));

        assertThat(countryService.getHistoryByCountry("portugal",LocalDate.parse("2022-02-07"),LocalDate.parse("2022-02-08"))).isEqualTo(history);
    }
}
