package com.demo.webflux.adapter.in.exception.handler;

import com.demo.webflux.adapter.in.dto.ErrorDto;
import com.demo.webflux.adapter.in.exception.HttpExceptionHandler;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public class DefaultExceptionHandler implements HttpExceptionHandler<Exception> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<ResponseEntity<ErrorDto>> handle(Exception exception) {
        logger.error("handle; exception=\"{}\";", ExceptionUtils.getStackTrace(exception));
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
