package com.demo.webflux.core;

import com.demo.webflux.domain.NotFoundException;
import com.demo.webflux.domain.Pagination;
import com.demo.webflux.domain.Product;
import com.demo.webflux.domain.interfaces.QueryData;
import com.demo.webflux.domain.interfaces.WriteProductData;
import com.demo.webflux.port.in.ProductPortIn;
import com.demo.webflux.port.out.ProductDatabasePortOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ProductCore implements ProductPortIn {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductDatabasePortOut database;

    public ProductCore(final ProductDatabasePortOut database) {
        this.database = database;
    }

    @Override
    public Mono<Pagination<Product>> findAll(QueryData query) {
        this.logger.info("find-all; start;");

        var result = this.database.findAll(query);

        this.logger.info("find-all; end; success;");

        return result;
    }

    @Override
    public Flux<Product> findAll() {
        this.logger.info("find-all; start;");

        var result = this.database.findAll();

        this.logger.info("find-all; end; success;");

        return result;
    }

    @Override
    public Mono<Product> findById(String id) {
        this.logger.info("find-by-id; start; id=\"{}\";", id);

        var result = this.database.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException()));

        this.logger.info("find-by-id; end; success; id=\"{}\";", id);

        return result;
    }

    @Override
    public Mono<String> save(WriteProductData p) {
        this.logger.info("save; start; product=\"{}\";", p);

        var result = this.database.save(Product.from(p));

        this.logger.info("save; end; success; product=\"{}\";", p);

        return result;
    }

    @Override
    public Mono<Void> update(String id, WriteProductData p) {
        this.logger.info("update; start; id=\"{}\"; product=\"{}\";", id, p);

        var result = this.database.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException()))
                .flatMap(product -> this.database.save(product.update(p)))
                .then();

        this.logger.info("update; end; success; id=\"{}\"; product=\"{}\";", id, p);

        return result;
    }

    @Override
    public Mono<Void> deleteById(String id) {
        this.logger.info("delete-by-id; start; id=\"{}\";", id);

        var result= this.database.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException()))
                .flatMap(product -> this.database.deleteById(id));

        this.logger.info("delete-by-id; end; success; id=\"{}\";", id);

        return result;
    }
}
