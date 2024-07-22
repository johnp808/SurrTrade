package com.surrtrade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surrtrade.entities.MarketItem;

public interface MarketItemRepository extends JpaRepository<MarketItem, Integer>{
	

}
