package com.demo.springbootwebfluxdemo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.demo.springbootwebfluxdemo.dao.CustomerDao;
import com.demo.springbootwebfluxdemo.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StreamHandler {
	
	@Autowired
	private CustomerDao customerDao;
	
	public Mono<ServerResponse> loadAllCustomersStream(ServerRequest request){
		Flux<Customer> customerList = customerDao.getCustomersStream();
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(customerList,Customer.class);
	}


}
