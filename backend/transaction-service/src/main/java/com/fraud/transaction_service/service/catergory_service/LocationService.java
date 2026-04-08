package com.fraud.transaction_service.service.catergory_service;

import com.fraud.transaction_service.dto.catergory.LocationRequest;
import com.fraud.transaction_service.dto.catergory.LocationResponse;
import com.fraud.transaction_service.entity.catergory_entity.Location;
import com.fraud.transaction_service.repository.catergory_repo.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationResponse createLocation(LocationRequest request) {
        Location location = Location.builder()
                .locationCode(request.getLocationCode())
                .country(request.getCountry())
                .region(request.getRegion())
                .city(request.getCity())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .riskFlag(request.getRiskFlag())
                .build();

        Location saved = locationRepository.save(location);

        return LocationResponse.builder()
                .locationCode(saved.getLocationCode())
                .country(saved.getCountry())
                .region(saved.getRegion())
                .city(saved.getCity())
                .latitude(saved.getLatitude())
                .longitude(saved.getLongitude())
                .riskFlag(saved.getRiskFlag())
                .build();
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}