package com.demo.webflux.port.in;

import com.demo.webflux.domain.Planet;
import reactor.core.publisher.Flux;

public interface PlanetPortIn {

    Flux<Planet> findAll();
}
