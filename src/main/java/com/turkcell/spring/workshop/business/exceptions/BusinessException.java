package com.turkcell.spring.workshop.business.exceptions;


public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }
}