package com.demo.webflux.adapter.out;

import com.demo.webflux.domain.PageDto;
import com.demo.webflux.domain.Product;
import com.demo.webflux.domain.QueryDto;
import com.demo.webflux.port.out.ProductDatabasePortOut;
import com.demo.webflux.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

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
    public Mono<PageDto<Product>> findAll(QueryDto queryDto) {
        return Mono.just(queryDto)
                .map(this::buildQueryWithFilters)
                .flatMap(this::retrieveCountByQuery)
                .map(queryAndTotal -> this.buildDatabaseQueryWithPagination(queryDto, queryAndTotal))
                .flatMap(this::findAllUsingQuery)
                .map(resultAndTotalPagesAndTotal -> this.buildResponse(queryDto, resultAndTotalPagesAndTotal));
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

    private Query buildQueryWithFilters(QueryDto queryDto) {
        var query = new Query();

        if (!isEmpty(queryDto.getName())) {
            query.addCriteria(Criteria.where("name").is(queryDto.getName()));
        }

        return query;
    }

    private Tuple3<Query, Long, Long> buildDatabaseQueryWithPagination(QueryDto queryDto,
                                                                       Tuple2<Query, Long> queryAndTotal) {
        var query = queryAndTotal.getT1();
        var total = queryAndTotal.getT2();

        int limit = queryDto.getPerPage();

        var totalPages = total > limit ? Math.floorDiv(total, limit) : 1;

        query.with(PageRequest.of(queryDto.getPage(), limit));

        return Mono.zip(
                Mono.just(query),
                Mono.just(totalPages),
                Mono.just(total)
        ).block();
    }

    private Mono<Tuple2<Query, Long>> retrieveCountByQuery(Query query) {
        return Mono.zip(
                Mono.just(query),
                this.mongoTemplate.count(query, Product.class).defaultIfEmpty(0L)
        );
    }

    private Mono<Tuple3<List<Product>, Long, Long>> findAllUsingQuery(Tuple3<Query, Long, Long> queryAndTotalPagesAndTotal) {
        return Mono.zip(
                this.mongoTemplate.find(queryAndTotalPagesAndTotal.getT1(), Product.class).collectList(),
                Mono.just(queryAndTotalPagesAndTotal.getT2()),
                Mono.just(queryAndTotalPagesAndTotal.getT3())
        );
    }

    private PageDto<Product> buildResponse(QueryDto queryDto,
                                           Tuple3<List<Product>, Long, Long> resultAndTotalPagesAndTotal) {
        return new PageDto<>(
                queryDto.getPage(),
                queryDto.getPerPage(),
                resultAndTotalPagesAndTotal.getT2(),
                resultAndTotalPagesAndTotal.getT3(),
                resultAndTotalPagesAndTotal.getT1()
        );
    }
}
