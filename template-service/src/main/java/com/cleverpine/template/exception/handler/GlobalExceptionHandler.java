package com.cleverpine.template.exception.handler;

import com.cleverpine.template.model.ErrorData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorData> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return createErrorResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorData> createErrorResponse(Exception exception, HttpStatus httpStatus) {
        var errorData = new ErrorData();
        errorData.setStatus(httpStatus.value());
        errorData.setTitle(httpStatus.getReasonPhrase());
        errorData.setDetail(exception.getMessage());
        return new ResponseEntity<>(errorData, httpStatus);
    }

}
