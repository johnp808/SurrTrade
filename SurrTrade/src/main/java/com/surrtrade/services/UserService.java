package com.surrtrade.services;

import java.util.List;

import com.surrtrade.entities.User;

public interface UserService {

	List<User> findAllUsers();
	User findByUsername(String username);
	User findById(String username, int id);
	User update(User user, int id);
	boolean deleteUserById(int id);
	boolean enabledDisabledUser(int userId);
	
}
