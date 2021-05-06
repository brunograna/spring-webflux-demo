package com.demo.webflux.adapter.out.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiPlanetResponseDto {

    private final List<SwapiPlanetDto> results;

    @JsonCreator
    public SwapiPlanetResponseDto(@JsonProperty("results") List<SwapiPlanetDto> results) {
        this.results = results;
    }

    public List<SwapiPlanetDto> getResults() {
        return results;
    }
}
