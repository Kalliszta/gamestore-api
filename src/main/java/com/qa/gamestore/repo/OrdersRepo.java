package com.qa.gamestore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.gamestore.domain.Orders;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {
	
	//TO-DO: Custom queries
	
}