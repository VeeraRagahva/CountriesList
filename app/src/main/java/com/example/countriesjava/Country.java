package com.example.countriesjava;

public class Country {
    private String name;
    private String region;
    private String code;
    private String capital;

    public Country(String name, String region, String code, String capital) {
        this.name = name;
        this.region = region;
        this.code = code;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getCode() {
        return code;
    }

    public String getCapital() {
        return capital;
    }
}
