package com.example.demo.exception;

import com.example.demo.model.enums.ErrorInfo;

public class AuthException extends GlobalException {

    int status;

    public AuthException(ErrorInfo errorInfo, int status) {
        super(errorInfo);
        this.status = status;
    }
}
