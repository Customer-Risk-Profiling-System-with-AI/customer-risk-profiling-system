package com.fraud.transaction_service.controller.catergory_controller;

import com.fraud.transaction_service.dto.catergory.MerchantRequest;
import com.fraud.transaction_service.dto.catergory.MerchantResponse;
import com.fraud.transaction_service.entity.catergory_entity.Merchant;
import com.fraud.transaction_service.service.catergory_service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchants")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping
    public MerchantResponse createMerchant(@RequestBody MerchantRequest request) {
        return merchantService.createMerchant(request);
    }

    @GetMapping
    public List<Merchant> getAllMerchants() {
        return merchantService.getAllMerchants();
    }
}