package com.bms.dto;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MovieRequest {
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    @NotBlank(message = "Genre is required")
    private String genre;
    @NotBlank(message = "Language is required")
    private String language;
    @Min(1) private Integer durationMinutes;
    @DecimalMin("0.0") @DecimalMax("10.0") private Double rating;
    private String posterUrl;
    private LocalDate releaseDate;
}
