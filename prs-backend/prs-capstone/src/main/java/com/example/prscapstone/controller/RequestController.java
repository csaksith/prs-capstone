package com.example.prscapstone.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.prscapstone.model.Request;
import com.example.prscapstone.model.RequestDTO;
import com.example.prscapstone.model.User;
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

	public String getRequestNumber() {
		String datePart = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
		long countForToday = requestRepository.countBySubmittedDate(java.time.LocalDate.now());
		String sequence = String.format("%03d", countForToday + 1);
		return "R" + datePart + sequence;
	}
	
	@PostMapping
	public Request createRequest(@RequestBody RequestDTO requestDTO) {
		Optional<User> userOptional = userRepository.findById(requestDTO.getUserId());
		if (userOptional.isEmpty()) {
			throw new RuntimeException("User not found with ID: " + requestDTO.getUserId());
		}
		Request request = new Request();
		request.setUser(userOptional.get());
		request.setDescription(requestDTO.getDescription());
		request.setJustification(requestDTO.getJustification());
		request.setDateNeeded(requestDTO.getDateNeeded());
		request.setDeliveryMode(requestDTO.getDeliveryMode());
	
		request.setRequestNumber(getRequestNumber());
		request.setSubmittedDate(LocalDate.now());
		request.setStatus("NEW");
		request.setTotal(0.0);
	
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
