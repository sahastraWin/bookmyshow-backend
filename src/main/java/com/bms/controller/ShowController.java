package com.bms.controller;
import com.bms.dto.ShowRequest;
import com.bms.dto.ShowResponse;
import com.bms.service.ShowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {
    private final ShowService showService;

    @PostMapping
    public ResponseEntity<ShowResponse> addShow(@Valid @RequestBody ShowRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(showService.addShow(request));
    }

    @GetMapping
    public ResponseEntity<List<ShowResponse>> getAllShows() {
        return ResponseEntity.ok(showService.getAllShows());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowResponse> getShowById(@PathVariable Long id) {
        return ResponseEntity.ok(showService.getShowById(id));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowResponse>> getShowsByMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(showService.getShowsByMovie(movieId));
    }

    @GetMapping("/movie/{movieId}/date")
    public ResponseEntity<List<ShowResponse>> getShowsByMovieAndDate(
            @PathVariable Long movieId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(showService.getShowsByMovieAndDate(movieId, date));
    }
}
