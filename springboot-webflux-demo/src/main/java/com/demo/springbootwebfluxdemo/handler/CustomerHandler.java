package com.demo.springbootwebfluxdemo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.demo.springbootwebfluxdemo.dao.CustomerDao;
import com.demo.springbootwebfluxdemo.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {
	
	@Autowired
	private CustomerDao customerDao;
	
	public Mono<ServerResponse> loadAllCustomersFlux(ServerRequest request){
		Flux<Customer> customerList = customerDao.getCustomersListFlux();
		return ServerResponse.ok().body(customerList,Customer.class);
	}
	
	public Mono<ServerResponse> findCustomer(ServerRequest request){
	      int customerId= Integer.valueOf( request.pathVariable("input"));
	       // dao.getCustomerList().filter(c->c.getId()==customerId).take(1).single();
	        Mono<Customer> customerMono = customerDao.getCustomersListFlux().filter(c -> c.getCustomerId() == customerId).next();
	        return ServerResponse.ok().body(customerMono,Customer.class);
	    }

	    public Mono<ServerResponse> saveCustomer(ServerRequest request){
	        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
	        Mono<String> saveResponse = customerMono.map(x -> x.getCustomerId() + ":" + x.getCustomerName());
	        return ServerResponse.ok().body(saveResponse,String.class);
	    }

	
}
