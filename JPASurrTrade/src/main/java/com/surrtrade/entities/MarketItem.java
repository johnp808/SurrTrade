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
@Table(name="market_item")
public class MarketItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	private String description;
	
	private double price;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	private String category;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="view_count")
	private int viewCount;
	
	@ManyToOne
	@JoinColumn(name="user_id")
    @JsonBackReference("user-marketItems")
	private User user;
	
	@OneToMany(mappedBy="marketItem")
    @JsonManagedReference("marketItem-savedByUsers")
	private Set<UserFavoriteMarketItem> savedByUsers;

	
	public MarketItem() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<UserFavoriteMarketItem> getSavedByUsers() {
		return savedByUsers;
	}

	public void setSavedByUsers(Set<UserFavoriteMarketItem> savedByUsers) {
		this.savedByUsers = savedByUsers;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, createdAt, description, id, imageUrl, price, title, updatedAt, viewCount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarketItem other = (MarketItem) obj;
		return Objects.equals(category, other.category) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(imageUrl, other.imageUrl)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(title, other.title) && Objects.equals(updatedAt, other.updatedAt)
				&& viewCount == other.viewCount;
	}

	@Override
	public String toString() {
		return "MarketItem [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", category=" + category + ", imageUrl="
				+ imageUrl + ", viewCount=" + viewCount + "]";
	}
}
