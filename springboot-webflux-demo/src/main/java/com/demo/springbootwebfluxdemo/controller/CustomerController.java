package com.demo.springbootwebfluxdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springbootwebfluxdemo.dto.Customer;
import com.demo.springbootwebfluxdemo.service.CustomerService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	//Using the cancel midway doesnt cancels the processing
	@GetMapping()
	public List<Customer> getAllcustomers(){
		return customerService.loadAllCustomers();
	}
	
	//This still acts as synchronous n blocking but with less time yet to achieve Flux we need to produce event.
	@GetMapping("/stream")
	public Flux<Customer> getAllcustomersStream(){
		return customerService.loadAllCustomersStream();
	}
	
	//Using the cancel operation cancels the processing using Stream
	@GetMapping(value="/Fluxstream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Customer> getAllcustomersStream1(){
		return customerService.loadAllCustomersStream();
	}
}
