package com.demo.webflux.adapter.in.exception;

import com.demo.webflux.adapter.in.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

public class TreatableConstraintViolationException extends ConstraintViolationException implements TreatableException {

    public TreatableConstraintViolationException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, BodyWrapper bodyWrapper) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);

        var message = this.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .findFirst()
                .orElse("invalid data");

        return serverWebExchange.getResponse().writeWith(Mono.just(bodyWrapper.wrap(new ErrorDto(message))));
    }
}
