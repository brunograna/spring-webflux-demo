package com.demo.webflux.adapter.out.dto;

import com.demo.webflux.domain.Planet;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiPlanetResponseDto {

    private final List<Planet> results;

    @JsonCreator
    public SwapiPlanetResponseDto(@JsonProperty("results") List<Planet> results) {
        this.results = results;
    }

    public List<Planet> getResults() {
        return results;
    }
}
