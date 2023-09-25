package com.heap.springbootwebflux.heap;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MaxHeap {

	private int[] heap;
	private int size;
	private static int DEFAULT_CAPACITY = 10;

	public Mono<Void> createHeap(String input) {
		return Mono.fromRunnable(() -> {
			String[] values = input.split(",");
			System.out.println("INPUT :" + input);
			for (String i : values) {
				System.out.print("Element is :" + i + "   ");
			}
			
			// Expand the heap if needed
			if (values.length > DEFAULT_CAPACITY) {
				int[] newHeap = new int[values.length];
				DEFAULT_CAPACITY = values.length;
				System.arraycopy(heap, 0, newHeap, 0, heap.length);
				heap = newHeap;
			}

			// Populate the heap with values
			size = 0;
			for (String value : values) {
				try {
					heap[size] = Integer.parseInt(value.replaceAll("\"", "").trim());
					size++;
				} catch (Exception e) {
					System.out.println("exception thrown " + e.getMessage() + "for value: " + value);
				}
			}
			// Ensure the max-heap property is maintained
			for (int i = (size / 2) - 1; i >= 0; i--) {
				heapifyDown(i);
			}
		});
	}

	public Mono<Void> insertValue(int value) {
		return Mono.fromRunnable(() -> {
			if (size >= heap.length) {
				// Expand the heap if needed
				int[] newHeap = new int[heap.length * 2];
				System.arraycopy(heap, 0, newHeap, 0, heap.length);
				heap = newHeap;
			}

			// Add the new value to the end of the array
			int index = size;
			heap[index] = value;
			size++;

			// Restore the max-heap property
			heapifyUp(index);
		});
	}

	public Mono<Integer> extractMax() {
		return Mono.defer(() -> {
			if (size == 0) {
				return Mono.error(new IllegalStateException("Heap is empty"));
			}

			int max = heap[0];

			// Replace the root with the last element in the heap
			heap[0] = heap[size - 1];
			size--;

			// Restore the max-heap property
			heapifyDown(0);

			return Mono.just(max);
		});

	}

	// Other helper methods for heapifyUp, heapifyDown, etc.
	private void heapifyUp(int index) {
		int parent = (index - 1) / 2;
		while (index > 0 && heap[index] > heap[parent]) {
			// Swap the current node with its parent
			int temp = heap[index];
			heap[index] = heap[parent];
			heap[parent] = temp;

			// Move up the tree
			index = parent;
			parent = (index - 1) / 2;
		}
	}

	private void heapifyDown(int index) {
		int leftChild = 2 * index + 1;
		int rightChild = 2 * index + 2;
		int largest = index;

		if (leftChild < size && heap[leftChild] > heap[largest]) {
			largest = leftChild;
		}
		if (rightChild < size && heap[rightChild] > heap[largest]) {
			largest = rightChild;
		}

		if (largest != index) {
			// Swap the current node with the largest child
			int temp = heap[index];
			heap[index] = heap[largest];
			heap[largest] = temp;

			// Move down the tree
			heapifyDown(largest);
		}
	}

	public Mono<Integer> getMaxHeap() {
		if (size == 0) {
			return Mono.error(new IllegalStateException("Heap is empty"));
		}
		int max = heap[0];
		return Mono.just(max);
	}
	
	//Setters/Getter

	public int[] getHeap() {
		return heap;
	}

	public void setHeap(int[] heap) {
		this.heap = heap;
	}

	public MaxHeap() {
		this.heap = new int[DEFAULT_CAPACITY];
		this.size = 0;
	}
}
