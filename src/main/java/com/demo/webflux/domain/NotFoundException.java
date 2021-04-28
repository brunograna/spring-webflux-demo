package com.demo.webflux.domain;

public class NotFoundException extends Exception {

    public NotFoundException() {
        // Indicates whether an entity is not found on persistence layer
    }
}
