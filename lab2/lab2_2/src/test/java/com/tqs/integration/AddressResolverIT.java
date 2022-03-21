package com.tqs.integration;

import com.tqs.connection.TqsBasicHttpClient;
import com.tqs.geocoding.Address;
import com.tqs.geocoding.AddressResolver;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressResolverIT {

    private TqsBasicHttpClient httpClient;
    private AddressResolver aR;

    @BeforeEach
    public void init(){
        httpClient = new TqsBasicHttpClient();
        aR = new AddressResolver(httpClient);
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {

        Optional<Address> res = aR.findAddressForLocation(40.640661, -8.656688);
        assertEquals(res.get(), new Address( "Cais do Alboi", "Glória e Vera Cruz", "Centro", "3800-246", null) );

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {

        assertThrows(NoSuchElementException.class, () -> {aR.findAddressForLocation(4589258,298459245).get();});
        
    }

}
