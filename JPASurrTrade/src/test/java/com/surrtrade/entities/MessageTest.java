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

class MessageTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Message message;

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
		message = em.find(Message.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		message = null;
	}

	@Test
	void test_message_message_id() {
		assertNotNull(message);
		assertEquals(1, message.getId());
	}
	
	@Test
	void test_message_messageContent() {
		assertNotNull(message);
		assertEquals("How's it going?", message.getMessageContent());
		message = em.find(Message.class, 2);
		assertEquals("Good, do you still have that 48v battery for sale?", message.getMessageContent());

	}
	@Test
	void test_message_many_to_one_user() {
		assertNotNull(message.getSender());
		assertEquals("John", message.getSender().getUsername());
		
	}
}
