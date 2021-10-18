package com.maddytec.addressapi.service;

import com.maddytec.addressapi.dto.AddressResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface AddressViaCepService {

    @GetMapping("/{zipCode}/json")
    AddressResponseDTO search(@PathVariable final String zipCode);
}