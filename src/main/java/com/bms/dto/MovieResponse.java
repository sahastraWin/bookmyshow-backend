package com.bms.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data @Builder
public class MovieResponse {
    private Long id;
    private String title;
    private String description;
    private String genre;
    private String language;
    private Integer durationMinutes;
    private Double rating;
    private String posterUrl;
    private LocalDate releaseDate;
}
