package com.maddytec.addressapi.exception;

import com.maddytec.addressapi.dto.ErrorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

@RequiredArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private final ResponseMessage responseMessage;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<List<ErrorDTO>> handleResourceNotFound(ResourceNotFoundException ex){
        return responseMessage.getError(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<List<ErrorDTO>> handleServiceException(ServiceException ex){
        return responseMessage.getError(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<List<ErrorDTO>> handleUnknownHost(){
        return responseMessage.getError("API-0004", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SocketTimeoutException.class)
    public ResponseEntity<List<ErrorDTO>> handleSocketTimeout(){
        return responseMessage.getError("API-0005", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}