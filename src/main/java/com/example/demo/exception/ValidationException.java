package com.example.demo.exception;

import com.example.demo.model.enums.ErrorInfo;

public class ValidationException extends GlobalException {

    public ValidationException(ErrorInfo errorInfo) {
        super(errorInfo);
    }
}
