package com.example.prscapstone.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.prscapstone.model.Product;
import com.example.prscapstone.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Product> getProductById(@PathVariable int id) {
		return productRepository.findById(id);
	}

	@PostMapping
	public Product createProdxuct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
		product.setId(id);
		return productRepository.save(product);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable int id) {
		productRepository.deleteById(id);
	}
}
