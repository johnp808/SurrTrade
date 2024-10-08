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

class FeedPostPictureTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private FeedPostPicture feedPostPicture;

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
		feedPostPicture = em.find(FeedPostPicture.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		feedPostPicture = null;
	}

	@Test
	void test_feedPostPicture_feedPostPictureContent() {
		assertNotNull(feedPostPicture);
		assertEquals("www.examplepic.com", feedPostPicture.getPictureUrl());
	}
	
	@Test
	void test_feedPostPicture_many_to_one_feed_post() {
		assertNotNull(feedPostPicture);
		assertEquals("Ride Around Magic Island Ala Moana", feedPostPicture.getFeedPost().getFeedcontent());
	}
}
