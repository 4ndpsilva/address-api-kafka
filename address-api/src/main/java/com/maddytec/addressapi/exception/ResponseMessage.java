package com.maddytec.addressapi.exception;

import com.maddytec.addressapi.dto.ErrorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ResponseMessage {
    private final MessageSource messageSource;

    public ResponseEntity<List<ErrorDTO>> getError(final String msgCode, final HttpStatus status, Object...args){
        final String msg = messageSource.getMessage(msgCode, args, LocaleContextHolder.getLocale());
        final ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(msg);
        errorDTO.setStatusCode(status.value());
        errorDTO.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(status).body(List.of(errorDTO));
    }
}