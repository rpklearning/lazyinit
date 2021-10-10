package com.example.lazyinit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LazyinitApplication {

	public static void main(String[] args) {
		SpringApplication.run(LazyinitApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "Raj") String name) {
		System.out.println("Servicing the request....");
		return String.format("Hello %s!", name);
	}
}
