package com.example.prscapstone.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.prscapstone.model.Request;
import com.example.prscapstone.repository.RequestRepository;
import com.example.prscapstone.repository.UserRepository;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins = "*")
public class RequestController {
	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public List<Request> getAllRequests() {
		return requestRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Request> getRequestById(@PathVariable int id) {
		return requestRepository.findById(id);
	}

	@PostMapping
	public Request createRequest(@RequestBody Request request) {
		return requestRepository.save(request);
	}

	@PutMapping("/{id}")
	public Request updateRequest(@PathVariable int id, @RequestBody Request request) {
		request.setId(id);
		return requestRepository.save(request);
	}

	@DeleteMapping("/{id}")
	public void deleteRequest(@PathVariable int id) {
		requestRepository.deleteById(id);
	}
}
