package com.surrtrade.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="feed_post_like")
public class FeedPostLike {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="liked_at")
	private LocalDateTime likedAt;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getLikedAt() {
		return likedAt;
	}

	public void setLikedAt(LocalDateTime likedAt) {
		this.likedAt = likedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, likedAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeedPostLike other = (FeedPostLike) obj;
		return id == other.id && Objects.equals(likedAt, other.likedAt);
	}

	@Override
	public String toString() {
		return "FeedPostLike [id=" + id + ", likedAt=" + likedAt + "]";
	}
	
}
