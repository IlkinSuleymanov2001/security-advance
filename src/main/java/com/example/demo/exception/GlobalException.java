package com.example.demo.exception;


import com.example.demo.model.enums.ErrorInfo;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@FieldDefaults(level = PRIVATE)
class GlobalException extends RuntimeException {

    String message;
    String errorCode;

    protected GlobalException(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public GlobalException(ErrorInfo errorInfo) {
        this(errorInfo.getMessage(), errorInfo.getErrorCode());
    }
}
