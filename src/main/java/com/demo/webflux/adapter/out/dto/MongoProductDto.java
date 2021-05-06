package com.demo.webflux.adapter.out.dto;

import com.demo.webflux.domain.Product;
import com.demo.webflux.domain.interfaces.ReadProductData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "webflux-products")
public class MongoProductDto implements ReadProductData {

    @Id
    private final String id;
    private final String name;
    private final Integer quantity;

    public MongoProductDto(String id, String name, Integer quantity) {
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

    @Override
    public String toString() {
        return "MongoProduct{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public static MongoProductDto from(Product p) {
        return new MongoProductDto(p.getId(), p.getName(), p.getQuantity());
    }

}
