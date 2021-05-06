package com.demo.webflux.adapter.out.dto;

import com.demo.webflux.domain.interfaces.ReadPlanetData;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiPlanetDto implements ReadPlanetData {

    private final String name;
    private final String terrain;

    @JsonCreator
    public SwapiPlanetDto(@JsonProperty("name") String name,
                          @JsonProperty("terrain") String terrain) {
        this.name = name;
        this.terrain = terrain;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTerrain() {
        return terrain;
    }
}
