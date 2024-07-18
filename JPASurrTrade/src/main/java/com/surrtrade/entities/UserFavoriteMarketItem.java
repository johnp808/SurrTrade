package com.surrtrade.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="user_favorite_market_item")
public class UserFavoriteMarketItem {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="market_item_id")
	private MarketItem marketItem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MarketItem getMarketItem() {
		return marketItem;
	}

	public void setMarketItem(MarketItem marketItem) {
		this.marketItem = marketItem;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, marketItem, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserFavoriteMarketItem other = (UserFavoriteMarketItem) obj;
		return id == other.id && Objects.equals(marketItem, other.marketItem) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "UserFavoriteMarketItem [id=" + id + "]";
	}

}
