package com.demo.webflux.adapter.in.exception;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Configuration
@Order(-2)
public class HttpErrorHandler implements ErrorWebExceptionHandler {

    private final BodyWrapper bodyWrapper;

    public HttpErrorHandler(final BodyWrapper bodyWrapper) {
        this.bodyWrapper = bodyWrapper;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {

        serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        if (throwable instanceof TreatableException) {
            return ((TreatableException) throwable).handle(serverWebExchange, bodyWrapper);
        }

        return this.handleNotTreatedException(serverWebExchange);
    }

    private Mono<Void> handleNotTreatedException(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);

        return serverWebExchange.getResponse().writeWith(Mono.empty());
    }

}
