package com.fraud.transaction_service.controller.catergory_controller;

import com.fraud.transaction_service.dto.catergory.LocationRequest;
import com.fraud.transaction_service.dto.catergory.LocationResponse;
import com.fraud.transaction_service.entity.catergory_entity.Location;
import com.fraud.transaction_service.service.catergory_service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public LocationResponse createLocation(@RequestBody LocationRequest request) {
        return locationService.createLocation(request);
    }

    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }
}
