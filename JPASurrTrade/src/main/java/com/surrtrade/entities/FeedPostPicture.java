package com.surrtrade.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="feed_post_picture")
public class FeedPostPicture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="picture_url")
	private String pictureUrl;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, id, pictureUrl);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeedPostPicture other = (FeedPostPicture) obj;
		return Objects.equals(createdAt, other.createdAt) && id == other.id
				&& Objects.equals(pictureUrl, other.pictureUrl);
	}

	@Override
	public String toString() {
		return "FeedPostPicture [id=" + id + ", pictureUrl=" + pictureUrl + ", createdAt=" + createdAt + "]";
	}
}
