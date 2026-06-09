package com.bms.dto;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class TheaterResponse {
    private Long id;
    private String name;
    private String address;
    private String cityName;
    private String cityState;
}
