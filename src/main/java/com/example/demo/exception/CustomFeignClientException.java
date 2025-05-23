package com.example.demo.exception;

import lombok.Getter;

@Getter
public class CustomFeignClientException extends GlobalException {

    private final Integer status;

    public CustomFeignClientException(String message, String errorCode, Integer status) {
        super(message, errorCode);
        this.status = status;
    }

}
