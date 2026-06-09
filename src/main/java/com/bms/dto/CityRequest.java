package com.bms.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CityRequest {
    @NotBlank(message = "City name is required")
    private String name;
    @NotBlank(message = "State is required")
    private String state;
}
