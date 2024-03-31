package com.example.task.exception;


import com.example.task.common.utils.Localization;
import com.example.task.model.ErrorResponse;
import com.example.task.model.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;


@RestControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class CustomExceptionHandler {

    private final Localization localization;

    @ExceptionHandler(value = {CustomException.class, GeneralApiException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public GenericResponse handleGeneralApiException(CustomException exception) {
        log.error("Handled Exception {0}", exception);
        final var errorResponse = ErrorResponse.builder().code(exception.getErrorCode()).description(localization.getMessage(exception.getMessage())).build();
        return GenericResponse.errorMsg(errorResponse);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GenericResponse handleValidationErrors(MethodArgumentNotValidException ex) {
        log.error("Handled Exception {0}", ex);
        final var errorResponse = new ErrorResponse(400, Objects.requireNonNull(ex.getFieldError()).getDefaultMessage());
        return GenericResponse.errorMsg(errorResponse);
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GenericResponse handleValidationErrors(BindException ex) {
        log.error("Handled Exception {0}", ex);
        final var errorResponse = new ErrorResponse(400, Objects.requireNonNull(ex.getFieldError()).getDefaultMessage());
        return GenericResponse.errorMsg(errorResponse);
    }



    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public GenericResponse handleValidationErrors(BadCredentialsException exception) {
        log.error("Handled Exception {0}", exception);
        final var errorResponse = ErrorResponse.builder().code(401).description(localization.getMessage(exception.getMessage())).build();
        return GenericResponse.errorMsg(errorResponse);
    }

    @ExceptionHandler({HttpMessageConversionException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GenericResponse handleValidationErrors(HttpMessageConversionException ex) {
        log.error("Handled Exception {0}", ex);
        final var errorResponse = new ErrorResponse(400, Objects.requireNonNull(ex.getMessage()));
        return GenericResponse.errorMsg(errorResponse);
    }

    @ExceptionHandler({RestApiSuccessResponseException.class})
    @ResponseStatus(value = HttpStatus.OK)
    public GenericResponse handleValidationErrors(RestApiSuccessResponseException ex) {
        log.error("Handled Exception {0}", ex);
        final var errorResponse = new ErrorResponse(ex.errorCode, Objects.requireNonNull(ex.getMessage()));
        return GenericResponse.errorMsg(errorResponse);
    }

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public GenericResponse handleValidationErrors(UnauthorizedException ex) {
        log.error("Handled Exception {0}", ex);
        final var errorResponse = new ErrorResponse(ex.getErrorCode(), Objects.requireNonNull(ex.getMessage()));
        return GenericResponse.errorMsg(errorResponse);
    }
}