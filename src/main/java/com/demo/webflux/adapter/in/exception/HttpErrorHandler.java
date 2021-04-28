package com.demo.webflux.adapter.in.exception;

import com.demo.webflux.domain.NotFoundException;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import java.util.Map;


@Configuration
@Order(-2)
public class HttpErrorHandler implements ErrorWebExceptionHandler {

    private final BodyWrapper bodyWrapper;
    private final Map<Class<?>, ExceptionHandler> handlers;

    public HttpErrorHandler(final BodyWrapper bodyWrapper) {
        this.bodyWrapper = bodyWrapper;
        this.handlers = Map.of(
                ConstraintViolationException.class, new ConstraintViolationExceptionHandler(),
                NotFoundException.class, new NotFoundExceptionHandler()
        );
    }

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {

        serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        var handler = this.handlers.getOrDefault(throwable.getClass(), new DefaultExceptionHandler());

        return handler.handle(throwable, serverWebExchange, bodyWrapper);
    }

}
