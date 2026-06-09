package com.bms.dto;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class CityResponse {
    private Long id;
    private String name;
    private String state;
}
