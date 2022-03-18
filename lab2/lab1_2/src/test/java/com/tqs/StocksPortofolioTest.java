package com.tqs;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StocksPortofolioTest {
    @Mock
    IStockmarketService market;

    @InjectMocks
    StocksPortofolio portofolio;

    
}
