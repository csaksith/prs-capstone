package com.example.prscapstone.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.prscapstone.model.Vendor;
import com.example.prscapstone.repository.VendorRepository;
@RestController
@RequestMapping("/api/vendors")
@CrossOrigin(origins = "*")
public class VendorController {
	@Autowired
	private VendorRepository vendorRepository;
	
	@GetMapping
	public List<Vendor> getAllVendors() {
		return vendorRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Vendor> getVendorById(@PathVariable int id) {
		return vendorRepository.findById(id);
	}
	
	@PostMapping
	public Vendor createVendor(@RequestBody Vendor vendor) {
		return vendorRepository.save(vendor);
	}

	@PutMapping("/{id}")
	public Vendor updateVendor(@PathVariable int id, @RequestBody Vendor vendor) {
		vendor.setId(id);
		return vendorRepository.save(vendor);
	}
	
	@DeleteMapping("/{id}")
	public void deleteVendor(@PathVariable int id) {
		vendorRepository.deleteById(id);
	}
}
