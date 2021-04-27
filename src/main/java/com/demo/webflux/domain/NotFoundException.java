package com.demo.webflux.domain;

import com.demo.webflux.adapter.in.exception.BodyWrapper;
import com.demo.webflux.adapter.in.exception.TreatableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class NotFoundException extends Exception implements TreatableException {

    public NotFoundException() {
        // Indicates whether an entity is not found on persistence layer
    }

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, BodyWrapper bodyWrapper) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
        return serverWebExchange.getResponse().writeWith(Mono.empty());
    }
}
