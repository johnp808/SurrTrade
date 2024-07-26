package com.surrtrade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surrtrade.dto.UserDTO;
import com.surrtrade.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	UserDTO findByUsername(String username);
}
