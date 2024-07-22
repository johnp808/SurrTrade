package com.surrtrade.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surrtrade.entities.User;
import com.surrtrade.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class UserController {

	@Autowired
	private UserService userSvc;
	
	@GetMapping("users")
	public List<User> getAllUsers(HttpServletRequest req, HttpServletRequest res, Principal principal) {
		List<User> users = null;
		
		try {
			users = userSvc.findAllUsers();
		}
		catch (Exception exc){
			
		}
		return users;
	}
	
}
