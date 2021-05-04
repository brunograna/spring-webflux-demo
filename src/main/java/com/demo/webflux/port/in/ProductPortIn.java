package com.demo.webflux.port.in;

import com.demo.webflux.domain.PageDto;
import com.demo.webflux.domain.Product;
import com.demo.webflux.domain.QueryDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductPortIn {

    Mono<PageDto<Product>> findAll(QueryDto queryDto);
    Flux<Product> findAll();
    Mono<Product> findById(String id);
    Mono<String> save(Product p);
    Mono<Void> update(String id, Product p);
    Mono<Void> deleteById(String id);
}
