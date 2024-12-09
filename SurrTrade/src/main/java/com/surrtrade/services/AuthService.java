package com.surrtrade.services;

import com.surrtrade.entities.User;

public interface AuthService {

	User register(User user);
	User getUserByUsername(String username);
	void toggleStatus(String username, String status);
	void logoutUser(String username);
	void updateLastLogin(String username);
}
