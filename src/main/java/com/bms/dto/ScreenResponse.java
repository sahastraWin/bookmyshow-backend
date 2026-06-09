package com.bms.dto;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ScreenResponse {
    private Long id;
    private String name;
    private Integer totalSeats;
    private String theaterName;
    private String cityName;
}
