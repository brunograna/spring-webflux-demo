package com.demo.webflux.adapter.in.exception.handler;

import com.demo.webflux.adapter.in.dto.ErrorDto;
import com.demo.webflux.adapter.in.exception.HttpExceptionHandler;
import com.demo.webflux.domain.NotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public class NotFoundExceptionHandler implements HttpExceptionHandler<NotFoundException> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseEntity<ErrorDto> handle(NotFoundException exception) {
        logger.error("handle; exception=\"{}\";", ExceptionUtils.getStackTrace(exception));
        return ResponseEntity.notFound().build();
    }
}
