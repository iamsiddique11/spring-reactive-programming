package com.demo.springbootwebfluxdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.springbootwebfluxdemo.dao.CustomerDao;
import com.demo.springbootwebfluxdemo.dto.Customer;

import reactor.core.publisher.Flux;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao dao;
	
	
	public List<Customer> loadAllCustomers(){
		long startTime = System.currentTimeMillis();
		List<Customer> customers = dao.getCustomers();
		long endTime = System.currentTimeMillis();
		System.out.println("Time Taken :" + (endTime-startTime));

		return customers;
	}
	
	public Flux<Customer> loadAllCustomersStream(){
		long startTime = System.currentTimeMillis();
		Flux<Customer> customers = dao.getCustomersStream();
		long endTime = System.currentTimeMillis();
		System.out.println("Time Taken :" + (endTime-startTime));

		return customers;
	}

}
