package com.surrtrade.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surrtrade.entities.FeedPost;
import com.surrtrade.entities.User;
import com.surrtrade.repositories.FeedPostRepository;
import com.surrtrade.repositories.UserRepository;

@Service
public class FeedPostServiceImpl implements FeedPostService{

	@Autowired
	private FeedPostRepository postRepo;
	
	@Autowired
    private UserRepository userRepo;
	
	@Override
	public List<FeedPost> getAllFeedPosts() {

		return postRepo.findAll();
	}

	@Override
	public FeedPost createFeedPost(FeedPost feedPost, int id) {

		User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));;
		
		feedPost.setUser(user);
		feedPost.setCreatedAt(LocalDateTime.now());
		return postRepo.saveAndFlush(feedPost);
	}

	@Override
	public FeedPost getFeedPostById(int id) {
		return null;
	}

	@Override
	public FeedPost updateFeedPost(int id, FeedPost feedPost) {
		return null;
	}

	@Override
	public boolean deleteFeedPostById(int id) {
		return false;
	}

}
