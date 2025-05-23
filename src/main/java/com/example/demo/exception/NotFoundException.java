package com.example.demo.exception;

import com.example.demo.model.enums.ErrorInfo;

public class NotFoundException extends GlobalException {


    public NotFoundException(ErrorInfo errorInfo) {
        super(errorInfo);
    }


    public NotFoundException(String message) {
        super(message, message);
    }

}
