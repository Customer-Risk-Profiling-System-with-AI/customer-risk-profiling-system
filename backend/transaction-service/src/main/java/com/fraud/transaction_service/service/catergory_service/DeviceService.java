package com.fraud.transaction_service.service.catergory_service;

import com.fraud.transaction_service.dto.catergory.DeviceRequest;
import com.fraud.transaction_service.dto.catergory.DeviceResponse;
import com.fraud.transaction_service.entity.catergory_entity.Device;
import com.fraud.transaction_service.repository.catergory_repo.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceResponse createDevice(DeviceRequest request) {
        Device device = Device.builder()
                .deviceCode(request.getDeviceCode())
                .deviceFingerprint(request.getDeviceFingerprint())
                .deviceType(request.getDeviceType())
                .operatingSystem(request.getOperatingSystem())
                .trustedFlag(request.getTrustedFlag())
                .firstSeenDate(LocalDate.now())
                .lastSeenDate(LocalDate.now())
                .build();

        Device saved = deviceRepository.save(device);

        return DeviceResponse.builder()
                .deviceCode(saved.getDeviceCode())
                .deviceFingerprint(saved.getDeviceFingerprint())
                .deviceType(saved.getDeviceType())
                .operatingSystem(saved.getOperatingSystem())
                .firstSeenDate(saved.getFirstSeenDate())
                .lastSeenDate(saved.getLastSeenDate())
                .trustedFlag(saved.getTrustedFlag())
                .build();
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
}