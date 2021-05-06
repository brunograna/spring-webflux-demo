package com.demo.webflux.adapter.in.exception;

import com.demo.webflux.adapter.in.dto.ErrorDto;
import com.demo.webflux.adapter.in.exception.handler.*;
import com.demo.webflux.domain.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import javax.validation.ConstraintViolationException;


// Only works for @Controller and @RestController classes, Functional Endpoints are not covered
@ControllerAdvice
public class HttpControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return new DefaultExceptionHandler().handle(ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException ex) {
        return new ConstraintViolationExceptionHandler().handle(ex);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorDto> handleWebExchangeBindException(WebExchangeBindException ex) {
        return new WebExchangeBindExceptionHandler().handle(ex);
    }

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<ErrorDto> handleServerWebInputException(ServerWebInputException ex) {
        return new ServerWebInputExceptionHandler().handle(ex);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handleNotFoundException(NotFoundException ex) {
        return new NotFoundExceptionHandler().handle(ex);
    }
}
