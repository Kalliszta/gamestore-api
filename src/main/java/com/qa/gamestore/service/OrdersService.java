package com.qa.gamestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.gamestore.domain.Accounts;
import com.qa.gamestore.domain.Orders;
import com.qa.gamestore.exceptions.IdNotFoundException;
import com.qa.gamestore.repo.AccountsRepo;
import com.qa.gamestore.repo.OrdersRepo;

@Service
public class OrdersService implements ServiceInterface<Orders> {
	
	private OrdersRepo repo;
	private AccountsRepo accRepo;
	
	@Autowired
	public OrdersService(OrdersRepo repo, AccountsRepo accRepo) {
		this.repo = repo;
		this.accRepo = accRepo;
	}
	
	@Override
	public Orders create(Orders order) { 
		Optional<Accounts> opAcc = accRepo.findById(order.getAccountsId());
		if (opAcc.isPresent()) {
			Accounts existingAcc = opAcc.get();
			order.setAccounts(existingAcc);
			return this.repo.save(order);
		}
		return null;
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
		Optional<Accounts> opAcc = accRepo.findById(newOrder.getAccountsId());
		Optional<Orders> opOrder = this.repo.findById(id);
		if (opOrder.isPresent() & opAcc.isPresent()) {
			Orders existingOrder = opOrder.get();
			Accounts existingAcc = opAcc.get();
			existingOrder.updateFields(newOrder);
			newOrder.setAccounts(existingAcc);
			return this.repo.save(existingOrder);
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		try {
			this.repo.deleteById(id);
		} catch (IdNotFoundException e) {
			//TO-DO deal with more specific exceptions and log them using Loggers
			return false;
		}
		return !(this.repo.existsById(id)); //return true if delete successful
	}
	
	
}
