package com.surrtrade.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surrtrade.entities.User;
import com.surrtrade.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;

	   public UserServiceImpl(UserRepository userRepository) {
	        this.userRepo = userRepository;
	    }

	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public User findById(String username, int id) {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public User update(User user, int id) {
		return null;
	}
	
	@Override
	public boolean enabledDisabledUser(int userId) {
		Optional<User> user = userRepo.findById(userId);
	// if the users enabled is false, enable it, otherwise return false
		if(user.isPresent()) {
			User u = user.get();
			u.setEnabled(!u.isEnabled());
			userRepo.save(u);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deleteUserById(int id) {
		if (userRepo.existsById(id)) {
	            userRepo.deleteById(id);
	            return true;
	        }
	        return false;
	    }
}
