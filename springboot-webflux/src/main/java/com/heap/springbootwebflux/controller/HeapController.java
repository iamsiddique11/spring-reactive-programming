package com.heap.springbootwebflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heap.springbootwebflux.heap.MaxHeap;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/maxheap")
public class HeapController {

	private final MaxHeap maxHeap;

	@Autowired
	public HeapController(MaxHeap maxHeap) {
		this.maxHeap = maxHeap;
	}

	@PostMapping("/create")
	public Mono<Void> createHeap(@RequestBody String input) {
		return maxHeap.createHeap(input);
	}

	@PostMapping("/insert/{value}")
	public Mono<Void> insertValue(@PathVariable int value) {
		return maxHeap.insertValue(value);
	}

	@GetMapping("/extractMax")
	public Mono<Integer> extractMax() {
		return maxHeap.extractMax();
	}

	@GetMapping("/getHeap")
	public Flux<Integer> getHeap() {
		int[] heapArray = maxHeap.getHeap();
		Integer[] integerHeapArray = new Integer[heapArray.length];
		for (int i = 0; i < heapArray.length; i++) {
			integerHeapArray[i] = heapArray[i];
		}
		return Flux.fromArray(integerHeapArray);
	}

	@GetMapping("/getMaxHeap")
	public Mono<Integer> getMaxHeap() {
		return maxHeap.getMaxHeap();
	}
}
