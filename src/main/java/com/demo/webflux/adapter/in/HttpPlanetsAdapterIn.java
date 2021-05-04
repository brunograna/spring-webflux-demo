package com.demo.webflux.adapter.in;

import com.demo.webflux.domain.Planet;
import com.demo.webflux.port.in.PlanetPortIn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/webflux/v1/planets")
public class HttpPlanetsAdapterIn {

    private final PlanetPortIn planetPortIn;

    public HttpPlanetsAdapterIn(PlanetPortIn planetPortIn) {
        this.planetPortIn = planetPortIn;
    }

    @GetMapping
    public Flux<Planet> findAll() {
        return this.planetPortIn.findAll();
    }
}
