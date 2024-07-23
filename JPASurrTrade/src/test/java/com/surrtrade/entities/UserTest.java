package com.surrtrade.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
//		assertEquals("password", user.getPassword()); using b encrypt now
	}
	
	@Test
	void test_status() {
		assertNotNull(user);
		assertEquals("online", user.getStatus());
	}
	
	@Test
	void test_one_to_many_user_comments() {
		assertNotNull(user.getComments());
		assertEquals(1, user.getComments().size());
		Comment firstComment = user.getComments().iterator().next();
		assertNotNull(firstComment);
		assertEquals("Awesome let's go Friday around 5PM.", firstComment.getCommentContent());
	}
	
	@Test
	void test_one_to_many_user_market_items() {
		assertNotNull(user.getMarketItems());
		assertEquals(1, user.getMarketItems().size());
		MarketItem firstItem = user.getMarketItems().iterator().next();
		assertNotNull(firstItem);
		assertEquals("Jetson Bolt Pro OEM Seat and Post",firstItem.getTitle());
	}
	
	@Test
	void test_one_to_many_user_feed_posts() {
		assertNotNull(user.getFeedPosts());
		assertEquals(1, user.getFeedPosts().size());
		FeedPost firstPost = user.getFeedPosts().iterator().next();
		assertNotNull(firstPost);
		assertEquals("Ride Around Magic Island Ala Moana",firstPost.getFeedcontent());
	}
	
	@Test
	void test_one_to_many_user_feed_post_like() {
		// user 1 does not have data yet
		user = em.find(User.class, 2);
		assertNotNull(user.getFeedPostsLikes());
		assertEquals(1, user.getFeedPostsLikes().size());
		FeedPostLike firstFeedPostLike = user.getFeedPostsLikes().iterator().next();
		assertNotNull(firstFeedPostLike);
		assertEquals(2024,firstFeedPostLike.getLikedAt().getYear());
		assertEquals(15,firstFeedPostLike.getLikedAt().getDayOfMonth());
		assertEquals(7,firstFeedPostLike.getLikedAt().getMonthValue());
	}
	
	@Test
	void test_one_to_many_user_messages() {
		assertNotNull(user.getMessages());
		assertEquals(2, user.getMessages().size());
		Message firstMessage = user.getMessages().iterator().next();
		assertNotNull(firstMessage);
		assertEquals("How's it going?",firstMessage.getMessageContent());
		// just a further check
		user = em.find(User.class, 2);
		Message user2Message = user.getMessages().iterator().next();
		assertEquals("Good, do you still have that 48v battery for sale?",user2Message.getMessageContent());
	}
	
	@Test
	void test_one_to_many_user_sender_conversations() {
		assertNotNull(user.getInitiatedConvos());
		assertEquals(1, user.getInitiatedConvos().size());
		Conversation firstConvo = user.getInitiatedConvos().iterator().next();
		List<Message> sortedMessages =new ArrayList<>(firstConvo.getMessages());
		Collections.sort(sortedMessages);
		assertEquals("How's it going?", sortedMessages.get(0).getMessageContent());
	}
	
	@Test
	void test_one_to_many_user_receiver_conversations() {
		//receiver of test_one_to_many_user_sender_conversations conversation
		user = em.find(User.class, 2);
		assertNotNull(user.getReceivedConvos());
		assertEquals(1, user.getReceivedConvos().size());
		Conversation firstConvo = user.getReceivedConvos().iterator().next();
		List<Message> sortedMessages = new ArrayList<>(firstConvo.getMessages());
		Collections.sort(sortedMessages);
		assertEquals("How's it going?", sortedMessages.get(0).getMessageContent());
	}
	
	@Test
	void test_one_to_many_user_saved_market_item() {
		assertNotNull(user.getSavedMarketItems());
		System.out.println(user.getSavedMarketItems().size());
		assertEquals(1,user.getSavedMarketItems().size());
		UserFavoriteMarketItem firstItem = user.getSavedMarketItems().iterator().next();
		assertEquals("Surron Front Shocks", firstItem.getMarketItem().getTitle());
	}
}
