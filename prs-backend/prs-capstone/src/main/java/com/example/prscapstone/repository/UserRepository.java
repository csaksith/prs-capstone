package com.example.prscapstone.repository;
import com.example.prscapstone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{
	User findByUsernameAndPassword(String username, String password);
}
