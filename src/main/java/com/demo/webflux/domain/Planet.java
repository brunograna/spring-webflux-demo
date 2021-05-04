package com.demo.webflux.domain;

public class Planet {
    private String name;
    private String terrain;

    public String getName() {
        return name;
    }

    public Planet setName(String name) {
        this.name = name;
        return this;
    }

    public String getTerrain() {
        return terrain;
    }

    public Planet setTerrain(String terrain) {
        this.terrain = terrain;
        return this;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", terrain='" + terrain + '\'' +
                '}';
    }
}
