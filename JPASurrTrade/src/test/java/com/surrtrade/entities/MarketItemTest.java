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

class MarketItemTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private MarketItem marketItem;

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
		marketItem = em.find(MarketItem.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		marketItem = null;
	}

	@Test
	void test_marketItem_price() {
		assertNotNull(marketItem);
		assertEquals(30.00, marketItem.getPrice());
	}
	
	@Test
	void test_marketItem_createdAt() {
		assertNotNull(marketItem);
		assertEquals(2024, marketItem.getCreatedAt().getYear());
		assertEquals(7, marketItem.getCreatedAt().getMonthValue());
		assertEquals("MONDAY", marketItem.getCreatedAt().getDayOfWeek().name());
	}
	
	@Test
	void test_marketItem() {
		assertNotNull(marketItem);
		assertEquals("Jetson Bolt Pro OEM Seat and Post", marketItem.getTitle());
	}
	
	@Test
	void test_marketItem_one_to_many_market_item_to_user_favorite_market_item() {
		assertNotNull(marketItem);
		assertEquals(1, marketItem.getSavedByUsers().size());
		assertEquals("Jetson Bolt Pro OEM Seat and Post", marketItem.getSavedByUsers().iterator().next().getMarketItem().getTitle());
		
	}
}
