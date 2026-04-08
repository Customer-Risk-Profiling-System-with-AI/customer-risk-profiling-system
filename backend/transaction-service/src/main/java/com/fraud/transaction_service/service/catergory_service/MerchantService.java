package com.fraud.transaction_service.service.catergory_service;

import com.fraud.transaction_service.dto.catergory.MerchantRequest;
import com.fraud.transaction_service.dto.catergory.MerchantResponse;
import com.fraud.transaction_service.entity.catergory_entity.Merchant;
import com.fraud.transaction_service.repository.catergory_repo.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantResponse createMerchant(MerchantRequest request) {
        Merchant merchant = Merchant.builder()
                .merchantCode(request.getMerchantCode())
                .merchantName(request.getMerchantName())
                .merchantCategoryCode(request.getMerchantCategoryCode())
                .country(request.getCountry())
                .riskCode(request.getRiskCode())
                .createdAt(LocalDateTime.now())
                .build();

        Merchant saved = merchantRepository.save(merchant);

        return MerchantResponse.builder()
                .merchantCode(saved.getMerchantCode())
                .merchantName(saved.getMerchantName())
                .merchantCategoryCode(saved.getMerchantCategoryCode())
                .country(saved.getCountry())
                .riskCode(saved.getRiskCode())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }
}