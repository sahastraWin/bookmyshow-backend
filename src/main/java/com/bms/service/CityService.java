package com.bms.service;
import com.bms.dto.CityRequest;
import com.bms.dto.CityResponse;
import java.util.List;

public interface CityService {
    CityResponse addCity(CityRequest request);
    List<CityResponse> getAllCities();
    CityResponse getCityById(Long id);
}
