package com.demo.webflux.adapter.in.dto;

import java.io.Serializable;

public class ErrorDto implements Serializable {

    private final String message;

    public ErrorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
