package com.demo.webflux.adapter.in.exception.handler;

import com.demo.webflux.adapter.in.dto.ErrorDto;
import com.demo.webflux.adapter.in.exception.HttpExceptionHandler;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.ResponseEntity;

public class JsonParseExceptionHandler implements HttpExceptionHandler<JsonParseException> {

    @Override
    public ResponseEntity<ErrorDto> handle(JsonParseException exception) {
        return ResponseEntity.badRequest()
                .body(new ErrorDto("invalid request body"));
    }
}
