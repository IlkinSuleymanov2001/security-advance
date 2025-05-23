package com.example.demo.exception;

import com.example.demo.model.enums.ErrorInfo;

public class DuplicateException extends GlobalException {

    public DuplicateException(ErrorInfo errorInfo) {
        super(errorInfo);
    }
}
