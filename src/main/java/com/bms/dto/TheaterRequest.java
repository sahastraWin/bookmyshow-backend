package com.bms.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TheaterRequest {
    @NotBlank(message = "Theater name is required")
    private String name;
    @NotBlank(message = "Address is required")
    private String address;
    @NotNull(message = "City ID is required")
    private Long cityId;
}
