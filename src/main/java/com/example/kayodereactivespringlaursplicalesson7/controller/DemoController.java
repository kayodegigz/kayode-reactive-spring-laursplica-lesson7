package com.example.kayodereactivespringlaursplicalesson7.controller;

import org.springframework.http.MediaType;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by Kayode.Ogunrinde on 3/18/2023.
 */

@RestController
public class DemoController {

    @GetMapping(value = "/demo", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<String> demo() {
        ReactiveSecurityContextHolder
                .getContext()
                .map(sc -> sc.getAuthentication())
                .subscribe(System.out::println);
        return Mono.just("demo");
    }

    @GetMapping(value = "/hello", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<String> hello() {
        return Mono.just("hello");
    }
}
