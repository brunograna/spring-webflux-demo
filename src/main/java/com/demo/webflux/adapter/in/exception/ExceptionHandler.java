package com.demo.webflux.adapter.in.exception;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface ExceptionHandler {

    Mono<Void> handle(Throwable throwable, ServerWebExchange serverWebExchange, BodyWrapper bodyWrapper);
}
