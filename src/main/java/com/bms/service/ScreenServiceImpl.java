package com.bms.service;
import com.bms.dto.ScreenRequest;
import com.bms.dto.ScreenResponse;
import com.bms.entity.Screen;
import com.bms.entity.Theater;
import com.bms.exception.ResourceNotFoundException;
import com.bms.repository.ScreenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreenServiceImpl implements ScreenService {
    private final ScreenRepository screenRepository;
    private final TheaterServiceImpl theaterService;

    @Override
    public ScreenResponse addScreen(ScreenRequest request) {
        Theater theater = theaterService.findEntityById(request.getTheaterId());
        Screen screen = Screen.builder()
                .name(request.getName())
                .totalSeats(request.getTotalSeats())
                .theater(theater)
                .build();
        return toResponse(screenRepository.save(screen));
    }

    @Override
    public ScreenResponse getScreenById(Long id) {
        return toResponse(findEntityById(id));
    }

    @Override
    public List<ScreenResponse> getAllScreens() {
        return screenRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public List<ScreenResponse> getScreensByTheater(Long theaterId) {
        return screenRepository.findByTheaterId(theaterId).stream().map(this::toResponse).toList();
    }

    @Override
    public Screen findEntityById(Long id) {
        return screenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Screen", id));
    }

    private ScreenResponse toResponse(Screen s) {
        String theaterName = s.getTheater() != null ? s.getTheater().getName() : "N/A";
        String cityName = (s.getTheater() != null && s.getTheater().getCity() != null)
                ? s.getTheater().getCity().getName() : "N/A";
        return ScreenResponse.builder()
                .id(s.getId()).name(s.getName())
                .totalSeats(s.getTotalSeats() != null ? s.getTotalSeats() : 0)
                .theaterName(theaterName).cityName(cityName)
                .build();
    }
}
