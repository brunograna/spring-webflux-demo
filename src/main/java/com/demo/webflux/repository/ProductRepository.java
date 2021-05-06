package com.demo.webflux.repository;

import com.demo.webflux.adapter.out.dto.MongoProductDto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<MongoProductDto, String> {
}
