package com.demo.webflux.port.out;

import com.demo.webflux.adapter.out.dto.SwapiPlanetResponseDto;
import reactor.core.publisher.Mono;

public interface PlanetApiPortOut {

    Mono<SwapiPlanetResponseDto> findAll();
}
