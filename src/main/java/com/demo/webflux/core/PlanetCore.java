package com.demo.webflux.core;

import com.demo.webflux.domain.Planet;
import com.demo.webflux.port.in.PlanetPortIn;
import com.demo.webflux.port.out.PlanetApiPortOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PlanetCore implements PlanetPortIn {

    private final Logger logger = LoggerFactory.getLogger(PlanetCore.class);

    private final PlanetApiPortOut planetApiPortOut;

    public PlanetCore(PlanetApiPortOut planetApiPortOut) {
        this.planetApiPortOut = planetApiPortOut;
    }

    @Override
    public Flux<Planet> findAll() {
        this.logger.info("find-all; start;");

        var result =  this.planetApiPortOut.findAll();

        this.logger.info("find-all; end; success;");

        return result;
    }
}
