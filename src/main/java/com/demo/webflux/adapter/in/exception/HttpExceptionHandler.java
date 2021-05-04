package com.demo.webflux.adapter.in.exception;

import com.demo.webflux.adapter.in.dto.ErrorDto;
import org.springframework.http.ResponseEntity;

public interface HttpExceptionHandler<T> {

    ResponseEntity<ErrorDto> handle(T exception);
}
