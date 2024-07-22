package com.surrtrade.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surrtrade.entities.User;
import com.surrtrade.services.AuthService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class AuthController {
 
  @Autowired
  private AuthService authSvc;
  
  @PostMapping("register")
	public User register(@RequestBody User user,HttpServletResponse res) {

	    if (user == null) {
	        res.setStatus(400);
	    }
	    user = authSvc.register(user);

	    return user;
	}

	@GetMapping("authenticate")
	public User authenticate(Principal principal, HttpServletResponse res) {
		if (principal == null) { // no Authorization header sent
			res.setStatus(401);
			res.setHeader("WWW-Authenticate", "Basic");
			
		}
		
		return authSvc.getUserByUsername(principal.getName());
	}

}