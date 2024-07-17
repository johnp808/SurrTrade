package com.surrtrade.entities;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private String email;
	
	private String password;
	
	@Column(name="primary_bike")
	private String primaryBike;
	
	private String status;
	
	private String role;
	
	@OneToMany(mappedBy = "user")
	private Set<Comment> comments;
	
	@OneToMany(mappedBy = "user")
	private Set<MarketItem> marketItems;
	
	@OneToMany(mappedBy = "user")
	private Set<FeedPost> feedPosts;
	
	@OneToMany(mappedBy = "user")
	private Set<FeedPostLike> feedPostsLikes;
	
	@OneToMany(mappedBy = "sender")
	private Set<Message> messages;

	@OneToMany(mappedBy = "initiator")
	private Set<Conversation> initiatedConvo;
	
	@OneToMany(mappedBy = "receiver")
	private Set<Conversation> receivedConvo;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name="last_login")
	private LocalDateTime lastLogin;
	
	@Column(name="bike_picture")
	private String bikePicture;
	
	@Column(name="user_picture")
	private String userPicture;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrimaryBike() {
		return primaryBike;
	}

	public void setPrimaryBike(String primaryBike) {
		this.primaryBike = primaryBike;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<MarketItem> getMarketItems() {
		return marketItems;
	}

	public void setMarketItems(Set<MarketItem> marketItems) {
		this.marketItems = marketItems;
	}

	public Set<FeedPost> getFeedPosts() {
		return feedPosts;
	}

	public void setFeedPosts(Set<FeedPost> feedPosts) {
		this.feedPosts = feedPosts;
	}

	public Set<FeedPostLike> getFeedPostsLikes() {
		return feedPostsLikes;
	}

	public void setFeedPostsLikes(Set<FeedPostLike> feedPostsLikes) {
		this.feedPostsLikes = feedPostsLikes;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<Conversation> getInitiatedConvo() {
		return initiatedConvo;
	}

	public void setInitiatedConvo(Set<Conversation> initiatedConvo) {
		this.initiatedConvo = initiatedConvo;
	}

	public Set<Conversation> getReceivedConvo() {
		return receivedConvo;
	}

	public void setReceivedConvo(Set<Conversation> receivedConvo) {
		this.receivedConvo = receivedConvo;
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

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getBikePicture() {
		return bikePicture;
	}

	public void setBikePicture(String bikePicture) {
		this.bikePicture = bikePicture;
	}

	public String getUserPicture() {
		return userPicture;
	}

	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bikePicture, createdAt, email, id, lastLogin, password, primaryBike, receivedConvo, role,
				status, updatedAt, userPicture, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(bikePicture, other.bikePicture) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(email, other.email) && id == other.id && Objects.equals(lastLogin, other.lastLogin)
				&& Objects.equals(password, other.password) && Objects.equals(primaryBike, other.primaryBike)
				&& Objects.equals(receivedConvo, other.receivedConvo) && Objects.equals(role, other.role)
				&& Objects.equals(status, other.status) && Objects.equals(updatedAt, other.updatedAt)
				&& Objects.equals(userPicture, other.userPicture) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", primaryBike=" + primaryBike + ", status=" + status + ", role=" + role + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", lastLogin=" + lastLogin + ", bikePicture=" + bikePicture
				+ ", userPicture=" + userPicture + "]";
	}
}