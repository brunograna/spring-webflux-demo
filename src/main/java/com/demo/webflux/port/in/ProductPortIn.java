package com.demo.webflux.port.in;

import com.demo.webflux.domain.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductPortIn {

    Flux<Product> findAll();
    Mono<Product> findById(String id);
    Mono<String> save(Product p);
}
