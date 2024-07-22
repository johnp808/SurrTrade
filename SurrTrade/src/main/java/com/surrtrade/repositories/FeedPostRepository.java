package com.surrtrade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surrtrade.entities.FeedPost;

public interface FeedPostRepository extends JpaRepository<FeedPost, Integer>{
	

}
