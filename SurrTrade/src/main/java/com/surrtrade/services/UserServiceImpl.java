package com.surrtrade.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surrtrade.entities.User;
import com.surrtrade.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

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
	public User showUser(int id) {
		Optional<User> existingUserOpt = userRepo.findById(id);
		return existingUserOpt.orElse(null);
	}

	@Override
	public User update(User user, int id) {
		Optional<User> existingUserOpt = userRepo.findById(id);
		
		if (existingUserOpt.isPresent()) {
			User existingUser = existingUserOpt.get();
			
			if (user.getUsername() != null) {
				existingUser.setUsername(user.getUsername());
			}
			
			if (user.getEmail() != null) {
				existingUser.setEmail(user.getEmail());
			}
			
			if (user.getPassword() != null) {
				existingUser.setPassword(user.getPassword());
			}
			
			if (user.getPrimaryBike() != null) {
				existingUser.setPrimaryBike(user.getPrimaryBike());
			}
			
			if (user.getStatus() != null) {
				existingUser.setStatus(user.getStatus());
			}
			
			if (user.getRole() != null) {
				existingUser.setRole(user.getRole());
			}
			
			existingUser.setUpdatedAt(LocalDateTime.now());
			
			if (user.getLastLogin() != null) {
				existingUser.setLastLogin(user.getLastLogin());
			}
			
			
			if (user.getBikePicture() != null) {
				existingUser.setBikePicture(user.getBikePicture());
			}

			
			if (user.getUserPicture() != null) {
				existingUser.setUserPicture(user.getUserPicture());
			}
			
			existingUser.setEnabled(user.isEnabled());
			
			return userRepo.saveAndFlush(existingUser);
		}
		return null;
	}

	@Override
	public boolean enabledDisabledUser(int userId) {
		Optional<User> foundUserOpt = userRepo.findById(userId);
		if (foundUserOpt.isPresent()) {
			User user = foundUserOpt.get();
			user.setEnabled(!user.isEnabled());
			userRepo.save(user);
			return true;
		}
		return false;
	}

//	@Override
//	public boolean deleteUserById(int id) {
//		if (userRepo.existsById(id)) {
//	            userRepo.deleteById(id);
//	            return true;
//	        }
//	        return false;
//	    }
}
