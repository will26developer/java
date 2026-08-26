package com.william.concurrenthttp.dtos;

import java.util.List;

public class DataDto {
    private List<CountryDto> objects;

    public List<CountryDto> getObjects() {
        return objects;
    }

    public void setObjects(List<CountryDto> objects) {
        this.objects = objects;
    }

    @Override
    public String toString() {
        return "DataDto [objects=" + objects + "]";
    }

}
