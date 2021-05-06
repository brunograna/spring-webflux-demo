package com.demo.webflux.port.in;

import com.demo.webflux.domain.Pagination;
import com.demo.webflux.domain.Product;
import com.demo.webflux.domain.interfaces.QueryData;
import com.demo.webflux.domain.interfaces.WriteProductData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductPortIn {

    Mono<Pagination<Product>> findAll(QueryData query);
    Flux<Product> findAll();
    Mono<Product> findById(String id);
    Mono<String> save(WriteProductData p);
    Mono<Void> update(String id, WriteProductData p);
    Mono<Void> deleteById(String id);
}
