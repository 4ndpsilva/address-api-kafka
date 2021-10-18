package com.maddytec.addressapi.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maddytec.addressapi.dto.AddressRequestDTO;
import com.maddytec.addressapi.dto.AddressResponseDTO;
import com.maddytec.addressapi.service.AddressService;
import com.maddytec.addressapi.service.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/addresses")
public class AddressResource {
    private final AddressService service;
    private final KafkaService kafkaService;

    @GetMapping("/{zipCode}")
    public ResponseEntity<AddressResponseDTO> search(@PathVariable final String zipCode) {
        return ResponseEntity.ok(service.search(zipCode));
    }

    @PostMapping
    public ResponseEntity<AddressResponseDTO> sendAddress(@RequestBody final AddressRequestDTO requestDTO) throws JsonProcessingException {
        log.info("## Dados enviados pelo cliente: {}", requestDTO);

        final AddressResponseDTO responseDTO = service.search(requestDTO.getZipCode());
        responseDTO.setComplemento(requestDTO.getComplement());
        responseDTO.setNumero(requestDTO.getNumber());

        kafkaService.sendMessage(responseDTO);
        log.info("## Endereco retornado pela API de CEP: {}", responseDTO);

        return ResponseEntity.ok(responseDTO);
    }
}