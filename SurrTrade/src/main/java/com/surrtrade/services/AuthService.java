package com.surrtrade.services;

import com.surrtrade.entities.User;

public interface AuthService {

	User register(User user);

	User getUserByUsername(String username);
}
