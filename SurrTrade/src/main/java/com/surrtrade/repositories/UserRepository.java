package com.surrtrade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surrtrade.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);
}
