package com.demo.webflux.adapter.in;

import com.demo.webflux.adapter.in.dto.ProductDto;
import com.demo.webflux.domain.NotFoundException;
import com.demo.webflux.domain.Product;
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
                .body(this.productPortIn.findAll(), Product.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        var id = request.pathVariable("id");

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.productPortIn.findById(id), Product.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        var body = request.bodyToMono(ProductDto.class);
        var product = body.map(ProductDto::toDomain);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(product.flatMap(productPortIn::save), String.class));
    }
}
