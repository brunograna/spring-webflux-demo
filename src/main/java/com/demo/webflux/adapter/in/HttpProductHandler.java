package com.demo.webflux.adapter.in;

import com.demo.webflux.adapter.in.dto.ProductDto;
import com.demo.webflux.adapter.out.dto.MongoProductDto;
import com.demo.webflux.port.in.ProductPortIn;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
public class HttpProductHandler {

    private final ProductPortIn productPortIn;

    public HttpProductHandler(final ProductPortIn productPortIn) {
        this.productPortIn = productPortIn;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.productPortIn.findAll(), MongoProductDto.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        var id = request.pathVariable("id");

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.productPortIn.findById(id), MongoProductDto.class);
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        var id = request.pathVariable("id");

        return request.bodyToMono(ProductDto.class)
            .flatMap(p -> this.productPortIn.update(id, p))
            .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        var id = request.pathVariable("id");

        return this.productPortIn.deleteById(id)
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        var body = request.bodyToMono(ProductDto.class);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(body.flatMap(productPortIn::save), String.class));
    }
}
