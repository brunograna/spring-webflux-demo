package com.demo.webflux.domain;

import com.demo.webflux.domain.interfaces.ReadPlanetData;

public class Planet {
    private final String name;
    private final String terrain;

    public Planet(String name, String terrain) {
        this.name = name;
        this.terrain = terrain;
    }
    public String getName() {
        return name;
    }

    public String getTerrain() {
        return terrain;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", terrain='" + terrain + '\'' +
                '}';
    }

    public static Planet from(ReadPlanetData planetData) {
        return new Planet(planetData.getName(), planetData.getTerrain());
    }
}
