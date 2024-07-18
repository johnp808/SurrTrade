package com.surrtrade.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class ConversationTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Conversation conversation;

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
		conversation = em.find(Conversation.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		conversation = null;
	}

	@Test
	void test_conversation_many_to_one_user() {
		assertNotNull(conversation);
		assertEquals(1, conversation.getId());
		assertEquals("John",conversation.getInitiator().getUsername());
	}
}
