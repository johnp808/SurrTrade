package com.surrtrade.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.surrtrade.entities.User;
import com.surrtrade.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	private PasswordEncoder encoder;
	private UserRepository userRepo;

	public AuthServiceImpl(PasswordEncoder encoder, UserRepository userRepo) {
		super();
		this.encoder = encoder;
		this.userRepo = userRepo;
	}

	@Override
	public User register(User user) {
		if (userRepo.findByUsername(user.getUsername()) != null) {
			throw new RuntimeException("Username already exists");
			}
		   
		String encryptedPassword = encoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		user.setRole("registered");
		user.setEnabled(true);
		userRepo.saveAndFlush(user);
		return user;

	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}