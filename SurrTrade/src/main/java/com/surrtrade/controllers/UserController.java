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
		} 
		
		catch (Exception exc) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("search/{username}")
	public ResponseEntity<UserDTO> getUserByUsername(HttpServletRequest req, HttpServletResponse res, @PathVariable("username") String username, Principal principal) {
		
		if (principal == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			
		}
		
		UserDTO user = userSvc.findByUsername(username);
		
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("profile/{id}")
	public ResponseEntity<UserDTO> getUser(HttpServletRequest req, HttpServletResponse res, @PathVariable("id") int id, Principal principal) {
		
		if (principal == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		}

		UserDTO userDTO = userSvc.getUserDTOById(id);
		
		if (userDTO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
		
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	@PutMapping("profile/update/{id}")
	public ResponseEntity<UserDTO> updateUser(HttpServletRequest req, HttpServletResponse res, @RequestBody UserDTO userDTO, @PathVariable("id") int id, Principal principal) {
		
		if (principal == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		}

		UserDTO authUser = userSvc.findByUsername(principal.getName());
		
		if( authUser == null || authUser.getId() != id) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
		
		UserDTO updatedUser = userSvc.update(userDTO, id);

		if (updatedUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	@PutMapping("profile/changepassword/{id}")
	public ResponseEntity<Boolean> changeUserPassword(HttpServletRequest req, HttpServletResponse res, @PathVariable("id") int id, @RequestBody ChangePassDTO changePass, Principal principal) {
		
		if (principal == null) {
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);

		}
		
		UserDTO authUser = userSvc.findByUsername(principal.getName());
		if( authUser == null || authUser.getId() != id ) {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);

		}
		
		boolean changePassBool = userSvc.changePassword(id, changePass);
		
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
		
		UserDTO authUser = userSvc.findByUsername(principal.getName());
		
		if(authUser.getRole().equalsIgnoreCase("admin")) {
			userDeleted = userSvc.deleteUserById(id);
			return new ResponseEntity<>(userDeleted, HttpStatus.OK);
		}

		return new ResponseEntity<>(userDeleted,HttpStatus.UNAUTHORIZED);
	}
	
	@PutMapping("profile/toggleuser/{id}")
	public ResponseEntity<Boolean> toggleUser(HttpServletRequest req, HttpServletResponse res, @PathVariable("id") int id, Principal principal) {
		
		if (principal == null) {
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
		}
		
		User userToToggle = userSvc.getUserById(id);
		
		if (userToToggle == null) {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
		
		UserDTO authUser = userSvc.findByUsername(principal.getName());
		
		if (authUser == null || authUser.getId() != id && !authUser.getRole().equalsIgnoreCase("admin")) {
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);

		}
		
		boolean enableDisabledUser = userSvc.enabledDisableUser(id);
		return new ResponseEntity<>(enableDisabledUser, HttpStatus.OK);
	}
}
