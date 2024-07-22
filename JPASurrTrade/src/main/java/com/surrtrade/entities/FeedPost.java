package com.surrtrade.entities;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="feed_post")
public class FeedPost {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="feed_content")
	private String Feedcontent;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;

	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonBackReference
	private User user;
	
	@OneToMany(mappedBy="feedPost")
	@JsonManagedReference
	private Set<Comment> postComments;
	
	@OneToMany(mappedBy="feedPost")
	@JsonManagedReference
	private Set<FeedPostPicture> feedPostPics;
	
	@OneToMany(mappedBy="feedPost")
	@JsonManagedReference
	private Set<FeedPostLike> feedPostLikes;

	public FeedPost() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFeedcontent() {
		return Feedcontent;
	}

	public void setFeedcontent(String feedcontent) {
		Feedcontent = feedcontent;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Comment> getPostComments() {
		return postComments;
	}

	public void setPostComments(Set<Comment> postComments) {
		this.postComments = postComments;
	}

	public Set<FeedPostPicture> getFeedPostPics() {
		return feedPostPics;
	}

	public void setFeedPostPics(Set<FeedPostPicture> feedPostPics) {
		this.feedPostPics = feedPostPics;
	}

	public Set<FeedPostLike> getFeedPostLikes() {
		return feedPostLikes;
	}

	public void setFeedPostLikes(Set<FeedPostLike> feedPostLikes) {
		this.feedPostLikes = feedPostLikes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Feedcontent, createdAt, id, updatedAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeedPost other = (FeedPost) obj;
		return Objects.equals(Feedcontent, other.Feedcontent) && Objects.equals(createdAt, other.createdAt)
				&& id == other.id && Objects.equals(updatedAt, other.updatedAt);
	}

	@Override
	public String toString() {
		return "FeedPost [id=" + id + ", Feedcontent=" + Feedcontent + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}
}
