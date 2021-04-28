package com.demo.webflux.adapter.in.exception;

import com.demo.webflux.adapter.in.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface HttpExceptionHandler<T> {

    Mono<ResponseEntity<ErrorDto>> handle(T exception);
}
