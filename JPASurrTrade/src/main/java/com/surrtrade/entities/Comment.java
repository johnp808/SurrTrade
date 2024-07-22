package com.surrtrade.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="comment_content")
	private String commentContent;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonBackReference
	private User user;
	
	@ManyToOne
	@JoinColumn(name="feed_post_id")
	@JsonBackReference
	private FeedPost feedPost;
	
	public Comment() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FeedPost getFeedPost() {
		return feedPost;
	}

	public void setFeedPost(FeedPost feedPost) {
		this.feedPost = feedPost;
	}

	@Override
	public int hashCode() {
		return Objects.hash(commentContent, createdAt, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(commentContent, other.commentContent) && Objects.equals(createdAt, other.createdAt)
				&& id == other.id;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", commentContent=" + commentContent + ", createdAt=" + createdAt + "]";
	}
}
