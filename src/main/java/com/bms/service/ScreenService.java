package com.bms.service;
import com.bms.dto.ScreenRequest;
import com.bms.dto.ScreenResponse;
import com.bms.entity.Screen;
import java.util.List;

public interface ScreenService {
    ScreenResponse addScreen(ScreenRequest request);
    ScreenResponse getScreenById(Long id);
    List<ScreenResponse> getAllScreens();
    List<ScreenResponse> getScreensByTheater(Long theaterId);
    Screen findEntityById(Long id);
}
