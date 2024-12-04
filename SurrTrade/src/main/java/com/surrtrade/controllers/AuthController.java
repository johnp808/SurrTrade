package com.surrtrade.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surrtrade.dto.UserDTO;
import com.surrtrade.entities.User;
import com.surrtrade.services.AuthService;
import com.surrtrade.services.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class AuthController {
 
  @Autowired
  private AuthService authSvc;
  
  @Autowired
  private UserService userSvc;
  
  @PostMapping("register")
	public ResponseEntity<UserDTO> register(@Valid @RequestBody User user, BindingResult result) {

	    if (result.hasErrors()) {
	    	StringBuilder errorMessage = new StringBuilder("Validation Failed: ");
	    	result.getFieldErrors().forEach(error ->
	    		errorMessage.append(error.getField())
	    		.append(" - ")
	    		.append(error.getDefaultMessage())
	    		.append("; ")
	    			);
	    	throw new RuntimeException(errorMessage.toString());
	    }
	    try {
	    	User registeredUser = authSvc.register(user);
	    	UserDTO registeredDTO = userSvc.convertToUserDTO(registeredUser);
	    	return ResponseEntity.ok(registeredDTO);
	    }
	    catch(RuntimeException e) {
	    	throw new RuntimeException("Username or Email Already Exists");
	    }
	}

	@GetMapping("authenticate")
	public ResponseEntity<UserDTO> authenticate(Principal principal, HttpServletResponse res) {
		if (principal == null) { // no Authorization header sent
			res.setStatus(401);
			res.setHeader("WWW-Authenticate", "Basic");
	        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	    
		User user = authSvc.getUserByUsername(principal.getName());
	    if(user == null) {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
		
	    UserDTO userDTO = userSvc.convertToUserDTO(user);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@GetMapping("checkusername/{username}")
	public ResponseEntity<Boolean> checkUsername(@PathVariable("username") String username) {
		boolean usernameExists = userSvc.findByUsername(username) != null;
		return ResponseEntity.ok(usernameExists);
	}
}