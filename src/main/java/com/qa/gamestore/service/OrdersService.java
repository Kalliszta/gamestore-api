package com.qa.gamestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.gamestore.domain.Accounts;
import com.qa.gamestore.domain.Games;
import com.qa.gamestore.domain.OrderGames;
import com.qa.gamestore.domain.Orders;
import com.qa.gamestore.exceptions.IdNotFoundException;
import com.qa.gamestore.repo.OrderGamesRepo;
import com.qa.gamestore.repo.OrdersRepo;

@Service
public class OrdersService implements ServiceInterface<Orders> {

	private OrdersRepo repo;
	private OrderGamesRepo ordGRepo;
	private AccountsService aSer;
	private GamesService gSer;

	@Autowired
	public OrdersService(OrdersRepo repo, AccountsService aSer, GamesService gSer, OrderGamesRepo ordGRepo) {
		this.repo = repo;
		this.aSer = aSer;
		this.gSer = gSer;
		this.ordGRepo = ordGRepo;
	}

	@Override
	public Orders create(Orders order) {
		Accounts account = aSer.readById(order.getAccountsId());
		order.setAccounts(account);
		return this.repo.save(order);
	}

	@Override
	public List<Orders> readAll() {
		return this.repo.findAll();
	}

	@Override
	public Orders readById(Long id) {
		Optional<Orders> opOrder = this.repo.findById(id);
		if (opOrder.isPresent()) {
			return opOrder.get();
		} else {
			return null;
		}
	}

	@Override
	public Orders update(Long id, Orders newOrder) {
		Accounts account = aSer.readById(newOrder.getAccountsId());
		Optional<Orders> opOrder = this.repo.findById(id);
		if (opOrder.isPresent()) {
			Orders existingOrder = opOrder.get();
			existingOrder.updateFields(newOrder);
			newOrder.setAccounts(account);
			return this.repo.save(existingOrder);
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		try {
			if (repo.findById(id).isPresent()) {
				this.repo.deleteById(id);
			} else {
				throw new IdNotFoundException();
			}
		} catch (IdNotFoundException e) {
			return false;
		}
		return !(this.repo.existsById(id)); //return true if delete successful
	}

	// ### custom query methods go below ###

	public OrderGames add(OrderGames orderGame) {
		Orders order = this.readById(orderGame.getOrdersId());
		Games game = gSer.readById(orderGame.getGamesId());
		orderGame.setOrders(order);
		orderGame.setGames(game);
		return this.ordGRepo.save(orderGame);
	}



}
