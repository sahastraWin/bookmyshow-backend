package com.bms.controller;
import com.bms.dto.TheaterRequest;
import com.bms.dto.TheaterResponse;
import com.bms.service.TheaterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/theaters")
@RequiredArgsConstructor
public class TheaterController {
    private final TheaterService theaterService;

    @PostMapping
    public ResponseEntity<TheaterResponse> addTheater(@Valid @RequestBody TheaterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(theaterService.addTheater(request));
    }

    @GetMapping
    public ResponseEntity<List<TheaterResponse>> getAllTheaters() {
        return ResponseEntity.ok(theaterService.getAllTheaters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheaterResponse> getTheaterById(@PathVariable Long id) {
        return ResponseEntity.ok(theaterService.getTheaterById(id));
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<TheaterResponse>> getTheatersByCity(@PathVariable Long cityId) {
        return ResponseEntity.ok(theaterService.getTheatersByCity(cityId));
    }
}
