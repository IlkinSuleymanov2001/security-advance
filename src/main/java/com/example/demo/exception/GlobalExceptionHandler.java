package com.example.demo.exception;


import com.example.demo.exception.response.ErrorResponse;
import com.example.demo.exception.response.ValidationResponse;
import com.example.demo.model.enums.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.model.enums.ErrorInfo.INTERNAL_ERROR;
import static com.example.demo.model.enums.ErrorInfo.VALIDATION_ERROR;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception ex) {
        ex.printStackTrace();
        return new ErrorResponse(INTERNAL_ERROR.getMessage(), INTERNAL_ERROR.getErrorCode());
    }

    // Handle FeignException (this includes 500 errors from MS1)
    @ExceptionHandler(CustomFeignClientException.class)
    public ResponseEntity<?> handle(CustomFeignClientException e) {

        ErrorResponse defaultErrorResponse = ErrorResponse
                .builder()
                .message(e.getErrorCode())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(e.getStatus()).body(defaultErrorResponse);
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle(NotFoundException ex) {
        return new ErrorResponse(ex.getMessage(), ex.getErrorCode());
    }

    @ExceptionHandler(value = AuthorizationServiceException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ErrorResponse handle(AuthorizationServiceException ex) {
        log.error("AuthorizationServiceException", ex);
        return new ErrorResponse(ErrorInfo.USER_UNAUTHORIZED.getMessage(), ErrorInfo.USER_UNAUTHORIZED.getErrorCode());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(FORBIDDEN)
    public ErrorResponse handle(AccessDeniedException ex) {
        log.error("AccessDeniedException", ex);
        return new ErrorResponse("user dont have access for this operation.", "ACCESS_DENIED");
    }

    @ExceptionHandler(value = DuplicateException.class)
    @ResponseStatus(CONFLICT)
    public ErrorResponse handle(DuplicateException ex) {
        return new ErrorResponse(ex.getMessage(), ex.getErrorCode());
    }

    @ExceptionHandler(value = ValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handle(ValidationException ex) {
        return new ErrorResponse(ex.getMessage(), ex.getErrorCode());
    }


    @ExceptionHandler(value = AuthException.class)
    public ResponseEntity<ErrorResponse> handle(AuthException ex) {
        return ResponseEntity.status(ex.status).body(new ErrorResponse(ex.getMessage(), ex.getErrorCode()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ValidationResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Iterate through the validation errors
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ValidationResponse
                .builder()
                .errors(errors)
                .message(VALIDATION_ERROR.getMessage())
                .errorCode(VALIDATION_ERROR.getErrorCode())
                .build();

    }
}
