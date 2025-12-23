package ru.faickoff.api.auth.controller.handler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import ru.faickoff.api.auth.dto.response.error.BaseErrorResponse;

@ControllerAdvice
@Log4j2
public class RestExceptionHandlerAdvice {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<BaseErrorResponse> handleException(
            NoResourceFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        BaseErrorResponse responseBody = BaseErrorResponse.builder()
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(ex.getMessage())
                .details(Collections.emptyMap())
                .build();

        RestExceptionHandlerAdvice.log.warn(responseBody);

        return ResponseEntity.status(status).body(responseBody);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<BaseErrorResponse> handleException(
            IllegalStateException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        BaseErrorResponse responseBody = BaseErrorResponse.builder()
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(ex.getMessage())
                .details(Collections.emptyMap())
                .build();

        RestExceptionHandlerAdvice.log.warn(responseBody);

        return ResponseEntity.status(status).body(responseBody);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseErrorResponse> handleException(
            IllegalArgumentException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        BaseErrorResponse responseBody = BaseErrorResponse.builder()
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(ex.getMessage())
                .details(Collections.emptyMap())
                .build();

        RestExceptionHandlerAdvice.log.warn(responseBody);

        return ResponseEntity.status(status).body(responseBody);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseErrorResponse> handleException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, String> details = new HashMap<>();
        ex.getFieldErrors().stream()
                .forEach(error -> details.put(error.getField(), error.getDefaultMessage()));

        BaseErrorResponse responseBody = BaseErrorResponse.builder()
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error("Argument validation failed")
                .details(details)
                .build();

        RestExceptionHandlerAdvice.log.warn(responseBody);

        return ResponseEntity.status(status).body(responseBody);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<BaseErrorResponse> handleException(
            HttpMediaTypeNotSupportedException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        BaseErrorResponse responseBody = BaseErrorResponse.builder()
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error("Content-Type is not supported")
                .details(Collections.emptyMap())
                .build();

        RestExceptionHandlerAdvice.log.warn(responseBody);

        return ResponseEntity.status(status).body(responseBody);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseErrorResponse> handleException(
            HttpMessageNotReadableException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        BaseErrorResponse responseBody = BaseErrorResponse.builder()
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error("Required request body is missing")
                .details(Collections.emptyMap())
                .build();

        RestExceptionHandlerAdvice.log.warn(responseBody);

        return ResponseEntity.status(status).body(responseBody);
    }

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
