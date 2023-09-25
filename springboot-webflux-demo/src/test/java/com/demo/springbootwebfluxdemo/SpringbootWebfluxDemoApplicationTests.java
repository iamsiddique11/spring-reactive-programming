package com.demo.springbootwebfluxdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;


@SpringBootTest
class SpringbootWebfluxDemoApplicationTests {

	@Test
	void testMono() {
		Mono<String> monoString=Mono.just("ExampleString").log();
		monoString.subscribe(System.out::println);
	}
	
	@Test
	void testMonoError() {
		Mono<?> monoString=Mono.just("Example")
				.then(Mono.error(new RuntimeException("Exception thrown")))
				.log();
		monoString.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));
	}
	
	@Test
	void testFlux() {
		Flux<Integer> flux=Flux.just(1,2,3,4,5)
				.concatWithValues(10,12)
				.log()
				;
		flux.subscribe(System.out::println);
	}
	
	@Test
	void testFluxError() {
		Flux<Integer> flux=Flux.just(1,2,3,4,5)
				.concatWith(Flux.error(new RuntimeException(" Exception occured")))
				.concatWithValues(10,12)
				.log()
				;
		flux.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));
	}

}
