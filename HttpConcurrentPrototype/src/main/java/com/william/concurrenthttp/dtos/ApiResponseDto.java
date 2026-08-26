package com.william.concurrenthttp.dtos;

public class ApiResponseDto {
    private DataDto data;

    public DataDto getData() {
        return data;
    }

    public void setDto(DataDto data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponseDto [data=" + data + "]";
    }

}
