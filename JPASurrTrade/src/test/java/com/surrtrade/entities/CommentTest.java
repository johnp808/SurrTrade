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

class CommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Comment comment;

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
		comment = em.find(Comment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		comment = null;
	}

	@Test
	void test_comment_price() {
		assertNotNull(comment);
		assertEquals("Hey I can Join!", comment.getCommentContent());
	}
	
	@Test
	void test_many_to_one_comments_to_user() {
		assertNotNull(comment);
//		assertEquals("password", comment.getUser().getPassword()); using B Encrypt now
		assertEquals("Mac", comment.getUser().getUsername());
	}
	
	@Test
	void test_many_to_one_comments_to_feed_post() {
		assertNotNull(comment);
		assertEquals("Ride Around Magic Island Ala Moana", comment.getFeedPost().getFeedcontent());
	}
}
