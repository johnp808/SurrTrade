package com.surrtrade.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.surrtrade.dto.ChangePassDTO;
import com.surrtrade.dto.UserDTO;
import com.surrtrade.entities.User;
import com.surrtrade.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder encoder;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepo = userRepository;
	}

	@Override
	public List<UserDTO> findAllUsers() {

		List<User> users = userRepo.findAll();
		List<UserDTO> usersDTO = new ArrayList<>();

		for (User user : users) {
			usersDTO.add(convertToUserDTO(user));
		}

		return usersDTO;
	}

	@Override
	public User findByUsername(String username) {
		
		return userRepo.findByUsername(username);
	}

	@Override
	public User getUserById(int id) {
		
		Optional<User> existingUserOpt = userRepo.findById(id);
		
		return existingUserOpt.orElse(null);
	}

	@Override
	public UserDTO getUserDTOById(int id) {

		Optional<User> existingUserOpt = userRepo.findById(id);

		if (existingUserOpt.isPresent()) {
			return convertToUserDTO(existingUserOpt.get());
		}
		
		return null;
	}

	@Override
	public UserDTO update(UserDTO userDTO, int id) {
		
		Optional<User> existingUserOpt = userRepo.findById(id);

		if (existingUserOpt.isPresent()) {
			User existingUser = existingUserOpt.get();

			if (userDTO.getUsername() != null) {
				existingUser.setUsername(userDTO.getUsername());
			}

			if (userDTO.getEmail() != null) {
				existingUser.setEmail(userDTO.getEmail());
			}

			if (userDTO.getPrimaryBike() != null) {
				existingUser.setPrimaryBike(userDTO.getPrimaryBike());
			}

			if (userDTO.getStatus() != null) {
				existingUser.setStatus(userDTO.getStatus());
			}

			if (userDTO.getRole() != null) {
				existingUser.setRole(userDTO.getRole());
			}

			existingUser.setUpdatedAt(LocalDateTime.now());

			if (userDTO.getLastLogin() != null) {
				existingUser.setLastLogin(userDTO.getLastLogin());
			}

			if (userDTO.getBikePicture() != null) {
				existingUser.setBikePicture(userDTO.getBikePicture());
			}

			if (userDTO.getUserPicture() != null) {
				existingUser.setUserPicture(userDTO.getUserPicture());
			}

			existingUser.setEnabled(userDTO.isEnabled());

			User updatedUser = userRepo.saveAndFlush(existingUser);

			return convertToUserDTO(updatedUser);
		}

		return null;
	}

	@Override
	public boolean enabledDisableUser(int id) {
		
		Optional<User> foundUserOpt = userRepo.findById(id);
		
		if (foundUserOpt.isPresent()) {
			User user = foundUserOpt.get();
			user.setEnabled(!user.isEnabled());
			userRepo.save(user);
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

	@Override
	public boolean changePassword(int id, ChangePassDTO changePass) {
		
		Optional<User> foundUserOpt = userRepo.findById(id);

		if (foundUserOpt.isPresent()) {
			User user = foundUserOpt.get();
			if (encoder.matches(changePass.getOldPass(), user.getPassword()))
				user.setPassword(encoder.encode(changePass.getNewPass()));
			userRepo.saveAndFlush(user);
			return true;
		}

		return false;
	}

	@Override
	public UserDTO convertToUserDTO(User user) {

		if (user == null) {
			return null;
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		userDTO.setEmail(user.getEmail());
		userDTO.setPrimaryBike(user.getPrimaryBike());
		userDTO.setStatus(user.getStatus());
		userDTO.setRole(user.getRole());
		userDTO.setCreatedAt(user.getCreatedAt());
		userDTO.setUpdatedAt(user.getUpdatedAt());
		userDTO.setLastLogin(user.getLastLogin());
		userDTO.setBikePicture(user.getBikePicture());
		userDTO.setUserPicture(user.getUserPicture());
		userDTO.setEnabled(user.isEnabled());

		return userDTO;
	}
}
