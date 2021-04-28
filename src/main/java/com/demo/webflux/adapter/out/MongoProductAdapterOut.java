package com.demo.webflux.adapter.out;

import com.demo.webflux.domain.Product;
import com.demo.webflux.port.out.ProductDatabasePortOut;
import com.demo.webflux.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MongoProductAdapterOut implements ProductDatabasePortOut {

    private final ProductRepository repository;

    public MongoProductAdapterOut(final ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Product> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Mono<Product> findById(String id) {
        return this.repository.findById(id);
    }

    @Override
    public Mono<String> save(Product p) {
        return this.repository.save(p)
                .map(Product::getId);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return this.repository.deleteById(id);
    }
}
