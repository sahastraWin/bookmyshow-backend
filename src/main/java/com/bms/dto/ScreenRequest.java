package com.bms.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ScreenRequest {
    @NotBlank(message = "Screen name is required")
    private String name;
    @NotNull @Min(value = 1, message = "Total seats must be at least 1")
    private Integer totalSeats;
    @NotNull(message = "Theater ID is required")
    private Long theaterId;
}
