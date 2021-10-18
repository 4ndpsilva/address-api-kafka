package com.maddytec.addressapi.service;

import com.maddytec.addressapi.dto.AddressResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressViaCepService service;
    private final ValidatorService validator;

    public AddressResponseDTO search(final String zipCode){
        validator.validateZipCode(zipCode);
        final AddressResponseDTO responseDTO = service.search(zipCode);
        validator.validateAddress(responseDTO);
        return responseDTO;
    }
}