package com.maddytec.addressclient.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maddytec.addressclient.domain.Address;
import com.maddytec.addressclient.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClientAddressListener {
    private final AddressService service;

    @KafkaListener(topics = "${topic.address-client}", groupId = "${spring.kafka.consumer.group-id}")
    public void retrieveMessage(final String message) throws JsonProcessingException {
       log.info("Mensagem endereco recuperada do kafka: {}", message);

       final ObjectMapper mapper = new ObjectMapper();
       final Address address = mapper.readValue(message, Address.class);

       service.save(address);
       log.info("Endereco salvo com sucesso: {}", address.toString());
    }
}