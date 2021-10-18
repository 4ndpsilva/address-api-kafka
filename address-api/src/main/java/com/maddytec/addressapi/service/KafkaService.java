package com.maddytec.addressapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class KafkaService {
    @Value("${topic.address}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(final String message){
        kafkaTemplate.send(topic, message);
        log.info("Mensagem enviada para o topico {}: {}", topic, message);
    }

    public void sendMessage(final Object object) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String message = objectMapper.writeValueAsString(object);
        sendMessage(message);
    }
}