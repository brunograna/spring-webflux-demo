package com.demo.webflux.adapter.in.exception.handler;

import com.demo.webflux.adapter.in.dto.ErrorDto;
import com.demo.webflux.adapter.in.exception.HttpExceptionHandler;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class InvalidFormatExceptionHandler implements HttpExceptionHandler<InvalidFormatException> {

    @Override
    public ResponseEntity<ErrorDto> handle(InvalidFormatException exception) {
        return ResponseEntity.badRequest()
                .body(this.buildBody(exception));
    }

    private ErrorDto buildBody(InvalidFormatException exception) {
        return Optional.ofNullable(exception.getMessage())
                .map(message -> message.substring(message.lastIndexOf("through reference chain")))
                .map(referenceChain -> {
                    var startPattern = "[\"";
                    var startFieldIndex = referenceChain.lastIndexOf(startPattern);
                    var endFieldIndex = referenceChain.indexOf("\"]");

                    return referenceChain
                            .substring(startFieldIndex + startPattern.length(), endFieldIndex) + " is in invalid format";
                })
                .map(ErrorDto::new)
                .orElse(new ErrorDto("invalid request"));
    }
}
