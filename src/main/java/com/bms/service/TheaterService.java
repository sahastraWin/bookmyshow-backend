package com.bms.service;
import com.bms.dto.TheaterRequest;
import com.bms.dto.TheaterResponse;
import com.bms.entity.Theater;
import java.util.List;

public interface TheaterService {
    TheaterResponse addTheater(TheaterRequest request);
    TheaterResponse getTheaterById(Long id);
    List<TheaterResponse> getAllTheaters();
    List<TheaterResponse> getTheatersByCity(Long cityId);
    Theater findEntityById(Long id);
}
