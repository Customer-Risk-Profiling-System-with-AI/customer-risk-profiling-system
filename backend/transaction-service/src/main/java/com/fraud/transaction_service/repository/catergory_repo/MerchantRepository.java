package com.fraud.transaction_service.repository.catergory_repo;

import com.fraud.transaction_service.entity.catergory_entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {


}
