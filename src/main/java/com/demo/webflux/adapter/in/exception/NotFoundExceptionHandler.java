package com.demo.webflux.adapter.in.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class NotFoundExceptionHandler implements ExceptionHandler {

    @Override
    public Mono<Void> handle(Throwable throwable,
                             ServerWebExchange serverWebExchange,
                             BodyWrapper bodyWrapper) {

        serverWebExchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
        return serverWebExchange.getResponse().writeWith(Mono.empty());
    }
}
