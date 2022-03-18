package com.tqs;

import java.util.List;

public class StocksPortofolio {
    private List<Stock> stocks;
    private IStockmarketService stockmarket;


    public StocksPortofolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
    }

    public void addStock(Stock s) {
        //Implementar
    }

    public double getTotalValue() {
        //Implementar
        return 0;
    }

    
}
