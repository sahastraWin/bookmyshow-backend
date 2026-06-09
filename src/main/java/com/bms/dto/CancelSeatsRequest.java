package com.bms.dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Data
public class CancelSeatsRequest {
    @NotEmpty(message = "Seat IDs must not be empty")
    private List<Long> seatIds;
}
