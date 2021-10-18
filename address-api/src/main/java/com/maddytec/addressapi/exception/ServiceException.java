package com.maddytec.addressapi.exception;

public class ServiceException extends RuntimeException{
    public ServiceException(final String msg){
        super(msg);
    }
}