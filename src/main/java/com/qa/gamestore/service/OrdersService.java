package com.qa.gamestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.gamestore.domain.Orders;
import com.qa.gamestore.repo.AccountsRepo;
import com.qa.gamestore.repo.OrdersRepo;

@Service
public class OrdersService implements ServiceInterface<Orders> {
	
	private OrdersRepo repo;
	
	@Autowired
	public OrdersService(OrdersRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public Orders create(Orders order) { 
		//TO-DO exception handling
		return this.repo.save(order);
	}
	@Override
	public List<Orders> readAll() {
		return this.repo.findAll();
	}

	@Override
	public Orders readById(Long id) {
		//TO-DO exception handling
		Optional<Orders> opOrder = this.repo.findById(id);
		return opOrder.get();
	}

	@Override
	public Orders update(Long id, Orders newOrder) {
		//TO-DO exception handling
		Optional<Orders> opOrder = this.repo.findById(id);
		if (opOrder.isPresent()) {
			Orders existingOrder = opOrder.get();
			existingOrder.updateFields(newOrder);
			return this.repo.save(existingOrder);
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		try {
			this.repo.deleteById(id);
		} catch (Exception e) {
			//TO-DO deal with more specific exceptions and log them using Loggers
			return false;
		}
		return !(this.repo.existsById(id)); //return true if delete successful
	}
	
	
}
