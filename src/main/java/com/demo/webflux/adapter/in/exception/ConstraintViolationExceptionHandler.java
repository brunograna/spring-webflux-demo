package com.demo.webflux.adapter.in.exception;

import com.demo.webflux.adapter.in.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;

public class ConstraintViolationExceptionHandler implements ExceptionHandler {

    @Override
    public Mono<Void> handle(Throwable throwable,
                             ServerWebExchange serverWebExchange,
                             BodyWrapper bodyWrapper) {

        var castedException = (ConstraintViolationException) throwable;

        serverWebExchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);

        var message = castedException.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .findFirst()
                .orElse("invalid data");

        return serverWebExchange.getResponse().writeWith(Mono.just(bodyWrapper.wrap(new ErrorDto(message))));
    }
}
