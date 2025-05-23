package com.example.demo.exception.util;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.CustomFeignClientException;
import com.example.demo.exception.DuplicateException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.enums.ErrorInfo;

public enum ExceptionUtil {

    EXCEPTION;

    public void validation(ErrorInfo errorInfo) {
        throw new DuplicateException(errorInfo);
    }

    public DuplicateException validationThrowable(ErrorInfo errorInfo) {
        throw new DuplicateException(errorInfo);
    }

    public void notFound(ErrorInfo errorInfo) {
        throw new NotFoundException(errorInfo);
    }

    public NotFoundException notFoundThrowable(ErrorInfo errorInfo) {
        return new NotFoundException(errorInfo);
    }

    public void duplicate(ErrorInfo errorInfo) {
        throw new DuplicateException(errorInfo);
    }

    public DuplicateException duplicateThrowable(ErrorInfo errorInfo) {
        throw new DuplicateException(errorInfo);
    }

    public BadRequestException badRequestThrowable(ErrorInfo errorInfo) {
        throw new BadRequestException(errorInfo);
    }

    public void CustomFeignClient(String message, String errorCode, Integer status) {
        throw new CustomFeignClientException(message, errorCode, status);
    }

    public CustomFeignClientException CustomFeignClientThrowable(String message, String errorCode, Integer status) {
        return new CustomFeignClientException(message, errorCode, status);
    }

    public void badRequest(ErrorInfo errorInfo) {
        throw new BadRequestException(errorInfo);
    }
}
