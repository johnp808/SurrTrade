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

class FeedPostLikeTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private FeedPostLike feedPostLike;

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
		feedPostLike = em.find(FeedPostLike.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		feedPostLike = null;
	}

	@Test
	void test_feedPostLike_feedPostLikeContent() {
		assertNotNull(feedPostLike);
		assertEquals(1, feedPostLike.getId());
	}

	@Test
	void test_feedPostLike_updatedAt() {
		assertNotNull(feedPostLike);
		assertEquals(2024, feedPostLike.getLikedAt().getYear());
		assertEquals(7, feedPostLike.getLikedAt().getMonth().getValue());
		assertEquals("MONDAY", feedPostLike.getLikedAt().getDayOfWeek().name());
		assertEquals(14, feedPostLike.getLikedAt().getHour());
		assertEquals(45, feedPostLike.getLikedAt().getMinute());
	}
}
