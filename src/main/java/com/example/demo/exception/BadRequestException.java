package com.example.demo.exception;

import com.example.demo.model.enums.ErrorInfo;

public class BadRequestException extends GlobalException {

    public BadRequestException(ErrorInfo errorInfo) {
        super(errorInfo);
    }

}
