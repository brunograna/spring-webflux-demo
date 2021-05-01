package com.demo.webflux.port.out;

import com.demo.webflux.domain.PageDto;
import com.demo.webflux.domain.Product;
import com.demo.webflux.domain.QueryDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductDatabasePortOut {

    Mono<PageDto<Product>> findAll(QueryDto queryDto);
    Flux<Product> findAll();
    Mono<Product> findById(String id);
    Mono<String> save(Product p);
    Mono<Void> deleteById(String id);
}
