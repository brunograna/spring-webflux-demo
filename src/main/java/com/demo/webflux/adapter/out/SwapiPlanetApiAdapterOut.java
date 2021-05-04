package com.demo.webflux.adapter.out;

import com.demo.webflux.adapter.out.config.SwapiConfig;
import com.demo.webflux.adapter.out.dto.SwapiPlanetResponseDto;
import com.demo.webflux.port.out.PlanetApiPortOut;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class SwapiPlanetApiAdapterOut implements PlanetApiPortOut {

    private final WebClient webClient;
    private final SwapiConfig config;

    public SwapiPlanetApiAdapterOut(SwapiConfig config) {
        this.config = config;
        this.webClient = WebClient.create(config.getBasePath());
    }

    @Override
    public Mono<SwapiPlanetResponseDto> findAll() {
        return this.webClient.get()
                .uri(this.config.getAllPlanetsUri())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(SwapiPlanetResponseDto.class);
    }
}
