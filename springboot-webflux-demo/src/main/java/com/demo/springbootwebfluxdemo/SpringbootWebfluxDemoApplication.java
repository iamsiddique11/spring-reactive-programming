package com.demo.springbootwebfluxdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info =@Info(
		title="Spring webflux crud example"
		))
public class SpringbootWebfluxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebfluxDemoApplication.class, args);
	}
}
