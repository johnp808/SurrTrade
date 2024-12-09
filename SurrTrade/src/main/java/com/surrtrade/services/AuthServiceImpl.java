package com.surrtrade.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.surrtrade.entities.User;
import com.surrtrade.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User register(User user) {
		
		if (userRepo.findByUsername(user.getUsername()) != null) {
			throw new RuntimeException("Username already exists");
		}
		   
		String encryptedPassword = encoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		user.setCreatedAt(LocalDateTime.now());
		user.setRole("registered");
		user.setStatus("Online");
		user.setEnabled(true);
		user.setLastLogin(LocalDateTime.now());
		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	@Override
	public void updateLastLogin(String username) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			user.setLastLogin(LocalDateTime.now());
			userRepo.save(user);
		}
	}

	@Override
	public void toggleStatus(String username, String status) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			user.setStatus(status);
			userRepo.save(user);
		}
	}
	
	@Override
	public void logoutUser(String username) {
		toggleStatus(username, "Offline");
	}
}