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
                .map(this::buildQuery)
                .flatMap(query ->
                        Mono.zip(
                                Mono.just(query),
                                this.mongoTemplate.count(query, Product.class).defaultIfEmpty(0L)
                        )
                )
                .flatMap(queryAndTotal -> {
                    var query = queryAndTotal.getT1();
                    var total = queryAndTotal.getT2();

                    int limit = queryDto.getPerPage();

                    var totalPages = total > limit ? Math.floorDiv(total, limit) : 1;

                    query.with(PageRequest.of(queryDto.getPage(), limit));

                    return Mono.zip(
                            Mono.just(query),
                            Mono.just(totalPages),
                            Mono.just(total)
                    );
                })
                .flatMap(queryAndTotalPagesAndTotal ->
                    Mono.zip(
                            this.mongoTemplate.find(queryAndTotalPagesAndTotal.getT1(), Product.class).collectList(),
                            Mono.just(queryAndTotalPagesAndTotal.getT2()),
                            Mono.just(queryAndTotalPagesAndTotal.getT3())
                    )
                )
                .map(resultAndTotalPagesAndTotal ->
                        new PageDto<>(
                                queryDto.getPage(),
                                queryDto.getPerPage(),
                                resultAndTotalPagesAndTotal.getT2(),
                                resultAndTotalPagesAndTotal.getT3(),
                                resultAndTotalPagesAndTotal.getT1()
                        )
                );
    }

    @Override
    public Flux<Product> findAll() {
        return this.repository.findAll();
    }

    private Query buildQuery(QueryDto queryDto) {
        var query = new Query();

        if (!isEmpty(queryDto.getName())) {
            query.addCriteria(Criteria.where("name").is(queryDto.getName()));
        }

        return query;
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
