package com.demo.springbootwebfluxdemo.router;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.demo.springbootwebfluxdemo.dto.Customer;
import com.demo.springbootwebfluxdemo.handler.CustomerHandler;
import com.demo.springbootwebfluxdemo.handler.StreamHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Configuration
public class RouterConfig {

	@Autowired
	private CustomerHandler customerHandler;

	@Autowired
	private StreamHandler streamHandler;

	@Bean
	@RouterOperations({ @RouterOperation(path = "/router/customers", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET, beanClass = CustomerHandler.class, beanMethod = "loadAllCustomersFlux", operation = @Operation(operationId = "loadAllCustomersFlux", responses = {
					@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Customer.class))) })),
			@RouterOperation(path = "/router/customer/{input}", produces = {
					MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET, beanClass = CustomerHandler.class, beanMethod = "findCustomer", operation = @Operation(operationId = "findCustomer", responses = {
							@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Customer.class))),
							@ApiResponse(responseCode = "404", description = "customer not found with given id") }, parameters = {
									@Parameter(in = ParameterIn.PATH, name = "input") })),
			@RouterOperation(path = "/router/customer/save", produces = {
					MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST, beanClass = CustomerHandler.class, beanMethod = "saveCustomer", operation = @Operation(operationId = "saveCustomer", responses = {
							@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))) }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Customer.class))))),
			@RouterOperation(path = "/router/customers/stream", produces = {
					MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET, beanClass = StreamHandler.class, beanMethod = "loadAllCustomersStream", operation = @Operation(operationId = "loadAllCustomersStream", responses = {
							@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Customer.class))) })),

	})
	public RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions.route().GET("/router/customers", customerHandler::loadAllCustomersFlux)
				.GET("/router/customers/stream", streamHandler::loadAllCustomersStream)
				.GET("/router/customer/{input}", customerHandler::findCustomer)
				.POST("/router/customer/save", customerHandler::saveCustomer).build();
		// .GET("/maxheap/loadHeap", accept(MediaType.APPLICATION_JSON),
		// handler::loadHeap)

	}
}
