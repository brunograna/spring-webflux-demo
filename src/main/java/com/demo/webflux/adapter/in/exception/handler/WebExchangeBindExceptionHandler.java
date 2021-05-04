package com.demo.webflux.adapter.in.exception.handler;

import com.demo.webflux.adapter.in.dto.ErrorDto;
import com.demo.webflux.adapter.in.exception.HttpExceptionHandler;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.Optional;

public class WebExchangeBindExceptionHandler implements HttpExceptionHandler<WebExchangeBindException> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseEntity<ErrorDto> handle(WebExchangeBindException exception) {
        logger.error("handle; exception=\"{}\";", ExceptionUtils.getStackTrace(exception));
        return ResponseEntity
                    .badRequest()
                    .body(this.buildBody(exception));
    }

    private ErrorDto buildBody(WebExchangeBindException exception) {
        return Optional.ofNullable(exception.getFieldError())
                .map(field -> field.getField() + " " + field.getDefaultMessage())
                .map(ErrorDto::new)
                .orElse(new ErrorDto("invalid data"));
    }
}
