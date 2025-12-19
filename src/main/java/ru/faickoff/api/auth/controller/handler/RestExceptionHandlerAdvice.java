package ru.faickoff.api.auth.controller.handler;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import ru.faickoff.api.auth.dto.response.error.BaseErrorResponse;

@ControllerAdvice
@Log4j2
public class RestExceptionHandlerAdvice {

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<BaseErrorResponse> handleException(
            UnsupportedOperationException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_IMPLEMENTED;

        BaseErrorResponse responseBody = BaseErrorResponse.builder()
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error("Not implemented")
                .details(Collections.emptyMap())
                .build();

        RestExceptionHandlerAdvice.log.warn(responseBody);

        return ResponseEntity.status(status).body(responseBody);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseErrorResponse> handleException(
            Exception ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        BaseErrorResponse responseBody = BaseErrorResponse.builder()
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error("Internal server error")
                .details(Collections.emptyMap())
                .build();

        ex.printStackTrace();
        RestExceptionHandlerAdvice.log.error(responseBody);

        return ResponseEntity.status(status).body(responseBody);
    }
}
