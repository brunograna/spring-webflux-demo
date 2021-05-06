package com.demo.webflux.adapter.in.dto;

import com.demo.webflux.domain.interfaces.WriteProductData;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Validated
public class ProductDto extends SelfValidator<ProductDto> implements WriteProductData {

    @NotBlank
    private final String name;

    @PositiveOrZero
    private final Integer quantity;

    @JsonCreator
    public ProductDto(@JsonProperty("name") String name,
                      @JsonProperty("quantity") Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

}
