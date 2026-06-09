package com.bms.dto;
import com.bms.enums.BookingStatus;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data @Builder
public class BookingResponse {
    private Long bookingId;
    private String userName;
    private String movieTitle;
    private String theaterName;
    private String screenName;
    private List<SeatInfo> seats;
    private Double totalPrice;
    private BookingStatus bookingStatus;
    private LocalDateTime bookedAt;

    @Data @Builder
    public static class SeatInfo {
        private Long id;
        private String seatNumber;
    }
}
