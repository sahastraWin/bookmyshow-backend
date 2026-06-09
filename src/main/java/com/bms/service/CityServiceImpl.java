package com.bms.service;
import com.bms.dto.CityRequest;
import com.bms.dto.CityResponse;
import com.bms.entity.City;
import com.bms.exception.BusinessException;
import com.bms.exception.ResourceNotFoundException;
import com.bms.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public CityResponse addCity(CityRequest request) {
        if (cityRepository.existsByNameIgnoreCase(request.getName())) {
            throw new BusinessException("City already exists: " + request.getName());
        }
        City city = City.builder()
                .name(request.getName())
                .state(request.getState())
                .build();
        return toResponse(cityRepository.save(city));
    }

    @Override
    public List<CityResponse> getAllCities() {
        return cityRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public CityResponse getCityById(Long id) {
        return toResponse(findById(id));
    }

    public City findById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City", id));
    }

    private CityResponse toResponse(City c) {
        return CityResponse.builder().id(c.getId()).name(c.getName()).state(c.getState()).build();
    }
}
