package com.tqs.lab3_2.Model;

public class CarDTO {
    private String model;
    private String maker;

    public CarDTO(String model, String maker) {
        this.model=model;
        this.maker=maker;
    }

    public String getModel() {
        return this.model;
    }


    public String getMaker() {
        return this.maker;
    }
    
}
