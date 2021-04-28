package com.demo.webflux.port.out;

import com.demo.webflux.domain.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductDatabasePortOut {

    Flux<Product> findAll();
    Mono<Product> findById(String id);
    Mono<String> save(Product p);
    Mono<Void> deleteById(String id);
}
