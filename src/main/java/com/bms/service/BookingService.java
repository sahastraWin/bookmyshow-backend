package com.bms.service;
import com.bms.dto.BookingRequest;
import com.bms.dto.BookingResponse;
import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest request);
    BookingResponse getBookingById(Long id);
    List<BookingResponse> getBookingsByUser(Long userId);
    BookingResponse cancelBooking(Long bookingId);
    BookingResponse cancelSeats(Long bookingId, List<Long> seatIds);
}
