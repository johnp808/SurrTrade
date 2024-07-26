package com.surrtrade.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class UserDTO {
	
	private int id;

	private String username;
	
	private String email;
	
	private String primaryBike;
	
	private String status;
	
	private String role;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private LocalDateTime lastLogin;
	
	private String bikePicture;
	
	private String userPicture;
	
	private boolean enabled;
	
	public UserDTO() {}

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bikePicture, createdAt, email, enabled, id, lastLogin, primaryBike, role, status, updatedAt,
				userPicture, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(bikePicture, other.bikePicture) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(email, other.email) && enabled == other.enabled && id == other.id
				&& Objects.equals(lastLogin, other.lastLogin) && Objects.equals(primaryBike, other.primaryBike)
				&& Objects.equals(role, other.role) && Objects.equals(status, other.status)
				&& Objects.equals(updatedAt, other.updatedAt) && Objects.equals(userPicture, other.userPicture)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", email=" + email + ", primaryBike=" + primaryBike
				+ ", status=" + status + ", role=" + role + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", lastLogin=" + lastLogin + ", bikePicture=" + bikePicture + ", userPicture=" + userPicture
				+ ", enabled=" + enabled + "]";
	}
}
