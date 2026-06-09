package com.bms.service;
import com.bms.dto.SeatRequest;
import com.bms.dto.SeatResponse;
import java.util.List;

public interface SeatService {
    SeatResponse addSeat(SeatRequest request);
    SeatResponse getSeatById(Long id);
    List<SeatResponse> getSeatsByShow(Long showId);
}
