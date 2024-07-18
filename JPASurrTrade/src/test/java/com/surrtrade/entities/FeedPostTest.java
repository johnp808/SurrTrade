package com.surrtrade.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class FeedPostTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private FeedPost feedPost;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPASurrTrade");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		feedPost = em.find(FeedPost.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		feedPost = null;
	}

	@Test
	void test_feedPost_feedPostContent() {
		assertNotNull(feedPost);
		assertEquals("Ride Around Magic Island Ala Moana", feedPost.getFeedcontent());
	}
	@Test
	void test_many_to_one_users_feedPost() {
		assertNotNull(feedPost);
		assertEquals("john@john.com", feedPost.getUser().getEmail());
	}
	
	@Test
	void test_feedPost_updatedAt() {
		assertNotNull(feedPost);
		assertEquals(2024, feedPost.getUpdatedAt().getYear());
		assertEquals(7, feedPost.getUpdatedAt().getMonth().getValue());
		assertEquals("MONDAY", feedPost.getUpdatedAt().getDayOfWeek().name());
	}
	
	@Test
	void test_feedPost_one_to_many_post_to_comments() {
		assertNotNull(feedPost);
		assertEquals("Hey I can Join!", feedPost.getPostComments().iterator().next().getCommentContent());
	}
	
	@Test
	void test_feedPost_one_to_many_post_to_post_pictures() {
		assertNotNull(feedPost);
		assertEquals("www.examplepic.com", feedPost.getFeedPostPics().iterator().next().getPictureUrl());
	}
	
	@Test
	void test_feedPost_one_to_many_post_to_post_likes() {
		assertNotNull(feedPost);
		assertEquals(7, feedPost.getFeedPostLikes().iterator().next().getLikedAt().getMonthValue());
		assertEquals(15, feedPost.getFeedPostLikes().iterator().next().getLikedAt().getDayOfMonth());
		assertEquals(2024, feedPost.getFeedPostLikes().iterator().next().getLikedAt().getYear());
	}
}
