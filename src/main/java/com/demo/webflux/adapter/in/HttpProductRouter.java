package com.demo.webflux.adapter.in;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class HttpProductRouter {

    private static final String BASE_PATH = "/webflux/v2";
    private static final String ID = "{id}";

    private static final String PRODUCTS_RESOURCE = "/products";

    @Bean
    public RouterFunction<ServerResponse> route(HttpProductHandler handler) {
        return RouterFunctions
                .route(
                        GET(BASE_PATH + PRODUCTS_RESOURCE).and(accept(MediaType.APPLICATION_JSON)),
                        handler::findAll)
                .andRoute(
                        GET(BASE_PATH + PRODUCTS_RESOURCE + ID).and(accept(MediaType.APPLICATION_JSON)),
                        handler::findById)
                .andRoute(
                        DELETE(BASE_PATH + PRODUCTS_RESOURCE + ID).and(accept(MediaType.APPLICATION_JSON)),
                        handler::deleteById)
                .andRoute(
                        POST(BASE_PATH + PRODUCTS_RESOURCE).and(accept(MediaType.APPLICATION_JSON)),
                        handler::save)
                .andRoute(
                        PUT(BASE_PATH + PRODUCTS_RESOURCE + ID).and(accept(MediaType.APPLICATION_JSON)),
                        handler::update);
    }
}
