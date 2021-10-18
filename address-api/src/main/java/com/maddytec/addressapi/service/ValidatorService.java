package com.maddytec.addressapi.service;

import com.maddytec.addressapi.dto.AddressResponseDTO;
import com.maddytec.addressapi.exception.ResourceNotFoundException;
import com.maddytec.addressapi.exception.ServiceException;
import org.springframework.stereotype.Component;

@Component
public class ValidatorService {
    public void validateZipCode(final String zipCode){
        if(!hasDigitOnly(zipCode)){
            throw new ServiceException("API-0002");
        }
        if(zipCode.length() != 8){
            throw new ServiceException("API-0003");
        }
    }

    public void validateAddress(final AddressResponseDTO responseDTO){
        if(responseDTO.getUf() == null){
            throw new ResourceNotFoundException("API-0001");
        }
    }

    private boolean hasDigitOnly(final String str){
        char characters[] = str.toCharArray();
        boolean status = true;

        for (int i = 0; i < characters.length; i++) {
            if(!Character.isDigit(characters[i])){
                status = false;
                break;
            }
        }

        return status;
    }
}