package com.bms.dto;
import com.bms.enums.SeatType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SeatRequest {
    @NotBlank(message = "Seat number is required")
    private String seatNumber;
    private String row;
    private Integer col;
    @NotNull(message = "Seat type is required")
    private SeatType seatType;
    @NotNull(message = "Screen ID is required")
    private Long screenId;
}
