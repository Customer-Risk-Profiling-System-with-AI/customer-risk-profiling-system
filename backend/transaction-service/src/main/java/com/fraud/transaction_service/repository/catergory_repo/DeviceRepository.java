package com.fraud.transaction_service.repository.catergory_repo;

import com.fraud.transaction_service.entity.catergory_entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, String> {
}
