## Spring-Reactive-Programming
Contains a basic implementation of Spring reactive programming. And contains a maxHeap implementation using spring-reactive-programming.
# MaxHeap Spring Boot Application

This is a Spring Boot application that provides functionality for managing a Max Heap data structure. You can use this application to create a Max Heap, insert values, extract the maximum value, and retrieve the entire heap.

## Getting Started

These instructions will help you set up and run the MaxHeap application on your local machine.

### Prerequisites

- Java 8 or higher
- [Spring Boot](https://spring.io/projects/spring-boot) (version 2.x)
- [Maven](https://maven.apache.org/) for building the project

### Installation

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/iamsiddique11/spring-reactive-programming.git
   
2. To directly run the project on your local machine: 
	
	1) Download the JAR file included with this file.
	2) Navigate to the project directory where jar is located:
		```bash
		cd PATH
	3) Execute the stand-alone jar.
		```bash
		java -jar springboot-webflux-0.0.1-SNAPSHOT.jar
	## Running the application.
	4) The application will start on port 9900. 
	   You can access the endpoints via http://localhost:9900.
    
  ## API Endpoints
  # 1) Create Max Heap: Create a new Max Heap by providing a string of integers separated by commas.
  POST http://localhost:9900/maxheap/create
  # Example request body:
	"10, 8, 5, 4, 2, 1, 3" 
  # 2) Insert Value: Insert a new integer value into the Max Heap.
	POST http://localhost:9900/maxheap/insert/{value}
  #  3)Extract Max: Extract and return the maximum value from the Max Heap.
	GET http://localhost:9900/maxheap/extractMax
  # 4)Get Heap: Retrieve the entire Max Heap.
	GET http://localhost:9900/maxheap/getHeap
  # 5)Get Maximum Heap: Retrieve the maximum heap (same as the entire heap).
	GET http://localhost:9900/maxheap/getMaxHeap
### Usage Tool / Testing
You can use tools like Postman to test the API endpoints by sending HTTP requests to the application.
