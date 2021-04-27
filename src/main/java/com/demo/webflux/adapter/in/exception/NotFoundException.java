package com.demo.webflux.adapter.in.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class NotFoundException extends Exception implements TreatableException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, BodyWrapper bodyWrapper) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
        return serverWebExchange.getResponse().writeWith(Mono.empty());
    }
}
