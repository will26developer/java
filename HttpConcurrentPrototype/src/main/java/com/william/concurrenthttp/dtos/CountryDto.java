package com.william.concurrenthttp.dtos;

import java.util.List;

public class CountryDto {
    private NamesDto names;
    private int population;
    private String region;
    private List<CapitalDto> capitals;

    public NamesDto getNames() {
        return names;
    }

    public void setNames(NamesDto names) {
        this.names = names;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<CapitalDto> getCapitals() {
        return capitals;
    }

    public void setCapitals(List<CapitalDto> capitals) {
        this.capitals = capitals;
    }

    @Override
    public String toString() {
        return "CountryDto [names=" + names + ", population=" + population + ", region=" + region
                + ", capitals="
                + capitals + "]";
    }

}
