package com.learnreactivespring.fluxandmonoplayground;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

    @Test
    public void fluxTest(){
       Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                                        /***/.concatWith(Flux.error(new RuntimeException("Exception Occurred!")))
                                        /***/.concatWith(Flux.just("Not Reachable After Error"))
                                            .log();

       stringFlux
               .subscribe(System.out::println, throwable -> System.err.println("Exception is: " + throwable)
               , () -> {System.out.println("After Completed Event!");});
    }

    @Test
    @DisplayName("FluxTestElements Without Error")
    public void fluxTestElements_WithoutError(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                                        .log();

        StepVerifier.create(stringFlux)
            .expectNext("Spring")
            .expectNext("Spring Boot")
            .expectNext("Reactive Spring")
            .verifyComplete();

    }

    @Test
    @DisplayName("FluxTestElements With Error")
    public void fluxTestElements_WithError(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                                        .concatWith(Flux.error(new RuntimeException("Exception Occurred!")))
                                        .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                //.expectError(RuntimeException.class)
                .expectErrorMessage("Exception Occurred!")
                .verify();
    }

    @Test
    @DisplayName("FluxTestElements Count")
    public void fluxTestElements_Count(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred!")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .expectErrorMessage("Exception Occurred!")
                .verify();
    }


    @Test
    @DisplayName("FluxTestElements With single expectNext() method")
    public void fluxTestElements_WithSingleExpectNextMethod(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred!")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring", "Spring Boot", "Reactive Spring")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    @DisplayName("Mono Test")
    public void monoTest(){
        Mono<String> stringMono = Mono.just("Spring");

        StepVerifier.create(stringMono.log())
                .expectNext("Spring")
                .verifyComplete();
    }

    @Test
    @DisplayName("Mono Test With Error")
    public void monoTest_WithError(){

        Mono<String> stringMono = Mono.error(new RuntimeException("Exception Occurred"));

        StepVerifier.create(stringMono.log())
                .expectError(RuntimeException.class)
                .verify();
    }

}
