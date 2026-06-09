package com.bms.dto;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class SeatResponse {
    private Long id;
    private String seatNumber;
    private String seatType;
    private String row;
    private Integer col;
    private String status; // AVAILABLE | BOOKED
}
