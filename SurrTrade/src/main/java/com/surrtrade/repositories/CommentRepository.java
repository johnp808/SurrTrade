package com.surrtrade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surrtrade.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	

}
