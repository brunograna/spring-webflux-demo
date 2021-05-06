package com.demo.webflux.domain;

import com.demo.webflux.domain.interfaces.ReadProductData;
import com.demo.webflux.domain.interfaces.WriteProductData;

public class Product {

    private final String id;
    private final String name;
    private final Integer quantity;

    public Product(String id, String name, Integer quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Product update(WriteProductData p) {
        return new Product(this.id, p.getName(), p.getQuantity());
    }

    public static Product from(ReadProductData p) {
        return new Product(p.getId(), p.getName(), p.getQuantity());
    }

    public static Product from(WriteProductData p) {
        return new Product(null, p.getName(), p.getQuantity());
    }
}
