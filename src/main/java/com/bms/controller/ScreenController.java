package com.bms.controller;
import com.bms.dto.ScreenRequest;
import com.bms.dto.ScreenResponse;
import com.bms.service.ScreenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/screens")
@RequiredArgsConstructor
public class ScreenController {
    private final ScreenService screenService;

    @PostMapping
    public ResponseEntity<ScreenResponse> addScreen(@Valid @RequestBody ScreenRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(screenService.addScreen(request));
    }

    @GetMapping
    public ResponseEntity<List<ScreenResponse>> getAllScreens() {
        return ResponseEntity.ok(screenService.getAllScreens());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScreenResponse> getScreenById(@PathVariable Long id) {
        return ResponseEntity.ok(screenService.getScreenById(id));
    }

    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<ScreenResponse>> getScreensByTheater(@PathVariable Long theaterId) {
        return ResponseEntity.ok(screenService.getScreensByTheater(theaterId));
    }
}
