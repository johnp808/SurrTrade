package com.surrtrade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surrtrade.entities.UserFavoriteMarketItem;

public interface UserFavoriteMarketItemRepository extends JpaRepository<UserFavoriteMarketItem, Integer>{
	

}
