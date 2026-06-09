package com.bms.dto;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowRequest {
    @NotNull(message = "Movie ID is required")
    private Long movieId;
    @NotNull(message = "Screen ID is required")
    private Long screenId;
    @NotNull(message = "Show date is required")
    private LocalDate showDate;
    @NotNull(message = "Start time is required")
    private LocalTime startTime;
    private LocalTime endTime;
    @NotNull @Positive(message = "Ticket price must be positive")
    private Double ticketPrice;
}
