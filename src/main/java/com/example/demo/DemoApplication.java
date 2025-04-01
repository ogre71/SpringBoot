package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/api/items") // Correct URL mapping
    public String getItems() {
        return "Items";
    }

    @GetMapping("/api/products/{productId}") // Correct use of path variable
    public String getProduct(@PathVariable String productId) {
        return "Product: " + productId;
    }
}
