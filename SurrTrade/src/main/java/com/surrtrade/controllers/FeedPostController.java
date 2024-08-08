package com.surrtrade.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surrtrade.entities.FeedPost;
import com.surrtrade.services.FeedPostService;
import com.surrtrade.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/feedposts")
@CrossOrigin({ "*", "http://localhost/" })
public class FeedPostController {

	@Autowired
	private FeedPostService postSvc;
	
	@Autowired
	private UserService userSvc;
	
	@GetMapping
	public ResponseEntity<List<FeedPost>> getAllFeedPosts(Principal principal, HttpServletResponse res, HttpServletRequest req) {
		List<FeedPost> posts = null;
		
		try {
			posts = postSvc.getAllFeedPosts();
			return new ResponseEntity<>(posts, HttpStatus.OK);
		}
		catch(Exception exc) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("createPost")
	public FeedPost createFeedPost(Principal principal, HttpServletResponse res, HttpServletRequest req, @PathVariable("id") int id) {
		
		if(principal == null) {
			
		}
		return null;
	}
}
