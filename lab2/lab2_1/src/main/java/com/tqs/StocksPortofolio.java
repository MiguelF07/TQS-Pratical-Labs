package com.tqs;

import java.util.ArrayList;
import java.util.List;

public class StocksPortofolio {
    private List<Stock> stocks;
    private IStockmarketService stockmarket;


    public StocksPortofolio(IStockmarketService stockmarket) {
        this.stocks = new ArrayList<Stock>();
        this.stockmarket = stockmarket;
    }

    public void addStock(Stock s) {
        this.stocks.add(s);
    }

    public double getTotalValue() {
        double total = 0;
        for(Stock s : stocks) {
            total = total + (this.stockmarket.lookuUpPrice(s.getLabel()) * s.getQuantity());
        }
        return total;
    }

    
}
