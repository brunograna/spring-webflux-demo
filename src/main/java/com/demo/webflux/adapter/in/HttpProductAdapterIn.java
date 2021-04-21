package com.demo.webflux.adapter.in;

import com.demo.webflux.adapter.in.dto.ProductDto;
import com.demo.webflux.domain.Product;
import com.demo.webflux.port.in.ProductPortIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;

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

    @GetMapping("{id}")
    public Mono<Product> findAll(@PathVariable("id") String id) {
        return this.productPortIn.findById(id);
    }

    @PostMapping
    public Mono<ResponseEntity<?>> findAll(@RequestBody @Valid ProductDto dto) {
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
