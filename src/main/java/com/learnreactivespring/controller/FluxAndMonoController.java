package com.learnreactivespring.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class FluxAndMonoController {

    /**
     * url http://localhost:8080/flux
     * @return
     */
    @GetMapping("/flux")
    public Flux<Integer> returnFlux(){
        return Flux.just(1,2,3,4)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }


    /**
     * url http://localhost:8080/fluxstream
     * @return
     */
    @GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> returnFluxStream(){
        return Flux.just(1,2,3,4)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    /**
     * url http://localhost:8080/fluxinfinite-stream
     * @return
     */
    @GetMapping(value = "/fluxinfinite-stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Long> returnFluxInfiniteStream(){
        return Flux.interval(Duration.ofSeconds(1))
                .log();
    }


    /**
     * url http://localhost:8080/mono
     * @return
     */
    @GetMapping("/mono")
    public Mono<Integer> returnMono(){
        return Mono.just(1)
                .log();
    }
}
