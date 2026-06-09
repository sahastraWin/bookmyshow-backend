package com.bms.controller;
import com.bms.dto.SeatRequest;
import com.bms.dto.SeatResponse;
import com.bms.service.SeatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @PostMapping
    public ResponseEntity<SeatResponse> addSeat(@Valid @RequestBody SeatRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(seatService.addSeat(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatResponse> getSeatById(@PathVariable Long id) {
        return ResponseEntity.ok(seatService.getSeatById(id));
    }

    @GetMapping("/show/{showId}")
    public ResponseEntity<List<SeatResponse>> getSeatsByShow(@PathVariable Long showId) {
        return ResponseEntity.ok(seatService.getSeatsByShow(showId));
    }
}
