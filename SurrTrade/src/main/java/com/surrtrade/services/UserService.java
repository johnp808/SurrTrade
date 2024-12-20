package com.surrtrade.services;

import java.util.List;
import java.util.Optional;

import com.surrtrade.dto.ChangePassDTO;
import com.surrtrade.dto.UserDTO;
import com.surrtrade.entities.User;

public interface UserService {

	List<UserDTO> findAllUsers();
	User findByUsername(String username);
	UserDTO getUserDTOById(int id);
	UserDTO convertToUserDTO(User user);
	User getUserById(int id);
	UserDTO update(UserDTO userDTO, int id);
	boolean deleteUserById(int id);
	boolean enabledDisableUser(int userId);
	boolean changePassword(int id, ChangePassDTO changePass);
	User findByEmail(String email);
}
