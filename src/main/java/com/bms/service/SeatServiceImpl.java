package com.bms.service;
import com.bms.dto.SeatRequest;
import com.bms.dto.SeatResponse;
import com.bms.entity.Screen;
import com.bms.entity.Seat;
import com.bms.entity.Show;
import com.bms.exception.ResourceNotFoundException;
import com.bms.repository.BookingRepository;
import com.bms.repository.SeatRepository;
import com.bms.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final ScreenServiceImpl screenService;
    private final ShowRepository showRepository;
    private final BookingRepository bookingRepository;

    @Override
    public SeatResponse addSeat(SeatRequest request) {
        Screen screen = screenService.findEntityById(request.getScreenId());
        Seat seat = Seat.builder()
                .seatNumber(request.getSeatNumber())
                .row(request.getRow())
                .col(request.getCol())
                .seatType(request.getSeatType())
                .screen(screen)
                .build();
        return toResponse(seatRepository.save(seat), "AVAILABLE");
    }

    @Override
    public SeatResponse getSeatById(Long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seat", id));
        return toResponse(seat, "AVAILABLE");
    }

    @Override
    public List<SeatResponse> getSeatsByShow(Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show", showId));
        List<Seat> seats = seatRepository.findByScreenId(show.getScreen().getId());
        List<Long> bookedIds = bookingRepository.findBookedSeatIdsByShowId(showId);
        return seats.stream()
                .map(seat -> toResponse(seat, bookedIds.contains(seat.getId()) ? "BOOKED" : "AVAILABLE"))
                .toList();
    }

    private SeatResponse toResponse(Seat s, String status) {
        return SeatResponse.builder()
                .id(s.getId()).seatNumber(s.getSeatNumber())
                .seatType(s.getSeatType().name())
                .row(s.getRow()).col(s.getCol())
                .status(status)
                .build();
    }
}
