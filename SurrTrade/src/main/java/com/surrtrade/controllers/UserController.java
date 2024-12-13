package com.surrtrade.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surrtrade.dto.ChangePassDTO;
import com.surrtrade.dto.UserDTO;
import com.surrtrade.entities.User;
import com.surrtrade.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/users")
@CrossOrigin({ "*", "http://localhost/" })
public class UserController {

	@Autowired
	private UserService userSvc;

	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers(HttpServletRequest req, HttpServletRequest res, Principal principal) {
		List<UserDTO> users = null;

		try {
			users = userSvc.findAllUsers();
			return new ResponseEntity<>(users, HttpStatus.OK);
		} 
		catch (Exception exc) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("{username}")
	public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username, Principal principal) {
		
		if (principal == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			
		}
		
		User foundUser = userSvc.findByUsername(username);
		if (foundUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		UserDTO userDTO = userSvc.convertToUserDTO(foundUser);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@GetMapping("profile")
	public ResponseEntity<UserDTO> getUser(Principal principal) {
		
		if (principal == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		}
		
		System.out.println("Principal: " + principal.getName());
		User authUser = userSvc.findByUsername(principal.getName());
		
		if( authUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
		
		UserDTO userDTO = userSvc.convertToUserDTO(authUser);
		
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	@PutMapping("profile/update/bio/{id}")
	public ResponseEntity<UserDTO> updateUser(HttpServletRequest req, HttpServletResponse res, @RequestBody UserDTO userDTO, @PathVariable("id") int id, Principal principal) {
		
		if (principal == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		}

		User authUser = userSvc.findByUsername(principal.getName());
		
		if( authUser == null || authUser.getId() != id) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
		
		UserDTO updatedUser = userSvc.update(userDTO, id);

		if (updatedUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	@PutMapping("profile/update/password/{id}")
	public ResponseEntity<Boolean> changeUserPassword(HttpServletRequest req, HttpServletResponse res, @PathVariable("id") int id, @RequestBody ChangePassDTO changePass, Principal principal) {
		
		boolean changePassBool = false;
		
		if (principal == null) {
			return new ResponseEntity<>(changePassBool, HttpStatus.UNAUTHORIZED);

		}
		
		User authUser = userSvc.findByUsername(principal.getName());
		
		if( authUser == null || authUser.getId() != id ) {
			return new ResponseEntity<>(changePassBool, HttpStatus.NOT_FOUND);

		}
		
		changePassBool = userSvc.changePassword(id, changePass);
		
		if(changePassBool) {
			
			return new ResponseEntity<>(changePassBool, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(changePassBool, HttpStatus.NOT_FOUND);
	}
		
	@DeleteMapping("profile/delete/{id}")
	public ResponseEntity<Boolean> deleteUser(HttpServletRequest req, HttpServletResponse res, @PathVariable("id") int id, Principal principal) {
		
		boolean userDeleted = false;
		
		if (principal == null) {
			return new ResponseEntity<>(userDeleted, HttpStatus.UNAUTHORIZED);

		}
		
		User authUser = userSvc.findByUsername(principal.getName());
		
		if(authUser.getRole().equalsIgnoreCase("admin")) {
			userDeleted = userSvc.deleteUserById(id);
			return new ResponseEntity<>(userDeleted, HttpStatus.OK);
		}

		return new ResponseEntity<>(userDeleted,HttpStatus.UNAUTHORIZED);
	}
	
	@PutMapping("profile/toggleuser/{id}")
	public ResponseEntity<Boolean> toggleUser(HttpServletRequest req, HttpServletResponse res, @PathVariable("id") int id, Principal principal) {
		
		boolean enableDisabledUser = false;
		
		if (principal == null) {
			return new ResponseEntity<>(enableDisabledUser, HttpStatus.UNAUTHORIZED);
		}
		
		User userToToggle = userSvc.getUserById(id);
		
		if (userToToggle == null) {
			return new ResponseEntity<>(enableDisabledUser, HttpStatus.NOT_FOUND);
		}
		
		User authUser = userSvc.findByUsername(principal.getName());
		
		if (authUser == null || (authUser.getId() != id && !authUser.getRole().equalsIgnoreCase("admin"))) {
			return new ResponseEntity<>(enableDisabledUser, HttpStatus.UNAUTHORIZED);

		}
		
		enableDisabledUser = userSvc.enabledDisableUser(id);
		return new ResponseEntity<>(enableDisabledUser, HttpStatus.OK);
	}
}
