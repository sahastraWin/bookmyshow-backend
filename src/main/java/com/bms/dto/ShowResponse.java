package com.bms.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data @Builder
public class ShowResponse {
    private Long id;
    private Long screenId;
    private String movieTitle;
    private String screenName;
    private String theaterName;
    private String cityName;
    private LocalDate showDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double ticketPrice;
}
