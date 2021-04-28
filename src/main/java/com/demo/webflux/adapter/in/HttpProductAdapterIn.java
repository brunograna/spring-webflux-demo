package com.demo.webflux.adapter.in;

import com.demo.webflux.adapter.in.dto.ProductDto;
import com.demo.webflux.domain.Product;
import com.demo.webflux.port.in.ProductPortIn;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import javax.validation.Valid;
import java.net.URI;
import java.time.Duration;

@RestController
@RequestMapping("/webflux/v1/products")
public class HttpProductAdapterIn {

    private final ProductPortIn productPortIn;

    public HttpProductAdapterIn(final ProductPortIn productPortIn) {
        this.productPortIn = productPortIn;
    }

    @GetMapping
    public Flux<Product> findAll() {
        return this.productPortIn.findAll();
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Product>> findAllAsEvents() {

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
        Flux<Product> events = this.productPortIn.findAll();

        return Flux.zip(interval, events);
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Product>> findById(@PathVariable("id") String id) {
        return this.productPortIn.findById(id)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<?>> save(@RequestBody @Valid ProductDto dto) {
        return this.productPortIn.save(dto.toDomain())
                .map(this::buildUri)
                .map(uri -> ResponseEntity.created(uri).build());
    }

    public URI buildUri(String id) {
        return UriComponentsBuilder.fromUriString("/webflux/v1/products")
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
