package com.demo.webflux.adapter.out.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Configuration
public class SwapiConfig {

    @Value("${app.swapi.base-path}")
    private String basePath;

    @Value("${app.swapi.resources.planets}")
    private String planetResource;

    public String getBasePath() {
        return basePath;
    }

    public String getPlanetResource() {
        return planetResource;
    }

    public URI getAllPlanetsUri() {
        return UriComponentsBuilder
                .fromUriString(basePath)
                .path(planetResource)
                .build()
                .toUri();
    }
}
