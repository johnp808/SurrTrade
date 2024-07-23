package com.surrtrade.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surrtrade.entities.User;
import com.surrtrade.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class UserController {

	@Autowired
	private UserService userSvc;

	@GetMapping("users")
	public List<User> getAllUsers(HttpServletRequest req, HttpServletRequest res, Principal principal) {
		List<User> users = null;

		try {
			users = userSvc.findAllUsers();
		} catch (Exception exc) {

		}
		return users;
	}

	@GetMapping("users/{id}")
	public ResponseEntity<User> getUser(HttpServletRequest req, HttpServletResponse res, @PathVariable("id") int id,
			Principal principal) {
		if (principal == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		}

		User user = userSvc.showUser(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PutMapping("users/{id}")
	public ResponseEntity<User> updateUser(HttpServletRequest req, HttpServletResponse res, @RequestBody User user,
			@PathVariable("id") int id, Principal principal) {
		if (principal == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		}

		User updatedUser = userSvc.update(user, id);

		if (updatedUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@PutMapping("users/toggleuser/{id}")
	public ResponseEntity<Boolean> toggleUser(HttpServletRequest req, HttpServletResponse res, @PathVariable("id") int id, Principal principal) {

		if (principal == null) {
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
		}
		User userToToggle = userSvc.showUser(id);
		if (userToToggle == null) {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
		User authUser = userSvc.findByUsername(principal.getName());
		
		if (authUser == null || authUser.getId() != id && !authUser.getRole().equalsIgnoreCase("admin")) {
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);

		}
		
		boolean enableDisabledUser = userSvc.enabledDisabledUser(id);
		return new ResponseEntity<>(enableDisabledUser, HttpStatus.OK);
	}
}
