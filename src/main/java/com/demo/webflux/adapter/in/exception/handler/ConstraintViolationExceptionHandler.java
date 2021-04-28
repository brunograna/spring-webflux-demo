package com.demo.webflux.adapter.in.exception.handler;

import com.demo.webflux.adapter.in.dto.ErrorDto;
import com.demo.webflux.adapter.in.exception.HttpExceptionHandler;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;

public class ConstraintViolationExceptionHandler implements HttpExceptionHandler<ConstraintViolationException> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<ResponseEntity<ErrorDto>> handle(ConstraintViolationException exception) {
        logger.error("handle; exception=\"{}\";", ExceptionUtils.getStackTrace(exception));
        return Mono.just(
                ResponseEntity
                        .badRequest()
                        .body(this.buildBody(exception))
        );
    }

    private ErrorDto buildBody(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .findFirst()
                .map(ErrorDto::new)
                .orElse(new ErrorDto("invalid data"));
    }
}
