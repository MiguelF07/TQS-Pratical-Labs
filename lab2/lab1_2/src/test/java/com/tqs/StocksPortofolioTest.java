package com.tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StocksPortofolioTest {
    @Mock
    IStockmarketService market;

    @InjectMocks
    StocksPortofolio portofolio;

    @Test
    public void whenGetTotalValue_SumWithMockedValues() {
        IStockmarketService mockMarket = Mockito.mock(IStockmarketService.class);
        StocksPortofolio portofolio = new StocksPortofolio(mockMarket);

        Mockito.when(mockMarket.lookuUpPrice("AAPL")).thenReturn(4.0);
        Mockito.when(mockMarket.lookuUpPrice("EBAY")).thenReturn(2.0);

        portofolio.addStock(new Stock("EBAY",2));
        portofolio.addStock(new Stock("AAPL", 3));

        assertEquals(16.0, portofolio.getTotalValue());
        Mockito.verify(mockMarket, Mockito.times(2)).lookuUpPrice(Mockito.anyString());
    }
}
