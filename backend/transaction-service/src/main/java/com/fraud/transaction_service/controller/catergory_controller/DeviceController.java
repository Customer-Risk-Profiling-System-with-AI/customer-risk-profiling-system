package com.fraud.transaction_service.controller.catergory_controller;

import com.fraud.transaction_service.dto.catergory.DeviceRequest;
import com.fraud.transaction_service.dto.catergory.DeviceResponse;
import com.fraud.transaction_service.entity.catergory_entity.Device;
import com.fraud.transaction_service.service.catergory_service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping
    public DeviceResponse createDevice(@RequestBody DeviceRequest request) {
        return deviceService.createDevice(request);
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }
}