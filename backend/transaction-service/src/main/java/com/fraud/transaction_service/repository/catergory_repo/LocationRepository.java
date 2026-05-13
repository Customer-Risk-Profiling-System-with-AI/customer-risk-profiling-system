package com.fraud.transaction_service.repository.catergory_repo;

import com.fraud.transaction_service.entity.catergory_entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, String> {


}
