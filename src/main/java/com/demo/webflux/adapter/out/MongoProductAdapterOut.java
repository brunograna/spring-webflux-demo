package com.demo.webflux.adapter.out;

import com.demo.webflux.adapter.out.dto.MongoProductDto;
import com.demo.webflux.domain.Pagination;
import com.demo.webflux.domain.Product;
import com.demo.webflux.domain.interfaces.QueryData;
import com.demo.webflux.port.out.ProductDatabasePortOut;
import com.demo.webflux.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.util.List;

@Service
public class MongoProductAdapterOut implements ProductDatabasePortOut {

    private final ProductRepository repository;
    private final ReactiveMongoTemplate mongoTemplate;

    public MongoProductAdapterOut(final ProductRepository repository,
                                  final ReactiveMongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<Pagination<Product>> findAll(QueryData queryData) {
        return Mono.just(queryData.toQuery())
                .flatMap(this::retrieveCountByQuery)
                .map(queryAndTotal -> this.buildDatabaseQueryWithPagination(queryData, queryAndTotal))
                .flatMap(this::findAllUsingQuery)
                .map(resultAndTotalPagesAndTotal -> this.buildResponse(queryData, resultAndTotalPagesAndTotal));
    }

    @Override
    public Flux<Product> findAll() {
        return this.repository.findAll()
                .map(Product::from);
    }

    @Override
    public Mono<Product> findById(String id) {
        return this.repository.findById(id)
                .map(Product::from);
    }

    @Override
    public Mono<String> save(Product p) {
        return this.repository.save(MongoProductDto.from(p))
                .map(MongoProductDto::getId);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return this.repository.deleteById(id);
    }

    private Tuple3<Query, Long, Long> buildDatabaseQueryWithPagination(QueryData queryData,
                                                                       Tuple2<Query, Long> queryAndTotal) {
        var query = queryAndTotal.getT1();
        var total = queryAndTotal.getT2();

        int limit = queryData.getPerPage();

        var totalPages = total > limit ? Math.floorDiv(total, limit) : 1;

        query.with(PageRequest.of(queryData.getPage(), limit));

        return Mono.zip(
                Mono.just(query),
                Mono.just(totalPages),
                Mono.just(total)
        ).block();
    }

    private Mono<Tuple2<Query, Long>> retrieveCountByQuery(Query query) {
        return Mono.zip(
                Mono.just(query),
                this.mongoTemplate.count(query, MongoProductDto.class).defaultIfEmpty(0L)
        );
    }

    private Mono<Tuple3<List<Product>, Long, Long>> findAllUsingQuery(Tuple3<Query, Long, Long> queryAndTotalPagesAndTotal) {
        return Mono.zip(
                this.mongoTemplate.find(queryAndTotalPagesAndTotal.getT1(), MongoProductDto.class)
                        .map(Product::from)
                        .collectList(),
                Mono.just(queryAndTotalPagesAndTotal.getT2()),
                Mono.just(queryAndTotalPagesAndTotal.getT3())
        );
    }

    private Pagination<Product> buildResponse(QueryData queryData,
                                              Tuple3<List<Product>, Long, Long> resultAndTotalPagesAndTotal) {
        return new Pagination<>(
                queryData.getPage(),
                queryData.getPerPage(),
                resultAndTotalPagesAndTotal.getT2(),
                resultAndTotalPagesAndTotal.getT3(),
                resultAndTotalPagesAndTotal.getT1()
        );
    }
}
