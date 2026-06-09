package com.bms.service;
import com.bms.dto.BookingRequest;
import com.bms.dto.BookingResponse;
import com.bms.entity.*;
import com.bms.enums.BookingStatus;
import com.bms.exception.BusinessException;
import com.bms.exception.ResourceNotFoundException;
import com.bms.repository.BookingRepository;
import com.bms.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final UserServiceImpl userService;
    private final ShowServiceImpl showService;

    @Override
    @Transactional
    public BookingResponse createBooking(BookingRequest request) {
        User user = userService.findEntityById(request.getUserId());
        Show show = showService.findEntityById(request.getShowId());

        List<Long> alreadyBooked = bookingRepository.findBookedSeatIdsByShowId(request.getShowId());
        for (Long seatId : request.getSeatIds()) {
            if (alreadyBooked.contains(seatId)) {
                throw new BusinessException("Seat " + seatId + " is already booked for this show");
            }
        }

        List<Seat> seats = seatRepository.findAllById(request.getSeatIds());
        if (seats.size() != request.getSeatIds().size()) {
            throw new BusinessException("One or more seat IDs are invalid");
        }

        double totalPrice = seats.size() * show.getTicketPrice();
        Booking booking = Booking.builder()
                .user(user).show(show).seats(seats)
                .totalPrice(totalPrice)
                .bookingStatus(BookingStatus.CONFIRMED)
                .build();
        return toResponse(bookingRepository.save(booking));
    }

    @Override
    public BookingResponse getBookingById(Long id) {
        return toResponse(findEntityById(id));
    }

    @Override
    public List<BookingResponse> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId).stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional
    public BookingResponse cancelBooking(Long bookingId) {
        Booking booking = findEntityById(bookingId);
        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            throw new BusinessException("Booking is already cancelled");
        }
        booking.setBookingStatus(BookingStatus.CANCELLED);
        return toResponse(bookingRepository.save(booking));
    }

    @Override
    @Transactional
    public BookingResponse cancelSeats(Long bookingId, List<Long> seatIds) {
        Booking booking = findEntityById(bookingId);
        List<Seat> remaining = new ArrayList<>(
                booking.getSeats().stream()
                        .filter(s -> !seatIds.contains(s.getId()))
                        .toList()
        );
        booking.setSeats(remaining);
        if (remaining.isEmpty()) {
            booking.setBookingStatus(BookingStatus.CANCELLED);
        }
        booking.setTotalPrice(remaining.size() * booking.getShow().getTicketPrice());
        return toResponse(bookingRepository.save(booking));
    }

    private Booking findEntityById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", id));
    }

    private BookingResponse toResponse(Booking b) {
        List<BookingResponse.SeatInfo> seatInfos = b.getSeats().stream()
                .map(s -> BookingResponse.SeatInfo.builder().id(s.getId()).seatNumber(s.getSeatNumber()).build())
                .toList();
        return BookingResponse.builder()
                .bookingId(b.getId())
                .userName(b.getUser().getName())
                .movieTitle(b.getShow().getMovie().getTitle())
                .theaterName(b.getShow().getScreen().getTheater().getName())
                .screenName(b.getShow().getScreen().getName())
                .seats(seatInfos)
                .totalPrice(b.getTotalPrice())
                .bookingStatus(b.getBookingStatus())
                .bookedAt(b.getBookedAt())
                .build();
    }
}
