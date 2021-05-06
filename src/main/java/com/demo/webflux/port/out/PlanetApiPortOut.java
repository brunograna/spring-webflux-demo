package com.demo.webflux.port.out;

import com.demo.webflux.domain.Planet;
import reactor.core.publisher.Flux;

public interface PlanetApiPortOut {

    Flux<Planet> findAll();
}
