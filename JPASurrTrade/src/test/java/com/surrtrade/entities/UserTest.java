package com.surrtrade.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UserTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test_username() {
		assertNotNull(user);
		assertEquals("John", user.getUsername());
	}
	
	@Test
	void test_password() {
		assertNotNull(user);
		assertEquals("password", user.getPassword());
	}
	
	@Test
	void test_status() {
		assertNotNull(user);
		assertEquals("online", user.getStatus());
	}
	
	@Test
	void test_one_to_many_user_comments() {
		assertNotNull(user);
		assertEquals(1, user.getComments().size());
		Comment firstComment = user.getComments().iterator().next();
		assertNotNull(firstComment);
		assertEquals("Awesome let's go Friday around 5PM.", firstComment.getCommentContent());
	}
	
	@Test
	void test_one_to_many_market_items() {
		assertNotNull(user);
		assertEquals(1, user.getMarketItems().size());
		MarketItem firstItem = user.getMarketItems().iterator().next();
		assertNotNull(firstItem);
		assertEquals("Jetson Bolt Pro OEM Seat and Post",firstItem.getTitle());
	}
	
	@Test
	void test_one_to_many_feed_posts() {
		assertNotNull(user);
		assertEquals(1, user.getFeedPosts().size());
		FeedPost firstPost = user.getFeedPosts().iterator().next();
		assertNotNull(firstPost);
		assertEquals("Ride Around Magic Island Ala Moana",firstPost.getFeedcontent());
	}
}
