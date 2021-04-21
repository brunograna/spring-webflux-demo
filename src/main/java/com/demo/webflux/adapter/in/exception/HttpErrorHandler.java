package com.demo.webflux.adapter.in.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class HttpErrorHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Not Found")
    @ExceptionHandler(NotFoundException.class)
    public void notFoundHandler() {
        logger.error("not-found");
    }
}
