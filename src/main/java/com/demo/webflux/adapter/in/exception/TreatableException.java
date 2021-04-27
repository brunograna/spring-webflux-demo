package com.demo.webflux.adapter.in.exception;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface TreatableException {
    Mono<Void> handle(ServerWebExchange serverWebExchange, BodyWrapper bodyWrapper);
}
