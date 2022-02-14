package com.learnreactivespring.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SampleHandlerFunction {

    /***
     * @url http://localhost:8080/functional/flux
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> returnFlux(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        Flux.just(1,2,3,4)
                        .log(), Integer.class
                );
    }


    /***
     * @url http://localhost:8080/functional/mono
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> returnMono(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        Mono.just(1)
                                .log(), Integer.class
                );
    }

}