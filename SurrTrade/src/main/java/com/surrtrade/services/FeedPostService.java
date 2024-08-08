package com.surrtrade.services;

import java.util.List;

import com.surrtrade.entities.FeedPost;

public interface FeedPostService {
	List<FeedPost> getAllFeedPosts();
	FeedPost createFeedPost(FeedPost feedPost, int id);
	FeedPost getFeedPostById(int id);
	FeedPost updateFeedPost(int id, FeedPost feedPost);
	boolean deleteFeedPostById(int id);
}
