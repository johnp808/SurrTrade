package com.surrtrade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surrtrade.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
	

}
