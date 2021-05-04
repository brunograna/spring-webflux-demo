package com.demo.webflux.adapter.in.exception.handler;

import com.demo.webflux.adapter.in.dto.ErrorDto;
import com.demo.webflux.adapter.in.exception.HttpExceptionHandler;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebInputException;

public class ServerWebInputExceptionHandler implements HttpExceptionHandler<ServerWebInputException> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseEntity<ErrorDto> handle(ServerWebInputException exception) {
        logger.error("handle; exception=\"{}\";", ExceptionUtils.getStackTrace(exception));

        var specificCause = exception.getMostSpecificCause();

        if (specificCause instanceof JsonParseException)
            return new JsonParseExceptionHandler().handle((JsonParseException) specificCause);

        if (specificCause instanceof InvalidFormatException)
            return new InvalidFormatExceptionHandler().handle((InvalidFormatException) specificCause);

        return ResponseEntity.badRequest()
                .body(new ErrorDto("invalid request data"));
    }
}
