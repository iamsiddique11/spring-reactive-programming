package com.demo.springbootwebfluxdemo.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.demo.springbootwebfluxdemo.dto.Customer;

import reactor.core.publisher.Flux;

@Component
public class CustomerDao {
	
	private static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//As we have only 50 data we do not see much difference here hence lets add 1sec sleep and check how it works
	public List<Customer> getCustomers(){
		return IntStream.rangeClosed(1, 10)
				.peek(CustomerDao::sleepExecution) //perfect ex for synchronous and blocking
				.peek(i->System.out.println("Processing customer :" + i))
				.mapToObj(i->new Customer(i,"customer"+i))
		.collect(Collectors.toList());
	};

	public Flux<Customer> getCustomersStream(){
		return Flux.range(1, 10)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i->System.out.println("Processing customer Stream:" + i))
		.map(i->new Customer(i,"customer"+i));
	}

	public Flux<Customer> getCustomersListFlux(){
		return Flux.range(1, 5)
				.doOnNext(i->System.out.println("Processing customer Stream:" + i))
		.map(i->new Customer(i,"customer"+i));
	}
}
