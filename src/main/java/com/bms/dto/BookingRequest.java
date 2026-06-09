package com.bms.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class BookingRequest {
    @NotNull(message = "User ID is required")
    private Long userId;
    @NotNull(message = "Show ID is required")
    private Long showId;
    @NotEmpty(message = "Seat IDs must not be empty")
    private List<Long> seatIds;
}
