package com.demo.webflux.adapter.in.exception;

import com.demo.webflux.adapter.in.dto.ErrorDto;
import com.demo.webflux.domain.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;


@ControllerAdvice
public class HttpErrorHandler {

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorDto>> handleException(Exception ex) {
        return new DefaultExceptionHandler().handle(ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Mono<ResponseEntity<ErrorDto>> handleConstraintViolationException(ConstraintViolationException ex) {
        return new ConstraintViolationExceptionHandler().handle(ex);
    }

    @ExceptionHandler(NotFoundException.class)
    public Mono<ResponseEntity<ErrorDto>> handleNotFoundException(NotFoundException ex) {
        return new NotFoundExceptionHandler().handle(ex);
    }
}
