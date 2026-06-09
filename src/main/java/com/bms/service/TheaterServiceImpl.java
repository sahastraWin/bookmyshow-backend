package com.bms.service;
import com.bms.dto.TheaterRequest;
import com.bms.dto.TheaterResponse;
import com.bms.entity.City;
import com.bms.entity.Theater;
import com.bms.exception.ResourceNotFoundException;
import com.bms.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterServiceImpl implements TheaterService {
    private final TheaterRepository theaterRepository;
    private final CityServiceImpl cityService;

    @Override
    public TheaterResponse addTheater(TheaterRequest request) {
        City city = cityService.findById(request.getCityId());
        Theater theater = Theater.builder()
                .name(request.getName())
                .address(request.getAddress())
                .city(city)
                .build();
        return toResponse(theaterRepository.save(theater));
    }

    @Override
    public TheaterResponse getTheaterById(Long id) {
        return toResponse(findEntityById(id));
    }

    @Override
    public List<TheaterResponse> getAllTheaters() {
        return theaterRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public List<TheaterResponse> getTheatersByCity(Long cityId) {
        return theaterRepository.findByCityId(cityId).stream().map(this::toResponse).toList();
    }

    @Override
    public Theater findEntityById(Long id) {
        return theaterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Theater", id));
    }

    private TheaterResponse toResponse(Theater t) {
        return TheaterResponse.builder()
                .id(t.getId()).name(t.getName()).address(t.getAddress())
                .cityName(t.getCity().getName()).cityState(t.getCity().getState())
                .build();
    }
}
