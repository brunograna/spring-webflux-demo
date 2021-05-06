package com.demo.webflux.adapter.in;

import com.demo.webflux.domain.Planet;
import com.demo.webflux.port.in.PlanetPortIn;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;

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

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Planet>> findAllAsEvents() {

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
        Flux<Planet> events = this.planetPortIn.findAll();

        return Flux.zip(interval, events);
    }
}
