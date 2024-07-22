package com.surrtrade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surrtrade.entities.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Integer>{
	

}
