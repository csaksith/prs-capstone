package com.example.prscapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prscapstone.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{
}
