package com.demo.webflux.adapter.in;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class HttpProductRouter {

    @Bean
    public RouterFunction<ServerResponse> route(HttpProductHandler handler) {
        return RouterFunctions
                .route(
                        GET("/webflux/v2/products").and(accept(MediaType.APPLICATION_JSON)),
                        handler::findAll)
                .andRoute(
                        GET("/webflux/v2/products/{id}").and(accept(MediaType.APPLICATION_JSON)),
                        handler::findById)
                .andRoute(
                        POST("/webflux/v2/products").and(accept(MediaType.APPLICATION_JSON)),
                        handler::save);
    }
}
