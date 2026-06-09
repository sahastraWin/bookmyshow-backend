package com.bms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotBlank(message = "Language is required")
    private String language;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @DecimalMin(value = "0.0") @DecimalMax(value = "10.0")
    private Double rating;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "release_date")
    private LocalDate releaseDate;
}
