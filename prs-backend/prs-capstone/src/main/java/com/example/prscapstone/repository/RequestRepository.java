package com.example.prscapstone.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prscapstone.model.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {
	long countBySubmittedDate(LocalDate submittedDate);
}
