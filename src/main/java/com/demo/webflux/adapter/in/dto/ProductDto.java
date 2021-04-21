package com.demo.webflux.adapter.in.dto;

import com.demo.webflux.domain.Product;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class ProductDto {

    @NotBlank
    private final String name;

    @JsonCreator
    public ProductDto(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Product toDomain() {
        return new Product()
                .setName(this.name);
    }
}
