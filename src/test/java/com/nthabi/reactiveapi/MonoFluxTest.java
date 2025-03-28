//package com.nthabi.reactiveapi;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@SpringBootTest
//public class MonoFluxTest {
//
//    @Test
//    public void testMono() {
//        Mono<String> one = Mono.just("test");
//        one.subscribe(System.out::println);
//        assert "test".equals(one.block());
//    }
//
//    @Test
//    public void testFlux() {
//        Flux<String> multiple = Flux.just("test", "test2", "test3");
//        multiple.subscribe(System.out::println);
//    }
//}
